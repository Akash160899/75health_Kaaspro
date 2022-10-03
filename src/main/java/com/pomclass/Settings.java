package com.pomclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Settings {
	public WebDriver driver;
	
	@FindBy(xpath = "//td[text()='Settings']")
	public WebElement clickSettings;
	
	@FindBy(xpath = "//button[@id='trash-btn']")
	public WebElement scrollTillDelete;
	
	@FindBy(xpath = "//button[@id='taxbutton']")
	public WebElement scrollTillTax;
	
	
	@FindBy(xpath = "//button[@onclick='hospitalcodedropdown();']")
	public WebElement  scrollTillHospitalCode;
	
	
	@FindBy(xpath = "//button[@id='edit-print-preference']")
	public WebElement scrollTillPrintSettings;
	
	
	@FindBy(xpath = "//div[@id='subscription-head']")
	public WebElement scrollTillSubscription;
	
	@FindBy(xpath = "(//div[text()='Notification Messages'])[1]")
	public WebElement scrollTillNotification;
	
	
	@FindBy(xpath = "//button[@onclick='setting.audit()']")
	public WebElement  scrollTillAuditReport;
	
           public Settings(WebDriver driver) {
			this.driver=driver;
			
			PageFactory.initElements(driver, this);
		}
}
