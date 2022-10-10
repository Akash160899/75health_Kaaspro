package com.Runner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.BaseClass.Base_Class;
import com.data.ConfigManager;
import com.pageObjectMan.PageObjMan;

public class Local_Host extends Base_Class {

	public static WebDriver driver;
	static PageObjMan pom;
	static JavascriptExecutor j;
	WebDriverWait ww;
	String kpid;

	@BeforeClass
	private void LaunchBrwoser() throws InterruptedException, IOException {

		driver = browserLaunch("chrome");
		pom = new PageObjMan(driver);
		j = (JavascriptExecutor) driver;
		ww = new WebDriverWait(driver, 20);
		String ur = ConfigManager.getconfigManager().getInstanceConfigReader().getUrl();

		while (true) {
			if (ur.equals("https://localhost:8443/")) {

				driver.get(ConfigManager.getconfigManager().getInstanceConfigReader().getUrl());
				sleep(3000);
				driver.findElement(By.id("details-button")).click();
				sleep(3000);

				driver.findElement(By.id("proceed-link")).click();
				sleep(4000);
				implicitWait(60, TimeUnit.SECONDS);

				break;
			} else if (ur.equals("https://www.75health.com/login.jsp")) {
				driver.get("https://www.75health.com/login.jsp");

				break;
			}

		}

		click(pom.getInstanceLoginPage().sigIn);
		sleep(2000);
		sendkeys(pom.getInstanceLoginPage().email,
				ConfigManager.getconfigManager().getInstanceConfigReader().getEmail());
		sendkeys(pom.getInstanceLoginPage().pass, ConfigManager.getconfigManager().getInstanceConfigReader().getpass());
		click(pom.getInstanceLoginPage().login);

		while (true) {
			if (!driver.getCurrentUrl().equals("https://localhost:8443/health/#home")) {
				click(pom.getInstanceLoginPage().login);
				break;
			}
		}
		sleep(3000);

		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		implicitWait(70, TimeUnit.SECONDS);

	}

	@Test(priority = 0)
	private void HomeModule() throws InterruptedException {

		WebElement ata = driver.findElement(By.xpath("(//span[contains(text(),'New Pa')])[4]//parent::button"));
		visbility(driver, ata, 60);
		ww.until(ExpectedConditions.elementToBeClickable(ata));

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

		WebElement id = driver.findElement(By.xpath("//td[@id='val-kpid']"));
		visbility(driver, id, 60);
		String hkpid = id.getText();

		driver.navigate().to("https://localhost:8443/health/#home");

		implicitWait(60, TimeUnit.SECONDS);

		// calendar appointment...

		List<WebElement> totalnumberrowdy = driver.findElements(By.xpath("//div[@id='date-data']"));

		int totalr = totalnumberrowdy.size();

		boolean cond = false;

		for (int i = 1; i <= totalr; i++) {
			int a = 1 + i;
			WebElement ss = driver.findElement(By.xpath("//div[@id='date-data'][" + i + "]/div[2]/div[2]/div"));
			if (ss.getText().equals("Doctor/User not working")) {

				WebElement abcd = driver.findElement(By.xpath("(//span[@id='editCalendar'])[" + a + "]"));
				visbility(driver, abcd, 60);
				actions("click", abcd);
				sleep(3000);
				WebElement checkbx = driver.findElement(By.xpath("(//input[@id='is-working-day'])[1]"));
				visbility(driver, checkbx, 60);

				actions("click", checkbx);
				WebElement ampm = driver.findElement(By.xpath("(//div[@id='thru-ampm'])[1]"));
				visbility(driver, ampm, 60);
				actions("click", ampm);
				driver.findElement(By.xpath("(//div[@id='save-btn'])[1]")).click();
				sleep(5000);

			}

			// represent total in a part..
			List<WebElement> rchange = driver
					.findElements(By.xpath("(//div[@id='date-data'][" + i + "]/div[2]/div/div[1]/div[1]/div[1])"));
			int avaiable = rchange.size();

			for (int b = 1; b <= avaiable; b++) {

				WebElement tp = driver.findElement(
						// represent the row change and time..
						By.xpath("(//div[@id='date-data'][" + i + "]/div[2]/div[" + b + "]/div[1]/div[1]/div[1])"));

				String tr = tp.getText();
				boolean trp = tp.isDisplayed();

				// the kpid ..
				WebElement kp = driver.findElement(
						By.xpath("(//div[@id='date-data'])[" + i + "]/div[2]/div[" + b + "]/div/div[2]/span[2]"));

				if (kp.getText().isEmpty() && tp.isDisplayed()) {

					cond = true;
					visbility(driver, tp, 60);
					javascriptclick(tp);

					WebElement qrs = driver.findElement(By.xpath("(//input[@id='AppointmentPatientName'])[" + i + "]"));
					visbility(driver, qrs, 60);
					qrs.sendKeys(hkpid);
					break;
				}

			}
			if (cond == true) {
				sleep(2000);
				implicitWait(60, TimeUnit.SECONDS);
				WebElement choosepatient = driver
						.findElement(By.xpath("(//td[text()='" + hkpid + "'])[2]//parent::td"));
				visbility(driver, choosepatient, 60);
				ww.until(ExpectedConditions.elementToBeClickable(choosepatient));
				actions("click", choosepatient);

				WebElement ut = driver.findElement(By.xpath("(//select[@id='triage-appointment'])[" + i + "]"));
				visbility(driver, ut, 60);
				// ut.click();
				dropDown("text", ut, "Emergency");

				WebElement qt = driver.findElement(By.xpath("(//textarea[@id='description'])[" + i + "]"));
				visbility(driver, qt, 60);
				qt.sendKeys("no worries...");
				WebElement utt = driver.findElement(By.xpath("(//button[@id='statusId_dropdown'])[" + i + "]"));
				clickble(driver, utt, 60);
				j.executeScript("arguments[0].click();", utt);

				List<WebElement> lop = driver.findElements(By.xpath("(//ul[@id='statusIdDropdown'])[" + i + "]/li"));
				for (WebElement w : lop) {
					if (w.getText().trim().equals("In Progress")) {
						w.click();
						break;
					}

				}

				WebElement vcv = driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]"));
				visbility(driver, vcv, 60);
				clickble(driver, vcv, 60);
				j.executeScript("arguments[0].click();", vcv);

				WebElement pip = driver.findElement(By.xpath("//span[text()='" + hkpid + "']"));
				visbility(driver, pip, 60);
				j.executeScript("arguments[0].click();", pip);

				sleep(2000);
				WebElement wtw = driver.findElement(By.xpath("(//span[@id='del-btn'])[" + i + "]"));
				clickble(driver, wtw, 60);
				j.executeScript("arguments[0].click();", wtw);

				WebElement delapp = driver
						.findElement(By.xpath("//div[@id='AppointmentCreateMessage']/div[2]/div[2]/button[2]"));
				clickble(driver, delapp, 60);
				javascriptclick(delapp);

				/*
				 * List<WebElement> rtr = driver .findElements(By.xpath(
				 * "(//span[@id='del-btn'])[1]//following::ul[1]/li/div/div[2]/span"));
				 */
				/*
				 * for (WebElement web : rtr) { if (web.getAttribute("id").equals("yes-btn")) {
				 * // System.out.println("yes it is deleted"); implicitWait(30,
				 * TimeUnit.SECONDS); actions("click", web);
				 * 
				 * break; }
				 * 
				 * }
				 */
				sleep(5000);

				break;
			}
		}

		WebElement fdf = driver.findElement(By.xpath("(//input[@id='patientPartyName'])[3]"));
		visbility(driver, fdf, 60);
		fdf.sendKeys(hkpid);

		implicitWait(50, TimeUnit.SECONDS);
		WebElement cli = driver.findElement(By.xpath("(//td[text()='" + hkpid + "'])[2]"));
		visbility(driver, cli, 60);
		clickble(driver, cli, 60);
		javascriptclick(cli);
		sleep(2000);
		ScriptExecutor(pom.getInstanceNewPatient().deletePatient);
		visbility(driver, pom.getInstanceNewPatient().deletePatient, 60);
		click(pom.getInstanceNewPatient().deletePatient);

		WebElement c = driver.findElement(By.xpath("(//button[text()='Delete'])[1]"));
		visbility(driver, c, 65);
		click(c);
		sleep(3000);
	}

	@Test(priority = 1)
	private void PatientModule() throws InterruptedException {
		sleep(2000);

		WebElement pit = driver.findElement(By.xpath("//td[text()='Patient']"));
		visbility(driver, pit, 60);
		j.executeScript("arguments[0].click();", pit);

		implicitWait(60, TimeUnit.SECONDS);
		WebElement ata = driver.findElement(By.xpath("(//button[@title='Add new Patient'])[1]"));
		visbility(driver, ata, 60);
		j.executeScript("arguments[0].click();", ata);
		visbility(driver, pom.getInstanceNewPatient().firstName, 60);
		sendkeys(pom.getInstanceNewPatient().firstName, "sam");
		visbility(driver, pom.getInstanceNewPatient().lastname, 60);
		sendkeys(pom.getInstanceNewPatient().lastname, "n");
		visbility(driver, pom.getInstanceNewPatient().clickGenderIcon, 60);
		click(pom.getInstanceNewPatient().clickGenderIcon);

		List<WebElement> genders = driver.findElements(By.xpath("(//ul[@id='genderDropdown'])[1]/li"));
		for (WebElement opt : genders) {

			if (opt.getText().equals("Male")) {

				driver.findElement(By.xpath("(//ul[@id='genderDropdown'])[1]/li")).click();

			}
			break;
		}

		// Acc gets Created..
		visbility(driver, pom.getInstanceNewPatient().CreatePatient, 60);
		click(pom.getInstanceNewPatient().CreatePatient);
		sleep(2000);
		WebElement es = driver.findElement(By.xpath("//td[@id='val-kpid']"));
		visbility(driver, es, 60);
		kpid = es.getText();
		System.out.println(kpid);

		WebElement abcd = driver.findElement(By.xpath("//td[text()='Patient']"));
		visbility(driver, abcd, 60);
		abcd.click();
		WebElement s = driver.findElement(By.xpath("(//input[@id='patientPartyName'])[2]"));
		visbility(driver, s, 60);
		s.sendKeys(kpid);

		WebElement ki = driver.findElement(By.xpath("//div[text()=" + "'" + kpid + "']"));
		visbility(driver, ki, 60);
		javascriptclick(ki);

		sleep(2000);

		// med info..

		/*
		 * WebElement ed =
		 * driver.findElement(By.xpath("//div[@id='basicinfo-div']/div/span[2]"));
		 * ww.until(ExpectedConditions.elementToBeClickable(ed)); actions("click", ed);
		 * 
		 * WebElement fname = driver.findElement(By.
		 * xpath("//span[text()='Medical Info ']//following::div[2]/input[2]"));
		 * fname.clear(); fname.sendKeys("Rolls"); WebElement lname =
		 * driver.findElement(By.
		 * xpath("//span[text()='Medical Info ']//following::div[2]/input[3]"));
		 * lname.clear(); lname.sendKeys("Royals");
		 */

		/*
		 * driver.findElement(By.
		 * xpath("//span[text()='Medical Info ']//following::div[2]/input[4]")).click();
		 * sleep(4000);
		 * 
		 * WebElement mnth =
		 * driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		 * mnth.click(); Select s = new Select(mnth); s.selectByIndex(5);
		 * 
		 * WebElement yr =
		 * driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")); Select
		 * ss = new Select(yr); ss.selectByIndex(9);
		 */
		/*
		 * List<WebElement> bldgrp = driver .findElements(By.
		 * xpath("//span[text()='Medical Info ']//following::div[3]/ul/li/a")); for
		 * (WebElement web : bldgrp) { if (web.getText().trim().equals("O Positive")) {
		 * web.click(); break; }
		 * 
		 * }
		 * 
		 * driver.findElement(By.
		 * xpath("//span[text()='Medical Info ']//following::div[3]//following::input[1]"
		 * )) .sendKeys("ins12234"); driver.findElement(By.
		 * xpath("//span[text()='Medical Info ']//following::div[3]//following::input[2]"
		 * )) .sendKeys("Riseup sTRONGER"); WebElement sav =
		 * driver.findElement(By.xpath(
		 * "//div[@id='p_previousname']/div//following::div[5]/button[2]"));
		 * ww.until(ExpectedConditions.elementToBeClickable(sav)); javascriptclick(sav);
		 * sleep(2000);
		 */

		// contact info

		WebElement cnin = driver.findElement(
				By.xpath("//div[@id='p-address-phone']/div/div/div[1]/div[1]//following::div[1]/div[1]/div"));
		visbility(driver, cnin, 60);
		javascriptclick(cnin);

		WebElement roy = driver
				.findElement(By.xpath("//div[@id='p-address-phone']/div/div/div[2]/div/div[1]/div[2]/input[4]"));
		visbility(driver, roy, 60);
		roy.sendKeys("no 224 washington");
		WebElement r1 = driver
				.findElement(By.xpath("//div[@id='p-address-phone']/div/div/div[2]/div/div[1]/div[2]/input[5]"));
		visbility(driver, r1, 60);
		r1.sendKeys("arizona");
		WebElement sr = driver
				.findElement(By.xpath("//div[@id='p-address-phone']/div/div/div[2]/div/div[1]/div[2]/input[6]"));
		visbility(driver, sr, 60);
		sr.sendKeys("los");

		WebElement selcntry = driver.findElement(By.xpath("//select[@class='edit-select country']"));
		dropDown("index", selcntry, "03");
		WebElement asd = driver.findElement(By.xpath("(//select[@class='edit-select state_province'])[2]"));
		dropDown("index", asd, "05");

		WebElement r2 = driver
				.findElement(By.xpath("//div[@id='p-address-phone']/div/div/div[2]/div/div[1]/div[2]/input[7]"));
		visbility(driver, r2, 60);

		r2.sendKeys("200786");

		WebElement savecon = driver.findElement(
				By.xpath("//div[@id='p-address-phone']/div/div/div[2]/div/div[2]/div[1]/div[1]/button[2]"));
		visbility(driver, savecon, 60);

		javascriptclick(savecon);

		// alternate contact info...

		WebElement ad = driver.findElement(
				By.xpath("(//div[@id='family-health-patient']/div/div/div[1]/div[1]//following::div[1]/div[1])[1]"));
		visbility(driver, ad, 60);
		javascriptclick(ad);
		WebElement r3 = driver.findElement(By.xpath("(//span[text()='Alternate Contact'])[2]//following::input[1]"));
		visbility(driver, r3, 60);
		r3.sendKeys("Hope");
		WebElement r4 = driver.findElement(By.xpath("(//span[text()='Alternate Contact'])[2]//following::input[2]"));
		visbility(driver, r4, 60);
		r4.sendKeys("2013550367");
		WebElement sa = driver
				.findElement(By.xpath("(//span[text()='Alternate Contact'])[2]//following::div[1]/img[1]"));
		visbility(driver, sa, 60);
		actions("click", sa);

		// care tream member..

		// WebElement crm =
		// driver.findElement(By.xpath("//div[@id='p-care-team-member']/div/div/div[1]/div/div[2]/div"));
		// javascriptclick(crm);

		// patient info..
		WebElement ptin = driver.findElement(By.xpath("//div[@id='patientInfo']/div[1]/span[2]"));
		visbility(driver, ptin, 60);
		actions("click", ptin);
		/*
		 * driver.findElement(By.
		 * xpath("//span[text()='Patient Info ']//following::div[4]/input"))
		 * .sendKeys("abcdef@gmail.com");
		 */
		WebElement r6 = driver.findElement(By.xpath("//label[text()='Occupation']//following::div[1]/input"));
		visbility(driver, r6, 60);
		sendkeys(r6, "tester");
		WebElement savepatinfo = driver
				.findElement(By.xpath("(//span[text()='Patient Info '])//following::div[1]/img[1]"));
		visbility(driver, savepatinfo, 60);
		javascriptclick(savepatinfo);
		driver.navigate().to("https://localhost:8443/health/#list_patient");

		WebElement u1 = driver.findElement(By.xpath("(//button[@id='advanceIcon'])[1]"));
		visbility(driver, u1, 60);
		ww.until(ExpectedConditions.elementToBeClickable(u1));
		j.executeScript("arguments[0].click();", u1);
		sleep(2000);
		WebElement u2 = driver.findElement(By.xpath("(//input[@id='patient-aname'])[1]"));
		visbility(driver, u2, 60);
		u2.sendKeys("Akash n");

		WebElement ellipse = driver.findElement(By.xpath("(//div[@id='list_patient']//following::span[3])[1]"));
		visbility(driver, ellipse, 60);
		ww.until(ExpectedConditions.elementToBeClickable(ellipse));
		j.executeScript("arguments[0].click();", ellipse);

		List<WebElement> u3 = driver
				.findElements(By.xpath("(//div[@id='list_patient']//following::span[3])[1]//following::ul[1]/li"));
		for (WebElement t : u3) {
			if (t.getText().trim().equals("Import")) {

				t.click();
				break;
			}

		}
		sleep(2000);
		List<WebElement> u5 = driver
				.findElements(By.xpath("(//ul[@class='dropdown-menu settings-buttonColor settings-heading'])[3]/li"));
		for (WebElement w : u5) {
			if (w.getText().trim().equals("CCD Import")) {
				w.click();
				break;
			}

		}
		sleep(2000);

		WebElement u6 = driver.findElement(By.xpath("//span[text()='Import Health Record']/following::div[1]/span"));
		visbility(driver, u6, 60);
		j.executeScript("arguments[0].click();", u6);
		sleep(3000);

	}

	@Test(priority = 2)
	private void HealthRec() throws InterruptedException, IOException {

		// implicitWait(60, TimeUnit.SECONDS);
		// click(pom.getInstanceHealthRec().clickHealthRec);
		driver.navigate().to("https://localhost:8443/health/#list_ehr");
		sleep(3000);
		implicitWait(60, TimeUnit.SECONDS);
		WebElement remv = driver
				.findElement(By.xpath("(//div[@id='ehr-search']/div[2]/div[1]/div[1]/div//following::span[4])[1]"));
		visbility(driver, remv, 60);

		javascriptclick(remv);

		List<WebElement> wwe = driver
				.findElements(By.xpath("//div[@id='vvid']//following::ul[2]/li/a/table/tbody/tr/td[2]"));
		for (WebElement web : wwe) {

			if (web.getText().trim().equals(kpid)) {

				visbility(driver, web, 60);
				clickble(driver, web, 60);

				web.click();
				break;
			}

		}
		sleep(2000);

		WebElement r7 = driver.findElement(By.id("newMedicalRecordButton"));
		visbility(driver, r7, 60);
		r7.click();
		WebElement elipse = driver.findElement(By.xpath("(//span[@id='list_patient_needHelp'])[1]/span"));
		visbility(driver, elipse, 60);
		clickble(driver, elipse, 60);
		actions("click", elipse);

		List<WebElement> hh = driver
				.findElements(By.xpath("(//span[@id='list_patient_needHelp'])[1]/span//following::ul[1]/li"));
		for (WebElement web : hh) {
			if (web.getText().trim().equals("Reset Setting")) {
				visbility(driver, web, 60);
				web.click();
				break;
			}

		}
		implicitWait(60, TimeUnit.SECONDS);

		sleep(3000);
		List<WebElement> rowfor = driver.findElements(By.xpath("(//div[@id='cols'])[2]/div"));

		int ehrrow = rowfor.size();

		boolean bl = false;
		for (int i = 1; i <= ehrrow; i++) {

			List<WebElement> ds = driver.findElements(By.xpath("(//div[@id='cols'])[2]/div[" + i + "]/div"));

			for (int b = 1; b < ds.size(); b++) {

				WebElement sf = driver.findElement(By.xpath("(//div[@id='cols'])[2]/div[" + i + "]/div[" + b + "]"));

				if (sf.isDisplayed() == false) {

					bl = true;
					WebElement r8 = driver.findElement(By.xpath("(//button[@id='options-img'])[1]"));
					visbility(driver, r8, 60);
					r8.click();
					List<WebElement> fin = driver.findElements(By.xpath("(//ul[@id='matchKey'])[2]/li/span/a"));
					driver.findElement(By.xpath("(//input[@id='optionsSearch'])[2]")).sendKeys("show");
					implicitWait(60, TimeUnit.SECONDS);

					for (WebElement web : fin) {

						if (web.getText().trim().equals("Show Allergy")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Alert")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Social History")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Family Health")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Symptoms")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Problems")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Vital")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Visit Reason")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Procedure")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Medications")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Test Order")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Note")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Vaccine")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Attach File")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Inpatient")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Referral")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Custom-form")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Goals")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Amendment")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Implantable Devices")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Advance Directives")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Physical Examination")) {

							actions("click", web);

						} else if (web.getText().trim().equals("Show Status")) {

							actions("click", web);

						} else {

							continue;
						}

					}

				}

			}
			if (bl == true) {
				WebElement bb2 = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/button"));
				visbility(driver, bb2, 60);
				bb2.click();
				break;
			}
		}

		for (int i = 1; i <= ehrrow; i++) {

			List<WebElement> qf = driver.findElements(By.xpath("(//div[@id='cols'])[2]/div[" + i + "]/div"));
			for (int j = 1; j <= qf.size(); j++) {

				WebElement gettag = driver
						.findElement(By.xpath("(//div[@id='cols'])[2]/div[" + i + "]/div[" + j + "]"));
				visbility(driver, gettag, 60);

				String tagnames = gettag.getAttribute("id");

				if (tagnames.equals("vital")) {
					sleep(4000);
					implicitWait(30, TimeUnit.SECONDS);

					driver.findElement(By.xpath("(//button[contains(@title,'Add Multiple Vitals')])[1]")).click();

					driver.findElement(By.id("wresult")).sendKeys("55");

					WebElement sel = driver.findElement(By.xpath("(//select[@id='unit'])[1]"));
					ww.until(ExpectedConditions.elementToBeClickable(sel));
					sel.click();
					sleep(2000);
					dropDown("text", sel, "kilograms");

					driver.findElement(By.id("hresult")).sendKeys("7'7");

					WebElement hdrp = driver.findElement(By.xpath("(//select[@id='unit'])[2]"));
					ww.until(ExpectedConditions.elementToBeClickable(hdrp));
					hdrp.click();

					dropDown("text", hdrp, "ft-in");

					WebElement edity = driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]"));
					ww.until(ExpectedConditions.elementToBeClickable(edity));
					javascriptclick(edity);

					sleep(2000);
					WebElement edicon = driver.findElement(By.xpath("(//span[text()='55 kilograms'])[1]"));
					ww.until(ExpectedConditions.elementToBeClickable(edicon));

					actions("click", edicon);
					sleep(2000);
					driver.findElement(By.xpath("//input[@id='wresult']")).clear();
					driver.findElement(By.xpath("//input[@id='wresult']")).sendKeys("59");

					driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]")).click();
					sleep(3000);
					/*
					 * WebElement mv =
					 * driver.findElement(By.xpath("//span[text()='59 kilograms']"));
					 * actions("click", mv); WebElement mm =
					 * driver.findElement(By.xpath("(//span[@id='del-btn'])[1]")); actions("click",
					 * mm); sleep(2000)
					 */;

				} else if (tagnames.equals("visit-reason")) {
					implicitWait(60, TimeUnit.SECONDS);

					WebElement u = driver.findElement(By.xpath("//div[contains(@title,'Add Visit R')]"));

					actions("move to element", u);
					visbility(driver, u, 60);
					javascriptclick(u);
					WebElement y = driver.findElement(By.xpath("//select[@id='triage-type']"));
					visbility(driver, y, 60);
					y.click();

					List<WebElement> ff = driver.findElements(By.xpath("//select[@id='triage-type']/option"));
					for (WebElement w : ff) {
						if (w.getText().trim().equals("Emergency")) {
							visbility(driver, w, 60);
							w.click();
							break;
						}

					}
					sleep(2000);
					WebElement mj = driver
							.findElement(By.xpath("//div[@title='Enter the description of the patient visit']"));
					visbility(driver, mj, 60);
					mj.sendKeys("cold");

					WebElement svg = driver
							.findElement(By.xpath("//div[@id='Visit_ReasonKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, svg, 60);
					//// div[@title='Enter the description of the patient
					//// visit']//following::div[28]/button[2]
					ww.until(ExpectedConditions.elementToBeClickable(svg));
					svg.click();
					sleep(2000);
					WebElement afk = driver.findElement(By.xpath("(//div[text()='cold'])[1]"));
					visbility(driver, afk, 60);
					ww.until(ExpectedConditions.elementToBeClickable(afk));
					actions("click", afk);

					implicitWait(30, TimeUnit.SECONDS);
					WebElement mjj = driver
							.findElement(By.xpath("//div[@title='Enter the description of the patient visit']"));
					visbility(driver, mjj, 60);
					ww.until(ExpectedConditions.elementToBeClickable(mjj));
					mjj.clear();
					mjj.sendKeys("KAASPRO");

					WebElement svg1 = driver
							.findElement(By.xpath("//div[@id='Visit_ReasonKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, svg1, 60);
					svg1.click();

					/*
					 * WebElement delvis = driver
					 * .findElement(By.xpath("//div[@id='Visit_ReasonKpop2']/div[1]/div[2]/span"));
					 * javascriptclick(delvis);
					 */
					sleep(3000);

				} else if (tagnames.equals("alert-allergy")) {

					WebElement add = driver.findElement(By.xpath("//div[contains(@title,'Add Allergy')]"));

					actions("move to element", add);
					visbility(driver, add, 60);
					ww.until(ExpectedConditions.elementToBeClickable(add));
					actions("click", add);
					WebElement se = driver.findElement(By.xpath("//select[@id='codeType']"));
					visbility(driver, se, 60);
					ww.until(ExpectedConditions.elementToBeClickable(se));
					se.click();
					dropDown("text", se, "Food Allergy");

					/* WebElement alcl = */ WebElement fd1 = driver
							.findElement(By.xpath("//div[@id='AllergyKpop2']/div[2]/div[1]/div[2]/div[2]/input"));
					visbility(driver, fd1, 60);
					fd1.sendKeys("food1");

					sleep(2000);
					WebElement alrgy = driver.findElement(By.xpath("//input[@placeholder='Reaction']"));
					visbility(driver, alrgy, 60);
					alrgy.sendKeys("stomach pain");

					WebElement rer = driver.findElement(By.xpath("//div[@id='AllergyKpop2']/div[2]/div[2]/div/button"));
					visbility(driver, rer, 60);
					ww.until(ExpectedConditions.elementToBeClickable(rer));

					javascriptclick(rer);

					sleep(3000);
					List<WebElement> wtw = driver.findElements(By.xpath("//div[@id='smore-btn']/ul/li"));
					for (WebElement w : wtw) {
						if (w.getText().trim().equals("Show Severity")) {
							w.click();
							break;
						}

					}
					sleep(2000);

					javascriptclick(rer);

					for (WebElement w : wtw) {
						if (w.getText().trim().equals("Show Status")) {
							visbility(driver, rer, 60);
							w.click();
							break;
						}

					}
					WebElement s = driver.findElement(By.xpath("//select[@id='severity']"));

					dropDown("text", s, "Mild");
					sleep(2000);
					WebElement ss = driver.findElement(By.xpath("//select[@id='status']"));

					dropDown("text", ss, "Inactive");

					WebElement cl2 = driver.findElement(By.xpath("//div[@id='saveadd-btn']/button"));
					visbility(driver, cl2, 60);
					cl2.click();
					List<WebElement> sss = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : sss) {
						if (w.getText().trim().equals("Save")) {
							visbility(wd, w, 60);
							w.click();
							break;
						}

					}
					sleep(2000);
					WebElement mk = driver.findElement(By.xpath("//span[text()='food1']"));
					visbility(driver, mk, 60);
					ww.until(ExpectedConditions.elementToBeClickable(mk));
					actions("click", mk);
					sleep(2000);
					WebElement jk = driver
							.findElement(By.xpath("//div[@id='AllergyKpop2']/div[2]/div[1]/div[2]/div[2]/input"));
					visbility(driver, jk, 60);
					jk.clear();
					jk.sendKeys("st");
					sleep(2000);
					WebElement scq = driver.findElement(By.xpath("//div[text()='Strawberry ']"));
					visbility(driver, scq, 60);
					ww.until(ExpectedConditions.elementToBeClickable(scq));
					actions("click", scq);

					WebElement cl5 = driver.findElement(By.xpath("//div[@id='saveadd-btn']/button"));
					visbility(driver, cl5, 60);
					cl5.click();
					List<WebElement> ssss = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : ssss) {
						if (w.getText().trim().equals("Save")) {
							visbility(driver, w, 60);
							w.click();
							break;
						}

					}

					sleep(3000);
					/*
					 * WebElement cc = driver.findElement(By.xpath("//span[text()='Strawberry ']"));
					 * actions("click", cc); sleep(3000);
					 * driver.findElement(By.xpath("(//span[@id='del-btn'])[1]")).click();
					 * sleep(4000);
					 */

					// Social history....

					// WebElement sh = driver.findElement(By.xpath("//div[contains(@title,'Add
					// Social History')]"));

					/*
					 * WebElement sctag = driver.findElement(By.xpath(
					 * "//div[contains(@title,'Add Social History')]//parent::div[1]//parent::div//parent::div//parent::div//parent::div[@id='social-history']"
					 * ));
					 */

					WebElement sh = ww.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//div[contains(@title,'Add Social History')]")));
					actions("move to element", sh);
					visbility(driver, sh, 60);
					actions("click", sh);
					sleep(2000);
					WebElement ssc = driver.findElement(By.xpath("//select[@id='habitType']"));
					visbility(driver, ssc, 60);
					ssc.click();
					dropDown("text", ssc, "Alcohol");
					WebElement hbt = driver.findElement(By.xpath("//select[@id='habitType']//following::div[3]/input"));
					visbility(driver, hbt, 60);
					hbt.sendKeys("social histry");

					WebElement hbt2 = driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]"));
					visbility(driver, hbt2, 60);
					hbt2.click();
					sleep(2000);
					WebElement jj = driver.findElement(By.xpath("//div[text()='( Alcohol )']"));
					visbility(driver, jj, 60);

					actions("click", jj);
					sleep(2000);
					WebElement hbt4 = driver
							.findElement(By.xpath("//select[@id='habitType']//following::div[3]/input"));
					visbility(driver, hbt4, 60);
					hbt4.clear();
					// .clear();
					WebElement kspr = driver
							.findElement(By.xpath("//select[@id='habitType']//following::div[3]/input"));
					visbility(driver, kspr, 60);
					kspr.sendKeys("Kaaspro");
					WebElement hbt5 = driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]"));
					visbility(driver, hbt5, 60);
					hbt5.click();

					sleep(3000);

					/*
					 * WebElement shdel =
					 * driver.findElement(By.xpath("(//span[@id='del-btn'])[1]"));
					 * javascriptclick(shdel); sleep(4000);
					 */

					// Family Health...

					WebElement a = driver.findElement(By.xpath("//div[contains(@title,'Add Family Health')]"));

					actions("move to element", a);
					visbility(driver, a, 60);
					ww.until(ExpectedConditions.elementToBeClickable(a));
					actions("click", a);
					WebElement ee = driver.findElement(By.xpath("//div[@id='Family_HealthKpop2']/div[2]/div/select"));
					visbility(driver, ee, 60);
					ee.click();
					dropDown("text", ee, "Half Brother");

					WebElement fh = driver.findElement(By.xpath(
							"//div[@id='Family_HealthKpop2']/div[2]/div/select//following::div[1]/div[2]/input"));
					visbility(driver, fh, 60);
					fh.sendKeys("24781");
					List<WebElement> fhkpop = driver
							.findElements(By.xpath("//div[@id='Family_HealthKpop2']/div[2]/ul/li/a/div/small"));
					for (WebElement web : fhkpop) {
						if (web.getText().trim().equals("ICD10 : F40.2 | SNOMED : 247810008")) {
							visbility(driver, web, 60);
							web.click();
							break;
						}

					}
					/*
					 * WebElement aq =
					 * driver.findElement(By.xpath("//div[text()='Fear of wetting self (finding)']")
					 * ); explicitWait(30, aq); actions("click", aq);
					 */
					sleep(2000);

					WebElement atf = driver.findElement(By.xpath("//button[@id='btnSaveAdd']"));
					visbility(driver, atf, 60);
					javascriptclick(atf);
					List<WebElement> rr = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : rr) {

						if (w.getText().trim().equals("Save")) {
							visbility(driver, w, 60);
							w.click();
							break;
						}

					}

					/*
					 * WebElement e = driver.findElement(By.xpath(
					 * "(//div[contains(@class,'col-xs-12 highlight-hover data-current-visit data-changed')])[2]/div/span"
					 * )); actions("click", e); WebElement delfam = driver
					 * .findElement(By.xpath("//div[@id='Family_HealthKpop2']/div/div[2]/span[1]"));
					 * javascriptclick(delfam); sleep(5000);
					 */
					sleep(4000);

					// Alert...
					// WebElement ip = driver.findElement(By.xpath("//div[contains(@title,'Add
					// Alert')]"));
					WebElement ip = ww.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//div[contains(@title,'Add Alert')]")));

					actions("move to element", ip);
					visbility(driver, ip, 60);
					actions("click", ip);

					WebElement fh2 = driver
							.findElement(By.xpath("//div[@title='Enter the description of the alert ']"));
					visbility(driver, fh2, 60);
					fh2.sendKeys("hello");
					WebElement r = driver.findElement(By.xpath("//select[@id='visibility']"));

					r.click();
					dropDown("text", r, "Only to me");
					WebElement hbt6 = driver.findElement(By.xpath("//div[@id='AlertKpop2']/div[2]/div[2]/button[2]"));// .click();
					visbility(driver, hbt6, 60);
					hbt6.click();
					sleep(2000);
					WebElement inner = ww
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='hello']")));
					// WebElement inner = driver.findElement(By.xpath("//div[text()='hello']"));
					visbility(driver, inner, 60);
					ww.until(ExpectedConditions.elementToBeClickable(inner));
					actions("click", inner);
					sleep(3000);

					WebElement alrt = driver
							.findElement(By.xpath("//div[@title='Enter the description of the alert ']"));// .clear();
					visbility(driver, alrt, 60);
					alrt.clear();
					WebElement alrt2 = driver
							.findElement(By.xpath("//div[@title='Enter the description of the alert ']"));
					visbility(driver, alrt2, 60);
					alrt2.sendKeys("wELCOME");
					WebElement alrt3 = driver.findElement(By.xpath("//div[@id='AlertKpop2']/div[2]/div[2]/button[2]"));// .click();
					visbility(driver, alrt3, 60);
					click(alrt3);
					/*
					 * WebElement delalert =
					 * driver.findElement(By.xpath("//div[@id='AlertKpop2']/div/div[2]/span[1]"));
					 * javascriptclick(delalert); sleep(3000);
					 */
					sleep(3000);
				} else if (tagnames.equals("vaccine")) {

					WebElement k = driver.findElement(By.xpath("//div[contains(@title,'Add Vaccine')]"));
					actions("move to element", k);
					visbility(driver, k, 60);
					ww.until(ExpectedConditions.elementToBeClickable(k));
					actions("click", k);
					WebElement x = driver.findElement(By.xpath("//select[@id='date-type']"));
					visbility(driver, x, 60);
					x.click();
					dropDown("text", x, "Taken Date");
					WebElement vcne = driver.findElement(By.id("vaccine-cvx"));
					visbility(driver, vcne, 60);
					vcne.sendKeys("kaaspro");

					WebElement vcne2 = driver.findElement(By.id("vaccineName"));
					visbility(driver, vcne2, 60);
					vcne2.sendKeys("TT");
					WebElement sv = driver
							.findElement(By.xpath("//div[@id='VaccineKpop2']/div[2]/div[3]/button[@id='accept-btn']"));
					visbility(driver, sv, 60);
					javascriptclick(sv);
					sleep(3000);
					WebElement l = driver.findElement(By.xpath("//div[text()='TT']"));
					visbility(driver, l, 60);
					actions("click", l);

					WebElement vcna = driver.findElement(By.id("vaccineName"));// .clear();
					visbility(driver, vcna, 60);
					vcna.clear();
					WebElement vcna1 = driver.findElement(By.id("vaccineName"));// .sendKeys("TT INJECTION");
					visbility(driver, vcna1, 60);
					sendkeys(vcna1, "TT INJECTION");
					implicitWait(60, TimeUnit.SECONDS);
					/*
					 * WebElement delvc = driver .findElement(By.xpath(
					 * "(//div[@id='VaccineKpop2']//following::div[1]/span[1])[1]"));
					 * javascriptclick(delvc);
					 */
					WebElement vcc = driver
							.findElement(By.xpath("//div[@id='VaccineKpop2']/div[2]/div[3]/button[@id='accept-btn']"));
					visbility(driver, vcc, 60);
					click(vcc);

					sleep(3000);

				} else if (tagnames.equals("implantable-devices")) {

					WebElement b = driver.findElement(By.xpath("//div[contains(@title,'Add Implantable')]"));
					actions("move to element", b);
					visbility(driver, b, 60);
					actions("click", b);
					WebElement impl = driver.findElement(By.id("udi"));// .sendKeys("(01)00844588003288");
					visbility(driver, impl, 60);
					sendkeys(impl, "(01)00844588003288");
					WebElement svimpl = driver.findElement(By.xpath("//button[@id='verify-btn']"));// .click();
					visbility(driver, svimpl, 60);
					click(svimpl);
					sleep(3000);
					driver.findElement(By.id("deviceactive"));

					WebElement a1 = driver.findElement(By.id("deviceNote"));
					visbility(driver, a1, 60);
					sleep(1000);
					a1.sendKeys("hello123");
					WebElement savimp = driver
							.findElement(By.xpath("//div[@id='Implantable_DevicesKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, savimp, 60);
					javascriptclick(savimp);

					sleep(2000);
					WebElement a2 = driver
							.findElement(By.xpath("//div[text()='Coated femoral stem prosthesis, modular']"));
					visbility(driver, a2, 60);
					ww.until(ExpectedConditions.elementToBeClickable(a2));
					actions("click", a2);
					WebElement a26 = driver.findElement(By.id("deviceNote"));
					ScriptExecutor(a26);
					visbility(driver, a26, 60);
					a26.clear();
					a26.sendKeys("JUst Rise up..");
					WebElement savimp1 = driver
							.findElement(By.xpath("//div[@id='Implantable_DevicesKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, savimp1, 60);
					javascriptclick(savimp1);

					/*
					 * driver.findElement(By.xpath("//div[@title='Remove UDI']")).click();
					 * WebElement delimp = driver .findElement(By.xpath(
					 * "//div[@id='Implantable_DevicesKpop2']/div/div[2]/span[1]"));
					 * javascriptclick(delimp);
					 */
					sleep(3000);

				} else if (tagnames.equals("amendment")) {

					WebElement d = driver.findElement(By.xpath("//div[contains(@title,'Add Amendment')]"));

					actions("move to element", d);
					visbility(driver, d, 60);
					clickble(driver, d, 25);
					actions("click", d);
					WebElement s1 = driver.findElement(By.xpath("//select[@id='source']"));
					visbility(driver, s1, 60);
					s1.click();
					dropDown("text", s1, "Patient");
					WebElement amd = driver
							.findElement(By.xpath("//div[@id='AmendmentKpop2']/div[2]/div/div[2]/div[2]/input"));
					visbility(driver, amd, 60);
					sendkeys(amd, "Akash");// .sendKeys("Akash");

					WebElement s2 = driver.findElement(By.xpath("//select[@id='status']"));
					visbility(driver, s2, 60);
					s2.click();
					dropDown("text", s2, "Accept");
					WebElement vs = driver.findElement(By.xpath("//input[@id='reason']"));
					visbility(driver, vs, 60);
					sendkeys(vs, "whats up...");// .sendKeys("whats up...");
					WebElement svamen = driver
							.findElement(By.xpath("//div[@id='AmendmentKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, svamen, 60);

					javascriptclick(svamen);
					sleep(3000);
					WebElement ac = driver.findElement(By.xpath("//div[text()='Accepted : whats up...']"));
					visbility(driver, ac, 60);
					actions("click", ac);
					sleep(2000);
					WebElement clr = driver.findElement(By.xpath("//input[@id='reason']"));
					visbility(driver, clr, 60);
					clear(clr);// .clear();

					WebElement ips = driver.findElement(By.xpath("//input[@id='reason']"));
					visbility(driver, ips, 60);

					sendkeys(ips, "warrior");// .sendKeys("warrior");
					WebElement iis = driver.findElement(By.xpath("//input[@id='reason']"));// .sendKeys("WAR BEGINS");
					visbility(driver, iis, 60);
					sendkeys(iis, "WAR BEGINS");
					WebElement svamen1 = driver
							.findElement(By.xpath("//div[@id='AmendmentKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, svamen1, 60);
					javascriptclick(svamen1);
					// driver.findElement(By.xpath("//div[@id='AmendmentKpop2']/div[1]/div[2]/span[1]")).click();
					sleep(3000);

				} else if (tagnames.equals("diagnosis")) {

					WebElement a3 = driver.findElement(By.xpath("//div[contains(@title,'Add Problems')]"));
					actions("move to element", a3);
					visbility(driver, a3, 60);
					ww.until(ExpectedConditions.elementToBeClickable(a3));
					actions("click", a3);
					sleep(2000);

					WebElement ct = ww.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//div[@id='ProblemsKpop2']/div[2]/div/div[1]/div[2]/input")));
					visbility(driver, ct, 60);
					// driver.findElement(By.xpath("//div[@id='ProblemsKpop2']/div[2]/div/div[1]/div[2]/input"))
					ct.sendKeys("Cleft uvula");

					sleep(3000);
					WebElement prbcl = driver.findElement(By.xpath("//small[text()='ICD10 : Q35.7 | SNOMED : --']"));
					visbility(driver, prbcl, 60);
					ww.until(ExpectedConditions.elementToBeClickable(prbcl));
					actions("click", prbcl);
					sleep(2000);

					WebElement gg = driver.findElement(By.xpath("//button[@id='btnSaveAdd']"));
					visbility(driver, gg, 60);
					ww.until(ExpectedConditions.elementToBeClickable(gg));

					javascriptclick(gg);

					List<WebElement> a5 = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : a5) {
						if (w.getText().trim().equals("Save")) {
							visbility(driver, w, 60);
							w.click();
							break;
						}

					}
					sleep(3000);
					WebElement a6 = driver.findElement(By.xpath("//div[text()='Cleft uvula']"));
					visbility(driver, a6, 60);
					ww.until(ExpectedConditions.elementToBeClickable(a6));
					actions("click", a6);
					sleep(3000);
					WebElement clr = driver.findElement(
							By.xpath("//div[@id='ProblemsKpop2']/div[2]/div[1]/div[1]/div[2]//following::div[2]"));
					visbility(driver, clr, 60);
					ww.until(ExpectedConditions.elementToBeClickable(clr));
					javascriptclick(clr);

					WebElement ljv = ww.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//div[@id='ProblemsKpop2']/div[2]/div/div[1]/div[2]/input")));
					// driver.findElement(By.xpath("//div[@id='ProblemsKpop2']/div[2]/div/div[1]/div[2]/input"))
					visbility(driver, ljv, 60);
					ljv.sendKeys("test");
					sleep(2000);
					List<WebElement> prbdrp = driver
							.findElements(By.xpath("//div[@id='ProblemsKpop2']/div[2]/ul/li/a/div/span"));
					for (WebElement web : prbdrp) {

						if (web.getText().trim().equals("Malignant neoplasm of testis")) {
							visbility(driver, web, 60);
							web.click();
							break;
						}

					}

					/*
					 * WebElement azn =
					 * driver.findElement(By.xpath("//div[text()='Malignant neoplasm of testis']"));
					 * implicitWait(20, TimeUnit.SECONDS); actions("click", azn);
					 */

					WebElement gg1 = driver.findElement(By.xpath("//button[@id='btnSaveAdd']"));
					visbility(driver, gg1, 60);

					javascriptclick(gg1);
					sleep(3000);
					List<WebElement> a56 = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : a56) {
						if (w.getText().trim().equals("Save")) {
							visbility(driver, w, 60);
							w.click();
							break;
						}

					}

					/*
					 * WebElement del = driver.findElement(By.xpath(
					 * "//div[@id='ProblemsKpop2']/div[1]/div[2]/span[1]"));
					 * js.executeScript("arguments[0].click();", del);
					 */
					sleep(3000);

				} else if (tagnames.equals("symptom")) {

					WebElement a7 = driver.findElement(By.xpath("//div[contains(@title,'Add Symptoms')]"));

					actions("move to element", a7);
					visbility(driver, a7, 60);

					actions("click", a7);
					WebElement prsend = driver
							.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div/div[2]/div[2]/input"));
					visbility(driver, prsend, 60);
					sendkeys(prsend, "R10.12:");// .sendKeys("R10.12:");
					implicitWait(30, TimeUnit.SECONDS);
					WebElement clsymi = driver
							.findElement(By.xpath("//div[contains(text(),'R10.12: Left upper quadrant pain')]"));
					visbility(driver, clsymi, 60);
					ww.until(ExpectedConditions.elementToBeClickable(clsymi));
					actions("click", clsymi);

					sleep(2000);
					WebElement sydes = ww.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//div[@id='SymptomsKpop2']/div[2]/div/div[3]/div[2]/input")));
					// driver.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div/div[3]/div[2]/input"))
					visbility(driver, sydes, 60);
					sydes.sendKeys("fever");
					WebElement svsymp = driver
							.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, svsymp, 60);
					javascriptclick(svsymp);
					sleep(3000);
					WebElement a8 = driver
							.findElement(By.xpath("//div[contains(text(),'R10.12: Left upper quadrant pain')]"));
					visbility(driver, a8, 60);
					ww.until(ExpectedConditions.elementToBeClickable(a8));
					actions("click", a8);
					WebElement smp1 = driver
							.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div/div[3]/div[2]/input"));
					visbility(driver, smp1, 60);
					clear(smp1);// .clear();

					sleep(2000);
					WebElement smp2 = driver
							.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div/div[3]/div[2]/input"));
					visbility(driver, smp2, 60);
					sendkeys(smp2, "covid");// .sendKeys("covid");

					WebElement svsymp1 = driver
							.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, svsymp1, 60);
					ww.until(ExpectedConditions.elementToBeClickable(svsymp1));
					javascriptclick(svsymp1);
					// driver.findElement(By.xpath("//div[@id='SymptomsKpop2']/div/div[2]/span[1]")).click();
					sleep(4000);

				} else if (tagnames.equals("procedure")) {

					WebElement b1 = driver.findElement(By.xpath("//div[contains(@title,'Add Procedure')]"));
					actions("move to element", b1);
					visbility(driver, b1, 60);
					ww.until(ExpectedConditions.elementToBeClickable(b1));
					actions("click", b1);

					WebElement b2 = driver.findElement(By.xpath("//select[@id='codeType']"));
					visbility(driver, b2, 60);
					ww.until(ExpectedConditions.elementToBeClickable(b2));
					b2.click();
					dropDown("text", b2, "SNOMED CT");
					WebElement ers = ww.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//div[@id='ProcedureKpop2']/div[2]/div[1]/div[1]/div[2]/input")));
					visbility(driver, ers, 60);
					// driver.findElement(By.xpath("//div[@id='ProcedureKpop2']/div[2]/div[1]/div[1]/div[2]/input"))
					ers.sendKeys("test");
					sleep(2000);
					WebElement clprcd = driver
							.findElement(By.xpath("//b[text()='Cytomegalovirus antigen test (procedure)']"));
					visbility(driver, clprcd, 60);

					ww.until(ExpectedConditions.elementToBeClickable(clprcd));
					sleep(2000);
					actions("click", clprcd);
					WebElement smp3 = driver
							.findElement(By.xpath("//div[@id='ProcedureKpop2']/div[2]/div/div[2]/div[2]/input"));
					visbility(driver, smp3, 60);
					sendkeys(smp3, "gdgdg");// .sendKeys("gdgdg");

					WebElement svprcd1 = driver.findElement(By.id("btnSaveAdd"));
					visbility(driver, svprcd1, 60);
					click(svprcd1);// .click();
					sleep(2000);
					List<WebElement> b6 = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : b6) {
						if (w.getText().trim().equals("Save")) {
							visbility(driver, w, 60);
							w.click();
							
							break;
						}

					}
					sleep(3000);
					WebElement b7 = driver.findElement(
							By.xpath("//div[text()='134287002: Cytomegalovirus antigen test (procedure)']"));
					visbility(driver, b7, 60);
					javascriptclick(b7);
					// actions("click", b7);
					sleep(2000);
					WebElement clrprc = driver
							.findElement(By.xpath("//div[@id='ProcedureKpop2']/div[2]/div/div[2]/div[2]/input"));
					visbility(driver, clrprc, 60);
					clear(clrprc);// .clear();
					WebElement prcd3 = driver
							.findElement(By.xpath("//div[@id='ProcedureKpop2']/div[2]/div/div[2]/div[2]/input"));
					visbility(driver, prcd3, 60);

					sendkeys(prcd3, "LARA");// .sendKeys("LARA");

					WebElement prcd4 = driver.findElement(By.id("btnSaveAdd"));
					visbility(driver, prcd4, 60);
					click(prcd4);// .click();
					sleep(3000);
					List<WebElement> b64 = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : b64) {
						if (w.getText().trim().equals("Save")) {
							visbility(driver, w, 60);
							w.click();
							break;
						}

					}

					// driver.findElement(By.xpath("//div[@id='ProcedureKpop2']/div[1]/div[2]/span[1]")).click();
					sleep(4000);

				} else if (tagnames.equals("goals")) {
					implicitWait(30, TimeUnit.SECONDS);
					WebElement b8 = driver.findElement(By.xpath("//div[contains(@title,'Add Goals')]"));
					actions("move to element", b8);
					visbility(driver, b8, 60);
					ww.until(ExpectedConditions.elementToBeClickable(b8));
					actions("click", b8);
					sleep(2000);
					WebElement gl1 = driver.findElement(By.xpath("//div[@title='Enter goal']"));
					visbility(driver, gl1, 60);
					sendkeys(gl1, "goal1");// .sendKeys("goal1");

					WebElement gl2 = driver
							.findElement(By.xpath("//div[@id='GoalsKpop2']/div[2]/div[1]/div[2]/div/input"));// .click();
					visbility(driver, gl2, 60);
					click(gl2);
					sleep(2000);
					WebElement month = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));

					implicitWait(30, TimeUnit.SECONDS);

					dropDown("index", month, "09");

					WebElement uyr = driver.findElement(By.xpath("//a[text()='14']"));// .click();
					visbility(driver, uyr, 60);
					click(uyr);
					sleep(2000);
					WebElement hk = driver.findElement(By.xpath("//div[@id='GoalsKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, hk, 60);
					javascriptclick(hk);
					sleep(3000);
					WebElement b10 = driver.findElement(By.xpath("//div[text()='goal1']"));
					visbility(driver, b10, 60);
					ww.until(ExpectedConditions.elementToBeClickable(b10));
					actions("click", b10);
					implicitWait(60, TimeUnit.SECONDS);

					WebElement ft = driver.findElement(By.xpath("//div[@title='Enter goal']"));// .clear();
					visbility(driver, ft, 60);
					clear(ft);
					WebElement glr1 = driver.findElement(By.xpath("//div[@title='Enter goal']"));
					// .sendKeys("HELLO THIS IS GOALS MODULE.");
					visbility(driver, glr1, 60);
					sendkeys(glr1, "HELLO THIS IS GOALS MODULE.");
					WebElement hk1 = driver.findElement(By.xpath("//div[@id='GoalsKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, hk1, 60);
					javascriptclick(hk1);

					/*
					 * WebElement jl =
					 * driver.findElement(By.xpath("//div[@id='GoalsKpop2']/div/div[2]/span[1]"));
					 * 
					 * javascriptclick(jl);
					 */
					sleep(4000);

				} else if (tagnames.equals("directives")) {

					WebElement c1 = driver.findElement(By.xpath("//div[contains(@title,'Add Advance directives')]"));
					actions("move to element", c1);
					visbility(driver, c1, 60);
					ww.until(ExpectedConditions.elementToBeClickable(c1));
					actions("click", c1);

					WebElement c2 = driver.findElement(By.xpath("//div[@id='Assessment-div']/select"));
					visbility(driver, c2, 60);
					c2.click();
					dropDown("text", c2, "Assessment");
					WebElement cc1 = driver.findElement(By.xpath("//input[@id='directive_desc']"));
					visbility(driver, cc1, 60);
					sendkeys(cc1, "lets hope");// .sendKeys("lets hope");
					WebElement cc2 = driver
							.findElement(By.xpath("//div[@id='Advance_DirectivesKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, cc2, 60);
					click(cc2);// .click();
					sleep(3000);
					WebElement c4 = driver.findElement(By.xpath("//div[text()='lets hope']"));
					visbility(driver, c4, 60);
					actions("click", c4);
					sleep(2000);
					WebElement cc5 = driver.findElement(By.xpath("//input[@id='directive_desc']"));
					visbility(driver, cc5, 60);
					sendkeys(cc5, "Advance directives");// .sendKeys("Advance directives");
					WebElement cc6 = driver
							.findElement(By.xpath("//div[@id='Advance_DirectivesKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, cc6, 60);
					click(cc6);// .click();

					/*
					 * WebElement deladvfac = driver .findElement(By.xpath(
					 * "//div[@id='Advance_DirectivesKpop2']/div/div[2]/span[1]"));
					 * javascriptclick(deladvfac);
					 */
					sleep(3000);

				} else if (tagnames.equals("status-module")) {

					WebElement c5 = driver.findElement(By.xpath("//div[contains(@title,'Add Status')]"));
					actions("move to element", c5);
					visbility(driver, c5, 60);
					ww.until(ExpectedConditions.elementToBeClickable(c5));
					actions("click", c5);
					WebElement c6 = driver.findElement(By.xpath("(//select[@id='statusType'])[1]"));
					visbility(driver, c6, 60);
					c6.click();
					dropDown("text", c6, "Cognitive status");
					WebElement cc7 = driver.findElement(By
							.xpath("//div[@id='StatusKpop2']/div[2]/div[1]/select[1]//following::div[1]/div[2]/input"));
					visbility(driver, cc7, 60);
					sendkeys(cc7, "test");// .sendKeys("test");
					sleep(2000);
					WebElement clsmd = driver
							.findElement(By.xpath("//div[text()='134374006: Hearing test bilateral abnormality ']"));
					visbility(driver, clsmd, 60);
					sleep(3000);
					ww.until(ExpectedConditions.elementToBeClickable(clsmd));
					actions("click", clsmd);
					WebElement cc8 = driver.findElement(By.xpath("//div[@id='StatusKpop2']/div[2]/div[2]/button[2]"));// .click();
					visbility(driver, cc8, 60);
					click(cc8);
					sleep(4000);
					WebElement c7 = driver
							.findElement(By.xpath("//div[text()='134374006: Hearing test bilateral abnormality']"));
					visbility(driver, c7, 60);
					ww.until(ExpectedConditions.elementToBeClickable(c7));
					actions("click", c7);
					sleep(3000);
					WebElement hjj = driver
							.findElement(By.xpath("//div[@id='StatusKpop2']/div[2]/div[1]/select//following::span[5]"));
					visbility(driver, hjj, 60);
					actions("click", hjj);
					WebElement cc9 = driver.findElement(By
							.xpath("//div[@id='StatusKpop2']/div[2]/div[1]/select[1]//following::div[1]/div[2]/input"));
					visbility(driver, cc9, 60);
					sendkeys(cc9, "yang");// .sendKeys("yang");
					sleep(2000);
					WebElement yng = driver.findElement(By.xpath("//div[text()='370534002: Yang deficiency ']"));
					visbility(driver, yng, 60);
					actions("click", yng);
					WebElement cc10 = driver.findElement(By.xpath("//div[@id='StatusKpop2']/div[2]/div[2]/button[2]"));// .click();
					visbility(driver, cc10, 60);
					click(cc10);
					/*
					 * WebElement delsmd =
					 * driver.findElement(By.xpath("//div[@id='StatusKpop2']/div[1]/div[2]/span[1]")
					 * ); javascriptclick(delsmd);
					 */
					sleep(3000);

				} else if (tagnames.equals("")) {

					/*
					 * WebElement ad1 =
					 * driver.findElement(By.xpath("//div[contains(@title,'Add Test Order')]"));
					 * 
					 * actions("move to element", ad1); actions("click", ad1);
					 * driver.findElement(By.xpath(
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[1]/div[1]/div[2]/input"))
					 * .sendKeys("test"); sleep(4000); WebElement b =
					 * driver.findElement(By.xpath("(//div[text()='test'])")); actions("click", b);
					 */
					/*
					 * List<WebElement> tyr = driver.findElements( By.xpath(
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[1]//following::ul[3]/li/a/div/span")
					 * ); for (WebElement webE : tyr) { if
					 * (webE.getText().contains("LOINC NUM :5802-4")) { webE.click(); break; }
					 * 
					 * }
					 */
					/*
					 * sleep(2000);
					 * 
					 * driver.findElement(By.xpath(
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[2]/div/button")).click();
					 * List<WebElement> chs = driver.findElements( By.xpath(
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[2]/div/button//following::ul[1]/li")
					 * ); for (WebElement w : chs) {
					 * 
					 * if (w.getText().trim().equals("Show Notes")) { w.click(); break; }
					 * 
					 * }
					 */

					/*
					 * sleep(3000); driver.findElement(By.xpath(
					 * 
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[1]/div[1]/div[2]//following::div[1]//following::div[1]/div[2]/input"
					 * )) .sendKeys("ERROR"); driver.findElement( By.xpath(
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[2]/div[1]//following::div[2]/button"
					 * )) .click(); List<WebElement> dss = driver.findElements(By.xpath(
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[2]/div[1]//following::div[2]/button//following::ul[1]/li"
					 * )); for (WebElement w : dss) { if (w.getText().trim().equals("Save")) {
					 * 
					 * w.click(); break; }
					 * 
					 * }
					 */
					/*
					 * sleep(3000); WebElement testorder =
					 * driver.findElement(By.xpath("//div[text()='ERROR']")); actions("click",
					 * testorder);
					 * 
					 * driver.findElement(By.xpath(
					 * 
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[1]/div[1]/div[2]//following::div[1]//following::div[1]/div[2]/input"
					 * )) .clear(); driver.findElement(By.xpath(
					 * 
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[1]/div[1]/div[2]//following::div[1]//following::div[1]/div[2]/input"
					 * )) .sendKeys("Test order..");
					 * 
					 * driver.findElement( By.xpath(
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[2]/div[1]//following::div[2]/button"
					 * )) .click(); List<WebElement> dsss = driver.findElements(By.xpath(
					 * "//div[@id='Test_OrderKpop2']/div[2]/div[2]/div[1]//following::div[2]/button//following::ul[1]/li"
					 * )); for (WebElement w : dsss) { if (w.getText().trim().equals("Save")) {
					 * 
					 * w.click(); break; }
					 * 
					 * }
					 */

					/*
					 * WebElement de = driver.findElement(By.xpath(
					 * "//div[@id='Test_OrderKpop2']/div[1]/div[2]/span[2]")); sleep(2000);
					 * actions("click", de);
					 */
					sleep(3000);

				} else if (tagnames.equals("drug")) {

					WebElement ci = driver.findElement(By.xpath("(//div[contains(@title,'Add Medications')])[1]"));
					actions("move to element", ci);
					visbility(driver, ci, 60);
					ww.until(ExpectedConditions.elementToBeClickable(ci));
					actions("click", ci);
					sleep(2000);
					driver.findElement(By.id("DRUG_NAME")).sendKeys("tata");
					driver.findElement(By.id("STRENGTH")).sendKeys("str");
					driver.findElement(By.id("DISP_QUANTITY")).sendKeys("1");
					driver.findElement(By.id("SIG_DIRECTIONS")).sendKeys("dg");
					// driver.findElement(By.id("startdateiid")).sendKeys("2022-07-20");
					// driver.findElement(By.id("enddateiid")).sendKeys("2022-07-22");
					WebElement dd1 = driver
							.findElement(By.xpath("//div[@id='MedicationsKpop2']/div[2]/div[3]/div[1]/button"));// .click();
					visbility(driver, dd1, 60);
					click(dd1);
					sleep(2000);
					List<WebElement> d1 = driver.findElements(
							By.xpath("//div[@id='MedicationsKpop2']/div[2]/div[3]/div[1]/button//following::ul[1]/li"));
					for (WebElement w : d1) {

						if (w.getText().trim().equals("Save")) {
							visbility(driver, w, 60);
							w.click();
							break;
						}

					}

					sleep(3000);
					WebElement d3 = driver.findElement(By.xpath("//div[text()='dg']"));
					visbility(driver, d3, 60);
					ww.until(ExpectedConditions.elementToBeClickable(d3));
					actions("click", d3);
					sleep(2000);

					/*
					 * WebElement delmed = driver
					 * .findElement(By.xpath("//div[@id='MedicationsKpop2']/div[1]/div[2]/span[1]"))
					 * ; javascriptclick(delmed);
					 */
					WebElement rqw = driver.findElement(By.xpath(
							"//div[@id='MedicationsKpop2']/div[2]/div[1]/div[1]/div[3]/table/tbody/tr/td[2]/div"));
					visbility(driver, rqw, 60);
					javascriptclick(rqw);
					sleep(2000);
					driver.findElement(By.id("DRUG_NAME")).sendKeys("MEDICATION");

					WebElement ry = driver
							.findElement(By.xpath("//div[@id='MedicationsKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, ry, 60);
					javascriptclick(ry);
					// sleep(3000);
					/*
					 * List<WebElement> d1R = driver.findElements( By.xpath(
					 * "//div[@id='MedicationsKpop2']/div[2]/div[3]/div[1]/button//following::ul[1]/li"
					 * )); for (WebElement w : d1R) {
					 * 
					 * if (w.getText().trim().equals("Save")) { w.click(); break; }
					 * 
					 * }
					 */
					sleep(3000);

				} else if (tagnames.equals("delivery-note")) {
					WebElement kk = driver.findElement(By.xpath("//div[contains(@title,'Add Notes')]"));
					actions("move to element", kk);
					visbility(driver, kk, 60);
					ww.until(ExpectedConditions.elementToBeClickable(kk));
					actions("click", kk);
					WebElement kk1 = driver
							.findElement(By.xpath("//div[@title='Enter the notes description of the patient visit']"));
					visbility(driver, kk1, 60);
					sendkeys(kk1, "hell");// .sendKeys("hell");
					WebElement zv = driver.findElement(By.xpath("//div[@id='NotesKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, zv, 60);
					javascriptclick(zv);
					sleep(3000);
					WebElement jl = driver.findElement(By.xpath("//div[text()='hell']"));
					visbility(driver, jl, 60);
					actions("click", jl);
					WebElement kk2 = driver
							.findElement(By.xpath("//div[@title='Enter the notes description of the patient visit']"));
					visbility(driver, kk2, 60);
					clear(kk2);// .clear();
					WebElement kk3 = driver
							.findElement(By.xpath("//div[@title='Enter the notes description of the patient visit']"));
					visbility(driver, kk3, 60);
					sendkeys(kk3, "NOTES--MMM");// .sendKeys("NOTES--MMM");
					WebElement zv1 = driver.findElement(By.xpath("//div[@id='NotesKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, zv1, 60);
					javascriptclick(zv1);

					/*
					 * WebElement fg =
					 * driver.findElement(By.xpath("//div[@id='NotesKpop2']/div/div[2]/span[1]"));
					 * javascriptclick(fg);
					 */
					sleep(3000);

				} else if (tagnames.equals("physical-examination")) {

					WebElement x1 = driver.findElement(By.xpath("//div[contains(@title,'Add Physical Examination')]"));
					actions("move to element", x1);
					visbility(driver, x1, 60);
					ww.until(ExpectedConditions.elementToBeClickable(x1));

					actions("click", x1);
					WebElement x2 = driver.findElement(By.id("bodyParts"));
					visbility(driver, x2, 60);
					sendkeys(x2, "hello");// .sendKeys("hello");

					WebElement x3 = driver.findElement(By.id("finding"));
					visbility(driver, x3, 60);
					sendkeys(x3, "hw are you");// .sendKeys("hw are you");
					sleep(2000);

					WebElement abc = driver
							.findElement(By.xpath("//div[@id='Physical_ExaminationsKpop2']/div[2]/div[2]/div/button"));
					visbility(driver, abc, 60);
					ww.until(ExpectedConditions.elementToBeClickable(abc));
					javascriptclick(abc);
					List<WebElement> abcd = driver.findElements(By.xpath(
							"//div[@id='Physical_ExaminationsKpop2']/div[2]/div[2]/div/button//following::ul[1]/li"));
					for (WebElement w : abcd) {
						if (w.getText().trim().equals("Show Notes")) {
							visbility(driver, w, 60);
							w.click();
							break;
						}

					}

					sleep(2000);
					WebElement x5 = driver.findElement(By.xpath("//input[@id='notes']"));
					visbility(driver, x5, 60);
					sendkeys(x5, "lets goo");// .sendKeys("lets goo");
					//// div[@id='Physical_ExaminationsKpop2']/div[2]/div[1]/div[5]/div[2]/input
					sleep(2000);
					WebElement nn = driver
							.findElement(By.xpath("//div[@id='Physical_ExaminationsKpop2']/div[2]/div[3]/button[2]"));
					visbility(driver, nn, 60);
					javascriptclick(nn);
					sleep(3000);
					WebElement et = driver.findElement(By.xpath("//div[text()='lets goo']"));
					visbility(driver, et, 60);
					actions("click", et);
					/*
					 * WebElement sf = driver .findElement(By.xpath(
					 * "//div[@id='Physical_ExaminationsKpop2']/div[1]/div[2]/span[1]"));
					 * javascriptclick(sf);
					 */

					WebElement x7 = driver.findElement(By.xpath("//input[@id='notes']"));
					visbility(driver, x7, 60);
					clear(x7);// .clear();
					WebElement x8 = driver.findElement(By.xpath("//input[@id='notes']"));
					visbility(driver, x8, 60);
					sendkeys(x8, "physical condition");// .sendKeys("physical condition");
					WebElement nnn = driver
							.findElement(By.xpath("//div[@id='Physical_ExaminationsKpop2']/div[2]/div[3]/button[2]"));
					visbility(driver, nnn, 60);
					javascriptclick(nnn);
					sleep(4000);

				} else if (tagnames.equals("custom-form")) {

					WebElement lj = driver.findElement(By.xpath("//div[contains(@title,'Add Forms')]"));
					actions("move to element", lj);
					visbility(driver, lj, 60);
					ww.until(ExpectedConditions.elementToBeClickable(lj));
					actions("click", lj);
					sleep(3000);

					List<WebElement> numberofformspresent = driver
							.findElements(By.xpath("(//div[@class='form-pop-body'])[10]/div/div[1]"));
					int ffs = numberofformspresent.size();
					// System.out.println(ffs);

					int u;
					for (int imp = 4; imp <= ffs; imp++) {

						// u = 1 + i;
						WebElement rtt = driver.findElement(
								By.xpath("(//div[@class='form-pop-body'])[10]/div[" + imp + "]/div/div[1]/span[2]"));
						// System.out.println(rtt.getText());

						if (rtt.getText().trim().equals("form5")) {
							visbility(driver, rtt, 60);
							rtt.click();
							WebElement js = driver.findElement(By.xpath("(//span[@id='del-form'])[2]"));
							visbility(driver, js, 60);
							javascriptclick(js);

							break;

						}

					}
					sleep(3000);

					WebElement addfrm = driver.findElement(By.xpath("//div[@id='FormsKpop2']/div[1]/span"));
					visbility(driver, addfrm, 60);
					actions("click", addfrm);
					WebElement x9 = driver
							.findElement(By.xpath("(//label[text()='Form Title*'])[2]//following::input[1]"));
					visbility(driver, x9, 60);
					sendkeys(x9, "form5");// .sendKeys("form5");

					List<WebElement> drk = driver
							.findElements(By.xpath("(//div[@id='build-wrap'])[2]/div[1]/div[2]/ul/li"));

					for (WebElement web : drk) {

						if (web.getText().trim().equals("Checkbox Group")) {

							WebElement drop = driver.findElement(By.xpath(
									"(//div[contains(@data-content,'Drag a field from the right to this area')])[2]/ul"));

							Actions ac = new Actions(driver);
							ac.dragAndDrop(web, drop).build().perform();
							driver.findElement(By.xpath("//label[text()='Label']//following::div[1]/input")).clear();
							driver.findElement(By.xpath("//label[text()='Label']//following::div[1]/input"))
									.sendKeys("Kaaspro Enterprise");
							driver.findElement(
									By.xpath("(//div[@id='build-wrap'])[2]/div[1]/div[2]/ul//following::div[1]/button"))
									.click();
							sleep(7000);
							implicitWait(30, TimeUnit.SECONDS);
							WebElement addit = driver
									.findElement(By.xpath("//span[text()='form5']//following::div[1]/span"));
							visbility(driver, addit, 60);
							actions("click", addit);
							sleep(6000);
							WebElement ytt = driver.findElement(By.xpath("//div[@id='FormsKpop2']/div[1]/div[2]/span"));
							javascriptclick(ytt);
							sleep(5000);
							WebElement ffr = driver
									.findElement(By.xpath("//span[text()='form5']//following::div[1]/div"));
							actions("click", ffr);
							sleep(4000);
							WebElement delfr = driver
									.findElement(By.xpath("(//span[text()='form5'])[2]//following::div[1]/span[1]"));
							actions("click", delfr);
							break;
						}

					}
					sleep(3000);
				} else if (tagnames.equals("attachFile")) {

					WebElement ar = driver.findElement(By.xpath("//div[contains(@title,'Add Attach File')]"));
					actions("move to element", ar);
					visbility(driver, ar, 60);
					ww.until(ExpectedConditions.elementToBeClickable(ar));
					actions("click", ar);

					WebElement selweb = driver
							.findElement(By.xpath("//div[@id='Attach_FileKpop2']/div[2]/div[1]/select"));
					visbility(driver, selweb, 60);
					dropDown("text", selweb, "Web link");
					WebElement y1 = driver.findElement(
							By.xpath("(//div[@id='Attach_FileKpop2']/div[2]/div[1]//following::div[3]/input[1])[1]"));
					visbility(driver, y1, 60);
					sendkeys(y1, "https://www.75health.com/");// .sendKeys("https://www.75health.com/");
					WebElement ps1 = driver
							.findElement(By.xpath("//div[@id='Attach_FileKpop2']/div[2]/div[3]/button[2]"));
					visbility(driver, ps1, 60);
					javascriptclick(ps1);
					sleep(3000);

				} else if (tagnames.equals("inpatient")) {

					WebElement qq = driver.findElement(By.xpath("//div[contains(@title,'Add Inpatient')]"));
					actions("move to element", qq);
					visbility(driver, qq, 60);
					ww.until(ExpectedConditions.elementToBeClickable(qq));
					actions("click", qq);
					// driver.findElement(By.id("admissioniid")).click();

					WebElement inpmnth = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
					visbility(driver, inpmnth, 60);
					dropDown("index", inpmnth, "08");

					WebElement ipmyr = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
					visbility(driver, ipmyr, 60);
					dropDown("text", ipmyr, "2022");
					WebElement y2 = driver.findElement(By.xpath("//a[text()='21']"));// .click();
					visbility(driver, y2, 60);
					click(y2);

					sleep(2000);
					driver.findElement(By.id("dischargeiid")).click();
					WebElement inpmnth1 = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
					visbility(driver, inpmnth1, 60);
					dropDown("index", inpmnth1, "10");
					WebElement ipmyr1 = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
					visbility(driver, ipmyr1, 60);
					dropDown("text", ipmyr1, "2022");
					WebElement y3 = driver.findElement(By.xpath("//a[text()='26']"));
					visbility(driver, y3, 60);
					click(y3);// .click();

					sleep(2000);
					WebElement re = driver.findElement(By.xpath("//div[@id='InpatientKpop2']/div[2]/div[1]/select"));
					visbility(driver, re, 60);
					// (//select[@id='admissionType'])[1]
					ww.until(ExpectedConditions.elementToBeClickable(re));
					javascriptclick(re);
					dropDown("text", re, "Urgent");
					driver.findElement(By.id("rmNo")).sendKeys("777");
					WebElement y4 = driver.findElement(By.id("dischargeSummary"));
					visbility(driver, y4, 60);
					sendkeys(y4, "okay");// .sendKeys("okay");
					WebElement yt = driver.findElement(By.xpath("//div[@id='InpatientKpop2']/div[2]/div[2]/button[2]"));
					visbility(driver, yt, 60);
					javascriptclick(yt);

					sleep(3000);

				} /*
					 * else if (tagnames.equals("refer")) {
					 * 
					 * WebElement ju =
					 * driver.findElement(By.xpath("//div[contains(@title,'Add Referral')]"));
					 * actions("move to element", ju); actions("click", ju); WebElement uk = driver
					 * .findElement(By.xpath(
					 * "//div[@id='ReferralKpop2']/div[2]/div/div[2]/div[2]/input"));
					 * uk.sendKeys(ConfigManager.getconfigManager().getInstanceConfigReader().
					 * doctorKpid());// kpid mention // dr... sleep(3000); List<WebElement> rwr =
					 * driver.findElements(By.xpath(
					 * "//div[@id='ReferralKpop2']/div[2]/div[2]//following::ul[1]/li/a/table[1]/tbody/tr/td[2]"
					 * )); for (WebElement w : rwr) { if (w.getText()
					 * .contains(ConfigManager.getconfigManager().getInstanceConfigReader().
					 * doctorKpid())) { w.click(); break;
					 * 
					 * }
					 * 
					 * }
					 * 
					 * sleep(4000); //
					 * driver.findElement(By.xpath("(//input[@id='phone'])[5]")).sendKeys(
					 * "201-525-2236"); sleep(2000); driver.findElement(By.xpath(
					 * "//div[@id='ReferralKpop2']/div[2]/div[1]/div[6]/div[2]/input"))
					 * .sendKeys("hello"); WebElement cv = driver.findElement(By.xpath(
					 * "//div[@id='ReferralKpop2']/div[2]/div[2]/button[2]")); javascriptclick(cv);
					 * sleep(2000);
					 * 
					 * WebElement df = driver.findElement(By.xpath("//div[text()='hello']"));
					 * actions("click", df); WebElement ssf = driver.findElement(By.xpath(
					 * "//div[@id='ReferralKpop2']/div[1]/div[2]/span[1]")); javascriptclick(ssf);
					 * 
					 * 
					 * }
					 */
			}

		}

		implicitWait(30, TimeUnit.SECONDS);
		sleep(2000);
		WebElement gnbill = driver.findElement(By.xpath("(//div[@id='generate_bill'])[1]/button[2]"));
		visbility(driver, gnbill, 60);
		javascriptclick(gnbill);
		List<WebElement> yjj = driver
				.findElements(By.xpath("(//div[@id='generate_bill'])[1]/button[2]//following::ul[1]/li"));
		for (WebElement we : yjj) {
			if (we.getText().trim().equals("Generate Bill from EHR")) {
				visbility(driver, we, 60);
				we.click();
				break;
			}

		}
		sleep(4000);

		// WebElement rds =
		// driver.findElement(By.xpath("//div[text()='MEDICATION-str']"));
		WebElement rds = ww
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='MEDICATION-str']")));
		visbility(driver, rds, 60);
		ww.until(ExpectedConditions.elementToBeClickable(rds));
		actions("click", rds);
		// WebElement =
		// driver.findElement(By.xpath("//div[text()='MEDICATION-str']//following::div[6]/div[2]//following::div[1]/div[1]/div[5]/div[2]/input"));
		WebElement rdss = ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//div[text()='MEDICATION-str']//following::div[6]/div[2]//following::div[1]/div[1]/div[5]/div[2]/input")));
	visbility(driver, rdss, 60);
		rdss.clear();
		rdss.sendKeys("500");
		WebElement sql = driver.findElement(By.xpath(
				"//div[text()='MEDICATION-str']//following::div[6]/div[2]//following::div[1]/div[2]/div[1]/button[3]"));
		visbility(driver, sql, 60);
		javascriptclick(sql);

		///
		sleep(2000);

		WebElement ee = ww
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='add-payment-btn'])[1]")));
		visbility(driver, ee, 60);
		javascriptclick(ee);
		// driver.findElement(By.xpath("(//button[@id='add-payment-btn'])[1]")).click();
		WebElement ddr = driver.findElement(By.xpath("(//span[@id='paymentMethodTypeSelectValue'])[2]"));
		visbility(driver, ddr, 60);
		click(ddr);//.click();
		sleep(2000);
		List<WebElement> choosepy = driver
				.findElements(By.xpath("(//span[@id='paymentMethodTypeSelectValue'])[2]//following::ul[1]/li"));
		for (WebElement w : choosepy) {
			if (w.getText().trim().equals("Cash Payment")) {
				visbility(driver, w, 60);
				w.click();
				break;
			}

		}

		WebElement dsp1 = driver.findElement(By.xpath(
				"(//button[@id='paymentMethodTypeId'])[2]/span[2]//following::div[4]/div[4]//following::div[2]//following::div[1]/div[2]/textarea"));
		actions("click", dsp1);
		sleep(2000);
		dsp1.sendKeys("cash pay+++++++++");

		WebElement tet = driver.findElement(By.xpath("(//button[@title='Make Payment'])[3]"));

		j.executeScript("arguments[0].click();", tet);

		WebElement fnls = driver.findElement(By.xpath("//button[@id='finalize-bill']"));
		ww.until(ExpectedConditions.elementToBeClickable(fnls));
		javascriptclick(fnls);

		WebElement dz = driver.findElement(By.xpath("(//button[@title='Finalize'])[1]"));
		ww.until(ExpectedConditions.elementToBeClickable(dz));
		javascriptclick(dz);
		sleep(2000);
		/*
		 * driver.findElement(By.xpath(
		 * "//button[@id='finalize-bill']//following::button[2]")).click(); sleep(2000);
		 */
		/*
		 * WebElement delbil = driver.findElement( By.
		 * xpath("//center[text()='Do you like to delete Invoice?']//following::div[1]/button[2]"
		 * )); javascriptclick(delbil);
		 */

		WebElement backtoehr = driver.findElement(By.xpath("//div[@id='paymentMadeInfull']//following::button[1]"));
		clickble(driver, backtoehr, 25);
		javascriptclick(backtoehr);
		WebElement complteehr = driver
				.findElement(By.xpath("(//button[contains(@title,'Complete Health Record')])[1]/span[2]"));
		javascriptclick(complteehr);
		sleep(2000);
		driver.findElement(By.xpath(
				"(//span[contains(text(),'Past date health record entry')])[1]//parent::div//parent::div[1]/div[3]/div/div[1]/div[1]/input"))
				.sendKeys("1");
		WebElement cmpehr = driver.findElement(
				By.xpath("(//span[contains(text(),'Past date health record entry')])[1]//following::div[4]/button[2]"));
		javascriptclick(cmpehr);
		///
		/*
		 * WebElement backtoehr =
		 * driver.findElement(By.xpath("//button[@onclick='window.history.back();']"));
		 * javascriptclick(backtoehr);
		 */
		sleep(3000);
		driver.navigate().to("https://localhost:8443/health/#list_ehr");
		implicitWait(30, TimeUnit.SECONDS);

		driver.findElement(By.id("newMedicalRecordButton")).click();

		sleep(3000);

		// Salt options Scenarios...

		for (int i = 1; i <= ehrrow; i++) {

			List<WebElement> qf = driver.findElements(By.xpath("(//div[@id='cols'])[2]/div[" + i + "]/div"));
			for (int j = 1; j <= qf.size(); j++) {
				sleep(2000);

				WebElement gettag = driver
						.findElement(By.xpath("(//div[@id='cols'])[2]/div[" + i + "]/div[" + j + "]"));

				String tagnames = gettag.getAttribute("id");

				if (tagnames.equals("vital")) {

					WebElement vitalsal = driver
							.findElement(By.xpath("//div[@id='vital']/div[1]/div[1]/div/div[2]/div[1]"));

					actions("click", vitalsal);

				} else if (tagnames.equals("visit-reason")) {
					WebElement vistsal = driver
							.findElement(By.xpath("(//div[@id='visit-reason'])[2]/div/div[1]/div/div[2]/div[1]"));
					actions("click", vistsal);

				} else if (tagnames.equals("diagnosis")) {

					WebElement prbsal = driver.findElement(By.xpath("//div[contains(@title,'SALT Problems')]"));
					actions("click", prbsal);

				} else if (tagnames.equals("symptom")) {

					WebElement symsalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Symptoms')]"));
					actions("click", symsalt);

				} else if (tagnames.equals("procedure")) {

					WebElement prosalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Procedure')]"));
					actions("click", prosalt);
				} else if (tagnames.equals("goals")) {

					WebElement goalsalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Goals')]"));
					actions("click", goalsalt);
				} else if (tagnames.equals("directives")) {

					WebElement advsalt = driver
							.findElement(By.xpath("//div[contains(@title,'SALT Advance directives')]"));
					actions("click", advsalt);
				} else if (tagnames.equals("status-module")) {

					WebElement statussalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Status')]"));
					actions("click", statussalt);
				} else if (tagnames.equals("drug")) {

					WebElement medsalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Medications')]"));
					actions("click", medsalt);
				} else if (tagnames.equals("delivery-note")) {
					WebElement notesalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Notes')]"));
					actions("click", notesalt);
				}

			}
		}
		sleep(3000);
		WebElement printehr = driver.findElement(By.xpath("//i[@onclick='ehr.ehrShowPrintOptions();']"));
		ScriptExecutor(printehr);
		WebElement timest = driver
				.findElement(By.xpath("//i[@onclick='ehr.ehrShowPrintOptions();']//following::span[3]"));

		visbility(driver, timest, 25);
		javascriptclick(timest);

		List<WebElement> stmp = driver.findElements(
				By.xpath("//i[@onclick='ehr.ehrShowPrintOptions();']//following::span[3]//following::ul[1]/li"));
		for (WebElement web : stmp) {
			if (web.getText().trim().equals("Show Timestamp")) {
				web.click();
				break;
			}

		}
		implicitWait(30, TimeUnit.SECONDS);
		sleep(2500);
		// follow up creation...
		j.executeScript("window.scroll(0,0)");
		WebElement createfollowup = driver.findElement(By.xpath("(//button[@id='followUpAdd'])[1]/div[1]"));
		ww.until(ExpectedConditions.elementToBeClickable(createfollowup));
		actions("click", createfollowup);
		sleep(4000);
		implicitWait(30, TimeUnit.SECONDS);
		WebElement crt = driver
				.findElement(By.xpath("//div[@id='followupEhr']/div[2]/div[3]/div[1]//following::div[2]/input"));
		actions("click", crt);
		sleep(2000);
		WebElement folowypyr = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
		dropDown("text", folowypyr, "2022");
		WebElement folowupmnth = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		dropDown("index", folowupmnth, "2");
		driver.findElement(By.xpath("(//a[text()='6'])[3]")).click();
		WebElement fixappo = driver.findElement(By.xpath("(//button[@id='fixAppointment'])[1]"));

		sleep(3000);
		javascriptclick(fixappo);

		List<WebElement> tot = driver.findElements(By.xpath("(//div[@id='date-data'])[1]"));
		int totaly = tot.size();
		// System.out.println("found you>>>" + totaly);
		implicitWait(30, TimeUnit.SECONDS);

		boolean b = false;
		for (int i = 1; i <= totaly; i++) {
			sleep(3000);
			List<WebElement> rqq = driver
					.findElements(By.xpath("(//div[@id='formAppointment'])[2]/div/div[2]/div[2]/div[2]/div"));

			// System.out.println("total number of displayed time in the list:" +
			// rqq.size());
			for (int jj = 1; jj <= rqq.size(); jj++) {

				WebElement checkcn = driver
						.findElement(By.xpath("(//div[@id='date-data'])[1]/div[2]/div[" + jj + "]/div/div[1]"));
				// System.out.println(checkcn.getText());

				WebElement sm = driver
						.findElement(By.xpath("(//div[@id='date-data'])[1]/div[2]/div[" + jj + "]/div/div[2]/span[3]"));
				// System.out.println(sm.getText().isEmpty());

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
				WebElement delfolup = driver
						.findElement(By.xpath("(//button[@id='fixAppointment'])[1]//following::div[1]/div[1]/span[2]"));
				actions("move to element", delfolup);
				actions("click", delfolup);
				WebElement bckfl = driver.findElement(By.xpath("//div[@id='followupEhr']/div[1]/div/span[1]"));
				javascriptclick(bckfl);
				break;
			}
		}

		sleep(2000);
		WebElement delehrf = driver.findElement(By.xpath("(//button[contains(@title,'Delete Health Record')])[1]"));
		javascriptclick(delehrf);
		sleep(2000);
		WebElement zr = driver
				.findElement(By.xpath("//span[text()='Delete Health Record']//following::div[4]/button[2]"));
		javascriptclick(zr);
		sleep(4000);
	}

	@Test(priority = 3, enabled = false)

	private void calendar() throws InterruptedException {
		driver.navigate().to("https://localhost:8443/health/#calendar");
		implicitWait(30, TimeUnit.SECONDS);
		/*
		 * explicitWait(20, pom.getInstanceCalendar().clickCalendar);
		 * click(pom.getInstanceCalendar().clickCalendar);
		 */
		driver.navigate().refresh();

		WebElement clbtn = driver.findElement(By.xpath("(//button[@id='calendar-day-month'])[1]"));

		visbility(driver, clbtn, 25);
		clickble(driver, clbtn, 25);
		clbtn.click();
		sleep(2000);
		List<WebElement> choose = driver.findElements(By.xpath("//ul[@id='calendarul']/li"));

		for (WebElement web : choose) {
			if (web.getText().equals("Today")) {
				driver.findElement(By.xpath("//ul[@id='calendarul']/li")).click();
				break;
			}
		}
		sleep(2000);

		List<WebElement> totalnumberrowdy = driver.findElements(By.xpath("//div[@id='date-data']"));
		int totalr = totalnumberrowdy.size();
		// System.out.println("found you>>>" + totalr);

		boolean cond = false;

		for (int i = 1; i <= totalr; i++) {
			int a = 1 + i;
			WebElement ss = driver.findElement(By.xpath("//div[@id='date-data'][" + i + "]/div[2]/div[2]/div"));
			if (ss.getText().equals("Doctor/User not working")) {
				System.out.println("yes doctor not working for the:" + i);
				WebElement abcd = driver.findElement(By.xpath("(//span[@id='editCalendar'])[" + a + "]"));
				actions("click", abcd);
				sleep(3000);
				WebElement checkbx = driver.findElement(By.xpath("(//input[@id='is-working-day'])[1]"));
				System.out.println("(//input[@id='is-working-day'])[" + i + "]");
				actions("click", checkbx);
				WebElement ampm = driver.findElement(By.xpath("(//div[@id='thru-ampm'])[1]"));
				actions("click", ampm);
				driver.findElement(By.xpath("(//div[@id='save-btn'])[1]")).click();
				sleep(5000);

			}

			// represent total in a part..
			List<WebElement> rchange = driver
					.findElements(By.xpath("(//div[@id='date-data'][" + i + "]/div[2]/div/div[1]/div[1]/div[1])"));
			int avaiable = rchange.size();

			// System.out.println("avaiable count for the present row:" + avaiable);
			for (int b = 1; b <= avaiable; b++) {
				// System.out.println("hello");
				WebElement tp = driver.findElement(
						// represent the row change and time..
						By.xpath("(//div[@id='date-data'][" + i + "]/div[2]/div[" + b + "]/div[1]/div[1]/div[1])"));

				String tr = tp.getText();
				boolean trp = tp.isDisplayed();
				/*
				 * System.out.println(tr); System.out.println(trp);
				 */

				// the kpid ..
				WebElement kp = driver.findElement(
						By.xpath("(//div[@id='date-data'])[" + i + "]/div[2]/div[" + b + "]/div/div[2]/span[2]"));

				if (kp.getText().isEmpty() && tp.isDisplayed()) {
					// System.out.println(tp.getAttribute("id"));
					cond = true;
					// System.out.println("condtion is true");
					javascriptclick(tp);
					// System.out.println(i);
					driver.findElement(By.xpath("(//input[@id='AppointmentPatientName'])[" + i + "]")).sendKeys(kpid);
					break;
				}

			}
			if (cond == true) {
				sleep(2000);
				implicitWait(30, TimeUnit.SECONDS);
				List<WebElement> choosepatient = driver
						.findElements(By.xpath("//ul[@id='ui-id-2']/li/a/table/tbody/tr/td[2]"));
				// (//td[text()='" + kpid + "'])[2]//parent::td
				for (WebElement web : choosepatient) {
					if (web.getText().trim().equals(kpid)) {
						web.click();
						break;
					}

				}
				sleep(3000);

				WebElement ut = driver.findElement(By.xpath("(//select[@id='triage-appointment'])[" + i + "]"));
				visbility(driver, ut, 25);
				// ut.click();
				dropDown("text", ut, "Emergency");

				WebElement qt = driver.findElement(By.xpath("(//textarea[@id='description'])[" + i + "]"));
				visbility(driver, qt, 25);
				qt.sendKeys("no worries...");
				WebElement utt = driver.findElement(By.xpath("(//button[@id='statusId_dropdown'])[" + i + "]"));
				visbility(driver, utt, 25);
				j.executeScript("arguments[0].click();", utt);

				List<WebElement> lop = driver.findElements(By.xpath("(//ul[@id='statusIdDropdown'])[" + i + "]/li"));
				for (WebElement w : lop) {
					if (w.getText().trim().equals("In Progress")) {
						w.click();
						break;
					}

				}

				WebElement vcv = driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]"));
				visbility(driver, vcv, 25);
				ScriptExecutor(vcv);
				clickble(driver, vcv, 25);
				j.executeScript("arguments[0].click();", vcv);
				sleep(1000);

				WebElement ez = driver.findElement(By.xpath("//span[text()='" + kpid + "']"));
				visbility(driver, ez, 25);
				j.executeScript("arguments[0].click();", ez);
				sleep(2000);
				// goto ehr..
				driver.findElement(By.xpath("(//button[@id='cancel-btn1'])[1]")).click();

				sleep(3000);
				driver.navigate().to("https://localhost:8443/health/#calendar");
				driver.navigate().refresh();

				WebElement pipes = driver.findElement(By.xpath("(//span[text()='" + kpid + "'])"));
				visbility(driver, pipes, 25);
				j.executeScript("arguments[0].click();", pipes);
				sleep(1000);
				WebElement wtw = driver.findElement(By.xpath("(//span[@id='del-btn'])[" + i + "]"));
				j.executeScript("arguments[0].click();", wtw);
				sleep(2000);

				/*
				 * List<WebElement> rtr = driver .findElements(By.xpath(
				 * "(//span[@id='del-btn'])[1]//following::ul[1]/li/div/div[2]/span"));
				 * 
				 * for (WebElement web : rtr) { if (web.getAttribute("id").equals("yes-btn")) {
				 * // System.out.println("yes it is deleted"); implicitWait(30,
				 * TimeUnit.SECONDS); actions("click", web);
				 * 
				 * break; }
				 * 
				 * }
				 */

				WebElement delappp = driver
						.findElement(By.xpath("//div[@id='AppointmentCreateMessage']/div[2]/div[2]/button[2]"));
				javascriptclick(delappp);

				break;
			}
		}
		sleep(2000);

	}

	@Test(priority = 4, enabled = false)
	private void BillingModule() throws InterruptedException {

		// explicitWait(15, pom.getInstanceBilling().clickBill); //
		visbility(driver, pom.getInstanceBilling().clickBill, 25);
		click(pom.getInstanceBilling().clickBill);

		driver.navigate().refresh();
		implicitWait(30, TimeUnit.SECONDS);
		sleep(3000);
		explicitWait(15, pom.getInstanceBilling().clickCreateNewBill);
		click(pom.getInstanceBilling().clickCreateNewBill);
		explicitWait(30, pom.getInstanceBilling().addItem);
		sleep(2000);
		click(pom.getInstanceBilling().addItem);
		sleep(2000);
		sendkeys(pom.getInstanceBilling().enterTheItem, "dolo"); //

		sendkeys(pom.getInstanceBilling().addPrice, "10"); //

		clear(pom.getInstanceBilling().addQuantity); //

		sendkeys(pom.getInstanceBilling().addQuantity, "2");
		sleep(2000);
		click(pom.getInstanceBilling().saveItem);
		sleep(3000);
		WebElement r = driver.findElement(By.xpath("//div[@id='assign-side']/div[1]/div"));

		actions("click", r);
		WebElement addiconfav = driver
				.findElement(By.xpath("//div[@id='assign-side']/div[2]/div[1]/div/table/tbody/tr/td[4]/span[2]"));
		visbility(driver, addiconfav, 25);
		javascriptclick(addiconfav);
		WebElement favdes = driver
				.findElement(By.xpath("//div[@id='assign-side']/div[3]/div/div/div[2]/div[3]/div[2]/div/input"));
		visbility(driver, favdes, 25);
		favdes.sendKeys("Kaaspro");
		driver.findElement(By.xpath("//div[@id='assign-side']/div[3]/div/div/div[2]/div[4]/div[2]/input"))
				.sendKeys("3");
		WebElement savefav = driver.findElement(By.xpath(
				"//div[@id='assign-side']/div[3]/div/div/div[2]/div[4]/div[2]//following::div[1]/div/div[4]//following::div[1]/div/button"));
		visbility(driver, savefav, 25);
		javascriptclick(savefav);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		sleep(1500);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//span[text()='Kaaspro']//parent::div//parent::div[1]//parent::div[1]/div[1]/span[1])[1]")));
		ww.until(ExpectedConditions.elementToBeClickable(until));
		actions("click", until);
		sleep(2000);
		WebElement edi = driver.findElement(
				By.xpath("(//span[text()='Kaaspro']//parent::div//parent::div[1]//parent::div[1]/div[2])[1]"));
		actions("click", edi);
		sleep(2000);
		WebElement delfav = driver
				.findElement(By.xpath("//div[@id='assign-side']/div[3]/div/div/div[1]/div[2]/span[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(delfav));
		actions("click", delfav);
		sleep(2000);
		WebElement itser = driver.findElement(By.xpath("//div[@id='item-code-side']/div[1]/div"));
		wait.until(ExpectedConditions.elementToBeClickable(itser));
		actions("click", itser);
		WebElement additser = driver
				.findElement(By.xpath("//div[@id='item-code-side']/div[2]/div[1]/div/table/tbody/tr/td[4]/span[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(additser));
		javascriptclick(additser);
		driver.findElement(By.xpath("//div[@id='item-code-side']/div[3]/div/div/div[2]/div[3]/div/input"))
				.sendKeys("product001");
		driver.findElement(By.xpath("//div[@id='item-code-side']/div[3]/div/div/div[2]/div[4]/div/textarea"))
				.sendKeys("sr");
		driver.findElement(By.xpath("//div[@id='item-code-side']/div[3]/div/div/div[2]/div[5]/div/input"))
				.sendKeys("5");
		WebElement itersav = driver
				.findElement(By.xpath("//div[@id='item-code-side']/div[3]/div/div/div[2]/div[7]/div/button"));
		javascriptclick(itersav);

		WebElement ft = driver
				.findElement(By.xpath("//div[text()='PRODUCT001']//parent::div[1]//parent::div[1]/div[1]/span"));
		wait.until(ExpectedConditions.elementToBeClickable(ft));
		javascriptclick(ft);
		sleep(2000);
		WebElement edititser = driver.findElement(By.xpath("//div[text()='PRODUCT001']"));
		wait.until(ExpectedConditions.elementToBeClickable(edititser));
		actions("click", edititser);

		WebElement delitser = driver
				.findElement(By.xpath("//div[@id='item-code-side']/div[3]/div/div/div[1]/div[2]/span[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(delitser));
		javascriptclick(delitser);
		sleep(2000);
		// service/charge tax..

		WebElement sct = driver.findElement(By.xpath("//div[@id='tax-side']/div[1]/div/span[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(sct));
		actions("click", sct);
		WebElement sctadd = driver
				.findElement(By.xpath("//div[@id='tax-side']/div[2]/div[1]/div/table/tbody/tr/td[4]/span[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(sctadd));
		javascriptclick(sctadd);
		driver.findElement(By.xpath("//div[@id='tax-side']/div[3]/div/div/div[2]/div[3]/div/input"))
				.sendKeys("service charge<>");
		driver.findElement(By.xpath("//div[@id='tax-side']/div[3]/div/div/div[2]/div[4]/div/input")).sendKeys("5");
		WebElement sctsav = driver
				.findElement(By.xpath("//div[@id='tax-side']/div[3]/div/div/div[2]/div[6]/div/button"));
		wait.until(ExpectedConditions.elementToBeClickable(sctsav));
		javascriptclick(sctsav);
		WebElement adsct = driver
				.findElement(By.xpath("//div[text()='service charge<>']//parent::div[1]//parent::div[1]/div/span"));
		wait.until(ExpectedConditions.elementToBeClickable(adsct));
		actions("click", adsct);
		sleep(2000);
		WebElement edsct = driver.findElement(By.xpath("//div[text()='service charge<>']"));
		wait.until(ExpectedConditions.elementToBeClickable(edsct));
		actions("click", edsct);
		WebElement delsct = driver.findElement(By.xpath("//div[@id='tax-side']/div[3]/div/div/div[1]/div[2]/span[1]"));
		javascriptclick(delsct);
		sleep(2000);
		// discount..

		WebElement dis = driver.findElement(By.xpath("//div[@id='discount-side']/div[1]/div"));
		wait.until(ExpectedConditions.elementToBeClickable(dis));
		actions("click", dis);

		WebElement addds = driver
				.findElement(By.xpath("//div[@id='discount-side']/div[2]/div[1]/div/table/tbody/tr/td[4]/span[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(addds));
		javascriptclick(addds);
		driver.findElement(By.xpath("//div[@id='discount-side']/div[3]/div/div/div[2]/div[3]/div/input"))
				.sendKeys("Discnt");
		WebElement cldrpdis = driver
				.findElement(By.xpath("//div[@id='discount-side']/div[3]/div/div/div[2]/div[4]/div/button"));
		javascriptclick(cldrpdis);
		List<WebElement> dispercen = driver
				.findElements(By.xpath("//div[@id='discount-side']/div[3]/div/div/div[2]/div[4]/div/ul/li/a"));
		for (WebElement we : dispercen) {
			if (we.getText().trim().equals("Percentage Discount")) {
				we.click();
				break;
			}

		}
		driver.findElement(By.xpath("//div[@id='discount-side']/div[3]/div/div/div[2]/div[5]/div/div/input"))
				.sendKeys("5");
		WebElement dissav = driver
				.findElement(By.xpath("//div[@id='discount-side']/div[3]/div/div/div[2]/div[8]/div/button"));
		javascriptclick(dissav);
		WebElement adddiscc = driver
				.findElement(By.xpath("//div[text()='Discnt']//parent::div[1]//parent::div/div/span"));
		wait.until(ExpectedConditions.elementToBeClickable(adddiscc));
		actions("click", adddiscc);
		sleep(2000);
		WebElement editdis = driver.findElement(By.xpath("//div[text()='Discnt']"));
		wait.until(ExpectedConditions.elementToBeClickable(editdis));
		actions("click", editdis);
		WebElement deldis = driver
				.findElement(By.xpath("//div[@id='discount-side']/div[3]/div/div/div[1]/div[2]/span[1]"));
		javascriptclick(deldis);
		sleep(2000);

		WebElement ee = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='add-payment-btn'])[1]")));
		javascriptclick(ee);
		// driver.findElement(By.xpath("(//button[@id='add-payment-btn'])[1]")).click();
		driver.findElement(By.xpath("(//span[@id='paymentMethodTypeSelectValue'])[2]")).click();
		sleep(2000);
		List<WebElement> choosepy = driver
				.findElements(By.xpath("(//span[@id='paymentMethodTypeSelectValue'])[2]//following::ul[1]/li"));
		for (WebElement w : choosepy) {
			if (w.getText().trim().equals("Cash Payment")) {
				w.click();
				break;
			}

		}

		WebElement dsp1 = driver.findElement(By.xpath(
				"(//button[@id='paymentMethodTypeId'])[2]/span[2]//following::div[4]/div[4]//following::div[2]//following::div[1]/div[2]/textarea"));
		actions("click", dsp1);
		sleep(2000);
		dsp1.sendKeys("cash pay+++++++++");

		WebElement tet = driver.findElement(By.xpath("(//button[@title='Make Payment'])[3]"));

		j.executeScript("arguments[0].click();", tet);

		WebElement fnls = driver.findElement(By.xpath("//button[@id='finalize-bill']"));
		wait.until(ExpectedConditions.elementToBeClickable(fnls));
		javascriptclick(fnls);

		WebElement dz = driver.findElement(By.xpath("(//button[@title='Finalize'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(dz));
		javascriptclick(dz);
		sleep(2000);
		driver.findElement(By.xpath("//button[@id='finalize-bill']//following::button[2]")).click();
		sleep(2000);
		WebElement delbil = driver.findElement(
				By.xpath("//center[text()='Do you like to delete Invoice?']//following::div[1]/button[2]"));
		javascriptclick(delbil);
		sleep(2000);
		driver.navigate().to("https://localhost:8443/health/#bill_report");
		// driver.navigate().refresh();
		driver.findElement(By.xpath(
				"//div[@id='bill_report']/div[1]/div[2]//following::div[1]/div[2]/div/div[2]/div[3]/div[1]/div[1]/div[1]//following::div[1]/input"))
				.sendKeys(kpid);
		sleep(2000);

		List<WebElement> billdrp = driver.findElements(By.xpath(
				"//div[@id='dmain']/div[4]/div[2]//following::div[2]//following::ul[3]/li/a/table/tbody/tr/td[2]"));
		for (WebElement we : billdrp) {
			if (we.getText().trim().equals(kpid)) {
				System.out.println("kpid met.");

				we.click();
				break;
			}

		}

		click(pom.getInstanceBilling().clickCreateNewBill);
		explicitWait(30, pom.getInstanceBilling().addItem);
		sleep(2000);

		click(pom.getInstanceBilling().addItem);
		sleep(2000);
		sendkeys(pom.getInstanceBilling().enterTheItem, "dolo"); //

		sendkeys(pom.getInstanceBilling().addPrice, "10"); //

		clear(pom.getInstanceBilling().addQuantity); //

		sendkeys(pom.getInstanceBilling().addQuantity, "2");
		sleep(3000);
		click(pom.getInstanceBilling().saveItem);
		sleep(2000);
		String inc;
		WebElement getinc = driver.findElement(By.xpath("//span[@id='receiptId']"));
		inc = getinc.getText();
		System.out.println("ivoice num:" + inc);

		sleep(3000);
		driver.navigate().to("https://localhost:8443/health/#bill_report");
		driver.navigate().refresh();

		WebElement rqs = driver.findElement(By.xpath("//div[text()='" + inc + "']"));
		ww.until(ExpectedConditions.elementToBeClickable(rqs));
		actions("click", rqs);
		WebElement editit = driver.findElement(By.xpath("//div[text()='dolo']"));
		visbility(driver, editit, 25);
		actions("click", editit);
		clear(pom.getInstanceBilling().enterTheItem);
		sendkeys(pom.getInstanceBilling().enterTheItem, "Paracetamal");
		clear(pom.getInstanceBilling().addPrice);
		sendkeys(pom.getInstanceBilling().addPrice, "90");
		WebElement saveit = driver.findElement(
				By.xpath("//div[text()='dolo']//following::div[6]/div[2]//following::div[1]/div[2]/div[1]/button[3]"));
		visbility(driver, saveit, 25);
		javascriptclick(saveit);

	}

	@Test(priority = 5, enabled = false)
	private void TeleDoctor() throws InterruptedException {

		// explicitWait(30, pom.getInstanceTeleDoctor().clickTeleDoctor);
		visbility(driver, pom.getInstanceTeleDoctor().clickTeleDoctor, 25);

		click(pom.getInstanceTeleDoctor().clickTeleDoctor);
		driver.navigate().refresh();
		implicitWait(30, TimeUnit.SECONDS);

		WebElement np1 = driver.findElement(By.xpath("//button[@title='Create new Patinet']"));
		visbility(driver, np1, 25);
		j.executeScript("arguments[0].click();", np1);
		driver.findElement(By.id("firstname")).sendKeys("Abigazi");
		driver.findElement(By.id("lastname")).sendKeys("Ak");
		WebElement gn1 = driver.findElement(By.xpath("(//button[@id='gender_dropdown'])[1]"));
		visbility(driver, gn1, 25);
		j.executeScript("arguments[0].click();", gn1);
		List<WebElement> jj = driver.findElements(By.xpath("(//ul[@id='genderDropdown'])[1]/li"));
		for (WebElement w : jj) {
			if (w.getText().trim().equals("Male")) {

				w.click();
				break;
			}

		}

		WebElement cp1 = driver.findElement(By.xpath("//div[@id='createPatient']"));
		j.executeScript("arguments[0].click();", cp1);

		implicitWait(30, TimeUnit.SECONDS);
		ScriptExecutor(pom.getInstanceNewPatient().deletePatient);
		click(pom.getInstanceNewPatient().deletePatient);
		visbility(driver, pom.getInstanceNewPatient().deletePatient, 25);
		driver.findElement(By.xpath("//button[text()='Delete']")).click();

		/*
		 * click(pom.getInstanceTeleDoctor().clickTeleDoctor);
		 * sendkeys(pom.getInstanceTeleDoctor().searchDoctor, "3523943"); sleep(3000);
		 * explicitWait(20, pom.getInstanceTeleDoctor().searchDoctor); WebElement srdr =
		 * driver.findElement(By.xpath("//td[@id='nameh']//following::td[1]"));
		 * actions("click", srdr); sleep(3000);
		 */
		visbility(driver, pom.getInstanceTeleDoctor().clickTeleDoctor, 25);
		clickble(driver, pom.getInstanceTeleDoctor().clickTeleDoctor, 25);
		click(pom.getInstanceTeleDoctor().clickTeleDoctor);
		visbility(driver, pom.getInstanceTeleDoctor().searchPatient, 25);
		sendkeys(pom.getInstanceTeleDoctor().searchPatient, kpid);

		WebElement pstl = driver.findElement(By.xpath("//td[@id='nameh']//following::td[1]"));
		visbility(driver, pstl, 25);
		actions("click", pstl);

		// ScriptExecutor(pom.getInstanceCalendar().saveAppointment);

		WebElement clickpatie = driver.findElement(By.xpath("(//div[@title='Click to view'])[4]"));

		visbility(driver, clickpatie, 25);
		actions("click", clickpatie);

		sleep(3000);

	}

	@Test(priority = 6, enabled = false)
	private void Message() throws InterruptedException {

		explicitWait(30, pom.getInstanceMessage().clickMessage);
		visbility(driver, pom.getInstanceMessage().clickMessage, 25);
		click(pom.getInstanceMessage().clickMessage);

		explicitWait(20, pom.getInstanceMessage().clickComposemMessage); // //
		click(pom.getInstanceMessage().clickComposemMessage); //
		sendkeys(pom.getInstanceMessage().search, kpid);
		sleep(2000); //
		WebElement msg = driver.findElement(By.xpath("(//td[@id='nameh'])[1]//following::td[1]"));
		visbility(driver, msg, 25);
		actions("click", msg);
		visbility(driver, pom.getInstanceMessage().enterSubject, 25);
		sendkeys(pom.getInstanceMessage().enterSubject, "GOOD MORNING");

		sendkeys(pom.getInstanceMessage().enterMessage, "hello welcome to chennai");
		visbility(driver, pom.getInstanceMessage().sendMessage, 25);
		click(pom.getInstanceMessage().sendMessage);
		visbility(driver, pom.getInstanceMessage().seeSentMessage, 25);
		click(pom.getInstanceMessage().seeSentMessage);
		sleep(3000);

	}

	@Test(priority = 7, enabled = false)
	private void Settings() throws InterruptedException, IOException {

		while (true) {
			driver.navigate().to("https://localhost:8443/health/#setting");
			String s = "https://localhost:8443/health/#setting";
			if (driver.getCurrentUrl().equals(s)) {
				break;
			}
			driver.navigate().refresh();
			sleep(3000);
		}
		implicitWait(40, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.findElement(By.xpath("//button[text()='Manage your Account']")).click();
		sleep(3000);
		WebElement Basicinfo = driver.findElement(By.xpath("(//span[@title='Edit'])[2]"));
		visbility(driver, Basicinfo, 25);
		j.executeScript("arguments[0].click();", Basicinfo);

		driver.findElement(By.id("hospitalName")).clear();
		sleep(2000);
		driver.findElement(By.id("hospitalName")).sendKeys("75health organisation");
		sleep(3000);
		driver.findElement(By.xpath("//button[@title='Administrator Title']")).click();
		sleep(3000);
		List<WebElement> titledrp = driver
				.findElements(By.xpath("//button[@title='Administrator Title']//following::ul[1]/li"));
		for (WebElement choose : titledrp) {
			if (choose.getText().trim().equals("Dr")) {
				choose.click();
				break;
			}

		}
		WebElement Firstnname = driver.findElement(By.id("firstName"));
		Firstnname.clear();
		sleep(2000);

		Firstnname.sendKeys("Automation Acc");
		sleep(2000);
		WebElement lastname = driver.findElement(By.id("lastName"));
		lastname.clear();
		lastname.sendKeys("Account");
		sleep(3000);
		WebElement hospitalActive = driver.findElement(By.xpath("//button[@id='hospitalActiveId']"));
		j.executeScript("arguments[0].click();", hospitalActive);
		sleep(2000);
		List<WebElement> Adminstatus = driver.findElements(By.xpath("(//ul[@id='advBillType_Dropdown'])[2]/li"));
		for (WebElement Ch1 : Adminstatus) {
			if (Ch1.getText().trim().equals("ACTIVE")) {
				Ch1.click();
				break;
			}

		}
		sleep(2000);
		WebElement phn1 = driver.findElement(By.id("phone"));
		phn1.clear();
		phn1.sendKeys("2013445247");
		sleep(2000);

		sleep(2000);
		System.out.println("executed...");
		WebElement em = driver.findElement(By.xpath("(//button[@id='smsP'])[1]"));
		j.executeScript("arguments[0].click();", em);
		System.out.println("clicked...");
		sleep(2000);
		List<WebElement> smsnotification = driver.findElements(By.xpath("//ul[@id='Smsul']/li"));
		for (WebElement web : smsnotification) {
			if (web.getText().trim().equals("ON")) {
				web.click();
				break;
			}

		}
		sleep(2000);
		WebElement fint = driver.findElement(By.xpath("(//button[@id='save-btn'])[3]"));
		j.executeScript("arguments[0].click();", fint);
		sleep(3000);

		// Contact info..

		WebElement contactinfoadd = driver.findElement(By.xpath("(//div[@id='edit-btn'])[2]"));
		actions("click", contactinfoadd);
		sleep(4000);
		WebElement add1 = driver.findElement(By.xpath("(//input[@id='address1'])[1]"));
		add1.clear();
		add1.sendKeys("no.224,Main avenue");
		WebElement add2 = driver.findElement(By.xpath("(//input[@id='address2'])[1]"));
		add2.clear();
		add2.sendKeys("watson street usa");
		sleep(2000);
		WebElement city = driver.findElement(By.xpath("(//input[@id='city'])[1]"));
		city.clear();
		city.sendKeys("usa");
		sleep(2000);
		driver.findElement(By.xpath("(//select[@id='countryGeoId'])[1]")).click();
		sleep(2000);
		WebElement selectcountry = driver.findElement(By.xpath("(//select[@id='countryGeoId'])[1]"));
		dropDown("text", selectcountry, "Germany");
		sleep(2000);
		WebElement selectstate = driver.findElement(By.xpath("(//select[@id='stateProvinceGeoId'])[1]"));
		dropDown("text", selectstate, "Berlin");
		sleep(2000);
		driver.findElement(By.xpath("(//input[@id='postalCode'])[1]")).sendKeys("2001143");
		WebElement savecontactinfo = driver.findElement(By.xpath("(//button[@id='save-btn'])[5]"));
		j.executeScript("arguments[0].click();", savecontactinfo);

		// specailaity

		/*
		 * WebElement add = driver.findElement(By.xpath("(//div[@id='add-btn'])[3]"));
		 * 
		 * sleep(2000); js.executeScript("arguments[0].click();", add); WebElement rr =
		 * driver.findElement(By.
		 * xpath("(//input[@placeholder='Select or Enter Specialty'])[1]"));
		 * sleep(2000); actions("click", rr); sleep(2000);
		 * rr.sendKeys("abdominal surgery"); sleep(3000); WebElement splm =
		 * driver.findElement(By.xpath("//span[text()='Abdominal Surgery']"));
		 * actions("click", splm);
		 * driver.findElement(By.xpath("(//button[@id='save-btn'])[7]")).click();
		 * sleep(3000); WebElement splrem =
		 * driver.findElement(By.xpath("//div[text()='Abdominal Surgery']"));
		 * actions("click", splrem); sleep(3000); WebElement spldel =
		 * driver.findElement(By.xpath("(//img[@id='del-btn'])[1]"));
		 * j.executeScript("arguments[0].click();", spldel);
		 * 
		 */ sleep(2000);

		// patient info
		WebElement patientinfoedit = driver.findElement(By.xpath("(//span[@id='edit-btn'])[1]"));
		actions("click", patientinfoedit);
		sleep(2000);
		WebElement gender = driver.findElement(By.xpath("(//button[@id='gender'])[1]"));
		j.executeScript("arguments[0].click();", gender);
		List<WebElement> genderdropdown = driver
				.findElements(By.xpath("(//button[@id='gender'])[1]//following::ul[1]/li"));
		for (WebElement web : genderdropdown) {
			if (web.getText().trim().equals("Male")) {
				web.click();
				break;
			}

		}
		WebElement education = driver.findElement(By.xpath("(//input[@id='educationform'])[1]"));
		education.clear();
		education.sendKeys("B.tech");
		sleep(2000);
		WebElement license = driver.findElement(By.xpath("(//input[@id='licensenum'])[1]"));
		license.clear();
		sleep(2000);
		license.sendKeys("trt43534");
		sleep(2000);
		WebElement saveadmininfo = driver.findElement(By.xpath("(//button[@id='save-btn'])[1]"));
		j.executeScript("arguments[0].click();", saveadmininfo);

		driver.findElement(By.xpath("(//button[@onclick='Page.goBack()'])[1]")).click();
		sleep(2000);
		driver.findElement(By.xpath("//button[@onclick='setting.changep()']")).click();
		driver.findElement(By.id("currentPassword")).sendKeys("1");
		driver.findElement(By.id("newPassword")).sendKeys("1");
		driver.findElement(By.id("confirmNewPassword")).sendKeys("1");

		driver.findElement(By.xpath("//button[@onclick='changep.save()']")).click();
		sleep(3000);

		// Manage users...
		WebElement manageuser = driver.findElement(By.xpath("//button[@onclick='setting.userList()']"));
		manageuser.click();
		sleep(2000);
		WebElement adduser = driver.findElement(By.xpath("//button[@onclick='Health.user_new()']"));
		adduser.click();
		sleep(2000);
		driver.findElement(By.xpath("//input[@id='Firstname']")).sendKeys("Akash");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("N");
		sleep(2000);
		driver.findElement(By.xpath("//input[@id='UserLoginId']")).sendKeys("Akashn1212@gmail.com");
		sleep(2000);
		driver.findElement(By.xpath("//button[@id='user_dropdown']")).click();
		List<WebElement> usserdrp = driver.findElements(By.xpath("//button[@id='user_dropdown']//following::ul[1]/li"));
		for (WebElement web : usserdrp) {
			if (web.getText().trim().equals("Standard User")) {
				web.click();
				break;
			}

		}
		sleep(2000);
		WebElement phone = driver.findElement(By.xpath("//input[@id='PhonE']"));
		phone.sendKeys("2013556237");
		sleep(2000);
		WebElement createuser = driver.findElement(By.xpath("(//button[@id='createButton'])[2]"));
		WebElement canceluser = driver.findElement(By.xpath("(//button[@onclick='window.history.back()'])[2]"));
		canceluser.click(); // createuser.click(); sleep(3000);
		click(pom.getInstanceSetting().clickSettings);
		sleep(5000);

		driver.findElement(By.xpath("//button[@id='auto-logout-time']")).click();
		sleep(2000);
		List<WebElement> time = driver.findElements(By.xpath("//ul[@id='logoutt']/li"));
		for (WebElement w : time) {

			if (w.getText().trim().equals("2 Hour")) {
				w.click();
				break;
			}

		}
		sleep(2000);

		driver.findElement(By.xpath("//button[@id='taxbutton']")).click();

		WebElement cl = driver.findElement(By.xpath(
				"/html/body/div[4]/div[4]/div[1]/div[2]/div/table/tbody/tr/td[2]/div/div[6]/div[8]/div/div[1]/div[1]/div[6]/div[1]/div/div[3]/div[1]/ul/li/a"));
		actions("click", cl);
		sleep(3000);
		driver.findElement(By.xpath("(//span[@id='plus-add'])[1]")).click();
		sleep(4000);
		driver.findElement(By.xpath("(//input[@id='description'])[2]")).sendKeys("DK");
		driver.findElement(By.xpath("(//input[@id='percentage'])[2]")).sendKeys("5");

		driver.findElement(By.xpath("(//button[@id='save-btn'])[10]")).click();
		sleep(3000);
		WebElement mn = driver.findElement(By.xpath("//div[text()='DK']"));
		actions("click", mn);
		sleep(3000);
		driver.findElement(By.xpath("(//span[@id='del-btn'])[2]")).click();
		sleep(2000);
		driver.findElement(By.xpath("(//span[@title='Cancel'])[2]")).click();
		sleep(3000);

		// cds
		WebElement cdsclick = driver.findElement(By.xpath("//button[contains(text(),'Clinical Decision')]"));
		cdsclick.click();
		sleep(2000);
		WebElement newcds = driver.findElement(By.xpath("//span[contains(text(),'New Clinical')]"));
		newcds.click();
		sleep(2000);
		driver.findElement(By.xpath("(//input[@id='description'])[3]")).sendKeys("Akash");
		sleep(2000);
		WebElement scrolltill = driver.findElement(By.xpath("//input[@id='weight_from']"));
		ScriptExecutor(scrolltill);
		sleep(2000);

		driver.findElement(By.xpath("(//input[@id='problem_icd_10'])[2]")).sendKeys("test");
		sleep(3000);
		WebElement actionproblem = driver.findElement(By.xpath("//div[text()='Malignant neoplasm of testis']"));
		actions("click", actionproblem);
		sleep(2000);
		WebElement activecheck = driver.findElement(By.xpath("//input[@id='active']"));
		actions("click", activecheck);
		sleep(2000);
		WebElement savecds = driver.findElement(By.xpath("//input[@id='active']//following::div[4]/div/button"));
		j.executeScript("arguments[0].click();", savecds);
		sleep(4000);

		WebElement clicksett = driver.findElement(By.xpath("//td[text()='Settings']"));
		j.executeScript("arguments[0].click();", clicksett);
		sleep(4000);
		ScriptExecutor(cdsclick);

		sleep(3000);

		// Set Favorities..

		/*
		 * driver.findElement(By.xpath("//button[@onclick='setfavdropdown();']")).click(
		 * ); sleep(3000); List<WebElement> setfav =
		 * driver.findElements(By.xpath("//ul[@id='setfavoritesul']/li")); for
		 * (WebElement w : setfav) { if (w.getText().trim().equals("Symptoms")) {
		 * w.click(); break; }
		 * 
		 * } implicitWait(30, TimeUnit.SECONDS); sleep(3000);
		 * driver.findElement(By.xpath(
		 * "(//div[@id='symptom'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[2]"))
		 * .click(); sleep(3000); driver.findElement(By.
		 * xpath("(//div[text()='ICD-10-CM R'])[2]//following::div[1]/div/textarea"))
		 * .sendKeys("R10.81: Abdominal tenderness"); sleep(5000); WebElement clicksym =
		 * driver.findElement(By.xpath("//div[text()='R10.81: Abdominal tenderness']"));
		 * actions("click", clicksym); sleep(3000); driver.findElement(By.xpath(
		 * "(//div[text()='Symptom'])[2]//following::div[1]/textarea")).sendKeys("fever"
		 * );
		 * 
		 * sleep(3000);
		 * 
		 * driver.findElement(By.xpath("(//button[@id='save-btn'])[12]")).click();
		 * sleep(3000); WebElement ff = driver.findElement(By.
		 * xpath("(//div[text()='R10.81: Abdominal tenderness'])[1]")); actions("click",
		 * ff); sleep(2000);
		 * driver.findElement(By.xpath("(//span[@id='del-btn'])[4]")).click();
		 * sleep(4000); WebElement back =
		 * driver.findElement(By.xpath("(//span[@id='assign-cancel-btn'])[1]"));
		 * j.executeScript("arguments[0].click();", back); sleep(4000); implicitWait(30,
		 * TimeUnit.SECONDS);
		 * 
		 * // problems.
		 * 
		 * driver.findElement(By.xpath("//button[@onclick='setfavdropdown();']")).click(
		 * ); sleep(3000); List<WebElement> setfav1 =
		 * driver.findElements(By.xpath("//ul[@id='setfavoritesul']/li")); for
		 * (WebElement w : setfav1) { if (w.getText().trim().equals("Problems")) {
		 * w.click(); break; }
		 * 
		 * } WebElement problemadd =
		 * driver.findElement(By.xpath("(//span[@id='plus-add'])[3]"));
		 * j.executeScript("arguments[0].click();", problemadd); sleep(2000);
		 * driver.findElement(By.xpath("(//textarea[@id='longDescription'])[4]")).
		 * sendKeys("test"); sleep(2000); List<WebElement> prbq =
		 * driver.findElements(By.xpath("//ul[@id='ui-id-27']/li/a/div/span")); for
		 * (WebElement web : prbq) {
		 * 
		 * if (web.getText().trim().equals("Malignant neoplasm of testis")) {
		 * web.click(); break; } }
		 * 
		 * sleep(2000); driver.findElement(By.xpath(
		 * "(//div[text()='Notes'])[2]//following::div[1]/textarea")).sendKeys("high");
		 * sleep(2000); WebElement saveproblem =
		 * driver.findElement(By.xpath("(//div[@id='save-btn'])[2]"));
		 * saveproblem.click(); sleep(2000);
		 * 
		 * WebElement editprob =
		 * driver.findElement(By.xpath("//span[text()='Malignant neoplasm of testis']"))
		 * ; ww.until(ExpectedConditions.elementToBeClickable(editprob));
		 * 
		 * actions("click", editprob); sleep(3000); WebElement delprob =
		 * driver.findElement(By.xpath("(//span[@id='del-btn'])[6]"));
		 * j.executeScript("arguments[0].click();", delprob);
		 * 
		 * WebElement cancelfavprob = driver .findElement(By.
		 * xpath("(//span[contains(text(),'Favorite Problems')])[1]//parent::div/span[1]"
		 * )); j.executeScript("arguments[0].click();", cancelfavprob); sleep(5000);
		 * implicitWait(30, TimeUnit.SECONDS);
		 * 
		 * // Medication...
		 * 
		 * driver.findElement(By.xpath("//button[@onclick='setfavdropdown();']")).click(
		 * ); sleep(3000); List<WebElement> setfav2 =
		 * driver.findElements(By.xpath("//ul[@id='setfavoritesul']/li")); for
		 * (WebElement w : setfav2) { if (w.getText().trim().equals("Medications")) {
		 * w.click(); break; }
		 * 
		 * } WebElement clickaddmed = driver .findElement(By.xpath(
		 * "(//div[@id='drug'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[2]"));
		 * javascriptclick(clickaddmed); sleep(2000); WebElement addmeddis = driver
		 * .findElement(By.xpath(
		 * "(//div[text()='Medication*'])[2]//following::div[1]/div[1]/input"));
		 * addmeddis.sendKeys("test"); sleep(3000); List<WebElement> meddrop = driver
		 * .findElements(By.xpath(
		 * "(//div[@id='drug'])/div[3]/div/div/div[2]/ul/li/a/div/small/em"));
		 * System.out.println("size of med drp:" + meddrop.size()); for (WebElement we :
		 * meddrop) { if (we.getText().trim().equals("RXNORM = 1190953")) { we.click();
		 * break; }
		 * 
		 * }
		 * 
		 * sleep(2000); WebElement savemed =
		 * driver.findElement(By.xpath("(//div[@id='save-btn'])[4]"));
		 * j.executeScript("arguments[0].click();", savemed); sleep(2000); WebElement
		 * editmed = driver.findElement( By.
		 * xpath("//div[text()='24 HR testosterone 0.0833 MG/HR Transdermal System 0.0833 MG/HR']"
		 * )); actions("click", editmed);
		 * 
		 * sleep(4000); WebElement deletemed =
		 * driver.findElement(By.xpath("(//span[@id='del-btn'])[8]"));
		 * j.executeScript("arguments[0].click();", deletemed); sleep(3000); WebElement
		 * backmed = driver .findElement(By.
		 * xpath("(//span[text()='Favorite Medications'])[1]//parent::div/span[1]"));
		 * j.executeScript("arguments[0].click();", backmed); sleep(5000);
		 * 
		 * // Test Order...
		 * 
		 * driver.findElement(By.xpath("//button[@onclick='setfavdropdown();']")).click(
		 * ); sleep(3000); List<WebElement> setfav3 =
		 * driver.findElements(By.xpath("//ul[@id='setfavoritesul']/li")); for
		 * (WebElement w : setfav3) { if (w.getText().trim().equals("Test Order")) {
		 * w.click(); break; }
		 * 
		 * } sleep(2000); WebElement clicktestorder = driver .findElement(By.xpath(
		 * "(//div[@id='test-order'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[2]"
		 * )); actions("click", clicktestorder); sleep(2000); driver.findElement( By.
		 * xpath("(//div[text()='LOINC / TEST CODE / DESCRIPTION*'])[2]//following::div[1]/div/textarea"
		 * )) .sendKeys("test"); sleep(3000); List<WebElement> testdrop = driver
		 * .findElements(By.xpath(
		 * "(//div[@id='test-order'])[1]/div[3]/div/div/div[2]/ul/li/a/div/span/em"));
		 * for (WebElement web : testdrop) { if
		 * (web.getText().trim().equals("LOINC-NUM :5802-4")) { web.click(); break; }
		 * 
		 * }
		 * 
		 * WebElement savetestorder =
		 * driver.findElement(By.xpath("(//div[@id='save-btn'])[6]"));
		 * j.executeScript("arguments[0].click();", savetestorder); sleep(4000);
		 * WebElement editest = driver .findElement(By.
		 * xpath("(//span[text()='Nitrite [Presence] in Urine by Test strip'])[1]"));
		 * actions("click", editest); sleep(4000); WebElement deltetest =
		 * driver.findElement(By.xpath("(//span[@id='del-btn'])[10]"));
		 * j.executeScript("arguments[0].click();", deltetest); WebElement canceltest =
		 * driver .findElement(By.
		 * xpath("(//span[text()='Favorite Test Order'])[1]//parent::div/span[1]"));
		 * 
		 * ww.until(ExpectedConditions.elementToBeClickable(canceltest));
		 * j.executeScript("arguments[0].click();", canceltest); sleep(5000);
		 * 
		 * // Item/services
		 * driver.findElement(By.xpath("//button[@onclick='setfavdropdown();']")).click(
		 * ); sleep(3000); List<WebElement> setfav4 =
		 * driver.findElements(By.xpath("//ul[@id='setfavoritesul']/li")); for
		 * (WebElement w : setfav4) { if (w.getText().trim().equals("Item/service")) {
		 * w.click(); break; }
		 * 
		 * } WebElement clickadditem = driver .findElement(By.xpath(
		 * "(//div[@id='invoiceAdd'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[2]"
		 * )); j.executeScript("arguments[0].click();", clickadditem);
		 * driver.findElement(By.xpath(
		 * "(//label[text()='Item/Service*'])[2]//following::div[1]/div/input"))
		 * .sendKeys("test"); driver.findElement(By.xpath(
		 * "(//label[text()='Price*'])[2]//following::div[1]/input")).sendKeys("5");
		 * WebElement saveitem =
		 * driver.findElement(By.xpath("(//button[@id='save-btn'])[14]")); sleep(4000);
		 * j.executeScript("arguments[0].click();", saveitem); sleep(4000); WebElement
		 * edititem = driver.findElement(By.xpath("//span[text()='test']"));
		 * actions("click", edititem); WebElement deleteitem =
		 * driver.findElement(By.xpath("(//span[@id='del-btn'])[12]"));
		 * j.executeScript("arguments[0].click();", deleteitem); WebElement
		 * itemservicebackarrow = driver .findElement(By.
		 * xpath("(//span[contains(text(),'Favorite Item/Service')])[2]//parent::div/span[1]"
		 * )); j.executeScript("arguments[0].click();", itemservicebackarrow);
		 * WebElement aaa =
		 * driver.findElement(By.xpath("(//span[@id='assign-cancel-btn'])[5]"));
		 * javascriptclick(aaa); sleep(3000);
		 * 
		 * // Message Favorite...
		 * 
		 * driver.findElement(By.xpath("//button[@onclick='setfavdropdown();']")).click(
		 * ); sleep(3000); List<WebElement> setfav5 =
		 * driver.findElements(By.xpath("//ul[@id='setfavoritesul']/li")); for
		 * (WebElement w : setfav5) { if (w.getText().trim().equals("Message")) {
		 * w.click(); break; }
		 * 
		 * } WebElement addnewfavmessage = driver .findElement(By.xpath(
		 * "(//div[@id='message'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[2]"));
		 * j.executeScript("arguments[0].click();", addnewfavmessage); sleep(2000);
		 * driver.findElement(By.xpath(
		 * "(//div[contains(text(),'Add your favorite message and use them quickly')])[2]//following::div[2]/textarea"
		 * )) .sendKeys("hello"); WebElement savemesssage =
		 * driver.findElement(By.xpath("(//div[@id='save-btn'])[8]"));
		 * j.executeScript("arguments[0].click();", savemesssage); sleep(4000);
		 * WebElement editmessage =
		 * driver.findElement(By.xpath("//div[text()='hello']")); actions("click",
		 * editmessage); WebElement deletemessage =
		 * driver.findElement(By.xpath("(//span[@id='del-btn'])[14]"));
		 * j.executeScript("arguments[0].click();", deletemessage); sleep(3000);
		 * WebElement gobackmessage = driver .findElement(By.
		 * xpath("(//span[text()='Favorite Message'])[1]//parent::div/span[1]"));
		 * j.executeScript("arguments[0].click();", gobackmessage); sleep(3000);
		 * 
		 * // Advance Directives...
		 * 
		 * driver.findElement(By.xpath("//button[@onclick='setfavdropdown();']")).click(
		 * ); sleep(3000); List<WebElement> setfav6 =
		 * driver.findElements(By.xpath("//ul[@id='setfavoritesul']/li")); for
		 * (WebElement w : setfav6) { if
		 * (w.getText().trim().equals("Advance Directives")) { w.click(); break; }
		 * 
		 * } sleep(3000); WebElement addadvfav = driver .findElement(By.xpath(
		 * "(//div[@id='directives'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[2]"
		 * )); j.executeScript("arguments[0].click();", addadvfav); sleep(3000);
		 * WebElement selectadvdir = driver .findElement(By.
		 * xpath("(//select[contains(@title,'Select Directive type')])[2]"));
		 * selectadvdir.click(); sleep(3000);
		 * 
		 * dropDown("text", selectadvdir, "Assessment"); driver.findElement(By.
		 * xpath("//textarea[contains(@title,'Enter favorite Assessment details')]"))
		 * .sendKeys("test"); WebElement saveaddvancedir =
		 * driver.findElement(By.xpath("(//div[@id='save-btn'])[10]"));
		 * js.executeScript("arguments[0].click();", saveaddvancedir); sleep(3000);
		 * WebElement editadv =
		 * driver.findElement(By.xpath("//span[text()='Assessment']")); actions("click",
		 * editadv); WebElement deladv =
		 * driver.findElement(By.xpath("(//span[@id='del-btn'])[16]"));
		 * j.executeScript("arguments[0].click();", deladv);
		 * 
		 * WebElement gobackadvfav = driver.findElement( By.
		 * xpath("(//span[contains(text(),'Favorite Advance Directives')])[1]//parent::div/span[1]"
		 * )); j.executeScript("arguments[0].click();", gobackadvfav); sleep(3000);
		 * 
		 * // status
		 * 
		 * driver.findElement(By.xpath("//button[@onclick='setfavdropdown();']")).click(
		 * ); sleep(3000); List<WebElement> setfav7 =
		 * driver.findElements(By.xpath("//ul[@id='setfavoritesul']/li")); for
		 * (WebElement w : setfav7) { if (w.getText().trim().equals("Status")) {
		 * w.click(); break; }
		 * 
		 * }
		 * 
		 * WebElement addstatus = driver.findElement( By.xpath(
		 * "(//div[@id='status-module'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[2]"
		 * )); j.executeScript("arguments[0].click();", addstatus); sleep(2000);
		 * WebElement selectstatus = driver.findElement(By.
		 * xpath("(//select[contains(@title,'Select Status type')])[2]"));
		 * dropDown("text", selectstatus, "Functional status"); sleep(2000);
		 * driver.findElement(By.
		 * xpath("(//select[contains(@title,'Select Status type')])[2]//following::div[4]/textarea"
		 * )) .sendKeys("hello");
		 * 
		 * WebElement clickdatebox = driver .findElement(By.
		 * xpath("(//div[contains(@title,'Select or Enter Effective Date')])[2]/div/input"
		 * )); clickdatebox.click(); sleep(3000); WebElement choosedate =
		 * driver.findElement(By.xpath("(//a[contains(text(),'13')])[2]"));
		 * choosedate.click();
		 * 
		 * WebElement savestatus =
		 * driver.findElement(By.xpath("(//div[@id='save-btn'])[12]"));
		 * j.executeScript("arguments[0].click();", savestatus);
		 * 
		 * sleep(3000); WebElement editstatus =
		 * driver.findElement(By.xpath("//div[text()='Functional Status']"));
		 * actions("click", editstatus); WebElement delstatus =
		 * driver.findElement(By.xpath("(//span[@id='del-btn'])[18]"));
		 * j.executeScript("arguments[0].click();", delstatus); sleep(3000); WebElement
		 * backtofav = driver .findElement(By.
		 * xpath("(//span[contains(text(),'Favorite Status')])[1]//parent::div/span[1]")
		 * ); j.executeScript("arguments[0].click();", backtofav); sleep(3000);
		 */
		// Hospital codes... // Item/service code...

		/*
		 * driver.findElement(By.xpath("//button[@onclick='hospitalcodedropdown();']")).
		 * click(); List<WebElement> fh =
		 * driver.findElements(By.xpath("//ul[@id='hospitalcodeul']/li")); for
		 * (WebElement w : fh) { if (w.getText().trim().equals("Item/Service Code")) {
		 * 
		 * w.click(); break; }
		 * 
		 * } sleep(3000); WebElement itemcodeadd = driver .findElement(By.xpath(
		 * "(//div[@id='item-code'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[3]")
		 * ); javascriptclick(itemcodeadd); sleep(3000); driver.findElement(By.
		 * xpath("(//label[text()='Item/Service Code*'])[2]//following::div[1]/input"))
		 * .sendKeys("160899"); driver.findElement( By.
		 * xpath("(//label[text()='Item/Service Name/Description*'])[2]//following::div[1]/textarea"
		 * )) .sendKeys("Birthday"); driver.findElement(By.
		 * xpath("(//input[contains(@title,'Enter price value for the Item/Service')])[2]"
		 * )) .sendKeys("5");
		 * 
		 * sleep(2000); WebElement hg =
		 * driver.findElement(By.xpath("(//button[@id='save-btn'])[16]"));
		 * js.executeScript("arguments[0].click();", hg); sleep(3000); WebElement cc =
		 * driver.findElement(By.xpath("//div[text()='160899']")); actions("click", cc);
		 * sleep(3000); WebElement vv =
		 * driver.findElement(By.xpath("(//span[@id='del-btn'])[20]"));
		 * js.executeScript("arguments[0].click();", vv); WebElement th = driver
		 * .findElement(By.
		 * xpath("(//span[contains(text(),'Item/Service Code')])[1]//parent::div/span[1]"
		 * )); js.executeScript("arguments[0].click();", th); sleep(3000);
		 * 
		 * // procedure code...
		 * 
		 * driver.findElement(By.xpath("//button[@onclick='hospitalcodedropdown();']")).
		 * click(); List<WebElement> fh22 =
		 * driver.findElements(By.xpath("//ul[@id='hospitalcodeul']/li")); for
		 * (WebElement w : fh22) { if (w.getText().trim().equals("Procedure Code")) {
		 * 
		 * w.click(); break; }
		 * 
		 * } WebElement addnewprocedurecode = driver.findElement( By.xpath(
		 * "(//div[@id='procedure-code'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[3]"
		 * )); j.executeScript("arguments[0].click();", addnewprocedurecode);
		 * sleep(3000); driver.findElement(By.
		 * xpath("(//label[text()='Procedure Code*'])[2]//following::div[1]/input"))
		 * .sendKeys("Procedure12"); driver.findElement(By.
		 * xpath("(//label[text()='Procedure Name/Description*'])[2]//following::div[1]/textarea"
		 * )) .sendKeys("medicine"); driver.findElement(By.
		 * xpath("(//input[contains(@title,'Enter amount value for the procedure')])[2]"
		 * )) .sendKeys("2"); WebElement saveprocedure =
		 * driver.findElement(By.xpath("(//button[@id='save-btn'])[18]"));
		 * j.executeScript("arguments[0].click();", saveprocedure); sleep(3000);
		 * WebElement editprocedure =
		 * driver.findElement(By.xpath("//div[text()='PROCEDURE12']")); actions("click",
		 * editprocedure); WebElement delprocd =
		 * driver.findElement(By.xpath("(//span[@id='del-btn'])[22]"));
		 * j.executeScript("arguments[0].click();", delprocd); WebElement gobackproced =
		 * driver .findElement(By.
		 * xpath("(//span[text()='Procedure Code'])[3]//parent::div/span[1]"));
		 * j.executeScript("arguments[0].click();", gobackproced);
		 * 
		 * // Medication code..
		 * 
		 * driver.findElement(By.xpath("//button[@onclick='hospitalcodedropdown();']")).
		 * click(); List<WebElement> fh22t =
		 * driver.findElements(By.xpath("//ul[@id='hospitalcodeul']/li")); for
		 * (WebElement w : fh22t) { if (w.getText().trim().equals("Medication Code")) {
		 * 
		 * w.click(); break; }
		 * 
		 * } WebElement clickaddmedd = driver .findElement(By.xpath(
		 * "(//div[@id='drug-code'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[3]")
		 * ); j.executeScript("arguments[0].click();", clickaddmedd); sleep(3000);
		 * driver.findElement(By.
		 * xpath("(//label[text()='Medication Code*'])[2]//following::div[1]/input"))
		 * .sendKeys("MED1"); driver.findElement(By.
		 * xpath("(//input[contains(@title,'Enter name of the Medication')])[2]"))
		 * .sendKeys("medication med"); driver.findElement(By.xpath(
		 * "(//label[text()='Strength'])[4]//following::div[1]/input")).sendKeys(
		 * "powerful"); driver.findElement(By.xpath(
		 * "(//label[text()='Company'])[2]//following::div[1]/input")).sendKeys(
		 * "kaaspro"); driver.findElement(By.
		 * xpath("(//input[contains(@title,'Enter amount value for the Medication')])[2]"
		 * )) .sendKeys("3"); WebElement savemedication = driver.findElement(By.xpath(
		 * "(//input[contains(@title,'Enter amount value for the Medication')])[2]//following::div[27]/button"
		 * )); j.executeScript("arguments[0].click();", savemedication); sleep(3000);
		 * WebElement editmedication =
		 * driver.findElement(By.xpath("(//div[@id='drug-code-text'])[2]"));
		 * actions("click", editmedication); sleep(3000); driver.findElement(By.xpath(
		 * "((//div[contains(text(),'Enter details of the Medication like code, name and optionally amount')])[2]//parent::div[1]//parent::div[1]//following::span[1]//following::div[1]//following::span[1])[1]"
		 * )) .click();
		 * 
		 * WebElement gobackmed = driver .findElement(By.
		 * xpath("(//span[text()='Medication Code'])[1]//parent::div/span[1]"));
		 * j.executeScript("arguments[0].click();", gobackmed); sleep(3000);
		 */
		// forms...

		driver.findElement(By.xpath("//button[@id='form-script']")).click();
		sleep(5000);

		List<WebElement> numberofformspresent = driver
				.findElements(By.xpath("(//div[@class='form-pop-body'])[1]/div/div[1]"));
		int ffs = numberofformspresent.size();

		int u;
		for (int imp = 3; imp <= ffs; imp++) {

			// u = 1 + i;
			WebElement rtt = driver.findElement(
					By.xpath("(//div[@class='form-pop-body'])[1]/div[" + imp + "]//following::div[5]/span[2]"));

			if (rtt.getText().trim().equals("form100")) {

				rtt.click();
				WebElement jsp = driver.findElement(By.xpath("(//span[@id='del-form'])[1]"));
				visbility(driver, jsp, 25);
				javascriptclick(jsp);

				break;

			}

		}
		sleep(3000);

		driver.navigate().refresh();
		WebElement jsp = driver.findElement(By.xpath("//button[@id='form-script']"));
		visbility(driver, jsp, 25);
		javascriptclick(jsp);

		WebElement addfrm = driver.findElement(By.xpath("//div[@id='FormsKpop2']/div[1]/span"));
		visbility(driver, addfrm, 25);

		actions("click", addfrm);
		sleep(4000);
		driver.findElement(By.xpath("(//label[text()='Form Title*'])[1]//following::input[1]")).sendKeys("form100");

		List<WebElement> drk = driver.findElements(By.xpath("(//div[@id='build-wrap'])[1]/div[1]/div[2]/ul/li"));

		for (WebElement web : drk) {

			if (web.getText().trim().equals("Checkbox Group")) {

				WebElement drop = driver.findElement(
						By.xpath("(//div[contains(@data-content,'Drag a field from the right to this area')])[1]/ul"));

				Actions ac = new Actions(driver);
				ac.dragAndDrop(web, drop).build().perform();
				driver.findElement(By.xpath("//label[text()='Label']//following::div[1]/input")).clear();
				driver.findElement(By.xpath("//label[text()='Label']//following::div[1]/input"))
						.sendKeys("Kaaspro Enterprise");
				driver.findElement(By.xpath("(//div[@id='build-wrap'])[1]/div[1]/div[2]/ul//following::div[1]/button"))
						.click();
				sleep(3000);

				break;
			}

		}

		/*
		 * WebElement cancelfrm =
		 * driver.findElement(By.xpath("//div[@id='FormsKpop2']/div[1]/div[2]/span"));
		 * javascriptclick(cancelfrm);
		 */
		sleep(3000); // edit preference....

		driver.findElement(By.xpath("//button[@id='edit-print-preference']")).click();
		sleep(3000);
		driver.findElement(By.xpath("(//button[@id='cancel-btn'])[18]")).click();
		sleep(3000);
		driver.findElement(By.xpath("//button[@title='Reset All Your Setting']")).click();
		sleep(2000);
		WebElement df = driver.findElement(By.xpath("(//button[text()='Cancel'])[1]"));
		js.executeScript("arguments[0].click();", df);
		sleep(3000);

		// notification

		WebElement er = driver.findElement(By.xpath("//span[text()='Edit Notification Messages']"));
		WebElement notify = driver.findElement(By.xpath("//button[@id='custom-notify']"));

		boolean b = true;
		while (b) {

			if (!notify.isDisplayed()) {
				WebElement uip = driver.findElement(By
						.xpath("//span[text()='Edit Notification Messages']//parent::div//parent::div[1]/label/input"));

				actions("click", uip);

				javascriptclick(notify);
				WebElement sd = driver.findElement(By.xpath("(//span[@class='slider1 round1'])[4]"));
				ScriptExecutor(sd);
				sleep(2000);
				js.executeScript("window.scrollBy(0,0)");

			}

			b = false;
		}
		sleep(1500);
		// notify ehr complte...
		WebElement ntfyehr = driver.findElement(By
				.xpath("//span[text()='Notify user when EHR is completed.']//parent::div//parent::div[1]/label/input"));
		ScriptExecutor(ntfyehr);
		actions("click", ntfyehr);

		// set interval time for emial...

		WebElement rr = driver.findElement(By
				.xpath("//span[text()='Set interval for receiving emails.']//parent::div//parent::div[1]/label/input"));

		actions("click", rr);

		// Audit Report...

		sleep(3000);
		click(pom.getInstanceSetting().clickSettings);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.navigate().refresh();
		sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='setting.audit()']")));
		WebElement rqqa = driver.findElement(By.xpath("//button[@onclick='setting.audit()']"));
		ScriptExecutor(rqqa);
		javascriptclick(rqqa);
		sleep(3000);
		driver.findElement(By.xpath("(//input[@id='patientPartyName'])[1]")).sendKeys(kpid);
		sleep(3000);
		List<WebElement> patdr = driver
				.findElements(By.xpath("//div[@id='vvid']//following::ul[1]/li/a/table/tbody/tr[1]/td[2]"));
		for (WebElement we : patdr) {

			if (we.getText().contains(kpid)) {
				System.out.println("true");

				actions("click", we);
				break;
			}

		}

		sleep(3000);
		/*
		 * driver.findElement(By.xpath("(//input[@id='userPartyName'])[2]"))
		 * .sendKeys(ConfigManager.getconfigManager().getInstanceConfigReader().
		 * doctorKpid()); List<WebElement> userd = driver .findElements(By.xpath(
		 * "//div[@id='vvid']//following::ul[2]/li/a/table/tbody/tr[1]/td[2]"));
		 * 
		 * for (WebElement we : userd) { if
		 * (we.getText().contains(ConfigManager.getconfigManager().
		 * getInstanceConfigReader().doctorKpid())) {
		 * 
		 * we.click(); break;
		 * 
		 * }
		 * 
		 * }
		 */
		WebElement seldate = driver.findElement(By.xpath("//select[@id='byDate']"));
		dropDown("text", seldate, "All");
		sleep(4000);
		WebElement selmod = driver.findElement(By.xpath("//select[@id='auditEventModule']"));
		dropDown("text", selmod, "Allergy");
		sleep(3000);
		driver.findElement(By.xpath("//button[@id='advanceSearching']")).click();

		// explicitWait(50, pom.getInstanceSetting().clickSettings);
		sleep(9000);
		click(pom.getInstanceSetting().clickSettings);
		sleep(2000);

		/*
		 * explicitWait(40, pom.getInstanceSetting().scrollTillDelete);
		 * ScriptExecutor(pom.getInstanceSetting().scrollTillDelete); sleep(2000);
		 */
		ScriptExecutor(pom.getInstanceSetting().scrollTillTax);
		sleep(2000);
		ScriptExecutor(pom.getInstanceSetting().scrollTillHospitalCode);
		sleep(2000);
		// ScriptExecutor(pom.getInstanceSetting().scrollTillPrintSettings); //
		sleep(2000); //
		ScriptExecutor(pom.getInstanceSetting().scrollTillSubscription); //
		sleep(2000); //
		ScriptExecutor(pom.getInstanceSetting().scrollTillNotification); //
		sleep(2000); //
		ScriptExecutor(pom.getInstanceSetting().scrollTillAuditReport); // //
		// JavascriptExecutor js = (JavascriptExecutor) driver; // //
		js.executeScript("window.scroll(0,0)");
		sleep(2000);

		// Dashboard

		WebElement dsbrd = driver.findElement(By.xpath("(//div[@id='option-setting'])[1]/div/img"));
		actions("click", dsbrd);
		List<WebElement> ths = driver
				.findElements(By.xpath("(//div[@id='option-setting'])[1]/div/img//following::ul[1]/li"));

		while (true) {

			for (WebElement st : ths) {
				if (st.getText().trim().equals("Home")) {
					st.click();
					sleep(1000);
					actions("click", dsbrd);
				} else if (st.getText().trim().equals("Dashboard")) {
					st.click();
					sleep(2000);
					actions("click", dsbrd);
				} else if (st.getText().trim().equals("Quick Tour")) {
					st.click();
					WebElement cncltr = driver.findElement(By.xpath("(//li[text()='NO, CANCEL TOUR'])[1]"));
					javascriptclick(cncltr);
					sleep(2000);
					actions("click", dsbrd);
				} else if (st.getText().trim().equals("Settings")) {
					st.click();
					sleep(2000);
					actions("click", dsbrd);
				} else if (st.getText().trim().equals("Migration Services")) {
					st.click();
					WebElement clmgr = driver
							.findElement(By.xpath("//h4[text()='Migration Services']//parent::div/button"));
					sleep(2000);
					actions("click", clmgr);
					sleep(2000);
					actions("click", dsbrd);
				} else if (st.getText().trim().equals("Sign out")) {
					st.click();

					break;
				}

			}
			break;
		}

	}

}
