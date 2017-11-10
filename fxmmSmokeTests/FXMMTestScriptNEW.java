package fxmmSmokeTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
FirefoxProfile fp = new FirefoxProfile();
DesiredCapabilities dc = DesiredCapabilities.firefox();
dc.setCapability("marionette", true);
dc.setCapability(FirefoxDriver.PROFILE, fp);
dc.setCapability("acceptInsecureCerts", true); 
driver = new FirefoxDriver(dc);
driver.get(URL);
driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);


  }

  
  @Test (priority=0)
  public void TestLogin() throws InterruptedException {

driver.findElement(By.name("USER")).clear();	  
driver.findElement(By.name("USER")).sendKeys("UMASING");
driver.findElement(By.name("PASSWORD")).clear();
driver.findElement(By.name("PASSWORD")).sendKeys("Bunge123");
driver.findElement(By.xpath("html/body/table[1]/tbody/tr[3]/td/form/p/table/tbody/tr[1]/td/table/tbody/tr[4]/td/input")).click();
driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);

Assert.assertEquals("FXMM - FX & Money Markets", driver.getTitle()	, "Page Title NOT VERIFIED");

driver.findElement(By.xpath(".//*[@id='buttons']/div/ul/li[1]/ul/li[1]/a")).click();

String x = driver.findElement(By.xpath("")).getText();

assertEquals(driver.getTitle(), x);

  }
  
  
  /*@Test (priority=1)
  public void TestSearchDeals() throws InterruptedException {

//TestLogin();

driver.findElement(By.xpath("")).click();

//SelectEment(xpath, text);
	  
  }*/  
  
  
  @AfterClass
  public void afterMethod() {
  driver.close();

  }

  public void SelectEment(String xpath, String selectBytext) {

		Select ele1= (Select) driver.findElement(By.xpath(xpath));
			ele1.selectByVisibleText(selectBytext);
		}
  
}

