package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.Myaccount_page;
import testBase.Baseclass;
import utilities.Dataproviders;

public class TC_003_LoginDDT extends Baseclass
{
	@Test(dataProvider="logindata",dataProviderClass=Dataproviders.class)//where dataProvidersClass is attribute and "Dataproviders" is a class name which is having the dataprovider method.
	public void verification_loginDDT(String email,String pwd,String expvalue) 
	{
		
		try {
		//Home page
		Homepage hp=new Homepage(driver);
		hp.clickmy_ac_btn();
		hp.click_login_btn();
		//login page
		Loginpage lp=new Loginpage(driver);
		lp.txt_emailfield(email);
		lp.txt_passwordbox(pwd);
		lp.click_loginbtn();
		//myaccount page
		Myaccount_page myac=new Myaccount_page(driver);
		boolean targetpage=myac.myaccountpagestatus();
		
		//test case validation
		/*Valid data--  login--testpass--logout
		 * 				failed to login--test fail
		 * Invalid data--  login--testfail
		 * 					failed to login--testpass--logout
		 */
		
		if(expvalue.equalsIgnoreCase("Valid")) 
		{
			if(targetpage==true) 
			{
				myac.click_logoutbtn();
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}
		if(expvalue.equalsIgnoreCase("Invalid")) 
		{
			if(targetpage==true) 
			{
				myac.click_logoutbtn();
				Assert.assertTrue(false);
			}
			else 
			{
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e) 
		{
			Assert.fail();
		}
	}
}
	
