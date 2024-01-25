package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		ChromeOptions options = new ChromeOptions();
		//options.addExtensions(new File("C:\\Users\\sanke\\eclipse-workspace\\inetBankingV1\\Drivers\\adblocker.crx"));
		options.addArguments("--disable-javascript");
		options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
		if(br.equals("chrome"))
		{
			
	        
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			
			driver= new ChromeDriver(options);
			logger.info("Chrome browser opened.");
			
		}
		
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.edge.driver", readconfig.getIEPath());
			driver = new EdgeDriver();
			logger.info("Edge browser opened.");
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
			logger.info("Firefox browser opened.");
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+ "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken.");
	}
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	public static String randomeNum()
	{
		String generatedstring2=RandomStringUtils.randomNumeric(4);
		return(generatedstring2);
	}

}
