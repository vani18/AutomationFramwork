package scripts;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pompages.LoginPage;
import generic.BaseTest;
import generic.Lib;

public class TestLogin extends BaseTest {
	@Test
	public void testLogin() throws InterruptedException
	{
	LoginPage l = new LoginPage(driver);
	String un = Lib.getCellValue(EXCEL_PATH, "ValidLogin", 1, 0);
	String pw = Lib.getCellValue(EXCEL_PATH, "ValidLogin", 1, 1);
	String expectedtitle = Lib.getCellValue(EXCEL_PATH, "ValidLogin", 1, 2);
	
	l.setUsername(un);
	l.setPassword(pw);
	l.clickLogin();
	Thread.sleep(5000);
	
	String actualtitle= driver.getTitle();
	System.out.println(actualtitle);
	SoftAssert s= new SoftAssert();
	s.assertEquals(actualtitle, expectedtitle);
	s.assertAll();
	}
}
