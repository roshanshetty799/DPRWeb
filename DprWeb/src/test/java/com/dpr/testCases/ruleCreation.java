package com.dpr.testCases;

import org.testng.annotations.Test;

import com.dpr.driver.DriverFactory;
import com.dpr.pages.CreateRulePage;
import com.dpr.pages.HomePage;
import com.dpr.pages.LoginPage;

import junit.framework.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class ruleCreation extends DriverFactory {

	@Test(priority = 1)
	public void AnewRuleCreation() {
		new LoginPage(driver).loginToDPR(getPropFile().getProperty("username"), getPropFile().getProperty("password"));
		new HomePage(driver).clickNew();
		new CreateRulePage(driver).enterDprRuleName("RandomName");
		new CreateRulePage(driver).selectCarded();
		new CreateRulePage(driver).openStartCalendar();
		new CreateRulePage(driver).selectDate("06");
		new CreateRulePage(driver).openEndCalendar();
		new CreateRulePage(driver).selectDate("08");
		new CreateRulePage(driver).selectRuleOwner(getPropFile().getProperty("Site1"));
	//	new CreateRulePage(driver).selectSite(getPropFile().getProperty("Site1"));
		new CreateRulePage(driver).selectRuleExtract(1);
		new CreateRulePage(driver).selectTrigger("Turnover");
		new CreateRulePage(driver).enterCriteria(50);
		new CreateRulePage(driver).selectTriggerTime("Day");
		new CreateRulePage(driver).selectAction("Paging");
		new CreateRulePage(driver).selectFrequency("Unlimited");
		new CreateRulePage(driver).enterPagingMessage("A test message");
		new CreateRulePage(driver).clickSaveAndExit();

	}

	@Test(enabled = false)
	public void unableToCreateRuleWithSameName() {

	}

	@Test (priority = 2,enabled = false)
	public void deleteNonStartedRule() throws InterruptedException {
		new LoginPage(driver).loginToDPR(getPropFile().getProperty("username"), getPropFile().getProperty("password"));
		new HomePage(driver).clickDprRule("RandomName");
		new HomePage(driver).clickModify();
		System.out.println(new CreateRulePage(driver).getNameFieldtext());
		// Assert.assertEquals("Wrong DPR rule has been opened", "RandomName",
		// new CreateRulePage(driver).getNameFieldtext());
		new CreateRulePage(driver).clickCancel();
		new HomePage(driver).clickDprRule("RandomName");
		new HomePage(driver).clickDelete();
		new HomePage(driver).selectDeleteCancel();

	}

	@Test (priority = 3,enabled = false)
	public void deleteNonStartedRuleOk() {
		new LoginPage(driver).loginToDPR(getPropFile().getProperty("username"), getPropFile().getProperty("password"));
		new HomePage(driver).clickDprRule("RandomName");
		new HomePage(driver).clickModify();
		new CreateRulePage(driver).clickCancel();
		new HomePage(driver).clickDprRule("RandomName");
		new HomePage(driver).clickDelete();
		new HomePage(driver).selectDeleteOK();
	}

	@BeforeMethod
	public void invokeDriver() {
		driver = new DriverFactory().getDriver();

		driver.navigate().to(new DriverFactory().getBaseUrl());
		driver.manage().window().maximize();

	}

/*	@AfterMethod
	public void destroyDriver() {
		new DriverFactory().closingBrowser();
	}*/

}
