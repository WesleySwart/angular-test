package hello;


import static test.generated.Tables.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController
{
	
	boolean debug = false;
	
	public User CreateUser(String name, String pass)
	{
		String db_url = "jdbc:mysql://localhost:3306";
		String username = "root";
		String password = "lilbro2";
		
		User user = new User(name, pass);
		
		try (Connection conn = DriverManager.getConnection(db_url, username, password))
		{	
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			
			//check to see if the username the user entered already exists
			Result<Record1<String>> result_1 = create.select(LOGIN.USERNAME).from(LOGIN).whereExists(create.selectOne().from(LOGIN).where(LOGIN.USERNAME.eq(name))).fetch();
			
			//if the username does exists, set user to 1 just so we know to fail them later
			if(result_1.size() != 0)
			{
				user.setUsername("1");
				user.setPassword("1");
				create.close();
				//conn.close();
				return user;				
			}
			//if username doesnt exist. register the user
			else
			{
				create.insertInto(LOGIN, LOGIN.USERNAME, LOGIN.PASSWORD).values(user.getUsername(), user.getPassword()).execute();    
			}   		
        	create.close();
			//conn.close();
				
		}
		/*catch (SQLException se)
		{                                
			se.printStackTrace();
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	
	@RequestMapping(value = "/register-javaconfig", method = RequestMethod.POST)
	public @ResponseBody String Register(@RequestBody Map<String,String> user)
	{		
		String username = user.get("username");
		String password = user.get("password");
		
		try
		{
			User profile = CreateUser(username, password);
			
			if(profile.getUsername() == "1" && profile.getPassword() == "1")
			{
				return("{\"message\":\"Pick another username!\"}");
			}
			else
			{
				if(debug)
					System.out.println("{\"message\":\"Success!\"}");
				return("{\"message\":\"Success!\"}");
			}	
		}
		catch (Exception e)
		{
			if(debug)
				System.out.println("{\"message\":\"User can't be created!\"}");
			return("{\"message\":\"User can't be created!\"}");
		}
		
	}

}
