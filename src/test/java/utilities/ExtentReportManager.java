package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
//import java.net.URL;
import java.net.URL;

//Extent report 5.x...//version

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClassCommonMethods;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {
		
/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
 * 	Date dt=new Date();
String currentdatetimestamp=df.format(dt);   //return date in String format---next line m ek hi line
		                                               implement krdiya h iss code ko i.e. (Line 46)
		*/
		
String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// current time stamp
		
repName = "Test-Report-" + timeStamp + ".html";
sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report

sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report
sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
	
test = extent.createTest(result.getTestClass().getName()); // .getName()-return name of testcase class
test.assignCategory(result.getMethod().getGroups()); // to display groups in report
test.log(Status.PASS,result.getName()+" got successfully executed"); // .getName()-return name of testcase class
		
	}

public void onTestFailure(ITestResult result) {
 test = extent.createTest(result.getTestClass().getName());  //creating new entry in report
	test.assignCategory(result.getMethod().getGroups());    //category=groups->sanity/smoke/regression
		
	test.log(Status.FAIL,result.getName()+" got failed");
	test.log(Status.INFO, result.getThrowable().getMessage());
		
	try {
		// for capturing screenshot of test failures
		String imgPath = new BaseClassCommonMethods().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
	}	
		catch (IOException e1) {
			e1.printStackTrace();  //this is pre-defined method, will display exception message
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		//It will open report on browser automatically. i don't need to open manually(below code).
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());   //Desktop is a pre-defined class.
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		/*  this code will sent generated report to other peoples/team members automatically.
		 * 
		 * try {
		 * 
			  URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		  
		  // Create the email message 
		  ImageHtmlEmail email = new ImageHtmlEmail();
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("smtp.googlemail.com"); 
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password")); 
		  email.setSSLOnConnect(true);
		  email.setFrom("pavanoltraining@gmail.com"); //Sender
		  email.setSubject("Test Results");
		  email.setMsg("Please find Attached Report....");
		  email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
		  email.attach(url, "extent report", "please check report..."); 
		  email.send(); // send the email 
		  }
		  catch(Exception e) 
		  { 
			  e.printStackTrace(); 
			  }
		 */ 
		 
	}

}
