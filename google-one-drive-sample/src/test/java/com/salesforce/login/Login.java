package com.salesforce.login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import PDFEmail.BaseClass;


public class Login {
	
	private Properties obj;
	public Properties getObj() {
		return obj;
	}

	public void setObj(Properties obj) {
		this.obj = obj;
	}

	public Properties getTestData() {
		return TestData;
	}

	public void setTestData(Properties testData) {
		TestData = testData;
	}

	public  WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	private Properties TestData;
	private WebDriver driver = BaseClass.getDriver();
	
	public Login()
	{
	try {
		LoadPropertyFiles();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

	public void LoadPropertyFiles() throws IOException
	{
	
		obj = new Properties();
		// Create Object of FileInputStream Class. Pass file path.
		FileInputStream objfile = new FileInputStream(
				System.getProperty("user.dir")
						+ "\\src\\com\\salesforce\\main\\object.properties");
		// Pass object reference objfile to load method of Properties object.
		obj.load(objfile);

		TestData = new Properties();
		// Create Object of FileInputStream Class. Pass file path.
		FileInputStream testdatafile = new FileInputStream(
				System.getProperty("user.dir")
						+ "\\src\\com\\salesforce\\main\\testdata.properties");
		// Pass object reference objfile to load method of Properties object.
		TestData.load(testdatafile);

		
	}
	public  void SFLogin() throws InterruptedException {
		
		driver.get(obj.getProperty("SF-URL"));
		driver.findElement(By.id(obj.getProperty("SF-UN"))).sendKeys(
				TestData.getProperty("SF-TDUN"));
		
		WebElement pwd = driver.findElement(By.id(obj.getProperty("SF-PW")));

		//byte[] encodedBytes = Base64.encodeBase64(TestData.getProperty("SF-TDPW").getBytes());
		//System.out.println("encodedBytes "+ new String(encodedBytes));

		byte[] decodedBytes = Base64.decodeBase64(TestData.getProperty("SF-TDPW"));
		//System.out.println("decodedBytes "+ new String(decodedBytes));

		pwd.sendKeys(new String(decodedBytes));
		
		
		//driver.findElement(By.id(obj.getProperty("SF-PW"))).sendKeys(
				//TestData.getProperty("SF-TDPW"));
		driver.findElement(By.id(obj.getProperty("SF-LoginButton"))).click();
		Thread.sleep(50000);
	

		
	}
	public void NSLogin() throws InterruptedException
	{
		driver.get(obj.getProperty("NS-URL"));
		Thread.sleep(10000);
		  driver.findElement(By.xpath(obj.getProperty("NS-UN"))).sendKeys(TestData.getProperty("NS-TDUN"));
		  
		  WebElement pwd = driver.findElement(By.name(obj.getProperty("NS-PW"))); 
		  
		//byte[] encodedBytes = Base64.encodeBase64(TestData.getProperty("NS-TDPW").getBytes());
		//System.out.println("encodedBytes "+ new String(encodedBytes));
		  
		  byte[] decodedBytes = Base64.decodeBase64(TestData.getProperty("NS-TDPW"));
		 // System.out.println("decodedBytes "+ new String(decodedBytes));
		  pwd.sendKeys(new String(decodedBytes));
		  
		 // driver.findElement(By.name(obj.getProperty("NS-PW"))).sendKeys(TestData.getProperty("NS-TDPW"));
		  driver.findElement(By.name(obj.getProperty("NS-LoginButton" ))).click();
		  driver.findElement(By.name(obj.getProperty("NS-SecurityAnswer"))).sendKeys(TestData.getProperty("NS-TDSecAnswer"));
		  driver.findElement(By.name(obj.getProperty("NS-SecuritySubmit"))).click();
		  Thread.sleep(10000); //driver.quit();
		  
		  
		 
		  Actions actions=new Actions(driver);
		  
		 WebElement hover=driver.findElement(By.xpath(obj.getProperty("NS-Role")));
		  
		 actions.moveToElement(hover).build().perform();
		 Thread.sleep(20000);
		 driver.findElement(By.partialLinkText(obj.getProperty("NS-RoleSelect"))).click();
		 Thread.sleep(10000);
		
	}

	public void BrowserCheckIn(String browser)
	{
		
		 
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:/Santhosh/selenium/Project 12990/Salesforce/lib/chromedriver.exe");
				driver = new ChromeDriver();
			} 
			
			else if (browser.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
			}
			else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						"D:/Dev/Jars/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		
		}
	}
	
	

