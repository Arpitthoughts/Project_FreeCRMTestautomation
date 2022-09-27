package com.crm.qa.testcases;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.Testbase;
import com.crm.qa.pages.Contactspage;
import com.crm.qa.pages.Homepage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactspageTest extends Testbase {
	private WebDriver driver;
	LoginPage loginpageobj;
	Homepage homepageobj;
	Contactspage contactobj;
	String SheetName="Sheet1";
	public static Logger log=LogManager.getLogger(ContactspageTest.class.getClass());
	public ContactspageTest() {
		
		super();
		
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {

		Testbase.intialization();
		driver = getDriver();
		loginpageobj= new LoginPage(driver);
		homepageobj = loginpageobj.login(prop.getProperty("username"), prop.getProperty("password"));
		contactobj=homepageobj.clickonContacts();
		

	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		
	Object data[][]=TestUtil.getTestData(SheetName);
	return data;
	}
	
	
	@Test(dataProvider="getCRMTestData")
	public void Verify_user_is_able_to_create_contact(String FirstName, String LastName, String Company, String Category) throws InterruptedException {
		log.info("********* Test case Verify_user_is_able_to_create_contact started ******");
		String contact_created=contactobj.Create_Contact(FirstName, LastName, Company,Category );
		
		System.out.println(contact_created);
	Assert.assertEquals(FirstName, contact_created);
	log.info("contact created successfully with "+FirstName +" "+ LastName+ " "+" "+Company+" "+Category );
	log.info("********* Test case Verify_user_is_able_to_create_contact completed *******");
	}
	

	
	@AfterMethod
	public void teardown() {

		driver.quit();
	}
}
