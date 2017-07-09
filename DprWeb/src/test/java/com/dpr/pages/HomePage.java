package com.dpr.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;

	@FindBy(xpath = "//a[@href='#/login']")
	private WebElement logoutButton;

	@FindBy(xpath = "//*[@id='New']")
	private WebElement newButton;

	@FindBy(xpath = "//*[@id='Modify']")
	private WebElement modifyButton;

	@FindBy(xpath = "//*[@id='Delete']")
	private WebElement deleteButton;

	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement includeInactiveButton;
	
	@FindBy(xpath="//div[@class='modal-footer ng-scope']/button[@class='btn btn-primary']")
	private WebElement deleteOKButton;
	
	@FindBy(xpath="//div[@class='modal-footer ng-scope']/button[@class='btn btn-warning']")
	private WebElement deleteCancelButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void clickLogout() {
		logoutButton.click();
	}

	public void clickNew() {
		newButton.click();
	}

	public void clickDelete() {
		deleteButton.click();
	}

	public void clickIncludeInactive() {
		includeInactiveButton.click();
	}

	public String getLogoutButtonText() {
		return logoutButton.getText();
	}

	public void clickModify() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(modifyButton));
		modifyButton.click();
	}

	public void clickDprRule(String name) {
		List<WebElement> dprName = driver.findElements(By.xpath("//*[contains(@class,'ag-row ')]/div[1]"));
		for (WebElement temp : dprName) {
			String t = temp.getText();
			if (t.equals(name)) {
				temp.click();
				break;
			}
		}

	}
	
	public void selectDeleteOK(){
		deleteOKButton.click();
	}
	
	public void selectDeleteCancel(){
		deleteCancelButton.click();
	}

}
