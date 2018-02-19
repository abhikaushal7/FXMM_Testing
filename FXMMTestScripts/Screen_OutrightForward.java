package FXMMTestScripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import databaseConnection.*;

public class Screen_OutrightForward {
	
	WebDriver driver;
	String URL = "https://bga-ux-wsd1.na.dir.bunge.com/fxmm/";   
	DBConnection DB = new DBConnection();
    
	
  @BeforeClass
  public void beforeMethod() {

System.setProperty("webdriver.gecko.driver", "D:\\A.K_softwares\\JAR_Files\\Jars\\geckodriver.exe");
FirefoxOptions dc = new FirefoxOptions();     //DesiredCapabilities dc = DesiredCapabilities.firefox(); were used earlier
dc.setCapability("marionette", true);
dc.setCapability("acceptInsecureCerts", true); 
driver = new FirefoxDriver(dc);
//driver.manage().deleteAllCookies();
driver.get(URL);
driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);

  }

  
  @Test (priority=1)
  public void TestSearchScreen() throws InterruptedException {

driver.findElement(By.name("USER")).clear();	  
driver.findElement(By.name("USER")).sendKeys("UMASING");
driver.findElement(By.name("PASSWORD")).clear();
driver.findElement(By.name("PASSWORD")).sendKeys("Bunge123");
find_Click("html/body/table[1]/tbody/tr[3]/td/form/p/table/tbody/tr[1]/td/table/tbody/tr[4]/td/input");
Thread.sleep(1000);
Assert.assertEquals("FXMM - FX & Money Markets", driver.getTitle()	, "Page Title NOT VERIFIED");
find_Click(".//*[@id='buttons']/div/ul/li[1]/a");
find_Click(".//*[@id='buttons']/div/ul/li[1]/ul/li[1]/a");
find_Click(".//*[@id='form']/div[1]/div[1]/div/div/button");
find_Click(".//*[@id='form']/div[1]/div[1]/div/div/div/ul/li[3]/a");
find_Click(".//*[@id='searchButton']");

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
public void TestOutrightForward() throws InterruptedException, SQLException {
	
	driver.get("https://bga-ux-wsd1.na.dir.bunge.com/fxmm/dealCapture/outrightForward/main");
	Thread.sleep(3000);
	find_Click(".//*[@id='form']/fieldset[2]/div[1]/div/img");
	Thread.sleep(2000);
	find_Click(".//*[@id='folderListDialog']/div/div[2]/table/tbody/tr[1]/td[1]");
	Thread.sleep(2000);
	find_Click(".//*[@id='form']/fieldset[2]/div[2]/div/img");
	Thread.sleep(2000);
	find_Click(".//*[@id='counterpartyListDialog']/div/div[2]/table/tbody/tr[1]/td[1]");
	Thread.sleep(2000);
	find_Click("//img[@onclick='dealCommons.showProfileListDialog()']");
	Thread.sleep(1000);
	find_Click(".//*[@id='profileListDialog']/div/div[2]/table/tbody/tr[1]/td");
	Thread.sleep(2000);
	find_Send(".//*[@id='maturityDate']", "11/04/2018");
	find_Send(".//*[@id='spotRate']", "11");
	find_Send(".//*[@id='forwardRate']", "11");
	find_Send(".//*[@id='forwardPoints']", "1");
	find_Send(".//*[@id='amount1']", "100");
	find_Click(".//*[@id='insertButton']");
	Thread.sleep(2000);
	find_Click(".//*[@id='confirmDialogYesButton']");
	WebElement Loading = driver.findElement(By.xpath(".//*[@id='loadingModalTitle']"));
	
	
	
	try {
		if (Loading.isDisplayed()) {
			Thread.sleep(10000);
			assertEquals(driver.findElement(By.xpath(".//*[@id='successMessageDiv']/b")).getText(), "Outright Forward Deal inserted successfully");
			System.out.println("Element INSERTED SUCCESSFULLY");
		}
		
		else {
		assertEquals(driver.findElement(By.xpath(".//*[@id='successMessageDiv']/b")).getText(), "Outright Forward Deal inserted successfully");
		System.out.println("Element INSERTED SUCCESSFULLY");
		}
		Thread.sleep(1000);
		
		String DealID = driver.findElement(By.xpath(".//*[@id='dealId']")).getAttribute("value");
	
			System.out.println(DealID);
			
			String DBData = DB.DBConnection("select * from FX_OBJECT.DEAL_OUTRIGHT_FORWARD where DEAL_ID =" + DealID);
			
			System.out.println("DATABASE VALUE IS= "+ DBData);
			
			assertEquals(DealID, DBData, "DB VALUES NOT MATCHING");
		
	} catch (Exception e) {
	
		System.out.println("THE FOLLOWING ERROR OCCURED WHILE INSERTING:- "+ driver.findElement(By.xpath(".//*[@id='errorMessageDiv']/b")).getText());
	}	
	
}
    
  
  
  @AfterClass
  public void afterMethod() {
 
	  driver.close();

  }

  
  
  
  public void SelectElement(String xpath, String selectBytext) {

		Select ele1= new Select(driver.findElement(By.xpath(xpath)));
			ele1.selectByVisibleText(selectBytext);
		}
  
  public void find_Click(String Xpath) {
	  
	driver.findElement(By.xpath(Xpath)).click();  
  }
  

  public void find_Send(String Xpath, String y) {
	  
	driver.findElement(By.xpath(Xpath)).sendKeys(y); 
  }



}

