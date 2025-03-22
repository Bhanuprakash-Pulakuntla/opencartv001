package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Registerpage;
import testBase.Baseclass;

public class TC_001_Accountregistrationtest extends Baseclass
{	@Test
	public void account_verification_msg() 
	{
		logger.info("test case TC_001_Accountregistrationtest execution started" );
		
		try {
		Homepage hp=new Homepage(driver);
		hp.clickmy_ac_btn();
		logger.info("clicked on myaccount link");
		hp.click_register_btn();
		logger.info("clicked on register account link");
		Registerpage rp=new Registerpage(driver);
		logger.info("passing the information to the required fields");
		rp.set_firstname(randomstring().toUpperCase());
		rp.set_lastname(randomstring().toUpperCase());
		rp.set_email(randomstring() +"@gmail.com");
		rp.set_phonenum(randomnumber());
		String password=randomstring()+"@"+randomnumber();
		rp.set_password(password);
		rp.set_confirm_pwd(password);
		rp.click_checkbox();
		rp.click_continue();
		String msg=rp.get_confirmation_msg();
		logger.info("validating the account registration successfull message");
		Assert.assertEquals(msg, "Your Account Has Been Created!");
		}
		catch(Exception e) 
		{
			logger.error("test case failed");
			logger.debug("debug logs...");
			Assert.fail();
		}
	logger.info("test case TC_001_Accountregistrationtest execution completed..");
	}
}
