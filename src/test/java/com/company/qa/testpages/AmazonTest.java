package com.company.qa.testpages;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.qa.drivermanager.DriverManager;
import com.company.qa.utils.Constant;

public class AmazonTest extends DriverManager{

	
	@Test(priority = 1)
	public void amazonTitleTest() {

		String expected = "Amazon.com. Spend less. Smile more.";
		String actual = driver.getTitle();

		System.out.println("Page title is " + actual);
		System.out.println(Constant.PAGE_TITLE_IS + actual);
		Assert.assertEquals(actual, expected);

		System.out.println("Page title is " + actual);

	}
	
	
	
	
}
