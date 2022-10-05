package com.Runner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.BaseClass.Base_Class;
import com.data.ConfigManager;
import com.pageObjectMan.PageObjMan;

public class notificatios extends Base_Class {
	public static WebDriver driver;
	static PageObjMan pom;
	static JavascriptExecutor j;

	public static void main(String[] args) throws InterruptedException, IOException {

		driver = browserLaunch("chrome");
		pom = new PageObjMan(driver);
		j = (JavascriptExecutor) driver;

		driver.get("https://localhost:8443/");
		driver.get(ConfigManager.getconfigManager().getInstanceConfigReader().getUrl());
		sleep(3000);
		driver.findElement(By.id("details-button")).click();
		sleep(3000);

		driver.findElement(By.id("proceed-link")).click();
		sleep(4000);

		implicitWait(30, TimeUnit.SECONDS);
		click(pom.getInstanceLoginPage().sigIn);
		sleep(2000);
		sendkeys(pom.getInstanceLoginPage().email,
				ConfigManager.getconfigManager().getInstanceConfigReader().getEmail());
		sendkeys(pom.getInstanceLoginPage().pass, "1");
		click(pom.getInstanceLoginPage().login);
		sleep(1000);
		while (true) {
			if (!driver.getCurrentUrl().equals("https://localhost:8443/health/#home")) {
				click(pom.getInstanceLoginPage().login);
				break;
			}
		}

		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			driver.navigate().to("https://localhost:8443/health/#setting");
			String s = "https://localhost:8443/health/#setting";
			if (driver.getCurrentUrl().equals(s)) {
				break;
			}
			driver.navigate().refresh();
			sleep(3000);
		}

		/*
		 * // button[contains(text(),'Customize Messages')]
		 * 
		 * WebElement er =
		 * driver.findElement(By.xpath("//span[text()='Edit Notification Messages']"));
		 * WebElement notify =
		 * driver.findElement(By.xpath("//button[@id='custom-notify']"));
		 * 
		 * String text = notify.getText(); ScriptExecutor(er); sleep(4000);
		 * 
		 * boolean b = true; while (b) {
		 * 
		 * if (!notify.isDisplayed()) { WebElement uip = driver.findElement(By
		 * .xpath("//span[text()='Edit Notification Messages']//parent::div//parent::div[1]/label/input"
		 * ));
		 * 
		 * actions("click", uip);
		 * 
		 * javascriptclick(notify);
		 * 
		 * }
		 * 
		 * b = false; }
		 */

		//notify ehr complte...
		WebElement ntfyehr = driver.findElement(By
				.xpath("//span[text()='Notify user when EHR is completed.']//parent::div//parent::div[1]/label/input"));
		ScriptExecutor(ntfyehr);
		actions("click", ntfyehr);

		// set interval time for emial...

		WebElement rr = driver.findElement(By
				.xpath("//span[text()='Set interval for receiving emails.']//parent::div//parent::div[1]/label/input"));

		actions("click", rr);

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
