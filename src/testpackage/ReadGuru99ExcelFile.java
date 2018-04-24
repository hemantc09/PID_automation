package testpackage;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Table.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ReadGuru99ExcelFile {

	private static final String String = null;

	public void readExcel(String filePath, String fileName, String sheetName) throws IOException {
		
		// Initialize browser
		//path /Users/hchoudhari/Documents/Selenium/chromedriver

		
				

		// Create an object of File class to open xlsx file  

		File file = new File(filePath + "//" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			guru99Workbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class

			guru99Workbook = new HSSFWorkbook(inputStream);

		}

		// Read sheet inside the workbook by its name

		Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);

		// Find number of rows in excel file

		int rowCount = guru99Sheet.getLastRowNum() - guru99Sheet.getFirstRowNum();
		System.out.println("row count is: " + rowCount);
		// Create a loop over all the rows of excel file to read it
		System.out.println(
				"================================================================================================================================================================================");
		/**
		 * get the one touch point and get the value to test against 1. Market name:
		 * e.g. BS 2. Touch point e.g. subscribe 3. Offer : UDA - Unlimited digital
		 * access 4. Offer line 1 : Unlimited Digital Access 5. Offer line 2: get your
		 * access today blah blah .... 6. Offer line 3: 4 weeks for 99¢ 7. Offer line 4:
		 * Then $1.99/week; billed every 4 weeks; cancel anytime 8. Offer URL:
		 * https://checkout.baltimoresun.com/subscriptionPanel/?presentation=MTRDigital&PID=5474&zipCode=00000
		 * 9. Market zipCode: e.g. 21201 10. 8. Store result: PASS or FAIL
		 */

		String marketName = null;
		String touchPoint = null;
		String offer = null;
		String line1Copy = null;
		String line2Copy = null;
		String line3Copy = null;
		String line4Copy = null;
		String result;
		String pid = null;
		String url = null;
		String zipCode = null;
		String totalPrice = null;// price from the xl sheet
		String returnResult;
		Row row;
		for (int i = 1; i < 2; i++) { // reading only 31 row constant number *Find the solution to read dynamic rows
										// with no empty row reading*

			row = guru99Sheet.getRow(i);

			// System.out.println("===================================================================================================");
			// Create a loop to print cell values in a row
			System.out.println("Row #" + i + ":");
			for (int j = 0; j < 8; j++) { // reading only 8 columns *Find the dynamic solution to read the columns with
											// no empty cells reading *

				// Print Excel data in console
				DataFormatter formatter = new DataFormatter(); // creating formatter using the default locale
				String celldata = formatter.formatCellValue(guru99Sheet.getRow(i).getCell(j));

				/*
				 * storing the only one row data at a time based one cell value
				 */

				touchPoint = formatter.formatCellValue(guru99Sheet.getRow(i).getCell(0));
				marketName = sheetName; // market name is taken from sheet name is sheet name is BS then market is
										// Baltimore Sun
				offer = formatter.formatCellValue(guru99Sheet.getRow(i).getCell(1)); // UDA = Unlimited Digital Access
				line1Copy = formatter.formatCellValue(guru99Sheet.getRow(i).getCell(1)); // also UDA = Unlimited Digital
																							// Access
				
				if(line1Copy.equals("UDA"))
				{
					//set the copy if its UDA = "Unlimited Digital Access
					line1Copy = "Unlimited Digital Access";
				}
				
				line2Copy = formatter.formatCellValue(guru99Sheet.getRow(i).getCell(2));
				// totalPrice = line2Copy.substring(10); // find the solution to read the total
				// price e.g. 99 from the xl sheet dyanmic
				totalPrice = "0.99";
				line3Copy = formatter.formatCellValue(guru99Sheet.getRow(i).getCell(3));
				line4Copy = formatter.formatCellValue(guru99Sheet.getRow(i).getCell(4));
				pid = formatter.formatCellValue(guru99Sheet.getRow(i).getCell(5));
				url = formatter.formatCellValue(guru99Sheet.getRow(i).getCell(6));
				zipCode = "00000"; // for UDA zipcode is alwasy 00000
				// find a solution to add dynamic PID based on sheet name e.g. is Sheetname is
				// BST then PID should be 21201 - differnt logic for print mix
				result = formatter.formatCellValue(guru99Sheet.getRow(i).getCell(7));
				// System.out.print("\t | " + celldata);

			}
			System.out.println("Touch Point: " + touchPoint);
			System.out.println("Market Name: " + marketName);
			System.out.println("Offer: " + offer);
			System.out.println("Total Price: " + totalPrice);
			System.out.println("Line 1 Copy: " + line1Copy +"\t\t length:"+line1Copy.length());
			System.out.println("Line 2 copy: " + line2Copy);
			System.out.println("Line 3 copy: " + line3Copy);
			System.out.println("Line 4 copy: " + line4Copy);
			System.out.println("PID: " + pid);
			System.out.println("Zip Code: " + zipCode);
			System.out.println("URL: " + url);
			System.out.println(
					"================================================================================================================================================================================");

			// Open the URL in the browser verify the URL is successful
			// Get the PID from the URL
			// Get the market name from the URL
			// get the zipCode from the URL
			// get the line 1 copy from the URL
			// get the line 2 copy from the URL
			// get the line 3 copy from the URL
			// get the line 4 copy from the URL
			// get the price from the URL
			 
			
			//driver = new ChromeDriver();
			//driver.get(baseUrl);
			
			returnResult = openPidUrl(url,line1Copy, line2Copy, line3Copy,line4Copy,pid,zipCode);
		}
	}

	// Main function is calling readExcel function to read data from excel file

	private String openPidUrl(String url,String line1Copy, String line2Copy,String line3Copy,String line4Copy,String pid,String zipCode) {
		
		String urlOfferLine1Copy; // get the line 1 to 4 copy from the PID URL  webpage
		String urlOfferLine2Copy;
		String urlOfferLine3Copy;
		String urlOfferLine4Copy;
		
		boolean line1flag = false;
		boolean line2flag;
		boolean line3flag;
		boolean line4flag;
		
		WebDriver driver;
		
		
		
		String baseUrl = "https://www.google.com";
		System.setProperty("webdriver.chrome.driver","/Users/hchoudhari/Documents/Selenium/chromedriver");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		driver.get(url);
		urlOfferLine1Copy = driver.findElement(By.className("offer-header")).getText();
		urlOfferLine3Copy = driver.findElement(By.xpath("//*[@id='selectable-offers']/ul/li/div/div[3]/span")).getText();
		urlOfferLine4Copy = driver.findElement(By.xpath(".//*[@id='selectable-offers']/ul/li/div/div[3]/p")).getText();
		
		//urlOfferLine3Copy = driver.findElement(By.ByXPath)
		
		// urlOfferLine2Copy = // find the line 2 copy PID and use it for testing later. make sure use the unique find tool from selenium 
		//urlOfferLine3Copy = driver.findElement(By.className(""));
		
		
		if(line1Copy.equals(urlOfferLine1Copy))
		{
			line1flag = true;
		}
		
		System.out.println("URL offer Line 1 copy = " +urlOfferLine1Copy + "\t line1flag:"+line1flag + "\tlength:"+ urlOfferLine1Copy.length());
		
		System.out.println("URL offer Line 3 copy = " +urlOfferLine3Copy);
		System.out.println("URL offer Line 4 copy = " +urlOfferLine4Copy);
		
		
		/*
		 * IDE script below
		 */
		
		driver.findElement(By.id("emailForDis")).clear();
	    driver.findElement(By.id("emailForDis")).sendKeys("hemanttronc+04242018@gmail.com");
	   // Thread.sleep(3000);
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("user-pswd")));
	    
	   
	    
	    driver.findElement(By.id("user-pswd")).clear();
	    
	    driver.findElement(By.id("user-pswd")).sendKeys("Tribune123");
	    driver.findElement(By.id("user-pswd-repeat")).clear();
	    driver.findElement(By.id("user-pswd-repeat")).sendKeys("Tribune123");
	    driver.findElement(By.xpath("//div[@id='digitalAccess']/div[2]/div[3]/a/span")).click();
	    driver.findElement(By.xpath("//input[@type='text']")).clear();
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Hemant");
	    driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("Test");
	    driver.findElement(By.xpath("//input[@type='tel']")).clear();
	    driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("2342342342");
	    driver.findElement(By.xpath("(//input[@type='tel'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@type='tel'])[2]")).sendKeys("21208");
	    driver.findElement(By.xpath("(//input[@type='tel'])[3]")).sendKeys("1111111111111111");
	    new Select(driver.findElement(By.xpath("//div[@id='credit-card-option']/div[2]/div/div/div/select"))).selectByVisibleText("01 - Jan");
	    new Select(driver.findElement(By.xpath("//div[@id='credit-card-option']/div[2]/div/div/div[2]/select"))).selectByVisibleText("2020");
	   // driver.findElement(By.partialLinkText("Continue")).click();
	    
	 //   driver.findElement(By.className("col-lg-7 col-xs-8 col-full text-right step-button")).click();
	    
	    ///div[2]/div[3]/a/span
	    
	    driver.findElement(By.xpath(".//*[@id='payment']/div[2]//a/span")).click();
	    
	    System.out.println("here is I am ");
	    //driver.findElement(By.xpath("//div[@id='payment']/div[2]/div[3]/a/span")).click();
		
		
		/**
		 * IDE script end here
		 */
		
		return "Pass";
	}

	public static void main(String... strings) throws IOException {

		// Create an object of ReadGuru99ExcelFile class

		ReadGuru99ExcelFile objExcelFile = new ReadGuru99ExcelFile();
		String filepath = System.getProperty("user.dir") + "//src//testpackage";
		objExcelFile.readExcel(filepath, "MasterPidList.xlsx", "bsttemp");
		
		
		
		//String baseUrl = "https://www.google.com";
//		WebDriver driver;
//		System.setProperty("webdriver.chrome.driver","/Users/hchoudhari/Documents/Selenium/chromedriver");
		
		
		
		//System.setProperty("webdriver.chrome.driver", "/Users/hchoudhari/Documents/Selenium/chromedriver.exe");
		
		 
		///Users/hchoudhari/Documents/qSelenium
		
		
	//	System.setProperty("webdriver.ie.driver", "C:/selenium/IEDriverServer_Win32_3.0.0/IEDriverServer.exe");
		
		
		// Prepare the path of excel file

		// String filePath =
		// System.getProperty("user.dir")+"\\src\\excelExportAndFileIO";

		// Call read file method of the class to read data

		// WebDriver driver;

		// create an object of Pid_Test class
		// PID_Test_Class pidtestobject = new PID_Test_Class();

		// prepare the file path for the xl file// xl file is in
		// the testpackage

		// call read file method of the class to read the data
		// send filepath, file name e.g. , sheet name e.g. BST
		// pidtestobject.read_Excel(filepath,"readPID.xlsx","pid_data");

	}

}