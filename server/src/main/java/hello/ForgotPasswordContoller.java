package hello;

import static test.generated.Tables.LOGIN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPasswordContoller
{
	public User ChangeUser(String name, String pass)
	{
		String db_url = "jdbc:mysql://localhost:3306";
		String username = "root";
		String password = "lilbro2";
		
		User user = new User(name, pass);
		
		try (Connection conn = DriverManager.getConnection(db_url, username, password))
		{	
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			
			//check to see if user entered username exists
			Result<Record1<String>> result_1 = create.select(LOGIN.USERNAME).from(LOGIN).whereExists(create.selectOne().from(LOGIN).where(LOGIN.USERNAME.eq(name))).fetch();
			
			//if it does change the password
			if(result_1.size() != 0)
			{
				create.update(LOGIN).set(LOGIN.PASSWORD, user.getPassword()).where(LOGIN.USERNAME.equal(user.getUsername())).execute();    
			}
			//if it doesnt, make user 1 so we know to fail them
			else
			{
				user.setUsername("1");
				user.setPassword("1");
				create.close();
				conn.close();
				return user;
			}
        	create.close();
			conn.close();
				
		}
		catch (SQLException se)
		{                                
			se.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	
	@RequestMapping(value = "/forgot-javaconfig", method = RequestMethod.POST)
	public @ResponseBody String Forgot(@RequestBody Map<String,String> user)
	{		
		String username = user.get("username");
		String password = user.get("password");
		
		try
		{
			User profile = ChangeUser(username, password);
			if(profile.getUsername() == "1" && profile.getPassword() == "1")
			{
				return("{\"message\":\"Account not found!\"}");
			}
			else
			{	
				return("{\"message\":\"Password Changed!\"}");
			}
		}
		catch (Exception e)
		{
			return("{\"message\":\"Password can't be changed.\"}");
		}
		
	}

}
