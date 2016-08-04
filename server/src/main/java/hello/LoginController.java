package hello;


import static test.generated.Tables.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import test.generated.tables.daos.LoginDao;
import test.generated.tables.pojos.Login;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@RestController
public class LoginController
{
	boolean debug = true;
	@Value("${spring.datasource.url}")
	String db_url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	
	public boolean CheckUser(String name, String pass)
	{
		String check = null;
		
		try (Connection conn = DriverManager.getConnection(db_url, username, password))
		{	
			Configuration configuration = new DefaultConfiguration().set(conn).set(SQLDialect.MYSQL);
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			LoginDao loginDao = new LoginDao(configuration);
			
			Login login = new Login(name, pass); 
			
			//check to see if the username the user entered even exists
			//if it does then check authentication, if it doesn't fail it
			if(loginDao.exists(login) == true)
			{
				Login userLogin = loginDao.fetchByUsername(name).get(0);
				check = userLogin.getPassword();
				if(check.equals(pass))
				{
					create.close();
					conn.close();
					if (debug)
					{
						System.out.println("Pass");
					}
					return true;
				}
			}
			else 
			{
					create.close();
					conn.close();
					if (debug)
					{
						System.out.println("Fail");
					}
					return false;
				
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
		
		return false;
	}
	
	@RequestMapping("/login-javaconfig")
	public @ResponseBody String Login(@RequestBody Map<String,String> user)
	{		
		String username = user.get("username");
		String password = user.get("password");
		
		if(CheckUser(username, password) == true)
		{
			if(debug)
				System.out.println("Success");
			return("{\"message\":\"Authentication Successful!\"}");
		}
		else
		{
			if(debug)
				System.out.println("Fail");
			return("{\"message\":\"Authentication Failed! Username doesn't not exist or you entered the wrong password.\"}");
		}

	}
	

}
