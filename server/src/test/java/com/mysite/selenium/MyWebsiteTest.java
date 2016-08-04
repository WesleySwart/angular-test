package com.mysite.selenium;

import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;

import org.junit.Test;

@Config(
		
        browser = Browser.CHROME,
        url     = "http://localhost:3000/sendEmail"
)

public class MyWebsiteTest extends Locomotive {

	@Test
	public void testHomepage()
	{
		validateUrl("http://localhost:3000/sendEmail");
	}
	
	@Test
	public void testEmailButtonWorks() 
	{
		click(MyWebsite.LOC_LNK_EMAILNAVIGATOR);
		validateUrl("http://localhost:3000/sendEmail");
		validatePresent(MyWebsite.LOC_BTN_SENDEMAILBUTTON);
		click(MyWebsite.LOC_BTN_SENDEMAILBUTTON);
	}
	
	@Test
	public void testMessagesButtonsWorks() 
	{
		click(MyWebsite.LOC_LNK_MESSAGENAVIGATOR);
		validateUrl("http://localhost:3000/message");
		validatePresent(MyWebsite.LOC_BTN_SENDMESSAGEBUTTON);
		validatePresent(MyWebsite.LOC_BTN_RECEIVEMESSAGEBUTTON);
		click(MyWebsite.LOC_BTN_SENDMESSAGEBUTTON);
		click(MyWebsite.LOC_BTN_RECEIVEMESSAGEBUTTON);
	}
	
}
