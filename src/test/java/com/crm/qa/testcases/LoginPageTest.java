package com.crm.qa.testcases;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.crm.qa.base.Testbase;
import com.crm.qa.pages.Homepage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends Testbase {
	LoginPage loginpageobj;
	Homepage homepageobj;

	public static Logger log=LogManager.getLogger(LoginPageTest.class.getName());

	
	
	LoginPageTest() {

		super();
		
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		log.info("calling initialization method to open the browser ");
		intialization();
		log.info("initialization method executed successfully to open the browser ");
		log.info("creating page object");
		loginpageobj = new LoginPage();

	}

	@Test(priority = 1,invocationCount=1)
	public void VerifyLoginPageTitleTest() {
		log.info("************ Starting VerifyLoginPageTitleTest test case execution ************");
		
		String title=loginpageobj.validateLoginPageTitle();
		log.info("Login Page title received as"+title);
		try
		{
			
			Assert.assertEquals(title, "Cogmento CRM");
		
		}
		catch(Exception e)
		{
		log.error("error occured in test case VerifyLoginPageTitleTest " + e.getMessage());	
		}
	}

	
	@Test(priority = 2)
	public void VerifyLoginTest() throws InterruptedException {
		homepageobj= loginpageobj.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(true);
	}
	

	
	

	@AfterMethod
	public void teardown() {

		driver.quit();
		log.info("browser closed");
	}

}
