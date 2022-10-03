package com.Runner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BaseClass.Base_Class;
import com.data.ConfigManager;
import com.pageObjectMan.PageObjMan;

public class Audit_Form extends Base_Class {
	public static WebDriver driver;
	static PageObjMan pom;
	static JavascriptExecutor j;
 static String kpid="3528-859";
	public static void main(String[] args) throws IOException, InterruptedException {

		driver = browserLaunch("chrome");
		pom = new PageObjMan(driver);
		j = (JavascriptExecutor) driver;

		driver.get("https://www.75health.com/");
		implicitWait(30, TimeUnit.SECONDS);
		click(pom.getInstanceLoginPage().sigIn);
		sleep(2000);
		sendkeys(pom.getInstanceLoginPage().email,
				ConfigManager.getconfigManager().getInstanceConfigReader().getEmail());
		sendkeys(pom.getInstanceLoginPage().pass, ConfigManager.getconfigManager().getInstanceConfigReader().getpass());
		click(pom.getInstanceLoginPage().login);
		sleep(3000);

		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		implicitWait(60, TimeUnit.SECONDS);
		;
		sleep(3000);
		// ScriptExecutor(o);
		click(pom.getInstanceSetting().clickSettings);
		WebDriverWait wait = new WebDriverWait(driver, 20);
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
		driver.findElement(By.xpath("(//input[@id='userPartyName'])[2]"))
				.sendKeys(ConfigManager.getconfigManager().getInstanceConfigReader().doctorKpid());
		List<WebElement> userd = driver
				.findElements(By.xpath("//div[@id='vvid']//following::ul[2]/li/a/table/tbody/tr[1]/td[2]"));

		for (WebElement we : userd) {
			if (we.getText().contains(ConfigManager.getconfigManager().getInstanceConfigReader().doctorKpid())) {

				actions("click", we);
				break;

			}

		}

		
		
		
		
		
		
		
		
		// Test code...

				/*
				 * driver.findElement(By.xpath("//button[@onclick='hospitalcodedropdown();']")).
				 * click(); List<WebElement> testcode =
				 * driver.findElements(By.xpath("//ul[@id='hospitalcodeul']/li")); for
				 * (WebElement w : testcode) { if (w.getText().trim().equals("Test Code")) {
				 * 
				 * w.click(); break; }
				 * 
				 * }
				 * 
				 * WebElement clickaddtest = driver.findElement( By.xpath(
				 * "(//div[@id='test-order-code'])[1]/div[2]/div[1]/div/table/tbody/tr/td[4]/span[3]"
				 * )); j.executeScript("arguments[0].click();", clickaddtest); sleep(2000);
				 * driver.findElement(By.
				 * xpath("(//label[text()='Test Code*'])[2]//following::div[1]/input")).sendKeys
				 * ("TEST12"); driver.findElement(By.
				 * xpath("(//input[contains(@title,'Enter test name or description')])[2]"))
				 * .sendKeys("test"); driver.findElement(By.
				 * xpath("(//input[contains(@title,'Enter normal reference rang')])[2]")).
				 * sendKeys("100"); driver.findElement(By.
				 * xpath("(//input[contains(@title,'Enter unit for the test code')])[2]")).
				 * sendKeys("pg/mL");
				 * 
				 * WebElement chooseunit = driver.findElement(By.xpath("//a[text()='pg/mL']"));
				 * actions("click", chooseunit);
				 * 
				 * driver.findElement(By.
				 * xpath("(//input[contains(@title,'Enter amount value for the Test')])[2]")).
				 * sendKeys("5"); WebElement savetest =
				 * driver.findElement(By.xpath("(//button[@id='save-btn'])[20]"));
				 * j.executeScript("arguments[0].click();", savetest); sleep(3000); WebElement
				 * editestq = driver.findElement(By.xpath("//div[text()='TEST12']"));
				 * actions("click", editestq); sleep(2000); WebElement deltest =
				 * driver.findElement(By.xpath("(//span[@id='del-btn'])[25]"));
				 * j.executeScript("arguments[0].click();", deltest); WebElement gobacktest =
				 * driver.findElement(By.
				 * xpath("(//span[text()='Test Code'])[1]//parent::div/span[1]"));
				 * j.executeScript("arguments[0].click();", gobacktest); sleep(4000);
				 */
		
	}

}
