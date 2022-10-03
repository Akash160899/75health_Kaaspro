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

public class Forms extends Base_Class {
	public static WebDriver driver;
	static PageObjMan pom;
	public static JavascriptExecutor j;

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

		implicitWait(60, TimeUnit.SECONDS);

		click(pom.getInstanceHealthRec().clickHealthRec);
		driver.findElement(By.id("newMedicalRecordButton")).click();
		sleep(3000);

		WebElement lj = driver.findElement(By.xpath("//div[contains(@title,'Add Forms')]"));
		actions("move to element", lj);
		actions("click", lj);
		sleep(3000);

		List<WebElement> numberofformspresent = driver
				.findElements(By.xpath("(//div[@class='form-pop-body'])[10]/div/div[1]"));
		int ffs = numberofformspresent.size();
		System.out.println(ffs);

		int u;
		for (int imp = 4; imp <= ffs; imp++) {
			System.out.println();
			// u = 1 + i;
			WebElement rtt = driver
					.findElement(By.xpath("(//div[@class='form-pop-body'])[10]/div[" + imp + "]/div/div[1]/span[2]"));
			// System.out.println(rtt.getText());

			if (rtt.getText().trim().equals("form5")) {

				rtt.click();
				WebElement js = driver.findElement(By.xpath("(//span[@id='del-form'])[2]"));
				javascriptclick(js);

				break;

			}

		}
		sleep(3000);

		WebElement addfrm = driver.findElement(By.xpath("//div[@id='FormsKpop2']/div[1]/span"));

		actions("click", addfrm);
		driver.findElement(By.xpath("(//label[text()='Form Title*'])[2]//following::input[1]")).sendKeys("form5");

		List<WebElement> drk = driver.findElements(By.xpath("(//div[@id='build-wrap'])[2]/div[1]/div[2]/ul/li"));

		for (WebElement web : drk) {

			if (web.getText().trim().equals("Checkbox Group")) {

				WebElement drop = driver.findElement(
						By.xpath("(//div[contains(@data-content,'Drag a field from the right to this area')])[2]/ul"));

				Actions ac = new Actions(driver);
				ac.dragAndDrop(web, drop).build().perform();
				driver.findElement(By.xpath("//label[text()='Label']//following::div[1]/input")).clear();
				driver.findElement(By.xpath("//label[text()='Label']//following::div[1]/input"))
						.sendKeys("Kaaspro Enterprise");
				driver.findElement(By.xpath("(//div[@id='build-wrap'])[2]/div[1]/div[2]/ul//following::div[1]/button"))
						.click();
				sleep(2000);
				WebElement addit = driver.findElement(By.xpath("//span[text()='form5']//following::div[1]/span"));
				actions("click", addit);
				sleep(3000);
				WebElement ytt = driver.findElement(By.xpath("//div[@id='FormsKpop2']/div[1]/div[2]/span"));
				javascriptclick(ytt);
				break;
			}

		}

		// follow up creation...
		WebElement createfollowup = driver.findElement(By.xpath("(//button[@id='followUpAdd'])[1]/div[1]"));
		javascriptclick(createfollowup);
		sleep(3000);
		implicitWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@id='followupEhr']/div[2]/div[3]/div[1]//following::div[2]/input")).click();

		WebElement folowypyr = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
		dropDown("text", folowypyr, "2022");
		WebElement folowupmnth = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		dropDown("index", folowupmnth, "2");
		driver.findElement(By.xpath("(//a[text()='6'])[3]")).click();
		WebElement fixappo = driver.findElement(By.xpath("(//button[@id='fixAppointment'])[1]"));
		sleep(4000);
		javascriptclick(fixappo);

		List<WebElement> tot = driver.findElements(By.xpath("(//div[@id='date-data'])[1]"));
		int totaly = tot.size();
		System.out.println("found you>>>" + totaly);
		implicitWait(30, TimeUnit.SECONDS);

		boolean b = false;
		for (int i = 1; i <= totaly; i++) {
			sleep(3000);
			List<WebElement> rqq = driver
					.findElements(By.xpath("(//div[@id='formAppointment'])[2]/div/div[2]/div[2]/div[2]/div"));

			System.out.println("total number of displayed time in the list:" + rqq.size());
			for (int jj = 1; jj <= rqq.size(); jj++) {

				WebElement checkcn = driver
						.findElement(By.xpath("(//div[@id='date-data'])[1]/div[2]/div[" + jj + "]/div/div[1]"));
				System.out.println(checkcn.getText());

				WebElement sm = driver
						.findElement(By.xpath("(//div[@id='date-data'])[1]/div[2]/div[" + jj + "]/div/div[2]/span[3]"));
				//System.out.println(sm.getText().isEmpty());

				if (checkcn.isDisplayed() == true && sm.getText().isEmpty() == true) {
					b = true;
					checkcn.click();
					break;

				}

			}
			if (b == true) {
				WebElement seltyp = driver.findElement(By.xpath("(//div[@id='emergencyFollowup'])[1]/div/select"));
				dropDown("index", seltyp, "1");
				driver.findElement(
						By.xpath("(//div[@id='emergencyFollowup'])[1]/div/following::div[3]/div[2]/textarea")).clear();
				driver.findElement(
						By.xpath("(//div[@id='emergencyFollowup'])[1]/div/following::div[3]/div[2]/textarea"))
						.sendKeys("yes follow");

				driver.findElement(By.xpath("(//div[@id='emergencyFollowup'])[1]/div/following::div[6]/div/div"))
						.click();
				sleep(3000);
				// WebElement delfolup = driver
				// .findElement(By.xpath("(//button[@id='fixAppointment'])[1]//following::div[1]/div[1]/span[2]"));
				// actions("move to element", delfolup);
				// actions("click", delfolup);
				// WebElement bckfl =
				// driver.findElement(By.xpath("//div[@id='followupEhr']/div[1]/div/span[1]"));
				// javascriptclick(bckfl);
				break;
			}
		}

	}

}
