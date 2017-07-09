package com.dpr.testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dpr.driver.DriverFactory;
import com.dpr.pages.HomePage;
import com.dpr.pages.LoginPage;
import com.dpr.utils.DataPicker;

import junit.framework.Assert;

public class testLogin extends DriverFactory {

	// To test login is successful with valid credentials
	@Test(dataProvider = "loginCredentials", dataProviderClass = DataPicker.class, enabled = false)
	public void loginValid(String username, String password) {
		new LoginPage(driver).enterUsername(username);
		new LoginPage(driver).enterPassword(password);
		new LoginPage(driver).clickLogin();

		Assert.assertEquals("Login was unsuccessfull.Driver not on Homepage",
				getPropFile().getProperty("logoutButtonText"), new HomePage(driver).getLogoutButtonText());

	}

	// To test login is unsuccessful with invalid credentials
	@Test(dataProvider = "loginCredentials", dataProviderClass = DataPicker.class, enabled = false)
	public void invalidLoginError(String username, String password) {
		if (username.isEmpty() || password.isEmpty()) {
			new LoginPage(driver).enterUsername(username);
			new LoginPage(driver).enterPassword(password);
			Assert.assertEquals("Login Button is enabled", new LoginPage(driver).verifyLoginButtonVisiblity(), false);
		}

		else {
			new LoginPage(driver).enterUsername(username);
			new LoginPage(driver).enterPassword(password);
			new LoginPage(driver).clickLogin();
			Assert.assertEquals("Authorization failed dialog did not pop up",
					new LoginPage(driver).authFailBox.getText(), getPropFile().getProperty("AuthFailMessage"));
			new LoginPage(driver).clickAuthFailOkButton();
			Assert.assertEquals("Login Button is enabled", new LoginPage(driver).verifyLoginButtonVisiblity(), false);
		}
	}

	// To test logout is successful
	@Test
	public void logoutSuccess() {
		new LoginPage(driver).loginToDPR(getPropFile().getProperty("username"), getPropFile().getProperty("password"));
		new HomePage(driver).clickLogout();
		Assert.assertEquals("Login Button is enabled", new LoginPage(driver).verifyLoginButtonVisiblity(), false);
	}

	@BeforeMethod
	public void invokeDriver() {
		driver = new DriverFactory().getDriver();

		driver.navigate().to(new DriverFactory().getBaseUrl());

	}

	@AfterMethod
	public void destroyDriver() {
		new DriverFactory().closingBrowser();
	}
}
