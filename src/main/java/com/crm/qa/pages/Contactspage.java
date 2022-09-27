package com.crm.qa.pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.Testbase;

public class Contactspage extends Testbase {
	private WebDriver driver;
	
	
	@FindBy(xpath="//button[@class='ui linkedin button'] //i[@class='edit icon']")
	@CacheLookup
	WebElement createcontact;
	
	@FindBy(name="category")
	WebElement categoryelement;
	
	@FindBy(xpath="//button[@class='ui linkedin button']/i")
	WebElement savecontact;
	
	
	
	
	public Contactspage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String Create_Contact(String firstName, String lastName, String Company, String Category) throws InterruptedException {
				
		createcontact.click();
		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys(lastName);
		driver.findElement(By.xpath("//div[@name='company'] //input[@class='search']")).sendKeys(Company);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@name='company'] //input[@class='search']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//div[@class='selected item addition']")).click();
		categoryelement.click();
		List<WebElement> categories= driver.findElements(By.xpath("//div[@name='category']/span"));
		for(WebElement category : categories) {
			
			if(category.getText().equalsIgnoreCase(Category)) {
				category.click();
				
				break;
			}
			
		}
		
		//wait.until(ExpectedConditions.elementToBeClickable(savecontact));
		Thread.sleep(3000);
		savecontact.click();
		Thread.sleep(3000);
		String contact_created=driver.findElement(By.xpath("//div[@class='ui header item mb5 light-black']")).getText().split(" ")[0];
	return contact_created;
	}
	
	
	
	
}
