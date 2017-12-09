package generic;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest implements IAutoConstant {
	
	public static WebDriver driver;
	static
	{
		System.setProperty(GECKO_KEY, GECKO_VALUE);
		//System.setProperty(CHROME_KEY, CHROME_VALUE);
	}
	@BeforeMethod
	public void openApplication()
	{
		driver = new FirefoxDriver();
		String url = Lib.getproperty(CONFIG_PATH,"URL");
		driver.get(url);
		String ITO = Lib.getproperty(CONFIG_PATH, "ImplicitTimeOut");
		int timeOutPeriod = Integer.parseInt(ITO);
		System.out.println("TimeOUT--"+timeOutPeriod);
		driver.manage().timeouts().implicitlyWait(timeOutPeriod, TimeUnit.SECONDS);
	}
	@AfterMethod
	public void closeApplication()
	{
		driver.close();
	}
	
	public void takeScreenshot(String testname)
	{
		Date d = new Date();
		String currentdate = d.toString().replaceAll(":", "_"); 
		
		TakesScreenshot ts =  (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(".\\ScreenShots\\"+currentdate+"\\"+testname+"_ScreenShots.png");
		try
		{
			FileUtils.copyFile(srcFile, destFile);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
		
	}
