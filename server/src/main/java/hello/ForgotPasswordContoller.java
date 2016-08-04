package hello;

import static test.generated.Tables.LOGIN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import test.generated.tables.daos.LoginDao;
import test.generated.tables.pojos.Login;

@RestController
public class ForgotPasswordContoller
{
	@Value("${spring.datasource.url}")
	String db_url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	
	public Login ChangeUser(String name, String pass)
	{
		
		try (Connection conn = DriverManager.getConnection(db_url, username, password))
		{	
			Configuration configuration = new DefaultConfiguration().set(conn).set(SQLDialect.MYSQL);
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			LoginDao loginDao = new LoginDao(configuration);
			
			Login login = new Login(name, pass);
			
			//check to see if user entered username exists
			//if it does change the password
			if(loginDao.exists(login))
			{
				//create.update(LOGIN).set(LOGIN.PASSWORD, user.getPassword()).where(LOGIN.USERNAME.equal(user.getUsername())).execute();
				login.setUsername(name);
				login.setPassword(pass);
				loginDao.update(login);
				create.close();
				conn.close();
				return login;
			}
			//if it doesnt, make user 1 so we know to fail them
			else
			{
				login.setUsername("1");
				login.setPassword("1");
				create.close();
				conn.close();
				return login;
			}
				
		}
		catch (SQLException se)
		{                                
			se.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/forgot-javaconfig", method = RequestMethod.POST)
	public @ResponseBody String Forgot(@RequestBody Map<String,String> user)
	{		
		String username = user.get("username");
		String password = user.get("password");
		
		try
		{
			Login profile = ChangeUser(username, password);
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
