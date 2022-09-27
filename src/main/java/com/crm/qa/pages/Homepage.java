package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.Testbase;

public class Homepage extends Testbase {
	private WebDriver driver;

	@FindBy(css = ".user-display")
	WebElement username;

	@FindBy(xpath = "//div[@class='header item']")
	WebElement crmlogo;

	@FindBy(xpath = "//a[@href='/contacts']")
	WebElement Contacts;

//initializing homepage webelements	
	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String validateusernametext() {

		// wait.until(ExpectedConditions.elementToBeClickable(username));
		return username.getText();

	}

	public boolean validatefreecrmlogo() {

		return crmlogo.isDisplayed();

	}

	public String getHomePageTitle() {
		return driver.getTitle();
	}

	public Contactspage clickonContacts() {

		Actions a = new Actions(driver);
		a.moveToElement(Contacts).build().perform();
		driver.findElement(By.xpath("//a[@href='/contacts']/span")).click();

		return new Contactspage(driver);

	}

}
