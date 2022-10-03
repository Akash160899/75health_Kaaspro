package com.Runner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.BaseClass.Base_Class;
import com.pageObjectMan.PageObjMan;

public class Home_calendar extends Base_Class {
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

		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> choosecalendar = driver.findElements(By.xpath("//div[@id='date-data']"));

		int row = choosecalendar.size();
		System.out.println(row);
		String xpat = "";
		boolean cond = false;
		for (int i = 1; i <= row; i++) {

			xpat = "(//input[@id='AppointmentPatientName'])[" + i + "]";
			System.out.println(xpat);

			List<WebElement> cols = driver
					.findElements(By.xpath("//div[@id='date-data'][" + i + "]/div[2]/div/div/div[1]/div[1]"));
			System.out.println(cols.size());
			for (int bs = 1; bs <= cols.size(); bs++) {
				// driver.findElement(By.xpath("(//input[@id='AppointmentPatientName'])[" + i
				// +"]"));
				WebElement df = driver
						.findElement(By.xpath("//div[@id='date-data']["+i+"]/div[2]/div[" + bs + "]/div/div[1]/div[1]"));

				if (df.getText().isEmpty()) {
					System.out.println(i);
					cond = true;
					System.out.println("condition mets");
					WebElement root = driver
							.findElement(By.xpath("(//div[@id='row-data'])[" +bs+ "]/div/div[3]/span//parent::div"));
					sleep(3000);
					j.executeScript("arguments[0].click();", root);
					sleep(4000);
					implicitWait(30, TimeUnit.SECONDS);

					driver.findElement(By.xpath("(//input[@id='AppointmentPatientName'])[" + i + "]"))
							.sendKeys("3524560");

					break;
				}

			}

			if (cond == true) {

				break;
			}
		}

		sleep(3000);

		WebElement choosepatient = driver.findElement(By.xpath("(//td[text()='3524-560'])[2]//parent::td"));
		actions("click", choosepatient);
		sleep(3000);

		WebElement ut = driver.findElement(By.xpath("(//select[@id='triage-appointment'])[1]"));
		ut.click();
		dropDown("text", ut, "Emergency");

		WebElement qt = driver.findElement(By.xpath("(//textarea[@id='description'])[1]"));
		qt.sendKeys("no worries...");
		WebElement utt = driver.findElement(By.xpath("(//button[@id='statusId_dropdown'])[1]"));
		j.executeScript("arguments[0].click();", utt);
		sleep(3000);
		List<WebElement> lop = driver.findElements(By.xpath("(//ul[@id='statusIdDropdown'])[1]/li"));
		for (WebElement w : lop) {
			if (w.getText().trim().equals("In Progress")) {
				w.click();
				break;
			}

		}
		sleep(5000);
		WebElement vcv = driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]"));
		j.executeScript("arguments[0].click();", vcv);

		sleep(5000);
		WebElement pip = driver.findElement(By.xpath("//span[text()='Akash']"));
		j.executeScript("arguments[0].click();", pip);

		sleep(3000);
		WebElement wtw = driver.findElement(By.xpath("(//span[@id='del-btn'])[5]"));
		j.executeScript("arguments[0].click();", wtw);
		sleep(3000);
		WebElement rtr = driver.findElement(By.xpath("(//span[@id='yes-btn'])[4]"));
		j.executeScript("arguments[0].click();", rtr);
		sleep(5000);

	}

}
