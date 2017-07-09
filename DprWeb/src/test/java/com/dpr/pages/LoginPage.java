package com.dpr.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@SuppressWarnings("unused")
	private WebDriver driver;

	@FindBy(xpath = "//*[@id='username']")
	private WebElement usernameField;

	@FindBy(xpath = "//*[@id='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//button[@class='btn btn-primary'][@type='submit']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[@class='text-center']")
	public WebElement authFailBox;

	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement authFailOkButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void enterUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void loginToDPR(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();

	}

	public void clickLogin() {
		loginButton.click();
	}

	public void clickAuthFailOkButton() {
		authFailOkButton.click();
	}

	public boolean verifyLoginButtonVisiblity(){
		return loginButton.isEnabled();
	}
}
