package com.inetBanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.LoginPage;
//import org.openqa.selenium.JavascriptExecutor;
public class TC_AddCustomerTest_003 extends BaseClass
{
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Username is Provided.");
		lp.setPasword(password);
		logger.info("Password is Provided.");
		lp.ClickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("providing customer details...");
		Thread.sleep(3000);
		logger.info("Waited for 3 seconds");
		//((JavascriptExecutor) driver).executeScript("document.querySelector('.ad-popup').style.display='none';");
		Thread.sleep(3000);
		addcust.custName("Sanket");
		addcust.custgender("male");
		addcust.custdob("05", "06", "1998");
		Thread.sleep(3000);
		addcust.custaddress("INDIA");
		addcust.custcity("HYD");
		addcust.custstate("AP");
		addcust.custpinno("5000074");
		addcust.custtelephoneno("9876543210");
		
		String email=randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("Validation started....");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");
		}
		else
		{
			logger.info("test case failed....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
		
	}
	
}
