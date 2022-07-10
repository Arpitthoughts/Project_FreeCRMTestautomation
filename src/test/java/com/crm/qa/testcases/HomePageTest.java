package com.crm.qa.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.Contactspage;
import com.crm.qa.pages.Homepage;
import com.crm.qa.pages.LoginPage;

public class HomePageTest extends Testbase {
	LoginPage loginpageobj;
	Homepage homepageobj;
	Contactspage contactobj;

	public HomePageTest() {

		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {

		intialization();
		loginpageobj = new LoginPage();
		homepageobj = loginpageobj.login(prop.getProperty("username"), prop.getProperty("password"));
		

	}

	@Test(priority = 1)
	public void VerifyusernameTest() {

		String username = homepageobj.validateusernametext();
		Assert.assertEquals(username, "Arpit Arjaria");
	}

	@Test(priority = 2)
	public void VerifycrmlogoTest() {

		boolean crmlogo = homepageobj.validatefreecrmlogo();
		Assert.assertTrue(crmlogo);

	}

	@Test(priority = 3)
	public void Verify_Contacts_linkTest() {
		
		
		

		contactobj = homepageobj.clickonContacts();

	}

	
	@AfterMethod
	public void teardown() {

		driver.quit();
	}

}
