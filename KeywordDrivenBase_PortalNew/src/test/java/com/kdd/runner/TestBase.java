package com.kdd.runner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Duration;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.internal.BaseTestMethod;

import com.kdd.config.PropertiesConfig;
import com.kdd.config.ReportDirectory;

import com.kdd.config.DriverManager;
import com.kdd.config.GlobalVariables;
import com.kdd.config.SessionDataManager;
import com.kdd.reports.ReportManager;
import com.kdd.utility.ExcelReader;
import com.kdd.utility.Log;

//import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Bheemarao.M
 *
 */
public class TestBase implements GlobalVariables {

	private static final Logger Logs = Logger.getLogger(TestBase.class.getName());

	@BeforeSuite
	public void configurations(ITestContext context) {
		DOMConfigurator.configureAndWatch("log4j.xml");
		Logs.info("Setting up Test data Excel sheet");
		ReportDirectory directory = new ReportDirectory();
		directory.clearFolder(screenshotFolder);
		directory.clearFolder(htmlReportPath);
		ExcelReader.setExcelFile(testDataPath);
		ExcelReader.clearColumnData(testDataSheet, resultColumn, testDataPath);
	}

	@BeforeMethod
	public void initialization(Method method, Object[] testData, ITestContext ctx) {
		WebDriver driver = launchBrowser();
		DriverManager.getInstance().setDriver(driver);
	}

	@AfterMethod
	public void updateStatus(ITestResult result, ITestContext context) {
		BaseTestMethod baseTestMethod = (BaseTestMethod) result.getMethod().clone();
		String testID = (String) result.getParameters()[1];
		Integer row = (Integer) result.getParameters()[0];
		String testTitle = ExcelReader.getTestTitle(testDataSheet, row.intValue());
		Field f;
		try {
			f = baseTestMethod.getClass().getSuperclass().getDeclaredField("m_methodName");
			f.setAccessible(true);
			f.set(baseTestMethod, testTitle);
			f = result.getClass().getDeclaredField("m_method");
			f.setAccessible(true);
			f.set(result, baseTestMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String status = SKIP;

		if (result.getStatus() == ITestResult.FAILURE) {
			status = FAIL;
		}
		if (result.getStatus() == ITestResult.SUCCESS) {
			status = PASS;
		}
		Logs.info("Closing all the browser.");
		String testCaseName = (String) SessionDataManager.getInstance().getSessionData("testCaseName");
		ReportManager.endTest();
		DriverManager.getInstance().getDriver().quit();
		Log.endTestCase(testCaseName + " " + status);
		String failedStep = (String) context.getAttribute("FAILEDSTEPID" + testID);
		String allStepsPass = (String) context.getAttribute("ALLSTEPSPASSID" + testID);
		String imagePath=(String) context.getAttribute("IMAGEFILEPATH" + testID);
        System.out.println("IMGPATH"+imagePath);
	}
	
	@AfterSuite
	public void tearDown(ITestContext context) {
	}

	private static WebDriver launchBrowser() {
		String browser = PropertiesConfig.getProperty("browser");
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("CHROME")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--window-size=1920,1080");
			opt.addArguments("--start-maximized");
			opt.addArguments("--remote-allow-origins=*");
			//opt.addArguments("--headless=new");
			driver = new ChromeDriver(opt);
		}
		driver.get(PropertiesConfig.getProperty("baseURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(implicitWaitTime));
		return driver;
	}

}
