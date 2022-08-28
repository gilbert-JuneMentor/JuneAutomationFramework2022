package com.company.qa.drivermanager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	protected static WebDriver driver;
//	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	@Parameters({"browser", "url"})
	@BeforeTest
	public void setUp(String browserName, String url) {

		System.out.println("Browser name is : " + browserName);

		if (browserName.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			driver.get(url);

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

			driver.get(url);

		} else if (browserName.equalsIgnoreCase("Edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

			driver.get(url);

		} else if (browserName.equalsIgnoreCase("safari")) {

			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();

			driver.get(url);

		} else {

			System.out.println("Wrong Browser selected");
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	
	
//	/**
//	 * this will return the thread local copy of the webdriver(driver)
//	 * 
//	 * @return
//	 */
//	public static WebDriver getDriver() {
//		return tlDriver.get();
//	}
	
	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}
	
	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}

