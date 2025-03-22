package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Myaccount_page extends Basepage
{

	public Myaccount_page(WebDriver driver) 
	{
		super(driver);
		
	}

@FindBy(xpath="//h2[normalize-space()='My Account']")//my account page heading
WebElement msgheading;

@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
WebElement logout_btn;

public boolean myaccountpagestatus() 
{
	try
	{
		return msgheading.isDisplayed();
	}
	catch(Exception e) 
	{
		return false;
	}
}

public void click_logoutbtn() 
{
	logout_btn.click();
}

}
