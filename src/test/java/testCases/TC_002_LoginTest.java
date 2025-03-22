package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.Myaccount_page;
import testBase.Baseclass;

public class TC_002_LoginTest extends Baseclass
{
	@Test
	public void logintestcase() 
	{
		try {
		Homepage hp=new Homepage(driver);
		hp.clickmy_ac_btn();
		hp.click_login_btn();
		
		Loginpage lp=new Loginpage(driver);
		lp.txt_emailfield(p.getProperty("email"));
		lp.txt_passwordbox(p.getProperty("password"));
		lp.click_loginbtn();
		
		Myaccount_page myac=new Myaccount_page(driver);
		Assert.assertEquals(myac.myaccountpagestatus(),true);
		myac.click_logoutbtn();
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
	}
}
