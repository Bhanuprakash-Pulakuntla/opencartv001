package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Searching_product extends Basepage
{

	public Searching_product(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement txt_searchbox;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']") 
	WebElement search_button;
	
	@FindBy(xpath="//img[@title='iMac']") 
	WebElement search_product_image;
	
	@FindBy(xpath="//a[normalize-space()='iMac']") 
	WebElement product_title_text;
	
	
	public void Pass_product_name(String productname) 
	{
		txt_searchbox.sendKeys(productname);
	}
	
	public void clcking_search_btn() 
	{
		search_button.click();
	}

	public void searched_product_image_clicking() 
	{
		search_product_image.click();
	}
	
	public String searched_prodcut_name() 
	{
		String text=product_title_text.getText();
		return text;
	}
}
