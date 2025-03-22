package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Basepage
{
	public Homepage(WebDriver driver) 
	{
		super(driver);
	}
	
@FindBy(xpath="//a[@title='My Account']") 
WebElement my_account_btn;
@FindBy(xpath="//a[normalize-space()='Register']")
WebElement register_btn;
@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
WebElement login_btn;

public void clickmy_ac_btn() 
{
	my_account_btn.click();
}

public void click_register_btn() 
{
	register_btn.click();
}

public void click_login_btn() 
{
	login_btn.click();
}
}
