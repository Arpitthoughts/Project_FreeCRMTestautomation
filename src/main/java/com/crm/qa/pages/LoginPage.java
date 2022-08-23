package com.crm.qa.pages;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.crm.qa.base.Testbase;


public class LoginPage extends Testbase {
	
	
	
	
	//creating object repositories
	
	@FindBy(name="email")
	WebElement emailaddress;

@FindBy(name="password")
WebElement password;

@FindBy(xpath="//div[text()='Login']")
WebElement Loginbtn;


//initializing the Pageobjects

public LoginPage() {
	
PageFactory.initElements(driver, this);	
}


//Actions

public String validateLoginPageTitle() {
	
return driver.getTitle();	
}



public Homepage login(String un, String pwd) throws InterruptedException {

//TestUtil.wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));

emailaddress.sendKeys(un);



password.sendKeys(pwd);



//TestUtil.wait.until(ExpectedConditions.elementToBeClickable(Loginbtn));
//Loginbtn.click();


driver.findElement(By.xpath("//div[text()='Login']")).click();
return new Homepage();


}



}


