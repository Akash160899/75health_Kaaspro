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

public class Health_rec23 extends Base_Class {

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
		JavascriptExecutor jss = (JavascriptExecutor) driver;
		jss.executeScript("window.scrollBy(0,350)", "");
		implicitWait(30, TimeUnit.SECONDS);
		// driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> rowfor = driver.findElements(By.xpath("(//div[@id='cols'])[2]/div"));
		System.out.println(1);
		int ehrrow = rowfor.size();

		boolean bl = false;
		for (int i = 1; i <= ehrrow; i++) {

			List<WebElement> ds = driver.findElements(By.xpath("(//div[@id='cols'])[2]/div[" + i + "]/div"));

			for (int b = 1; b < ds.size(); b++) {
				WebElement sf = driver.findElement(By.xpath("(//div[@id='cols'])[2]/div[" + i + "]/div[" + b + "]"));

				if (sf.isDisplayed() == false) {

					bl = true;
					driver.findElement(By.xpath("(//button[@id='options-img'])[1]")).click();
					List<WebElement> fin = driver.findElements(By.xpath("(//ul[@id='matchKey'])[2]/li/span/a"));
					driver.findElement(By.xpath("(//input[@id='optionsSearch'])[2]")).sendKeys("show");
					implicitWait(30, TimeUnit.SECONDS);
					for (WebElement web : fin) {

						if (web.getText().trim().equals("Show Allergy")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Alert")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Social History")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Family Health")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Symptoms")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Problems")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Vital")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Visit Reason")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Procedure")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Medications")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Test Order")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Note")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Vaccine")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Attach File")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Inpatient")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Referral")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Custom-form")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Goals")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Amendment")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Implantable Devices")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Advance Directives")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Physical Examination")) {

							actions("click", web);

						}
						if (web.getText().trim().equals("Show Status")) {

							actions("click", web);

						} else {

							continue;
						}

					}

				}

			}
			if (bl == true) {
				driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/button")).click();
				break;
			}
		}

		for (int i = 1; i <= ehrrow; i++) {

			List<WebElement> qf = driver.findElements(By.xpath("(//div[@id='cols'])[2]/div[" + i + "]/div"));
			for (int j = 1; j <= qf.size(); j++) {

				WebElement gettag = driver
						.findElement(By.xpath("(//div[@id='cols'])[2]/div[" + i + "]/div[" + j + "]"));

				String tagnames = gettag.getAttribute("id");
				// System.out.println(tagnames);

				if (tagnames.equals("vital")) {

					sleep(4000);
					implicitWait(30, TimeUnit.SECONDS);

					WebElement sw = driver
							.findElement(By.xpath("(//button[contains(@title,'Add Multiple Vitals')])[1]"));

					sleep(2000);
					javascriptclick(sw);
					driver.findElement(By.id("wresult")).sendKeys("55");

					sleep(4000);
					WebElement sel = driver.findElement(By.xpath("(//select[@id='unit'])[1]"));
					sel.click();
					sleep(2000);
					dropDown("text", sel, "kilograms");
					sleep(4000);
					driver.findElement(By.id("hresult")).sendKeys("7'7");
					sleep(2000);
					WebElement hdrp = driver.findElement(By.xpath("(//select[@id='unit'])[2]"));
					hdrp.click();
					sleep(2000);
					dropDown("text", hdrp, "ft-in");
					sleep(3000);
					WebElement edity = driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]"));
					javascriptclick(edity);

					sleep(3000);
					WebElement edicon = driver.findElement(By.xpath("(//span[text()='55 kilograms'])[1]"));
					actions("click", edicon);
					sleep(2000);
					driver.findElement(By.xpath("(//div[@class='inputBouder'])[71]/input")).clear();
					driver.findElement(By.xpath("(//div[@class='inputBouder'])[71]/input")).sendKeys("59");
					sleep(3000);
					driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]")).click();
					sleep(3000);
					/*
					 * WebElement mv =
					 * driver.findElement(By.xpath("//span[text()='59 kilograms']"));
					 * actions("click", mv); WebElement mm =
					 * driver.findElement(By.xpath("(//span[@id='del-btn'])[1]")); actions("click",
					 * mm); sleep(2000)
					 */;

				}
				if (tagnames.equals("visit-reason")) {
					implicitWait(20, TimeUnit.SECONDS);

					WebElement u = driver.findElement(By.xpath("//div[contains(@title,'Add Visit R')]"));

					sleep(2000);
					actions("move to element", u);
					actions("click", u);
					WebElement y = driver.findElement(By.xpath("//select[@id='triage-type']"));
					y.click();

					List<WebElement> ff = driver.findElements(By.xpath("//select[@id='triage-type']/option"));
					for (WebElement w : ff) {
						if (w.getText().trim().equals("Emergency")) {
							w.click();
							break;
						}

					}
					sleep(2000);
					WebElement mj = driver
							.findElement(By.xpath("//div[@title='Enter the description of the patient visit']"));
					mj.sendKeys("cold");
					sleep(2000);
					WebElement svg = driver.findElement(By.xpath(
							"//div[@title='Enter the description of the patient visit']//following::div[28]/button[2]"));
					svg.click();
					sleep(2000);
					WebElement afk = driver.findElement(By.xpath("(//div[text()='cold'])[1]"));
					actions("click", afk);
					sleep(2000);
					implicitWait(30, TimeUnit.SECONDS);
					WebElement mjj = driver
							.findElement(By.xpath("//div[@title='Enter the description of the patient visit']"));
					mjj.clear();
					mjj.sendKeys("KAASPRO");

					WebElement svg1 = driver.findElement(By.xpath(
							"//div[@title='Enter the description of the patient visit']//following::div[28]/button[2]"));
					svg1.click();

					/*
					 * WebElement delvis = driver
					 * .findElement(By.xpath("//div[@id='Visit_ReasonKpop2']/div[1]/div[2]/span"));
					 * javascriptclick(delvis);
					 */
					sleep(3000);

				}
				if (tagnames.equals("alert-allergy")) {

					WebElement add = driver.findElement(By.xpath("//div[contains(@title,'Add Allergy')]"));

					sleep(2000);

					actions("move to element", add);
					explicitWait(30, add);
					actions("click", add);
					WebElement se = driver.findElement(By.xpath("//select[@id='codeType']"));
					se.click();
					dropDown("text", se, "Food Allergy");
					sleep(3000);
					WebElement alcl = driver.findElement(By.xpath("//select[@id='codeType']//following::div[3]/input"));

					sleep(2000);
					alcl.sendKeys("food1");

					driver.findElement(By.xpath("//input[@placeholder='Reaction']")).sendKeys("stomach pain");

					WebElement rer = driver.findElement(By.xpath("(//button[@type='button'])[18]"));
					JavascriptExecutor jp = (JavascriptExecutor) driver;
					jp.executeScript("arguments[0].click();", rer);
					sleep(3000);
					List<WebElement> wtw = driver.findElements(By.xpath("//div[@id='smore-btn']/ul/li"));
					for (WebElement w : wtw) {
						if (w.getText().trim().equals("Show Severity")) {
							w.click();
							break;
						}

					}
					sleep(3000);
					jp.executeScript("arguments[0].click();", rer);

					for (WebElement w : wtw) {
						if (w.getText().trim().equals("Show Status")) {
							w.click();
							break;
						}

					}
					WebElement s = driver.findElement(By.xpath("//select[@id='severity']"));

					dropDown("text", s, "Mild");
					sleep(2000);
					WebElement ss = driver.findElement(By.xpath("//select[@id='status']"));

					dropDown("text", ss, "Inactive");

					driver.findElement(By.xpath("//div[@id='saveadd-btn']/button")).click();
					List<WebElement> sss = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : sss) {
						if (w.getText().trim().equals("Save")) {
							w.click();
							break;
						}

					}
					sleep(3000);
					WebElement mk = driver.findElement(By.xpath("//span[text()='food1']"));
					actions("click", mk);
					sleep(3000);
					WebElement jk = driver.findElement(By.xpath("(//div[@class='inputBouder'])[71]/input"));
					jk.clear();
					jk.sendKeys("st");
					sleep(3000);

					WebElement scq = driver.findElement(By.xpath("//div[text()='Strawberry ']"));
					actions("click", scq);

					driver.findElement(By.xpath("//div[@id='saveadd-btn']/button")).click();
					List<WebElement> ssss = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : ssss) {
						if (w.getText().trim().equals("Save")) {
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

					WebElement sh = driver.findElement(By.xpath("//div[contains(@title,'Add Social History')]"));
					WebElement sctag = driver.findElement(By.xpath(
							"//div[contains(@title,'Add Social History')]//parent::div[1]//parent::div//parent::div//parent::div//parent::div[@id='social-history']"));
					System.out.println(sctag.getAttribute("id"));
					actions("move to element", sh);
					actions("click", sh);
					sleep(2000);
					WebElement ssc = driver.findElement(By.xpath("//select[@id='habitType']"));
					ssc.click();
					dropDown("text", ssc, "Alcohol");
					driver.findElement(By.xpath("//select[@id='habitType']//following::div[3]/input"))
							.sendKeys("social histry");

					sleep(3000);
					driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]")).click();
					sleep(3000);
					WebElement jj = driver.findElement(By.xpath("//div[text()='( Alcohol )']"));
					actions("click", jj);
					sleep(2000);
					driver.findElement(By.xpath("//select[@id='habitType']//following::div[3]/input")).clear();
					driver.findElement(By.xpath("//select[@id='habitType']//following::div[3]/input"))
							.sendKeys("Kaaspro");
					driver.findElement(By.xpath("(//button[@id='accept-btn'])[1]")).click();
					sleep(3000);

					/*
					 * WebElement shdel =
					 * driver.findElement(By.xpath("(//span[@id='del-btn'])[1]"));
					 * javascriptclick(shdel); sleep(4000);
					 */

					// Family Health...

					WebElement a = driver.findElement(By.xpath("//div[contains(@title,'Add Family Health')]"));
					System.out.println("navigated to family health");
					System.out.println(a.getAttribute("id"));
					actions("move to element", a);
					actions("click", a);
					WebElement ee = driver.findElement(By.xpath("//div[@id='Family_HealthKpop2']/div[2]/div/select"));
					explicitWait(30, ee);
					ee.click();
					dropDown("text", ee, "Half Brother");
					sleep(3000);
					driver.findElement(By
							.xpath("//div[@id='Family_HealthKpop2']/div[2]/div/select//following::div[1]/div[2]/input"))
							.sendKeys("24781");
					WebElement aq = driver.findElement(By.xpath("//div[text()='Fear of wetting self (finding)']"));
					explicitWait(30, aq);
					actions("click", aq);
					sleep(5000);

					WebElement atf = driver.findElement(By.xpath("//button[@id='btnSaveAdd']"));
					javascriptclick(atf);
					List<WebElement> rr = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : rr) {

						if (w.getText().trim().equals("Save")) {
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
					sleep(5000);

					// Alert...
					WebElement ip = driver.findElement(By.xpath("//div[contains(@title,'Add Alert')]"));
					actions("move to element", ip);
					actions("click", ip);

					driver.findElement(By.xpath("//div[@title='Enter the description of the alert ']"))
							.sendKeys("hello");
					WebElement r = driver.findElement(By.xpath("//select[@id='visibility']"));
					r.click();
					dropDown("text", r, "Only to me");
					driver.findElement(By.xpath("//div[@id='AlertKpop2']/div[2]/div[2]/button[2]")).click();
					sleep(3000);
					WebElement inner = driver.findElement(By.xpath("//div[text()='hello']"));
					actions("click", inner);
					sleep(3000);

					driver.findElement(By.xpath("//div[@title='Enter the description of the alert ']")).clear();
					driver.findElement(By.xpath("//div[@title='Enter the description of the alert ']"))
							.sendKeys("wELCOME");
					driver.findElement(By.xpath("//div[@id='AlertKpop2']/div[2]/div[2]/button[2]")).click();

					/*
					 * WebElement delalert =
					 * driver.findElement(By.xpath("//div[@id='AlertKpop2']/div/div[2]/span[1]"));
					 * javascriptclick(delalert); sleep(3000);
					 */
					sleep(3000);
				}
				if (tagnames.equals("vaccine")) {

					WebElement k = driver.findElement(By.xpath("//div[contains(@title,'Add Vaccine')]"));
					actions("move to element", k);
					actions("click", k);
					WebElement x = driver.findElement(By.xpath("//select[@id='date-type']"));
					x.click();
					dropDown("text", x, "Taken Date");
					driver.findElement(By.id("vaccine-cvx")).sendKeys("kaaspro");
					driver.findElement(By.id("vaccineName")).sendKeys("TT");
					WebElement sv = driver
							.findElement(By.xpath("//div[@id='VaccineKpop2']/div[2]/div[3]/button[@id='accept-btn']"));
					javascriptclick(sv);
					sleep(3000);
					WebElement l = driver.findElement(By.xpath("//div[text()='TT']"));
					actions("click", l);
					driver.findElement(By.id("vaccineName")).clear();
					driver.findElement(By.id("vaccineName")).sendKeys("TT INJECTION");
					implicitWait(30, TimeUnit.SECONDS);
					/*
					 * WebElement delvc = driver .findElement(By.xpath(
					 * "(//div[@id='VaccineKpop2']//following::div[1]/span[1])[1]"));
					 * javascriptclick(delvc);
					 */
					driver.findElement(By.xpath("//div[@id='VaccineKpop2']/div[2]/div[3]/button[@id='accept-btn']"))
							.click();
					sleep(3000);

				}
				if (tagnames.equals("implantable-devices")) {

					WebElement b = driver.findElement(By.xpath("//div[contains(@title,'Add Implantable')]"));
					actions("move to element", b);
					actions("click", b);
					driver.findElement(By.id("udi")).sendKeys("(01)00844588003288");
					driver.findElement(By.xpath("//button[@id='verify-btn']")).click();
					sleep(6000);
					driver.findElement(By.id("deviceactive"));
					sleep(2000);

					WebElement a1 = driver.findElement(By.id("deviceNote"));
					ScriptExecutor(a1);
					sleep(2000);
					a1.sendKeys("hello123");
					WebElement savimp = driver
							.findElement(By.xpath("//div[@id='Implantable_DevicesKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(savimp);

					sleep(3000);
					WebElement a2 = driver
							.findElement(By.xpath("//div[text()='Coated femoral stem prosthesis, modular']"));
					actions("click", a2);
					WebElement a26 = driver.findElement(By.id("deviceNote"));
					ScriptExecutor(a26);
					a26.clear();
					a26.sendKeys("JUst Rise up..");
					WebElement savimp1 = driver
							.findElement(By.xpath("//div[@id='Implantable_DevicesKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(savimp1);

					/*
					 * driver.findElement(By.xpath("//div[@title='Remove UDI']")).click();
					 * WebElement delimp = driver .findElement(By.xpath(
					 * "//div[@id='Implantable_DevicesKpop2']/div/div[2]/span[1]"));
					 * javascriptclick(delimp);
					 */

					sleep(3000);

				}
				if (tagnames.equals("amendment")) {

					WebElement d = driver.findElement(By.xpath("//div[contains(@title,'Add Amendment')]"));
					actions("move to element", d);
					actions("click", d);
					WebElement s1 = driver.findElement(By.xpath("//select[@id='source']"));
					s1.click();
					dropDown("text", s1, "Patient");
					driver.findElement(By.xpath("//div[@id='AmendmentKpop2']/div[2]/div/div[2]/div[2]/input"))
							.sendKeys("Akash");
					WebElement s2 = driver.findElement(By.xpath("//select[@id='status']"));
					s2.click();
					dropDown("text", s2, "Accept");
					driver.findElement(By.xpath("//input[@id='reason']")).sendKeys("whats up...");
					WebElement svamen = driver
							.findElement(By.xpath("//div[@id='AmendmentKpop2']/div[2]/div[2]/button[2]"));

					javascriptclick(svamen);
					sleep(3000);
					WebElement ac = driver.findElement(By.xpath("//div[text()='Accepted : whats up...']"));
					actions("click", ac);
					sleep(2000);
					driver.findElement(By.xpath("//input[@id='reason']")).clear();
					driver.findElement(By.xpath("//input[@id='reason']")).sendKeys("warrior");
					driver.findElement(By.xpath("//input[@id='reason']")).sendKeys("WAR BEGINS");

					WebElement svamen1 = driver
							.findElement(By.xpath("//div[@id='AmendmentKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(svamen1);
					// driver.findElement(By.xpath("//div[@id='AmendmentKpop2']/div[1]/div[2]/span[1]")).click();
					sleep(3000);

				}
				if (tagnames.equals("diagnosis")) {

					WebElement a3 = driver.findElement(By.xpath("//div[contains(@title,'Add Problems')]"));
					actions("move to element", a3);

					actions("click", a3);
					sleep(6000);
					driver.findElement(By.xpath("//div[@id='ProblemsKpop2']/div[2]/div/div[1]/div[2]/input"))
							.sendKeys("Cleft uvula");

					sleep(5000);
					WebElement prbcl = driver.findElement(By.xpath("//small[text()='ICD10 : Q35.7 | SNOMED : --']"));
					actions("click", prbcl);
					sleep(4000);

					WebElement gg = driver.findElement(By.xpath("//button[@id='btnSaveAdd']"));
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", gg);
					sleep(3000);
					List<WebElement> a5 = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : a5) {
						if (w.getText().trim().equals("Save")) {
							w.click();
							break;
						}

					}
					sleep(3000);
					WebElement a6 = driver.findElement(By.xpath("//div[text()='Cleft uvula']"));
					actions("click", a6);
					sleep(3000);
					WebElement clr = driver.findElement(
							By.xpath("//div[@id='ProblemsKpop2']/div[2]/div[1]/div[1]/div[2]//following::div[2]"));
					javascriptclick(clr);
					sleep(2000);
					driver.findElement(By.xpath("//div[@id='ProblemsKpop2']/div[2]/div/div[1]/div[2]/input"))
							.sendKeys("test");
					sleep(2000);
					WebElement azn = driver.findElement(By.xpath("//div[text()='Malignant neoplasm of testis']"));
					implicitWait(20, TimeUnit.SECONDS);
					actions("click", azn);

					WebElement gg1 = driver.findElement(By.xpath("//button[@id='btnSaveAdd']"));
					JavascriptExecutor js12 = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", gg1);
					sleep(3000);
					List<WebElement> a56 = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : a56) {
						if (w.getText().trim().equals("Save")) {
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

				}
				if (tagnames.equals("symptom")) {

					WebElement a7 = driver.findElement(By.xpath("//div[contains(@title,'Add Symptoms')]"));
					actions("move to element", a7);
					actions("click", a7);
					driver.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div/div[2]/div[2]/input"))
							.sendKeys("R10.12: Left upper quadrant pain");
					implicitWait(30, TimeUnit.SECONDS);
					WebElement clsymi = driver
							.findElement(By.xpath("//div[contains(text(),'R10.12: Left upper quadrant pain')]"));
					actions("click", clsymi);

					sleep(3000);
					driver.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div/div[3]/div[2]/input"))
							.sendKeys("fever");
					WebElement svsymp = driver
							.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(svsymp);
					sleep(3000);
					WebElement a8 = driver
							.findElement(By.xpath("//div[contains(text(),'R10.12: Left upper quadrant pain')]"));
					actions("click", a8);
					driver.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div/div[3]/div[2]/input")).clear();
					sleep(3000);
					driver.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div/div[3]/div[2]/input"))
							.sendKeys("covid");
					sleep(2000);
					WebElement svsymp1 = driver
							.findElement(By.xpath("//div[@id='SymptomsKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(svsymp1);
					// driver.findElement(By.xpath("//div[@id='SymptomsKpop2']/div/div[2]/span[1]")).click();
					sleep(4000);

				}
				if (tagnames.equals("procedure")) {

					WebElement b1 = driver.findElement(By.xpath("//div[contains(@title,'Add Procedure')]"));
					actions("move to element", b1);
					actions("click", b1);
					sleep(2000);
					WebElement b2 = driver.findElement(By.xpath("//select[@id='codeType']"));
					b2.click();
					dropDown("text", b2, "SNOMED CT");
					sleep(2000);
					driver.findElement(By.xpath("//div[@id='ProcedureKpop2']/div[2]/div[1]/div[1]/div[2]/input"))
							.sendKeys("test");
					sleep(4000);
					WebElement clprcd = driver
							.findElement(By.xpath("//b[text()='Cytomegalovirus antigen test (procedure)']"));

					sleep(3000);
					actions("click", clprcd);
					driver.findElement(By.xpath("//div[@id='ProcedureKpop2']/div[2]/div/div[2]/div[2]/input"))
							.sendKeys("gdgdg");
					sleep(4000);

					driver.findElement(By.id("btnSaveAdd")).click();
					sleep(3000);
					List<WebElement> b6 = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : b6) {
						if (w.getText().trim().equals("Save")) {
							w.click();
							break;
						}

					}
					sleep(3000);
					WebElement b7 = driver.findElement(By.xpath("//div[text()='gdgdg']"));
					actions("click", b7);
					sleep(2000);
					driver.findElement(By.xpath("//div[@id='ProcedureKpop2']/div[2]/div/div[2]/div[2]/input")).clear();
					driver.findElement(By.xpath("//div[@id='ProcedureKpop2']/div[2]/div/div[2]/div[2]/input"))
							.sendKeys("LARA");

					driver.findElement(By.id("btnSaveAdd")).click();
					sleep(3000);
					List<WebElement> b64 = driver.findElements(By.xpath("//div[@id='saveadd-btn']/ul/li"));
					for (WebElement w : b64) {
						if (w.getText().trim().equals("Save")) {
							w.click();
							break;
						}

					}

					// driver.findElement(By.xpath("//div[@id='ProcedureKpop2']/div[1]/div[2]/span[1]")).click();
					sleep(4000);

				}
				if (tagnames.equals("goals")) {
					implicitWait(30, TimeUnit.SECONDS);
					WebElement b8 = driver.findElement(By.xpath("//div[contains(@title,'Add Goals')]"));
					actions("move to element", b8);
					actions("click", b8);
					sleep(2000);
					driver.findElement(By.xpath("//div[@title='Enter goal']")).sendKeys("goal1");
					sleep(3000);
					driver.findElement(By.xpath("//div[@id='GoalsKpop2']/div[2]/div[1]/div[2]/div/input")).click();
					sleep(2000);
					WebElement month = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));

					implicitWait(30, TimeUnit.SECONDS);
					// WebElement yr =
					// driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
					dropDown("index", month, "09");
					implicitWait(30, TimeUnit.SECONDS);
					// explicitWait(30, yr);
					// sleep(3000);
					// dropDown("text", yr, "2021");
					driver.findElement(By.xpath("//a[text()='14']")).click();
					sleep(2000);
					WebElement hk = driver.findElement(By.xpath("//div[@id='GoalsKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(hk);
					sleep(3000);
					WebElement b10 = driver.findElement(By.xpath("//div[text()='goal1']"));
					actions("click", b10);
					implicitWait(20, TimeUnit.SECONDS);

					driver.findElement(By.xpath("//div[@title='Enter goal']")).clear();
					driver.findElement(By.xpath("//div[@title='Enter goal']")).sendKeys("HELLO THIS IS GOALS MODULE.");

					WebElement hk1 = driver.findElement(By.xpath("//div[@id='GoalsKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(hk1);

					/*
					 * WebElement jl =
					 * driver.findElement(By.xpath("//div[@id='GoalsKpop2']/div/div[2]/span[1]"));
					 * 
					 * javascriptclick(jl);
					 */
					sleep(4000);

				}
				if (tagnames.equals("directives")) {

					WebElement c1 = driver.findElement(By.xpath("//div[contains(@title,'Add Advance Directives')]"));
					actions("move to element", c1);
					actions("click", c1);

					WebElement c2 = driver.findElement(By.xpath("//div[@id='Assessment-div']/select"));
					c2.click();
					dropDown("text", c2, "Assessment");
					driver.findElement(By.xpath("//input[@id='directive_desc']")).sendKeys("lets hope");
					driver.findElement(By.xpath("//div[@id='Advance_DirectivesKpop2']/div[2]/div[2]/button[2]"))
							.click();
					sleep(3000);
					WebElement c4 = driver.findElement(By.xpath("//div[text()='lets hope']"));
					actions("click", c4);
					sleep(2000);
					driver.findElement(By.xpath("//input[@id='directive_desc']")).sendKeys("Advance directives");
					driver.findElement(By.xpath("//div[@id='Advance_DirectivesKpop2']/div[2]/div[2]/button[2]"))
							.click();

					/*
					 * WebElement deladvfac = driver .findElement(By.xpath(
					 * "//div[@id='Advance_DirectivesKpop2']/div/div[2]/span[1]"));
					 * javascriptclick(deladvfac);
					 */
					sleep(3000);

				}
				if (tagnames.equals("status-module")) {

					WebElement c5 = driver.findElement(By.xpath("//div[contains(@title,'Add Status')]"));
					actions("move to element", c5);
					actions("click", c5);
					WebElement c6 = driver.findElement(By.xpath("(//select[@id='statusType'])[1]"));
					c6.click();
					dropDown("text", c6, "Cognitive status");
					driver.findElement(By
							.xpath("//div[@id='StatusKpop2']/div[2]/div[1]/select[1]//following::div[1]/div[2]/input"))
							.sendKeys("test");
					sleep(2000);
					WebElement clsmd = driver
							.findElement(By.xpath("//div[text()='134374006: Hearing test bilateral abnormality ']"));
					sleep(3000);
					actions("click", clsmd);
					driver.findElement(By.xpath("//div[@id='StatusKpop2']/div[2]/div[2]/button[2]")).click();
					sleep(4000);
					WebElement c7 = driver
							.findElement(By.xpath("//div[text()='134374006: Hearing test bilateral abnormality']"));
					actions("click", c7);
					sleep(3000);
					WebElement hjj = driver
							.findElement(By.xpath("//div[@id='StatusKpop2']/div[2]/div[1]/select//following::span[5]"));
					actions("click", hjj);
					driver.findElement(By
							.xpath("//div[@id='StatusKpop2']/div[2]/div[1]/select[1]//following::div[1]/div[2]/input"))
							.sendKeys("yang");
					sleep(2000);
					WebElement yng = driver.findElement(By.xpath("//div[text()='370534002: Yang deficiency ']"));
					actions("click", yng);
					driver.findElement(By.xpath("//div[@id='StatusKpop2']/div[2]/div[2]/button[2]")).click();

					/*
					 * WebElement delsmd =
					 * driver.findElement(By.xpath("//div[@id='StatusKpop2']/div[1]/div[2]/span[1]")
					 * ); javascriptclick(delsmd);
					 */
					sleep(3000);

				}
				if (tagnames.equals("test-order")) {

					WebElement ad1 = driver.findElement(By.xpath("//div[contains(@title,'Add Test Order')]"));

					actions("move to element", ad1);
					actions("click", ad1);
					driver.findElement(By.xpath("//div[@id='Test_OrderKpop2']/div[2]/div[1]/div[1]/div[2]/input"))
							.sendKeys("test");
					sleep(3000);
					WebElement cltest = driver
							.findElement(By.xpath("//b[text()='Nitrite [Presence] in Urine by Test strip']"));
					explicitWait(20, cltest);
					actions("click", cltest);
					sleep(2000);

					driver.findElement(By.xpath("//div[@id='Test_OrderKpop2']/div[2]/div[2]/div/button")).click();
					List<WebElement> chs = driver.findElements(
							By.xpath("//div[@id='Test_OrderKpop2']/div[2]/div[2]/div/button//following::ul[1]/li"));
					for (WebElement w : chs) {

						if (w.getText().trim().equals("Show Notes")) {
							w.click();
							break;
						}

					}

					sleep(3000);
					driver.findElement(By.xpath(

							"//div[@id='Test_OrderKpop2']/div[2]/div[1]/div[1]/div[2]//following::div[1]//following::div[1]/div[2]/input"))
							.sendKeys("ERROR");
					driver.findElement(
							By.xpath("//div[@id='Test_OrderKpop2']/div[2]/div[2]/div[1]//following::div[2]/div/button"))
							.click();
					List<WebElement> dss = driver.findElements(By.xpath(
							"//div[@id='Test_OrderKpop2']/div[2]/div[2]/div[1]//following::div[2]/div/button//following::ul[1]/li"));
					for (WebElement w : dss) {
						if (w.getText().trim().equals("Save")) {

							w.click();
							break;
						}

					}
					sleep(3000);
					WebElement testorder = driver.findElement(By.xpath("//div[text()='ERROR']"));
					actions("click", testorder);

					driver.findElement(By.xpath(

							"//div[@id='Test_OrderKpop2']/div[2]/div[1]/div[1]/div[2]//following::div[1]//following::div[1]/div[2]/input"))
							.clear();
					driver.findElement(By.xpath(

							"//div[@id='Test_OrderKpop2']/div[2]/div[1]/div[1]/div[2]//following::div[1]//following::div[1]/div[2]/input"))
							.sendKeys("Test order..");

					driver.findElement(
							By.xpath("//div[@id='Test_OrderKpop2']/div[2]/div[2]/div[1]//following::div[2]/div/button"))
							.click();
					List<WebElement> dsss = driver.findElements(By.xpath(
							"//div[@id='Test_OrderKpop2']/div[2]/div[2]/div[1]//following::div[2]/div/button//following::ul[1]/li"));
					for (WebElement w : dsss) {
						if (w.getText().trim().equals("Save")) {

							w.click();
							break;
						}

					}

					/*
					 * WebElement de = driver.findElement(By.xpath(
					 * "//div[@id='Test_OrderKpop2']/div[1]/div[2]/span[2]")); sleep(2000);
					 * actions("click", de);
					 */
					sleep(3000);

				}
				if (tagnames.equals("drug")) {

					WebElement ci = driver.findElement(By.xpath("(//div[contains(@title,'Add Medications')])[1]"));
					actions("move to element", ci);
					actions("click", ci);
					sleep(3000);
					driver.findElement(By.id("DRUG_NAME")).sendKeys("tata");
					driver.findElement(By.id("STRENGTH")).sendKeys("str");
					driver.findElement(By.id("DISP_QUANTITY")).sendKeys("1");
					driver.findElement(By.id("SIG_DIRECTIONS")).sendKeys("dg");
					driver.findElement(By.id("startdateiid")).sendKeys("2022-07-20");
					driver.findElement(By.id("enddateiid")).sendKeys("2022-07-22");
					driver.findElement(By.xpath("//div[@id='MedicationsKpop2']/div[2]/div[3]/div[1]/button")).click();
					sleep(3000);
					List<WebElement> d1 = driver.findElements(
							By.xpath("//div[@id='MedicationsKpop2']/div[2]/div[3]/div[1]/button//following::ul[1]/li"));
					for (WebElement w : d1) {

						if (w.getText().trim().equals("Save")) {
							w.click();
							break;
						}

					}

					sleep(3000);
					WebElement d3 = driver.findElement(By.xpath("//div[text()='dg']"));
					actions("click", d3);
					sleep(2000);

					/*
					 * WebElement delmed = driver
					 * .findElement(By.xpath("//div[@id='MedicationsKpop2']/div[1]/div[2]/span[1]"))
					 * ; javascriptclick(delmed);
					 */
					WebElement rqw = driver.findElement(By.xpath(
							"//div[@id='MedicationsKpop2']/div[2]/div[1]/div[1]/div[3]/table/tbody/tr/td[2]/div"));
					javascriptclick(rqw);
					sleep(2000);
					driver.findElement(By.id("DRUG_NAME")).sendKeys("MEDICATION");

					driver.findElement(By.xpath("//div[@id='MedicationsKpop2']/div[2]/div[3]/div[1]/button")).click();
					sleep(3000);
					List<WebElement> d1R = driver.findElements(
							By.xpath("//div[@id='MedicationsKpop2']/div[2]/div[3]/div[1]/button//following::ul[1]/li"));
					for (WebElement w : d1R) {

						if (w.getText().trim().equals("Save")) {
							w.click();
							break;
						}

					}

					sleep(3000);

				}
				if (tagnames.equals("delivery-note")) {
					WebElement kk = driver.findElement(By.xpath("//div[contains(@title,'Add Notes')]"));
					actions("move to element", kk);
					actions("click", kk);
					driver.findElement(By.xpath("//div[@title='Enter the notes description of the patient visit']"))
							.sendKeys("hell");
					WebElement zv = driver.findElement(By.xpath("//div[@id='NotesKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(zv);
					sleep(3000);
					WebElement jl = driver.findElement(By.xpath("//div[text()='hell']"));
					actions("click", jl);
					driver.findElement(By.xpath("//div[@title='Enter the notes description of the patient visit']"))
							.clear();
					driver.findElement(By.xpath("//div[@title='Enter the notes description of the patient visit']"))
							.sendKeys("NOTES--MMM");
					WebElement zv1 = driver.findElement(By.xpath("//div[@id='NotesKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(zv1);

					/*
					 * WebElement fg =
					 * driver.findElement(By.xpath("//div[@id='NotesKpop2']/div/div[2]/span[1]"));
					 * javascriptclick(fg);
					 */
					sleep(3000);

				}
				if (tagnames.equals("physical-examination")) {

					WebElement x1 = driver.findElement(By.xpath("//div[contains(@title,'Add Physical Examination')]"));
					actions("move to element", x1);
					actions("click", x1);
					driver.findElement(By.id("bodyParts")).sendKeys("hello");

					sleep(3000);
					driver.findElement(By.id("finding")).sendKeys("hw are you");
					sleep(3000);

					WebElement abc = driver
							.findElement(By.xpath("//div[@id='Physical_ExaminationsKpop2']/div[2]/div[2]/div/button"));
					javascriptclick(abc);
					List<WebElement> abcd = driver.findElements(By.xpath(
							"//div[@id='Physical_ExaminationsKpop2']/div[2]/div[2]/div/button//following::ul[1]/li"));
					for (WebElement w : abcd) {
						if (w.getText().trim().equals("Show Notes")) {
							w.click();
							break;
						}

					}

					sleep(2000);
					driver.findElement(By.xpath("//input[@id='notes']")).sendKeys("lets goo");
					//// div[@id='Physical_ExaminationsKpop2']/div[2]/div[1]/div[5]/div[2]/input
					sleep(2000);
					WebElement nn = driver
							.findElement(By.xpath("//div[@id='Physical_ExaminationsKpop2']/div[2]/div[3]/button[2]"));
					javascriptclick(nn);
					sleep(3000);
					WebElement et = driver.findElement(By.xpath("//div[text()='lets goo']"));
					actions("click", et);
					/*
					 * WebElement sf = driver .findElement(By.xpath(
					 * "//div[@id='Physical_ExaminationsKpop2']/div[1]/div[2]/span[1]"));
					 * javascriptclick(sf);
					 */

					driver.findElement(By.xpath("//input[@id='notes']")).clear();
					driver.findElement(By.xpath("//input[@id='notes']")).sendKeys("physical condition");
					WebElement nnn = driver
							.findElement(By.xpath("//div[@id='Physical_ExaminationsKpop2']/div[2]/div[3]/button[2]"));
					javascriptclick(nnn);
					sleep(4000);

				}
				if (tagnames.equals("custom-form")) {

					WebElement lj = driver.findElement(By.xpath("//div[contains(@title,'Add Forms')]"));
					actions("move to element", lj);
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

							rtt.click();
							WebElement js = driver.findElement(By.xpath("(//span[@id='del-form'])[2]"));
							javascriptclick(js);

							break;

						}

					}
					sleep(3000);

					WebElement addfrm = driver.findElement(By.xpath("//div[@id='FormsKpop2']/div[1]/span"));

					actions("click", addfrm);
					driver.findElement(By.xpath("(//label[text()='Form Title*'])[2]//following::input[1]"))
							.sendKeys("form5");

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
							sleep(2000);
							WebElement addit = driver
									.findElement(By.xpath("//span[text()='form5']//following::div[1]/span"));
							actions("click", addit);
							sleep(3000);
							WebElement ytt = driver.findElement(By.xpath("//div[@id='FormsKpop2']/div[1]/div[2]/span"));
							javascriptclick(ytt);
							sleep(2000);
							WebElement ffr = driver
									.findElement(By.xpath("//span[text()='form5']//following::div[1]/div"));
							actions("click", ffr);
							sleep(2000);
							WebElement delfr = driver
									.findElement(By.xpath("(//span[text()='form5'])[2]//following::div[1]/span[1]"));
							actions("click", delfr);
							break;
						}

					}
					sleep(3000);
				}

				if (tagnames.equals("attachFile")) {

					WebElement ar = driver.findElement(By.xpath("//div[contains(@title,'Add Attach Files')]"));
					actions("move to element", ar);
					actions("click", ar);

					WebElement selweb = driver
							.findElement(By.xpath("//div[@id='Attach_FileKpop2']/div[2]/div[1]/select"));
					dropDown("text", selweb, "Web link");
					driver.findElement(
							By.xpath("(//div[@id='Attach_FileKpop2']/div[2]/div[1]//following::div[3]/input[1])[1]"))
							.sendKeys("https://www.75health.com/");
					WebElement ps1 = driver
							.findElement(By.xpath("//div[@id='Attach_FileKpop2']/div[2]/div[3]/button[2]"));

					javascriptclick(ps1);
					sleep(3000);

				}
				if (tagnames.equals("inpatient")) {

					WebElement qq = driver.findElement(By.xpath("//div[contains(@title,'Add Inpatient details')]"));
					actions("move to element", qq);
					actions("click", qq);
					// driver.findElement(By.id("admissioniid")).click();

					WebElement inpmnth = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
					dropDown("index", inpmnth, "08");

					WebElement ipmyr = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
					dropDown("text", ipmyr, "2022");
					driver.findElement(By.xpath("//a[text()='21']")).click();

					sleep(2000);
					driver.findElement(By.id("dischargeiid")).click();
					WebElement inpmnth1 = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));

					dropDown("index", inpmnth1, "10");
					WebElement ipmyr1 = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
					dropDown("text", ipmyr1, "2022");
					driver.findElement(By.xpath("//a[text()='26']")).click();

					sleep(3000);
					WebElement re = driver.findElement(By.xpath("//div[@id='InpatientKpop2']/div[2]/div[1]/select"));
					// (//select[@id='admissionType'])[1]

					javascriptclick(re);
					dropDown("text", re, "Urgent");
					driver.findElement(By.id("rmNo")).sendKeys("777");
					driver.findElement(By.id("dischargeSummary")).sendKeys("okay");
					WebElement yt = driver.findElement(By.xpath("//div[@id='InpatientKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(yt);

					sleep(3000);

				}
				if (tagnames.equals("refer")) {

					WebElement ju = driver.findElement(By.xpath("//div[contains(@title,'Add Referral')]"));
					actions("move to element", ju);
					actions("click", ju);
					WebElement uk = driver
							.findElement(By.xpath("//div[@id='ReferralKpop2']/div[2]/div/div[2]/div[2]/input"));
					uk.sendKeys("352-3943");// kpid mention dr...
					sleep(3000);
					WebElement gfl = driver.findElement(By.xpath("//td[text()='352-3943']"));
					actions("click", gfl);
					sleep(4000);
					// driver.findElement(By.xpath("(//input[@id='phone'])[5]")).sendKeys("201-525-2236");
					sleep(2000);
					driver.findElement(By.xpath("//div[@id='ReferralKpop2']/div[2]/div[1]/div[6]/div[2]/input"))
							.sendKeys("hello");
					WebElement cv = driver.findElement(By.xpath("//div[@id='ReferralKpop2']/div[2]/div[2]/button[2]"));
					javascriptclick(cv);
					sleep(2000);
					/*
					 * WebElement df = driver.findElement(By.xpath("//div[text()='hello']"));
					 * actions("click", df); WebElement ssf = driver.findElement(By.xpath(
					 * "//div[@id='ReferralKpop2']/div[1]/div[2]/span[1]")); javascriptclick(ssf);
					 */

				}
			}

		}
		implicitWait(30, TimeUnit.SECONDS);
		sleep(2000);

		WebElement gnbill = driver.findElement(By.xpath("(//div[@id='generate_bill'])[1]/button[2]"));

		javascriptclick(gnbill);
		List<WebElement> yjj = driver
				.findElements(By.xpath("(//div[@id='generate_bill'])[1]/button[2]//following::ul[1]/li"));
		for (WebElement we : yjj) {
			if (we.getText().trim().equals("Generate Bill from EHR")) {
				we.click();
				break;
			}

		}
		sleep(4000);
		WebElement backtoehr = driver.findElement(By.xpath("//button[@onclick='window.history.back();']"));
		javascriptclick(backtoehr);
		sleep(2000);
		driver.navigate().to("https://www.75health.com/health/#list_ehr");
		implicitWait(30, TimeUnit.SECONDS);

		driver.findElement(By.id("newMedicalRecordButton")).click();
		sleep(4000);
		implicitWait(40, TimeUnit.SECONDS);

		// salt functions...

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

				}
				if (tagnames.equals("visit-reason")) {
					WebElement vistsal = driver
							.findElement(By.xpath("(//div[@id='visit-reason'])[2]/div/div[1]/div/div[2]/div[1]"));
					actions("click", vistsal);

				}
				if (tagnames.equals("diagnosis")) {

					WebElement prbsal = driver.findElement(By.xpath("//div[contains(@title,'SALT Problems')]"));
					actions("click", prbsal);

				}
				if (tagnames.equals("symptom")) {

					WebElement symsalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Symptoms')]"));
					actions("click", symsalt);

				}
				if (tagnames.equals("procedure")) {

					WebElement prosalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Procedure')]"));
					actions("click", prosalt);
				}
				if (tagnames.equals("goals")) {

					WebElement goalsalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Goals')]"));
					actions("click", goalsalt);
				}
				if (tagnames.equals("directives")) {

					WebElement advsalt = driver
							.findElement(By.xpath("//div[contains(@title,'SALT Advance Directives')]"));
					actions("click", advsalt);
				}
				if (tagnames.equals("status-module")) {

					WebElement statussalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Status')]"));
					actions("click", statussalt);
				}
				if (tagnames.equals("drug")) {

					WebElement medsalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Medications')]"));
					actions("click", medsalt);
				}
				if (tagnames.equals("delivery-note")) {
					WebElement notesalt = driver.findElement(By.xpath("//div[contains(@title,'SALT Notes')]"));
					actions("click", notesalt);
				}

			}
		}
		sleep(4000);

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

	}

}
