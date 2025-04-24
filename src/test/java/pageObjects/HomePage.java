package pageObjects;

import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

//Constructor
	
	/* when not extending Basepage
	 * WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}*/
	
	public HomePage(WebDriver driver)          //when extending Basepage
	{
		super(driver);
		}
	
	
	//Locators
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister;
	
	@FindBy(linkText = "Login")   WebElement lnkLogin; // Login link added in step5 of document

	
	
	
	
	//Action Methods
	
	
	public void clickMyAccount()
	{
		lnkMyAccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
}
