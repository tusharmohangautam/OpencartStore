package pageObjects;
// this class is parent of all pageobject classes


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	
	
	
	
	
	

}
