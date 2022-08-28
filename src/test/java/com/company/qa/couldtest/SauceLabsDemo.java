package com.company.qa.couldtest;


import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.company.qa.utils.Constant;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SauceLabsDemo {

	//https://docs.saucelabs.com/web-apps/automated-testing/selenium/
	
		WebDriver driver;
		public static final String USERNAME = "oauth-sadiaafroz775-47c11";
		public static final String ACCESS_KEY = "cafd8afa-32b2-4b7b-acd2-4c2488b02828";

		@Parameters({"browser", "platform", "version"})
		@BeforeMethod
		
		public void setUp(String browserName, String platformName, String versionName,  Method name) {

			System.out.println("browser name is : " + browserName);
			String methodName = name.getName();

			MutableCapabilities sauceOpts = new MutableCapabilities();
			sauceOpts.setCapability("name", methodName);
			sauceOpts.setCapability("build", "Java-W3C-Examples");
			sauceOpts.setCapability("seleniumVersion", "3.141.59");
			sauceOpts.setCapability("username", USERNAME);
			sauceOpts.setCapability("accessKey", ACCESS_KEY);
			sauceOpts.setCapability("tags", "w3c-chrome-tests");

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("sauce:options", sauceOpts);
			cap.setCapability("browserVersion", versionName);
			cap.setCapability("platformName", platformName);

			if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				cap.setCapability("browserName", "chrome");
			} else if (browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				cap.setCapability("browserName", "firefox");
			}
			
			try {
		//		driver = new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"), cap);
				driver = new RemoteWebDriver(new URL("https://oauth-sadiaafroz775-47c11:*****2828@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), cap);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		}
		@Test
		public void amazonTitleTest() {
			
			driver.get("https://www.amazon.com/");

			String expected = "Amazon.com. Spend less. Smile more.";
			String actual = driver.getTitle();

			System.out.println("Page title is " + actual);
			System.out.println(Constant.PAGE_TITLE_IS + actual);
			Assert.assertEquals(actual, expected);

			System.out.println("Page title is " + actual);

		}
		
		@Test(priority = 1)
		public void ebayTitleTest() {

			driver.get("https://www.ebay.com/");
			String expected = "Amazon.com. Spend less. Smile more.";
			String actual = driver.getTitle();

			System.out.println(Constant.PAGE_TITLE_IS + actual);
			Assert.assertEquals(actual, expected);

			System.out.println("Page title is " + actual);

		}
		
		
		@AfterMethod(alwaysRun = true)
		public void tearDown() {
			driver.quit();
			
		}
	
	
}
