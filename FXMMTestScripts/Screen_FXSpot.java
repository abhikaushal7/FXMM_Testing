package FXMMTestScripts;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import databaseConnection.DBConnection;
import FXMMTestScripts.Screen_OutrightForward;

public class Screen_FXSpot {


	WebDriver driver;
	DBConnection DB = new DBConnection();
	Screen_OutrightForward Screen_Outright = new Screen_OutrightForward();
	
@Test
public void TestFXSpot() throws InterruptedException {

	
	driver.get("https://bga-ux-wsd1.na.dir.bunge.com/fxmm/dealCapture/fxSpot/main");
	Thread.sleep(3000);
	Screen_Outright.find_Click(".//*[@id='form']/fieldset[2]/div[1]/div/img");
	Thread.sleep(2000);
	Screen_Outright.find_Click(".//*[@id='folderListDialog']/div/div[2]/table/tbody/tr[1]/td[2]");
	Thread.sleep(2000);
	Screen_Outright.find_Click(".//*[@id='form']/fieldset[2]/div[2]/div/img");
	Thread.sleep(2000);
	Screen_Outright.find_Click(".//*[@id='counterpartyListDialog']/div/div[2]/table/tbody/tr[1]/td[2]");
	Thread.sleep(2000);
	Screen_Outright.find_Click(".//*[@id='form']/fieldset[2]/div[3]/div/img");
	Thread.sleep(2000);
	Screen_Outright.find_Click(".//*[@id='profileListDialog']/div/div[2]/table/tbody/tr[1]/td");
	Thread.sleep(2000);
	Screen_Outright.find_Send(".//*[@id='tradeDate']", driver.findElement(By.xpath(".//*[@id='tradeDate']")).getText());
	Screen_Outright.find_Send(".//*[@id='spotRate']", "11");
	Screen_Outright.find_Send(".//*[@id='amount1']", "100");
	Screen_Outright.find_Click(".//*[@id='insertButton']");
	Thread.sleep(1000);
	Screen_Outright.find_Click(".//*[@id='confirmDialogYesButton']");
	Thread.sleep(2000);
	
	
WebElement Loading = driver.findElement(By.xpath(".//*[@id='loadingModalTitle']"));
	
	
	
	try {
		if (Loading.isDisplayed()) {
			Thread.sleep(10000);
			assertEquals(driver.findElement(By.xpath(".//*[@id='successMessageDiv']/b")).getText(), "FX Spot Deal inserted successfully");
			System.out.println("Element INSERTED SUCCESSFULLY");
		}
		
		else {
		assertEquals(driver.findElement(By.xpath(".//*[@id='successMessageDiv']/b")).getText(), "FX Spot Deal inserted successfully");
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


}
