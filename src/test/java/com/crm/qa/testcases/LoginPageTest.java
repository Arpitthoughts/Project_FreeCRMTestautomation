package com.crm.qa.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.Homepage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends Testbase {
	private WebDriver driver;
	LoginPage loginpageobj;
	Homepage homepageobj;
	private static Logger log = LogManager.getLogger(LoginPageTest.class.getName());

	LoginPageTest() {

		super();

	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		log.info("calling initialization method to open the browser ");
		Testbase.intialization();
		driver = getDriver();
		loginpageobj = new LoginPage(driver);

	}

	@Test(priority=1)
	public void VerifyLoginPageTitleTest() {
		log.info("************ Starting VerifyLoginPageTitleTest test case execution ************");

		String title = loginpageobj.validateLoginPageTitle();
		log.info("Login Page title received as" + title);
		

			Assert.assertEquals(title, "Cogmento CRM");
			log.info("Login Page title matched successfully");
		
		log.info("************ Completed VerifyLoginPageTitleTest test case execution ************");
	}

	@Test(priority=2)
	public void VerifyLoginTest() throws InterruptedException {
		log.info("************ Starting VerifyLoginTest test case execution ************");

		homepageobj=loginpageobj.login(prop.getProperty("username"), prop.getProperty("password"));
		String homePageTitle=homepageobj.getHomePageTitle();
		log.info("homepage title page received as "+homePageTitle);
		Assert.assertEquals(homePageTitle, "Cogmento CRM");
		log.info("************ Completed VerifyLoginTest test case execution ************");
	}

	@AfterMethod
	public void teardown() {
		getDriver().quit();
	}

}
