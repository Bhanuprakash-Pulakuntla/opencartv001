package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Searching_product;
import testBase.Baseclass;

public class TC_004_Product_searching extends Baseclass
{
	@Test
	public void product_searching_testcase() 
	{
		try {
		Searching_product sp=new Searching_product(driver);
		sp.Pass_product_name("imac");
		sp.clcking_search_btn();
		sp.searched_product_image_clicking();
		logger.info("validation started");
		Assert.assertEquals(sp.searched_prodcut_name().toLowerCase(),"imac");
		}catch(Exception e)
		{
			Assert.fail();
		}
	}
}
