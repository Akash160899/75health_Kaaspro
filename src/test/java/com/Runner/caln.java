package com.Runner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.BaseClass.Base_Class;
import com.pageObjectMan.PageObjMan;

public class caln extends Base_Class {

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

		// Home module(patient)....

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
		String hkpid = driver.findElement(By.xpath("//td[@id='val-kpid']")).getText();

		sleep(3000);

		driver.navigate().to("https://www.75health.com/health/#home");
		// driver.navigate().refresh();
		implicitWait(30, TimeUnit.SECONDS);
		sleep(3000);
		// Appointments books...
		// total div
		List<WebElement> totalnumberrowdy = driver.findElements(By.xpath("//div[@id='date-data']"));
		int totalr = totalnumberrowdy.size();
		System.out.println("found you>>>" + totalr);

		boolean cond = false;

		for (int i = 1; i <= totalr; i++) {
			// represent total in a part..
			List<WebElement> rchange = driver
					.findElements(By.xpath("(//div[@id='date-data'][" + i + "]/div[2]/div/div[1]/div[1]/div[1])"));
			int avaiable = rchange.size();

			System.out.println("avaiable count for the present row:" + avaiable);
			for (int b = 1; b <= avaiable; b++) {
				System.out.println("hello");
				WebElement tp = driver.findElement(
						// represent the row change and time..
						By.xpath("(//div[@id='date-data'][" + i + "]/div[2]/div[" + b + "]/div[1]/div[1]/div[1])"));

				String tr = tp.getText();
				boolean trp = tp.isDisplayed();
				System.out.println(tr);
				System.out.println(trp);

				// the kpid ..
				WebElement kp = driver.findElement(
						By.xpath("(//div[@id='date-data'])[" + i + "]/div[2]/div[" + b + "]/div/div[2]/span[2]"));

				if (kp.getText().isEmpty() && tp.isDisplayed()) {
					System.out.println(tp.getAttribute("id"));
					cond = true;
					System.out.println("condtion is true");
					javascriptclick(tp);
					System.out.println(i);
					driver.findElement(By.xpath("(//input[@id='AppointmentPatientName'])[" + i + "]")).sendKeys(hkpid);
					break;
				}

			}
			if (cond == true) {
				sleep(2000);
				implicitWait(30, TimeUnit.SECONDS);
				WebElement choosepatient = driver
						.findElement(By.xpath("(//td[text()='" + hkpid + "'])[2]//parent::td"));
				actions("click", choosepatient);
				sleep(3000);

				WebElement ut = driver.findElement(By.xpath("(//select[@id='triage-appointment'])[" + i + "]"));
				// ut.click();
				dropDown("text", ut, "Emergency");

				WebElement qt = driver.findElement(By.xpath("(//textarea[@id='description'])[" + i + "]"));
				qt.sendKeys("no worries...");
				WebElement utt = driver.findElement(By.xpath("(//button[@id='statusId_dropdown'])[" + i + "]"));
				j.executeScript("arguments[0].click();", utt);
				sleep(3000);
				List<WebElement> lop = driver.findElements(By.xpath("(//ul[@id='statusIdDropdown'])[" + i + "]/li"));
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
				WebElement pip = driver.findElement(By.xpath("//span[text()='" + hkpid + "']"));
				j.executeScript("arguments[0].click();", pip);

				sleep(3000);
				WebElement wtw = driver.findElement(By.xpath("(//span[@id='del-btn'])[" + i + "]"));
				j.executeScript("arguments[0].click();", wtw);
				sleep(3000);
				WebElement rtr = driver.findElement(By.xpath("(//span[@id='yes-btn'])[8]"));
				j.executeScript("arguments[0].click();", rtr);
				sleep(5000);

				break;
			}
		}

		driver.findElement(By.xpath("(//input[@id='patientPartyName'])[3]")).sendKeys(hkpid);
		WebElement cli = driver.findElement(By.xpath("(//td[text()='" + hkpid + "'])[2]"));
		javascriptclick(cli);

		ScriptExecutor(pom.getInstanceNewPatient().deletePatient);
		click(pom.getInstanceNewPatient().deletePatient);
		driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();

		// Patient module....

		WebElement pit = driver.findElement(By.xpath("//td[text()='Patient']"));
		j.executeScript("arguments[0].click();", pit);
		WebElement at = driver.findElement(By.xpath("(//button[@title='Add new Patient'])[1]"));
		j.executeScript("arguments[0].click();", at);
		sendkeys(pom.getInstanceNewPatient().firstName, "sam");
		sendkeys(pom.getInstanceNewPatient().lastname, "n");
		click(pom.getInstanceNewPatient().clickGenderIcon);

		List<WebElement> gn = driver.findElements(By.xpath("(//ul[@id='genderDropdown'])[1]/li"));
		for (WebElement opt : gn) {

			if (opt.getText().equals("Male")) {

				driver.findElement(By.xpath("(//ul[@id='genderDropdown'])[1]/li")).click();

			}
			break;
		}

		// Acc gets Created..

		click(pom.getInstanceNewPatient().CreatePatient);
		sleep(3000);

		// driver.findElement(By.xpath("(//div[text()='3526-892'])[1]")).click();

		sleep(4000);
		String kpid = driver.findElement(By.xpath("//td[@id='val-kpid']")).getText();
		System.out.println(kpid);
		sleep(3000);

		driver.findElement(By.xpath("//td[text()='Patient']")).click();

		if (driver.findElement(By.xpath("//td[@id='val-kpid']")).getText().equals(kpid)) {
			System.out.println("sucessfully found");
		}

		// String s = "//div[text()=" + "'" + kpid + "']";
		sleep(4000);

		driver.findElement(By.xpath("//div[text()=" + "'" + kpid + "']")).click();
		System.out.println("okay done...");

	}

}
