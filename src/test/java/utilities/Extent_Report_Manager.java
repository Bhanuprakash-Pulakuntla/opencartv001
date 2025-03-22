package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

import testBase.Baseclass; 

public class Extent_Report_Manager implements ITestListener
{
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repname;
	
	public void onStart(ITestContext testcontext) 
	{
		/*SimpleDateFormat df=new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);//this is also timestamp capturing
		*/
		
		String timestamp=new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss").format(new Date());//timestamp capturing in one statement.
		repname="Test-Report-"+timestamp+".html";
		sparkreporter=new ExtentSparkReporter(".\\reports\\"+repname);//specify the path to store the report
		
		sparkreporter.config().setDocumentTitle("opencart Automation report");
		sparkreporter.config().setReportName("opencart functional testing");
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkreporter);
		
		extent.setSystemInfo("Application","Opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub module","customers");
		extent.setSystemInfo("user name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		
		String os=testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("operating", os);
		
		String browser=testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includegroups=testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includegroups.isEmpty()) 
		{
			extent.setSystemInfo("Gropus", includegroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.PASS,result.getName()+" got successfully executed.");
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.FAIL,result.getName()+" got failed.");
		test.log(Status.FAIL," test case failed cause is:"+result.getThrowable().getMessage());
		
		try 
		{
			String imgpath= new Baseclass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}catch(IOException e1) 
		{
			e1.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.SKIP,result.getName()+" got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testcontext) 
	{
		extent.flush();
		
		String pathofextentreport=System.getProperty("user.dir")+"\\reports\\"+repname;
		File extentreport=new File(pathofextentreport);
		try 
		{
			Desktop.getDesktop().browse(extentreport.toURI());
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
}
