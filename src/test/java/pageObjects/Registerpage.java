package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Registerpage extends Basepage
{
public Registerpage(WebDriver driver) 
	{
		super(driver);
	}
	
@FindBy(xpath="//input[@id='input-firstname']") ////input[@id='input-firstname']
WebElement txt_firstname;
@FindBy(xpath="//input[@id='input-lastname']") 
WebElement txt_laststname;
@FindBy(xpath="//input[@id='input-email']") 
WebElement txt_email;
@FindBy(xpath="//input[@id='input-telephone']") 
WebElement txt_telephone_num;
@FindBy(xpath="//input[@id='input-password']") 
WebElement txt_password;
@FindBy(xpath="//input[@id='input-confirm']") 
WebElement txt_confirm_password;
@FindBy(xpath="//input[@name='agree']") 
WebElement policy_checkbox;
@FindBy(xpath="//input[@value='Continue']") 
WebElement btn_continue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement confirmation_msg;

public void set_firstname(String firstname) 
{
	txt_firstname.sendKeys(firstname);
}

public void set_lastname(String lastname) 
{
	txt_laststname.sendKeys(lastname);
}

public void set_email(String email) 
{
	txt_email.sendKeys(email);
}

public void set_phonenum(String phonenum) 
{
	txt_telephone_num.sendKeys(phonenum);
}

public void set_password(String pwd) 
{
	txt_password.sendKeys(pwd);
}

public void set_confirm_pwd(String pwd) 
{
	txt_confirm_password.sendKeys(pwd);
}

public void click_checkbox() 
{
	policy_checkbox.click();
}

public void click_continue() 
{
	btn_continue.click();
}

public String get_confirmation_msg() 
{
	try 
	{
		return (confirmation_msg.getText());
	}
	catch(Exception e) 
	{
		 return (e.getMessage());
	}
}


}












