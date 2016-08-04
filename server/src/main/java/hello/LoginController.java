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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@RestController
public class LoginController
{
	boolean debug = false;
	
	@Value("${spring.datasource.url}")
	String db_url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	
	public int CheckUser(String name, String pass)
	{
		
		String check = null;
		
		try (Connection conn = DriverManager.getConnection(db_url, username, password))
		{	
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			
			//check to see if the username the user entered even exists
			Result<Record1<String>> result_1 = create.select(LOGIN.USERNAME).from(LOGIN).whereExists(create.selectOne().from(LOGIN).where(LOGIN.USERNAME.eq(name))).fetch();
			//if it does then check authentication, if it doesn't fail it
			if(result_1.size() != 0)
			{
				//gets the password that is with the username the user entered
				Record1<String> result_2 = create.select(LOGIN.PASSWORD).from(LOGIN).where(LOGIN.USERNAME.eq(name))
						.fetchOne();

				check = result_2.getValue(LOGIN.PASSWORD);
				
				//if the password the user entered matches what the actual password is then it accepts login
				if (check.equals(pass)) 
				{
					create.close();
					conn.close();
					if (debug)
					{
						System.out.println("User entered: " + pass + "\nReal password: " + check);
						System.out.println("Pass");
					}
					return 1;
				} 
				else 
				{
					create.close();
					conn.close();
					if (debug)
					{
						System.out.println("User entered: " + pass + "\nReal password: " + check);
						System.out.println("Pass");
					}
					return 0;
				}
			}
			else
			{
				return 2;
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
		
		return 0;
	}
	
	@RequestMapping("/login-javaconfig")
	public @ResponseBody String Login(@RequestBody Map<String,String> user)
	{		
		String username = user.get("username");
		String password = user.get("password");
		
		User profile = new User(username, password);
		profile.getUsername();
		profile.getPassword();
		
		if(CheckUser(username, password) == 1)
		{
			if(debug)
				System.out.println("Success");
			return("{\"message\":\"Authentication Successful!\"}");
		}
		else if(CheckUser(username, password) == 0)
		{
			if(debug)
				System.out.println("Fail");
			return("{\"message\":\"Authentication Failed!\"}");
		}
		else
		{
			return("{\"message\":\"Username Not Found!\"}");
		}
		
	}
	

}
