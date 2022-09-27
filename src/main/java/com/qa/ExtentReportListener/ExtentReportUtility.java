package com.qa.ExtentReportListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtility {

	
	public static ExtentReports extent;
	public static ExtentReports getExtentReportObject(){
		
		String path =System.getProperty("user.dir")+"\\Extent_reports\\extent.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		 extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Arpit Arjaria");
		return extent;
		
	}

}
