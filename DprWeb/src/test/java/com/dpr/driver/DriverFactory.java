package com.dpr.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

	protected static WebDriver driver;
	protected static Properties propFile;

	public static Properties getPropFile() {
		return propFile;
	}

	// Method to locate and load the properties file
	public static Properties loadPropFile() {
		propFile = new Properties();
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\BaseData.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Properties File is missing or the path specified is incorrect");
		}
		try {
			propFile.load(fs);
		} catch (IOException e) {
			System.out.println("Unable to load the propeties file");
		}
		return propFile;
	}

	// The constructor initialises the driver and loads the properties file
	public DriverFactory() {
		initialize();

	}

	// Method to initialise driver
	public void initialize() {
		if (driver == null)
			createNewDriverInstance();
	}

	// This method reads the properties file and creates a driver instance for a
	// browser
	public void createNewDriverInstance() {

		loadPropFile();

		if (propFile.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();

			// To get through the firefox security warning
			capabilities.setCapability("acceptInsecureCerts", true);

			driver = new FirefoxDriver(capabilities);
			driver.manage().deleteAllCookies();

		} else if (propFile.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
			driver = new ChromeDriver();

		} else if (propFile.getProperty("browser").equals("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "//IEDriver//IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void closingBrowser() {
		driver.quit();
		driver = null;

		/*
		 * Selenium 3.0 has got this issue, where if firefox browser is closed
		 * using the driver.quit method then a pop up is displayed stating that
		 * 'Firefox' was not closed properly. To close the dialog box, created a
		 * script in 'AutoIT' which locates the dialog box and clicks on close.
		 * Not a permanent solution however this will do for now.
		 */
		loadPropFile();

		if (propFile.getProperty("browser").equals("firefox")) {

			try {
				Runtime.getRuntime().exec(System.getProperty("user.dir") + "//AutoITScripts//PopUpCloser.exe");
			} catch (IOException e) {
				System.out.println("Unable to close the Firefox pop up");
			}
		}

	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getBaseUrl() {

		String BaseUrl = propFile.getProperty("baseUrl");
		return BaseUrl;

	}
}
