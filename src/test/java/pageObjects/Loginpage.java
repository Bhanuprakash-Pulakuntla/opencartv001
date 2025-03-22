package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends Basepage
{

	public Loginpage(WebDriver driver) 
	{
		super(driver);
	}
@FindBy(xpath="//input[@id='input-email']")
WebElement txt_emailaddress;

@FindBy(xpath="//input[@id='input-password']")
WebElement txt_passwordfield;	

@FindBy(xpath="//input[@value='Login']")
WebElement button_login;

public void txt_emailfield(String emailaddress) 
{
	txt_emailaddress.sendKeys(emailaddress);
}

public void txt_passwordbox(String password) 
{
	txt_passwordfield.sendKeys(password);
}

public void click_loginbtn() 
{
	button_login.click();
}
	
}
