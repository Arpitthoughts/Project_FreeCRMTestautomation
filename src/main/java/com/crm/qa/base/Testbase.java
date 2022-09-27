package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
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
	private WebDriver driver;
	private static ThreadLocal<WebDriver> WebDriverFactory = new ThreadLocal<>();
	public static org.apache.log4j.Logger log = LogManager.getLogger(Testbase.class.getName());

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
			WebDriverManager.chromedriver().setup();

			// ChromeOptions handlingSSL = new ChromeOptions();

			// Using the accept insecure cert method with true as parameter to accept the
			// untrusted certificate
			log.info("creating instance of ChromeDriver");
			WebDriverFactory.set(new ChromeDriver());
			log.info("instance for ChromeDriver created successfully");

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			log.info("creating instance of FirefoxDriver");
			WebDriverFactory.set(new FirefoxDriver());
			log.info("creating instance of FirefoxDriver successfully");
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();

		// driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
		// driver.manage().timeouts().setScriptTimeout(6L, TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		getDriver().get(prop.getProperty("url"));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		String ready = (String) js.executeScript("return document.readyState");
		if (!ready.equalsIgnoreCase("complete")) {
			Thread.sleep(10000);
		}

	}

	public static synchronized WebDriver getDriver() {
		return WebDriverFactory.get();
	}

}