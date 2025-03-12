package testCases;


import java.time.Duration;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClassCommonMethods;

public class TC001_AccountRegistrationTest extends BaseClassCommonMethods{
	
	
	@Test(groups={"Regression","Master"})
	public void verify_accountregistration()
	{
		logger.info("....starting TC001_AccountRegistrationTest.....");
		
		try
		{
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("clicked on My Account link");
		hp.clickRegister();
		logger.info("clicked on Register link");
		
		
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);	
		
		logger.info("providing customer details...");
		regpage.setFirstname(randomString().toUpperCase());
		regpage.setLastname(randomString().toUpperCase());
		regpage.setEmail(randomNumber()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmpassword(password);
		
		regpage.setPrivacypolicy();
		regpage.clickContinue();
		
		logger.info("validating expected message...");
		
		String confmessage=regpage.getConfirmationMsg();
		if(confmessage.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed...");
			Assert.assertTrue(false);
		}
		
	  }
		 catch(Exception e)
		{
			 
			
			 Assert.fail();
		}
		logger.info("....finished TC001_AccountRegistrationTest.....");
		
	}		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	

}
