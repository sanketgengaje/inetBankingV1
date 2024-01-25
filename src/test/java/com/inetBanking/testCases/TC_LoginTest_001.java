package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass
{
	@Test
	public void LoginTest() throws IOException
	{
		
		
		logger.info("URL is opened");
		
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered Username");
		lp.setPasword(password);
		logger.info("entered password");
		lp.ClickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			captureScreen(driver, "LoginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
		
		
	}
}
