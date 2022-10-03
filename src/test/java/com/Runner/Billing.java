package com.Runner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BaseClass.Base_Class;
import com.data.ConfigManager;
import com.pageObjectMan.PageObjMan;

public class Billing extends Base_Class {

	public static WebDriver driver;
	static PageObjMan pom;
	static JavascriptExecutor j;

	public static void main(String[] args) throws InterruptedException, IOException {

		driver = browserLaunch("chrome");
		pom = new PageObjMan(driver);
		j = (JavascriptExecutor) driver;

		driver.get("https://www.75health.com/");
		implicitWait(30, TimeUnit.SECONDS);
		click(pom.getInstanceLoginPage().sigIn);
		sleep(2000);
		sendkeys(pom.getInstanceLoginPage().email,
				ConfigManager.getconfigManager().getInstanceConfigReader().getEmail());
		sendkeys(pom.getInstanceLoginPage().pass, "1");
		click(pom.getInstanceLoginPage().login);
		sleep(3000);

		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		explicitWait(15, pom.getInstanceBilling().clickBill); //
		click(pom.getInstanceBilling().clickBill);
		driver.navigate().refresh();
		implicitWait(30, TimeUnit.SECONDS);
		sleep(5000);
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
		sleep(3000);
		click(pom.getInstanceBilling().saveItem);
		sleep(3000);
		WebElement r = driver.findElement(By.xpath("//div[@id='assign-side']/div[1]/div"));

		actions("click", r);
		WebElement addiconfav = driver
				.findElement(By.xpath("//div[@id='assign-side']/div[2]/div[1]/div/table/tbody/tr/td[4]/span[2]"));

		javascriptclick(addiconfav);
		WebElement favdes = driver
				.findElement(By.xpath("//div[@id='assign-side']/div[3]/div/div/div[2]/div[3]/div[2]/div/input"));
		favdes.sendKeys("Kaaspro");
		driver.findElement(By.xpath("//div[@id='assign-side']/div[3]/div/div/div[2]/div[4]/div[2]/input"))
				.sendKeys("3");
		WebElement savefav = driver.findElement(By.xpath(
				"//div[@id='assign-side']/div[3]/div/div/div[2]/div[4]/div[2]//following::div[1]/div/div[4]//following::div[1]/div/button"));
		javascriptclick(savefav);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		sleep(3000);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//span[text()='Kaaspro']//parent::div//parent::div[1]//parent::div[1]/div[1]/span[1])[1]")));
		actions("click", until);
		sleep(2000);
		WebElement edi = driver.findElement(
				By.xpath("(//span[text()='Kaaspro']//parent::div//parent::div[1]//parent::div[1]/div[2])[1]"));
		actions("click", edi);
		sleep(2500);
		WebElement delfav = driver
				.findElement(By.xpath("//div[@id='assign-side']/div[3]/div/div/div[1]/div[2]/span[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(delfav));
		actions("click", delfav);
		sleep(3000);
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
	}

}
