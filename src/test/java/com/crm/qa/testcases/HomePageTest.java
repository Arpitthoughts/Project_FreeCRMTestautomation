package com.crm.qa.testcases;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.Contactspage;
import com.crm.qa.pages.Homepage;
import com.crm.qa.pages.LoginPage;

public class HomePageTest extends Testbase {
	private WebDriver driver;
	LoginPage loginpageobj;
	Homepage homepageobj;
	Contactspage contactobj;
	private static Logger log = LogManager.getLogger(HomePageTest.class.getName());

	public HomePageTest() {

		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		log.info("calling initialization method to open the browser ");
		Testbase.intialization();
		driver = getDriver();
		loginpageobj = new LoginPage(driver);
		homepageobj = loginpageobj.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}

	@Test(priority = 1)
	public void VerifyusernameTest() {
		log.info("************ Starting VerifyusernameTest test case execution ************");
		String username = homepageobj.validateusernametext();
		log.info("user name matched "+ username);
		Assert.assertEquals(username, "Arpit Arjaria","invalid user name found !!");
		
		log.info("************ Completed VerifyusernameTest test case execution ************");
	}

	@Test(priority = 2) 
	public void VerifycrmlogoTest() {
		
		log.info("************ Starting VerifycrmlogoTest test case execution ************");
		
		boolean crmlogo = homepageobj.validatefreecrmlogo();
		Assert.assertTrue(crmlogo);
		log.info("************ Completed VerifycrmlogoTest test case execution ************");
		

	}

	@Test(priority = 3,enabled=false)
	public void Verify_Contacts_linkTest() throws InterruptedException {
		
		contactobj = homepageobj.clickonContacts();
	
		Assert.assertTrue(true);

	}

	
	@AfterMethod
	public void teardown() {

		getDriver().quit();
	}

}
