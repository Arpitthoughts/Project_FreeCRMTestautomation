package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;



//import com.crm.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testbase {

	public static Properties prop;
	public static WebDriver driver;

	public static EventFiringWebDriver e_driver;
	//public static WebEventListener eventListener;
	public static org.apache.log4j.Logger log=LogManager.getLogger(Testbase.class.getName());

	public Testbase() {
		try {
			log.info("creating Properties object");
			prop = new Properties();
			log.info("creating Properties object created");
			FileInputStream fin = new FileInputStream(
					"D:\\E-Banking Automation Mini Project\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			log.info("loading file data to properties object");
			prop.load(fin);
			log.info("file data to properties object loaded successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void intialization() throws InterruptedException {
		log.info("getting browser name from config.properties file");
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			// System.setProperty("webdriver.chrome.driver", "D:\\E-Banking Automation Mini
			// Project\\FreeCRMTest\\Drivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();

			// ChromeOptions handlingSSL = new ChromeOptions();

			// Using the accept insecure cert method with true as parameter to accept the
			// untrusted certificate
			log.info("creating instance of ChromeDriver");
			driver = new ChromeDriver();
			log.info("instance for ChromeDriver created successfully");

		} else if (browserName.equals("firefox")) {
			// System.setProperty("webdriver.gecko.driver","D:\\E-Banking Automation Mini
			// Project\\FreeCRMTest\\Drivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			log.info("creating instance of FirefoxDriver");
			driver = new FirefoxDriver();
			log.info("creating instance of FirefoxDriver successfully");
		}

		//e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		//eventListener = new WebEventListener();
		//e_driver.register(eventListener);
		//driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		// driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
		// driver.manage().timeouts().setScriptTimeout(6L, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		log.info("Opening free crm site");
		driver.get(prop.getProperty("url"));
		log.info("Opening free crm site opened successfully");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String ready = (String) js.executeScript("return document.readyState");
		if (!ready.equalsIgnoreCase("complete")) {
			Thread.sleep(15000);
		}
	}

}