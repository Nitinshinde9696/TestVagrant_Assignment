package reportpackage;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonutil.Utility;

public class ListenersClass implements ITestListener {

	WebDriver driver= null;
	Utility Utility = new Utility();
	 ExtentReports extent;
	 ExtentTest logger;
	public void onFinish(ITestContext arg0) {
		System.out.println("on Finish of Execution "+arg0.getName());
		 // writing everything to document
		 //flush() - to write or update test information to your report. 
		                extent.flush();
		                //Call close() at the very end of your session to clear all resources. 
		                //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
		                //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
		                //Once this method is called, calling any Extent method will throw an error.
		                //close() - To close all the operation
		                extent.close();
		
	}

	public void onStart(ITestContext arg0) {

		System.out.println("on Start of Execution "+arg0.getName());
		//ExtentReports(String filePath,Boolean replaceExisting) 
		 //filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
		 //replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
		 //True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
		 //False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
		 extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
		 extent
		                .addSystemInfo("Host Name", "Test Assignment")
		                .addSystemInfo("Environment", "Automation Testing")
		                .addSystemInfo("User Name", "Nitin Shinde");
		                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
		                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		                extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));

		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
	}

	public void onTestFailure(ITestResult arg0) {

		
		System.out.println("on Test Failure "+arg0.getName());
		logger = extent.startTest("failTest");
		if(arg0.getStatus() == ITestResult.FAILURE){
			 logger.log(LogStatus.FAIL, "Test Case Failed is "+arg0.getName());
			 logger.log(LogStatus.FAIL, "Test Case Failed is "+arg0.getThrowable());
			 
			 }
		 
		try {
			Utility.screenShot(driver, arg0.getName());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult arg0) {

		System.out.println("on Test Skipped "+arg0.getName());
		 logger = extent.startTest("skipTest");
		 
		  if(arg0.getStatus() == ITestResult.SKIP){
			 logger.log(LogStatus.SKIP, "Test Case Skipped is "+arg0.getName());
			 extent.endTest(logger);
			 }
		 throw new SkipException("Skipping - This is not ready for testing ");
		 
		 
		 
	}

	public void onTestStart(ITestResult arg0) {

		System.out.println("on Test Start "+arg0.getName());
	}

	public void onTestSuccess(ITestResult arg0) {

		System.out.println("on Test Success"+arg0.getName());

		 //extent.startTest("TestCaseName", "Description")
		 //TestCaseName – Name of the test
		 //Description – Description of the test
		 //Starting test
		 logger = extent.startTest("passTest");
		 //To generate the log when the test case is passed
		 logger.log(LogStatus.PASS, "Test Case Passed is passTest "+arg0.getName());
		 
		  // ending test
		 //endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}

}
