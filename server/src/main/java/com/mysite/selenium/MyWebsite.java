package com.mysite.selenium;

import org.openqa.selenium.By;

public class MyWebsite
{
		public static final By LOC_LNK_EMAILNAVIGATOR = By.xpath("/html/body/my-app/nav/a[1]");
		public static final By LOC_LNK_MESSAGENAVIGATOR = By.xpath("/html/body/my-app/nav/a[2]");
		public static final By LOC_BTN_SENDEMAILBUTTON = By.xpath("/html/body/my-app/ses-component/div/button");
		public static final By LOC_BTN_SENDMESSAGEBUTTON = By.xpath("/html/body/my-app/sqs-component/div[1]/button");
		public static final By LOC_BTN_RECEIVEMESSAGEBUTTON = By.xpath("/html/body/my-app/sqs-component/div[2]/button");		
		
}
