package utilities;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

// use this class for generate Extent Report

//@SuppressWarnings("deprecation")
public class Listners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public ExtentSparkReporter spark;

	// ExtentSparkReporter spark = new ExtentHtmlReporter("ExtentSpark.html");
	// ExtentReports extent = new ExtentReports();
	// extent.attachReporter(spark);
	// extent.createTest("TestName").pass("Test Passed");
	// extent.flush();

	public void onStart(ITestContext testContext) {

		// create Reports folder if it dosen't exist
		
		// This method will be called before any test method starts
        String folderPath = System.getProperty("user.dir") + "/Harmi";
        File reportFolder = new File(folderPath);
        if (!reportFolder.exists()) {
            boolean folderCreated = reportFolder.mkdirs();
            if (folderCreated) {
                System.out.println("--------------------Folder created successfully at: " + folderPath);
            } else {
                System.out.println("------------------------Failed to create folder at: " + folderPath);
            }
        } else {
            System.out.println("-------------------------Folder already exists at: " + folderPath);
        }
    

	
	/*
	 * File reportFolder = new File(System.getProperty("user.dir") + "/Harmi");
	 * System.out.
	 * println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%reportFolder is%%%%%%%%%%% : " +
	 * reportFolder);
	 * 
	 * if (!reportFolder.exists()) { reportFolder.mkdirs();
	 * System.out.println("reportFolder is: " + reportFolder); }
	 * System.out.println("reportFolder is:------ " + reportFolder);
	 */
		

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Harmi/MyExtentReport.html");
		// spark = new ExtentSparkReporter(System.getProperty("user.dir") +
		// "/Reports/MyExtentReport.html");
		// spark = new
		// ExtentSparkReporter("D:/QA/CucumberWorkSpace/RestAssured_FRAMEWORK_API_Testing/Reports/MyExtentReport.html");
		// ExtentHtmlReporter htmlReporter = new
		// ExtentHtmlReporter("test-output/extent-report.html");

		// title of report
		htmlReporter.config().setDocumentTitle("Automation Testing");
		// name of the report
		htmlReporter.config().setReportName("Rest API Testing Report");
		htmlReporter.config().setTheme(Theme.DARK);

		// spark.config().setDocumentTitle("Automation Testing");
		// name of the report
		// spark.config().setReportName("Rest API Testing Report");
		// spark.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		// extent.attachReporter(htmlReporter);
		extent.attachReporter(spark);
		extent.setSystemInfo("Project name", "Employees Database API Testing");
		extent.setSystemInfo("Host name", "Localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "harmi");

	}

	public void onTestSuccess(ITestResult result) {

		// create new entry in the report
		test = extent.createTest(result.getName());

		test.log(Status.PASS, "TEST CASE PASSED IS : " + result.getName()); // to add name in test report

	}

	public void onTestFailuer(ITestResult result) {

		// create new entry in the report
		test = extent.createTest(result.getName());

		test.log(Status.FAIL, "TEST CASE FAILDE IS : " + result.getName()); // to add name in test report
		test.log(Status.FAIL, "TEST CASE FAILDE IS : " + result.getThrowable()); // to add error/exception in extent
																					// report

	}

	public void onTestSkipped(ITestResult result) {

		// create new entry in the report
		test = extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.SKIP, "TEST CASE SKIPPED IS : " + result.getName());

	}

	public void onFinish(ITestResult testContext) {
		extent.flush();
	}
}
