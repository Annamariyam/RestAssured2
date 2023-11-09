package activitesUtilities;

import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

// This class manages the creation and generation of ExtentReports for test results
public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repname;

	// Called when the test execution starts
	public void onStart(ITestContext context) {
		// Generate a timestamp for unique report name
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		repname = "Test-Report-" + timeStamp + ".html";

		// Create and configure the ExtentSparkReporter
		sparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/TestOutput/" + "/Reports/" + repname);
		sparkReporter.config().setReportName("Fakerest API Automation Report");
		sparkReporter.config().setDocumentTitle("RestAssured API Automation");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Set system information for the report
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Application", "Fakerest API - Activities");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester", "Anna");
	}

	// Called when the test execution finishes
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	// Called when a test method succeeds
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}

	// Called when a test method fails
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

	// Called when a test method is skipped
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
}
