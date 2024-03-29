package com.kdd.reports;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.kdd.config.GlobalVariables;
import com.kdd.config.PropertiesConfig;

/**
 * @author Bheemarao.M
 *
 */
public class ReportManager implements GlobalVariables {
	private static ExtentReports extent;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

	// Create an extent report instance
	private static ExtentReports createInstance() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(htmlReportPath + htmlFileName);
		htmlReporter.loadXMLConfig(new File(extentConfigFilePath),true);
		extent = new ExtentReports();
		extent.setSystemInfo("User: ", System.getProperty("user.name"));
		extent.setSystemInfo("URL: ", PropertiesConfig.getProperty("baseURL"));
		extent.setSystemInfo("Browser: ", "Chrome");
		extent.setSystemInfo("OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Version: ", System.getProperty("os.version"));
		extent.attachReporter(htmlReporter);
		return extent;
	}

	private static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		getInstance().flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = getInstance().createTest(testName);
		extentTestMap.put((int) (Thread.currentThread().getId()), test);
		return test;
	}

}