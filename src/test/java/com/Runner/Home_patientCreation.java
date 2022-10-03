package com.Runner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.BaseClass.Base_Class;
import com.pageObjectMan.PageObjMan;

public class Home_patientCreation extends Base_Class {
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
		implicitWait(30, TimeUnit.SECONDS);

		WebElement ata = driver.findElement(By.xpath("(//span[contains(text(),'New Pa')])[4]//parent::button"));

		j.executeScript("arguments[0].click();", ata);
		sendkeys(pom.getInstanceNewPatient().firstName, "sam");
		sendkeys(pom.getInstanceNewPatient().lastname, "n");
		click(pom.getInstanceNewPatient().clickGenderIcon);

		List<WebElement> genders = driver.findElements(By.xpath("(//ul[@id='genderDropdown'])[1]/li"));
		for (WebElement opt : genders) {

			if (opt.getText().equals("Male")) {

				driver.findElement(By.xpath("(//ul[@id='genderDropdown'])[1]/li")).click();

			}
			break;
		} // Acc gets Created..
		click(pom.getInstanceNewPatient().CreatePatient);

		sleep(4000);

		ScriptExecutor(pom.getInstanceNewPatient().deletePatient);
		click(pom.getInstanceNewPatient().deletePatient);
		driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();

		sleep(3000);

		// appointment booking..

		List<WebElement> choosecalendar = driver.findElements(By.xpath("//div[@id='row-data']"));

		List<WebElement> rq = driver.findElements(By.xpath("(//div[@id='row-data'])[1]/div/div[2]/span[3]"));
		int col = rq.size();
		System.out.println("text colum:" + col);

		int row = choosecalendar.size();
		System.out.println(row);
		boolean cond = false;
		for (int i = 1; i <= row; i++) {

			for (int b = 1; b <= col; b++) {

				WebElement df = driver.findElement(By.xpath("(//div[@id='row-data'])[" + i + "]/div/div[2]/span[3]"));

				if (df.getText().isEmpty()) {
					cond = true;
					System.out.println("condition mets");
					WebElement root = driver
							.findElement(By.xpath("(//div[@id='row-data'])[" + i + "]/div/div[3]/span//parent::div"));
					sleep(3000);
					j.executeScript("arguments[0].click();", root);
					break;
				}

			}
			if (cond == true) {
				break;
			}
		}

		WebElement aaa = driver.findElement(By.xpath("(//input[@id='AppointmentPatientName'])[1]"));
		j.executeScript("arguments[0].click();", aaa);
		aaa.sendKeys("3524560");
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
		
		

		// patient module....

		JavascriptExecutor j = (JavascriptExecutor) driver;
		sleep(6000);
		Actions ac = new Actions(driver);

		WebElement pit = driver.findElement(By.xpath("//td[text()='Patient']"));
		j.executeScript("arguments[0].click();", pit);
		WebElement ataa = driver.findElement(By.xpath("(//button[@title='Add new Patient'])[1]"));
		j.executeScript("arguments[0].click();", ataa);
		sendkeys(pom.getInstanceNewPatient().firstName, "sam");
		sendkeys(pom.getInstanceNewPatient().lastname, "n");
		click(pom.getInstanceNewPatient().clickGenderIcon);

		List<WebElement> genderss = driver.findElements(By.xpath("(//ul[@id='genderDropdown'])[1]/li"));
		for (WebElement opt : genderss) {

			if (opt.getText().equals("Male")) {

				driver.findElement(By.xpath("(//ul[@id='genderDropdown'])[1]/li")).click();

			}
			break;
		}

		// Acc gets Created..

		click(pom.getInstanceNewPatient().CreatePatient);
		sleep(3000);

		sleep(4000);
		String kpid = driver.findElement(By.xpath("//td[@id='val-kpid']")).getText();
		System.out.println(kpid);
		sleep(3000);

		driver.findElement(By.xpath("//td[text()='Patient']")).click();

		if (driver.findElement(By.xpath("//td[@id='val-kpid']")).getText().equals(kpid)) {
			System.out.println("sucessfully found");
		}

		sleep(4000);
		driver.findElement(By.xpath("(//input[@id='patientPartyName'])[2]")).sendKeys(kpid);
		driver.findElement(By.xpath("//div[text()=" + "'" + kpid + "']")).click();
		System.out.println("okay done...");

	}

}
