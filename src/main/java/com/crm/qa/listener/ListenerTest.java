package com.crm.qa.listener;


import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.crm.qa.base.Testbase;
import com.crm.qa.util.TestUtil;
import com.qa.ExtentReportListener.ExtentReportUtility;

public class ListenerTest extends Testbase implements ITestListener {
	ExtentTest test;
	ExtentReports extent=ExtentReportUtility.getExtentReportObject();
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String methodname=result.getMethod().getMethodName();
		
		System.out.println("test started" + methodname);
		test=extent.createTest(methodname);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
       String methodname=result.getMethod().getMethodName();
		
		System.out.println("test is successfully executed" + methodname);
		test.log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		  String methodname=result.getMethod().getMethodName();
			
			System.out.println("test" + methodname+"is failed" );
			test.fail(result.getThrowable());
			try {
				TestUtil.takeScreenshotAtEndOfTest(methodname);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	extent.flush();
	}
	

}
