package fxmmSmokeTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class FXMMTestScriptNEW {
	
	WebDriver driver;
	String URL = "https://bgamodel.na.dir.bunge.com/fxmm";   
    
	
  @BeforeClass
  public void beforeMethod() {

System.setProperty("webdriver.gecko.driver", "D:\\A.K_softwares\\JAR_Files\\Jars\\geckodriver.exe");
FirefoxOptions dc = new FirefoxOptions();     //DesiredCapabilities dc = DesiredCapabilities.firefox(); were used earlier
dc.setCapability("marionette", true);
dc.setCapability("acceptInsecureCerts", true); 
driver = new FirefoxDriver(dc);
driver.get(URL);
driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);

  }

  
  @Test (priority=1)
  public void TestSearchScreen() throws InterruptedException {

driver.findElement(By.name("USER")).clear();	  
driver.findElement(By.name("USER")).sendKeys("UMASING");
driver.findElement(By.name("PASSWORD")).clear();
driver.findElement(By.name("PASSWORD")).sendKeys("Bunge123");
findElement("html/body/table[1]/tbody/tr[3]/td/form/p/table/tbody/tr[1]/td/table/tbody/tr[4]/td/input");
Thread.sleep(1000);
Assert.assertEquals("FXMM - FX & Money Markets", driver.getTitle()	, "Page Title NOT VERIFIED");
findElement(".//*[@id='buttons']/div/ul/li[1]/a");
findElement(".//*[@id='buttons']/div/ul/li[1]/ul/li[1]/a");
findElement(".//*[@id='form']/div[1]/div[1]/div/div/button");
findElement(".//*[@id='form']/div[1]/div[1]/div/div/div/ul/li[3]/a");
findElement(".//*[@id='searchButton']");

Thread.sleep(7000);
String Results = driver.findElement(By.xpath(".//*[@id='pager']/div/span[3]")).getText();
Results = Results.substring(12, 16);

int Result = Integer.parseInt(Results);
System.out.println(Result);


if (Result > 0) {
	System.out.println("PASSED.... "+ Result + " results found");
	}

else
	{
	Assert.fail("FAILED");
	}

}
  
  
  
  
@Test (priority =2) 
public void TestOutrightForward() throws InterruptedException {
	
	driver.get("https://bgamodel.na.dir.bunge.com/fxmm/dealCapture/fxSwap/main");
	Thread.sleep(3000);
	findElement(".//*[@id='form']/fieldset[2]/div[1]/div/img");
	Thread.sleep(2000);
	findElement(".//*[@id='folderListDialog']/div/div[2]/table/tbody/tr[1]/td[1]");
	Thread.sleep(2000);
	findElement(".//*[@id='form']/fieldset[2]/div[2]/div/img");
	Thread.sleep(2000);
	findElement(".//*[@id='counterpartyListDialog']/div/div[2]/table/tbody/tr[1]/td[1]");
}
    
  
  
  @AfterClass
  public void afterMethod() {
  driver.close();

  }

  
  
  
  public void SelectEment(String xpath, String selectBytext) {

		Select ele1= new Select(driver.findElement(By.xpath(xpath)));
			ele1.selectByVisibleText(selectBytext);
		}
  
  public void findElement(String Xpath) {
	  
	driver.findElement(By.xpath(Xpath)).click();;  
  }
  
}

;