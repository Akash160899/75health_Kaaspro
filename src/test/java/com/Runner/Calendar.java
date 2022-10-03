package com.Runner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.BaseClass.Base_Class;
import com.pageObjectMan.PageObjMan;

public class Calendar extends Base_Class {
	public static WebDriver driver;
	static PageObjMan pom;
	static JavascriptExecutor j;

	public static void main(String[] args) throws InterruptedException {

		driver = browserLaunch("chrome");
		pom = new PageObjMan(driver);
		j = (JavascriptExecutor) driver;

		driver.get("https://www.75health.com/");
		implicitWait(30, TimeUnit.SECONDS);
		click(pom.getInstanceLoginPage().sigIn);
		sleep(2000);
		sendkeys(pom.getInstanceLoginPage().email, "userbasic1608@gmail.com");
		sendkeys(pom.getInstanceLoginPage().pass, "1");
		click(pom.getInstanceLoginPage().login);
		sleep(3000);

		explicitWait(20, pom.getInstanceCalendar().clickCalendar);
		click(pom.getInstanceCalendar().clickCalendar);
		sleep(3000);

		driver.findElement(By.xpath("(//button[@id='calendar-day-month'])[1]")).click();
		sleep(2000);
		List<WebElement> choose = driver.findElements(By.xpath("//ul[@id='calendarul']/li"));
		/*
		 * for (WebElement web : choose) { if (web.getText().equals("Today")) {
		 * driver.findElement(By.xpath("//ul[@id='calendarul']/li")).click(); break; } }
		 * sleep(4000);
		 */

		List<WebElement> choosecalendar = driver.findElements(By.xpath("//div[@id='row-data']"));

		List<WebElement> rq = driver.findElements(By.xpath("(//div[@id='row-data'])[1]/div/div[3]/span"));
		int rtrt = rq.size();
		boolean cond;
		int row = choosecalendar.size();
		System.out.println(row);

		for (int i = 1; i <= row; i++) {

			WebElement df = driver.findElement(By.xpath("(//div[@id='row-data'])[" + i + "]/div/div[3]/span"));

			if (df.getText().isEmpty()) {
				cond = true;
				WebElement root = driver
						.findElement(By.xpath("(//div[@id='row-data'])[" + i + "]/div/div[3]/span//parent::div"));
				root.click();
				break;
			}

		}

		driver.findElement(By.id("AppointmentPatientName")).sendKeys("3524560");
		sleep(3000);
		WebElement clnd = driver.findElement(By.xpath("(//td[text()='3524-560'])"));
		actions("click", clnd);
		sleep(3000);
		click(pom.getInstanceCalendar().selectAppointmentType);
		dropDown("index", pom.getInstanceCalendar().selectAppointmentType, "1");

		sleep(5000);

		WebElement qtqt = driver.findElement(By.xpath(
				"(//div[@id='description-lb'][1]//following::div//textarea[@placeholder='Description/Visit reason'])[1]")); // //
																															// //
		actions("click", qtqt);
		sleep(4000);
		qtqt.sendKeys("chill and cough..");

		sleep(4000);
		ScriptExecutor(pom.getInstanceCalendar().saveAppointment);

		explicitWait(50, pom.getInstanceCalendar().saveAppointment);
		sleep(4000);

		click(pom.getInstanceCalendar().saveAppointment);
		sleep(3000);
		WebElement tyui = driver.findElement(By.xpath("//span[text()='chill and cough..']"));
		actions("click", tyui);
		sleep(3000);
		driver.findElement(By.xpath(
				"/html/body/div[4]/div[4]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/div[6]/div[76]/div[2]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[1]/div[3]/div/div[1]/div[2]/span/div/span[2]"))
				.click();
		sleep(3000);
		driver.findElement(By.xpath(
				"/html/body/div[4]/div[4]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/div[6]/div[76]/div[2]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[1]/div[3]/div/div[1]/div[2]/span/div/ul/li/div/div[2]/span[1]"))
				.click();

	}

}
