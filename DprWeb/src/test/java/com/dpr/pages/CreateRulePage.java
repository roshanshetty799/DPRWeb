package com.dpr.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateRulePage {

	private WebDriver driver;

	Select typeDropdown, triggerDropDown, ownerDropdown, ruleExtractDropdown, triggerTimeDropDown, actionDropDown,
			frequencyDropDown, messagingDropDown;
	String uncarded = "Uncarded";
	String carded = "Carded";

	@FindBy(xpath = "//input[@name='name']")
	private WebElement nameField;

	@FindBy(xpath = "//select[@name='ruleType']")
	private WebElement ruleType;

	@FindBy(xpath = "//abbr[@title='Pause will temporarily disable rule activity.']/label/input")
	private WebElement pauseCheckbox;

	@FindBy(xpath = "//button[@class='btn btn-default'][@ng-click='openStartDate($event)']")
	private WebElement startCalendar;

	@FindBy(xpath = "//button[@class='btn btn-default'][@ng-click='openEndDate($event)']")
	private WebElement endCalendar;

	@FindBys(value = { @FindBy(xpath = "//input[@type='radio']") })
	private List<WebElement> radioButtons;

	@FindBy(xpath = "//select[@name='ruleOwnerSite']")
	private WebElement ruleOwner;

	@FindBy(xpath = "//span[@class='ag-header-cell-menu-button']")
	private WebElement siteSelectButton;

	@FindBys(value = { @FindBy(xpath = "//span[@class='ag-filter-value']") })
	private List<WebElement> siteSelected;

	@FindBy(xpath = "//select[@name='ruleTrigger_A']")
	private WebElement triggerField;

	@FindBy(xpath = "//select[@name='ruleExtract']")
	private WebElement ruleExtract;

	@FindBy(xpath = "//input[@name='criteria_A']")
	private WebElement criteria;

	@FindBy(xpath = "//select[@name='triggerTime_A']")
	private WebElement triggerTime;

	@FindBy(xpath = "//select[@name='ruleAction_A']")
	private WebElement action;

	@FindBy(xpath = "//select[@name='ruleActionFrequency_A']")
	private WebElement frequency;

	@FindBy(id = "pagerMessage_A")
	private WebElement pagingMessageBox;

	@FindBy(xpath = "//*[@id='ruleActionMessage_A']")
	private WebElement messagingBox;

	@FindBy(xpath = "//button[@ng-click='addAction()']")
	private WebElement addAction;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveAndExitButton;

	@FindBy(xpath = "//button[@id='Cancel']")
	private WebElement cancelButton;

	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement cancelYesButton;

	@FindBy(xpath = "//button[@class='btn btn-warning']")
	private WebElement cancelNoButton;

	@FindBy(xpath = "//input[@id='selectAll']")
	private WebElement siteSelectAllCheckBox;

	public void enterDprRuleName(String name) {
		nameField.sendKeys(name);
	}

	public void selectCarded() {
		typeDropdown = new Select(ruleType);
		typeDropdown.selectByVisibleText(carded);
	}

	public void selectUncarded() {
		typeDropdown = new Select(ruleType);
		typeDropdown.selectByVisibleText(uncarded);
	}

	public CreateRulePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void openStartCalendar() {
		startCalendar.click();
	}

	public void openEndCalendar() {
		endCalendar.click();
	}

	public void selectDate(String sdate) {
		List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='uib-daypicker']//td"));

		for (WebElement ele : allDates) {

			String date = ele.getText();

			if (date.equalsIgnoreCase(sdate)) {
				ele.click();
				break;
			}

		}
	}

	public void selectRadioButton(String name) {
		for (int i = 0; i < radioButtons.size(); i++) {
			/*
			 * switch (name.toUpperCase()) { case "EGM":
			 * radioButtons.get(0).click(); break; case "MTGM":
			 * radioButtons.get(1).click(); break; }
			 * 
			 */
			if (name.equalsIgnoreCase("egm")) {
				radioButtons.get(0).click();
			} else if (name.equalsIgnoreCase("mtgm")) {
				radioButtons.get(1).click();
			}
		}
	}

	public void selectRuleOwner(String name) {
		ownerDropdown = new Select(ruleOwner);
		ownerDropdown.selectByVisibleText(name);
	}

	public void selectSite(String siteName) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(siteSelectButton));
		siteSelectButton.click();
		for (int i = 0; i < siteSelected.size(); i++) {
			String siteText = siteSelected.get(i).getText();

			if (siteText.equalsIgnoreCase(siteName)) {
				siteSelected.get(i).click();
			}
		}

	}

	public void selectTrigger(String name) {
		triggerDropDown = new Select(triggerField);
		triggerDropDown.selectByVisibleText(name);
	}

	public void selectRuleExtract(int index) {
		ruleExtractDropdown = new Select(ruleExtract);
		ruleExtractDropdown.selectByIndex(index);
	}

	public void enterCriteria(int amount) {
		String nAmount = Integer.toString(amount);
		criteria.sendKeys(nAmount);
	}

	public void selectTriggerTime(String name) {
		triggerTimeDropDown = new Select(triggerTime);
		triggerTimeDropDown.selectByVisibleText(name);

	}

	public void selectAction(String name) {
		actionDropDown = new Select(action);
		actionDropDown.selectByVisibleText(name);
	}

	public void selectFrequency(String name) {
		frequencyDropDown = new Select(frequency);
		/*
		 * try { Thread.sleep(2000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		frequencyDropDown.selectByVisibleText(name);
	}

	public void enterPagingMessage(String message) {
		pagingMessageBox.sendKeys(message);
	}

	public void selectMessagingMessage(String message) {
		messagingDropDown = new Select(messagingBox);
		messagingDropDown.selectByVisibleText(message);
	}

	public void clickAddAction() {
		addAction.click();
	}

	public void clickSaveAndExit() {
		saveAndExitButton.click();
	}

	public void clickCancel() {
		cancelButton.click();
	}

	public void selectCancelYes() {
		cancelYesButton.click();
	}

	public void selectCancelNo() {
		cancelNoButton.click();
	}

	public void checkSiteSelectAll() {
		siteSelectAllCheckBox.click();
	}
	
	public String getNameFieldtext(){
		return nameField.getAttribute("value");
	}

}
