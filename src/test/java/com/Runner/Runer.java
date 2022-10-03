package com.Runner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BaseClass.Base_Class;

import com.data.ConfigManager;
import com.pageObjectMan.PageObjMan;

public class Runer extends Base_Class {
	public static WebDriver driver;
	static PageObjMan pom;

	public static void main(String[] args) throws IOException, InterruptedException {
		driver = Base_Class.browserLaunch("chrome");
		pom = new PageObjMan(driver);
		String ur = ConfigManager.getconfigManager().getInstanceConfigReader().getUrl();

		while (true) {
			if (ur.equals("https://localhost:8443/")) {

				driver.get(ConfigManager.getconfigManager().getInstanceConfigReader().getUrl());
				sleep(3000);
				driver.findElement(By.id("details-button")).click();
				sleep(3000);

				driver.findElement(By.id("proceed-link")).click();
				sleep(4000);
				implicitWait(30, TimeUnit.SECONDS);

				break;
			} else if (ur.equals("https://www.75health.com/login.jsp")) {
				driver.get("https://www.75health.com/login.jsp");

				break;
			}

		}

		click(pom.getInstanceLoginPage().sigIn);
		sendkeys(pom.getInstanceLoginPage().email,
				ConfigManager.getconfigManager().getInstanceConfigReader().getEmail());
		sendkeys(pom.getInstanceLoginPage().pass, ConfigManager.getconfigManager().getInstanceConfigReader().getpass());
		click(pom.getInstanceLoginPage().login);
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavascriptExecutor j = (JavascriptExecutor) driver;
		sleep(6000);
		Actions ac = new Actions(driver);

		WebElement pit = driver.findElement(By.xpath("//td[text()='Patient']"));
		j.executeScript("arguments[0].click();", pit);
		WebElement ata = driver.findElement(By.xpath("(//button[@title='Add new Patient'])[1]"));
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

		sleep(4000);
		driver.findElement(By.xpath("(//td[text()='Health Record'])[1]")).click();

		//
		driver.findElement(By.xpath("(//input[@id='patientPartyName'])[6]")).sendKeys(kpid);
		sleep(5000);
		WebElement remove = driver.findElement(By.xpath("//span[@onclick='list_ehr.patientRemoved();']"));
		ac.moveToElement(remove).click(remove).build().perform();
		sleep(3000);
		driver.findElement(By.xpath("(//input[@id='patientPartyName'])[6]")).sendKeys(kpid);
		sleep(3000);
		List<WebElement> ct = driver.findElements(By.cssSelector("#ui-id-3 > li"));
		System.out.println(ct.size());
		int siz = ct.size();

		List<WebElement> col = driver.findElements(By.xpath("//ul[@id='ui-id-3']/li[1]/a/table[1]/tbody/tr[1]/td"));
		int col1 = col.size();
		System.out.println(col1);
		List<WebElement> tablecount = driver.findElements(By.xpath("//ul[@id='ui-id-3']/li[1]/a/table/tbody/tr[1]/td"));
		System.out.println(tablecount.size());
		int tablect = tablecount.size();

		System.out.println("//ul[@id='ui-id-3']/li[" + 1 + "]/a/table[1]/tbody/tr[1]/td[" + 1 + "]");

		for (int i = 1; i <= siz; i++) {

			for (int jj = 1; jj <= col1; jj++) {
				WebElement findme = driver.findElement(
						By.xpath("//ul[@id='ui-id-3']/li[" + i + "]/a/table[1]/tbody/tr[1]/td[" + jj + "]"));

				if (findme.getText().equals(kpid)) {
					findme.click();
					break;

				}
			}
		}

		driver.findElement(By.xpath("(//input[@id='userPartyName'])[8]"))
				.sendKeys(ConfigManager.getconfigManager().getInstanceConfigReader().doctorKpid());

		// doctor..
		List<WebElement> drrow = driver.findElements(By.xpath("//ul[@id='ui-id-4']/li/a/table/tbody/tr/td"));

		int rowandcols = drrow.size();

		List<WebElement> colitertate = driver
				.findElements(By.xpath("//ul[@id='ui-id-4']/li/a/table/tbody[1]/tr[1]/td[1]"));
		int colsiter = colitertate.size();

		for (int i = 1; i <= rowandcols; i++) {
			for (int b = 1; b <= colsiter; b++) {
				WebElement getname = driver
						.findElement(By.xpath("//ul[@id='ui-id-4']/li/a/table/tbody[1]/tr[1]/td[" + b + "]"));
				if (getname.getText().equals(ConfigManager.getconfigManager().getInstanceConfigReader().doctorKpid())) {
					getname.click();
					break;
				}

			}

		}

		sleep(2000);

		driver.findElement(By.id("newMedicalRecordButton")).click();

		// calendar module...

		/*
		 * click(pom.getInstanceCalendar().clickCalendar); sleep(5000);
		 * driver.findElement(By.xpath("(//button[@id='calendar-day-month'])[1]")).click
		 * (); sleep(2000); List<WebElement> choose =
		 * driver.findElements(By.xpath("//ul[@id='calendarul']/li")); for (WebElement
		 * web : choose) { if (web.getText().equals("Today")) {
		 * driver.findElement(By.xpath("//ul[@id='calendarul']/li")).click(); break; } }
		 * sleep(4000); String calxpath = "(//td[text()='" + kpid + "'])[2]";
		 * System.out.println(calxpath);
		 * 
		 * 
		 * 
		 * WebElement clnd2 = driver.findElement( By.
		 * xpath("(//div[@title='Click to book appointment'])[1]//following::div[2][@id='time-09-00']"
		 * )); actions("click", clnd2); implicitWait(30, TimeUnit.SECONDS); sleep(3000);
		 * driver.findElement(By.id("AppointmentPatientName")) .sendKeys(kpid);
		 * sleep(3000);
		 * 
		 * WebElement clickpatient = driver.findElement(By.xpath(calxpath));
		 * actions("click", clickpatient);
		 * click(pom.getInstanceCalendar().selectAppointmentType); dropDown("index",
		 * pom.getInstanceCalendar().selectAppointmentType, "1");
		 * 
		 * sleep(5000);
		 * 
		 * WebElement qtqt = driver.findElement(By.xpath(
		 * "(//div[@id='description-lb'][1]//following::div//textarea[@placeholder='Description/Visit reason'])[1]"
		 * )); // // // // actions("click", qtqt); sleep(4000);
		 * qtqt.sendKeys("chill and cough..");
		 * 
		 * sleep(4000); ScriptExecutor(pom.getInstanceCalendar().saveAppointment);
		 * 
		 * explicitWait(50, pom.getInstanceCalendar().saveAppointment); sleep(4000);
		 * 
		 * click(pom.getInstanceCalendar().saveAppointment); sleep(3000); WebElement
		 * tyui = driver.findElement(By.xpath("//span[text()='chill and cough..']"));
		 * actions("click", tyui); sleep(3000); driver.findElement(By.xpath(
		 * "/html/body/div[4]/div[4]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/div[6]/div[76]/div[2]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[1]/div[3]/div/div[1]/div[2]/span/div/span[2]"
		 * )) .click(); sleep(3000); driver.findElement(By.xpath(
		 * "/html/body/div[4]/div[4]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/div[6]/div[76]/div[2]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[1]/div[3]/div/div[1]/div[2]/span/div/ul/li/div/div[2]/span[1]"
		 * )) .click();
		 */

		// Billing Module...

		explicitWait(15, pom.getInstanceBilling().clickBill); //
		click(pom.getInstanceBilling().clickBill);
		sleep(5000);
		explicitWait(15, pom.getInstanceBilling().clickCreateNewBill);
		click(pom.getInstanceBilling().clickCreateNewBill);
		explicitWait(30, pom.getInstanceBilling().addItem);
		sleep(4000);
		click(pom.getInstanceBilling().addItem);
		sleep(2000);
		sendkeys(pom.getInstanceBilling().enterTheItem, "dolo"); //
		sleep(2000);
		sendkeys(pom.getInstanceBilling().addPrice, "10"); //
		sleep(2000);
		clear(pom.getInstanceBilling().addQuantity); //
		sleep(2000);
		sendkeys(pom.getInstanceBilling().addQuantity, "2");
		sleep(3000);
		click(pom.getInstanceBilling().saveItem);
		sleep(3000);

		// Add from favorite...

		WebElement clickfav = driver.findElement(By.xpath("(//*[@id=\"assign-icon\"])[1]"));
		actions("click", clickfav);
		sleep(3000);
		WebElement tt20 = driver.findElement(By.xpath("(//span[@id='plus-add'])[1]"));
		j.executeScript("arguments[0].click();", tt20);
		WebElement tt21 = driver.findElement(By.xpath("(//input[@id='description'])[4]"));
		tt21.sendKeys("Hello");
		driver.findElement(By.xpath("(//input[@id='price'])[3]")).sendKeys("2");
		sleep(3000);
		WebElement tt22 = driver.findElement(By.xpath("(//button[@id='save-btn'])[37]"));
		j.executeScript("arguments[0].click();", tt22);
		sleep(2000);
		WebElement tt23 = driver.findElement(By.xpath("(//span[@id='select'])[2]"));
		j.executeScript("arguments[0].click();", tt23);
		
		
		
		sleep(3000);
		WebElement tt26 = driver.findElement(By.xpath("(//span[text()='Item/Service Code'])[1]"));
		actions("move to element", tt26);
		actions("click", tt26);
		
		WebElement tt27 = driver.findElement(By.xpath("(//span[@id='plus-add'])[2]"));
		j.executeScript("arguments[0].click();", tt27);
		sleep(2000);
		
		driver.findElement(By.xpath("(//input[@id='codeId'])[2]")).sendKeys("service code001");
		driver.findElement(By.xpath("(//textarea[@id='description'])[13]")).sendKeys("yt");
		driver.findElement(By.xpath("(//input[@title='Enter price value for the Item/Service'])[2]")).sendKeys("3");
		WebElement tt28 = driver.findElement(By.xpath("(//button[@id='save-btn'])[13]"));
		j.executeScript("arguments[0].click();", tt28);
		sleep(3000);
		
		
		
		
		
		
		

		driver.findElement(By.xpath("(//button[@id='add-payment-btn'])[1]")).click();
		driver.findElement(By.xpath("(//span[@id='paymentMethodTypeSelectValue'])[2]")).click();
		sleep(2000);
		List<WebElement> choosepy = driver.findElements(By.xpath("(//ul[@id='calendarul'])[4]/li"));
		for (WebElement w : choosepy) {
			if (w.getText().trim().equals("Cash Payment")) {
				w.click();
				break;
			}

		}
		WebElement dsp1 = driver.findElement(By.xpath(
				"/html/body/div[4]/div[4]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/div[6]/div[82]/div/div/div[1]/div/div[17]/div[4]/div[2]/div/div[3]/div[2]/div/div[2]/div[7]/div[2]/textarea"));
		actions("click", dsp1);
		sleep(3000);
		dsp1.sendKeys("cash pay+++++++++");
		sleep(3000);
		WebElement tet = driver.findElement(By.xpath("(//button[@title='Make Payment'])[3]"));
		j.executeScript("arguments[0].click();", tet);
		sleep(3000);
		driver.findElement(By.xpath("//button[@id='finalize-bill']")).click();
		sleep(3000);
		driver.findElement(By.xpath("(//button[@title='Finalize'])[1]")).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);

		// driver.findElement(By.xpath("(//input[@id='patientPartyName'])[6]")).click();
		// WebElement click =
		// driver.findElement(By.xpath("(//ul[contains(@class,'ui-autocomplete
		// ui-menu')])[3]/li[1]"));
		// wait.until(ExpectedConditions.visibilityOf(click));
		// ac.moveToElement(click).build().perform();
		sleep(2000);
		// System.out.println("sucessfully///");
		// WebElement ck =
		// driver.findElement(By.xpath("(//ul[contains(@class,'ui-autocomplete
		// ui-menu')])[3]/li[1]/a/table/tbody/tr/td/span"));
		// ac.click(ck).build().perform();

		/*
		 * WebElement calen = driver.findElement(By.
		 * xpath("(//div[@title='Click to book appointment'][1]//following::div/div)[1]"
		 * )); actions("move to element", calen);
		 * wait.until(ExpectedConditions.visibilityOf(calen));
		 */

	}

}
