package FXMMTestScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import databaseConnection.DBConnection_FolderGroup;

public class Screen_FolderGroup {
	
	WebDriver driver;
	DBConnection_FolderGroup DB = new DBConnection_FolderGroup();
	Screen_OutrightForward Screen_Outright = new Screen_OutrightForward();
	String URL = "https://bga-ux-wsd1.na.dir.bunge.com/fxmm/";  
	String FolderName;
	
	@BeforeClass
	public void BeforeMethod() {

System.setProperty("webdriver.gecko.driver", "D:\\A.K_softwares\\JAR_Files\\Jars\\geckodriver.exe");
FirefoxOptions dc = new FirefoxOptions();     //DesiredCapabilities dc = DesiredCapabilities.firefox(); were used earlier
dc.setCapability("marionette", true);
dc.setCapability("acceptInsecureCerts", true); 
driver = new FirefoxDriver(dc);
//driver.manage().deleteAllCookies();
driver.get(URL);
driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		
	}
	

	
	
	@AfterClass
	public void AfterMethod() {
		
	}
	
	
	
	
	@Test (priority = 1)
	public void TestCreatedBy() throws InterruptedException {
		
	
	driver.findElement(By.name("USER")).clear();	  
	driver.findElement(By.name("USER")).sendKeys("UMASING");
	driver.findElement(By.name("PASSWORD")).clear();
	driver.findElement(By.name("PASSWORD")).sendKeys("Bunge123");	
	
	driver.findElement(By.xpath("html/body/table[1]/tbody/tr[3]/td/form/p/table/tbody/tr[1]/td/table/tbody/tr[4]/td/input")).click();
	Thread.sleep(1000);
	driver.get("https://bga-ux-wsd1.na.dir.bunge.com/fxmm/dataManagement/staticData/hierarchy/folderGroup/main");
	Thread.sleep(5000);
	
	WebElement CreatedBy = driver.findElement(By.xpath(".//*[@id='form']/fieldset/fieldset/div[1]/div[1]/label[1]"));
	WebElement CreatedOn = driver.findElement(By.xpath(".//*[@id='form']/fieldset/fieldset/div[1]/div[2]/label[1]"));
	WebElement UpdatedBy = driver.findElement(By.xpath(".//*[@id='form']/fieldset/fieldset/div[2]/div[1]/label[1]"));
	WebElement UpdatedOn = driver.findElement(By.xpath(".//*[@id='form']/fieldset/fieldset/div[2]/div[2]/label[1]"));
	
	assertTrue(CreatedBy.isDisplayed());
	assertTrue(CreatedOn.isDisplayed());
	assertTrue(UpdatedBy.isDisplayed());
	assertTrue(UpdatedOn.isDisplayed());
	
	
	driver.findElement(By.xpath(".//*[@id='form']/fieldset/div[2]/div/img")).click();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath(".//*[@id='folderGroupListDialog']/div/div[2]/table/tbody/tr[3]/td[1]")).click();
	Thread.sleep(3000);
	FolderName = driver.findElement(By.xpath(".//*[@id='folderGroup']")).getAttribute("value");
	System.out.println(FolderName);
	
	/*String TxtCreatedBy = driver.findElement(By.xpath(".//*[@id='form']/fieldset/fieldset/div[1]/div[1]/label[2]/input")).getAttribute("value");
	System.out.println("Created By: " + TxtCreatedBy);
	try {
//	String DBData = DB.DBConnection("select first_name, last_name from FX_OBJECT.FOLDER_GROUP where folder_group = '" + FolderName + "'", "CREATED_BY");
	String DBData2 = DB.DBConnection("select first_name, last_name from FX_OBJECT.FX_USER where USER_ID in (select created_by from FX_OBJECT.FOLDER_GROUP where folder_group = '"+FolderName+"')", "first_Name");
	System.out.println(DBData2);
//	assertEquals(TxtCreatedBy, DBData2, "NO ENTRY FOUND IN DATABASE");
	if(TxtCreatedBy.contains(DBData2)) 
	{
	System.out.println("Data Verified Successfully");
	}
	else {
	System.out.println("Data not matching.. FAILURE");
	}
	
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	
	}
	
	
	/*@Test (priority = 2)
	public void TestupdatedBy() throws InterruptedException {
	
		FolderName = driver.findElement(By.xpath(".//*[@id='folderGroup']")).getAttribute("value");
		String TxtUpdatedBy = driver.findElement(By.xpath(".//*[@id='form']/fieldset/fieldset/div[2]/div[1]/label[2]/input")).getAttribute("value");
		System.out.println("Updated By: " + TxtUpdatedBy);
		try {
		String DBData = DB.DBConnection("select first_name, last_name from FX_OBJECT.FX_USER where USER_ID in (select updated_by from FX_OBJECT.FOLDER_GROUP where folder_group = '"+FolderName+"')", "first_Name");
		System.out.println(DBData);
		if(TxtUpdatedBy.contains(DBData)) 
		{
		System.out.println("Data Verified Successfully");
		}
		else {
		System.out.println("Data not matching.. FAILURE");
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	
	@Test (priority = 2)
	public void TestCreatedOn() throws InterruptedException {
	
		FolderName = driver.findElement(By.xpath(".//*[@id='folderGroup']")).getAttribute("value");
		String TxtCreatedOn = driver.findElement(By.xpath(".//*[@id='form']/fieldset/fieldset/div[1]/div[2]/label[2]/input")).getAttribute("value");
		System.out.println("Created On: " + TxtCreatedOn);
		try {
		String DBData = DB.DBConnection("select CREATED_ON from FX_OBJECT.FOLDER_GROUP where folder_group = '" + FolderName + "'", "CREATED_ON");
		System.out.println(DBData);
		if(TxtCreatedOn.contains(DBData)) 
		{
		System.out.println("Data Verified Successfully");
		}
		else {
		System.out.println("Data not matching.. FAILURE");
		
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}



