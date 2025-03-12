package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClassCommonMethods;
import utilities.DataProviders;



/*Data is valid  - login success - test pass  - logout
                  - login failed - test fail

Data is invalid - login success - test fail  - logout
                - login failed - test pass
*/

public class TC003_LoginDDT extends BaseClassCommonMethods

{

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups="Datadriven")  //getting dataProvider from different class
	public void verify_loginDDT(String email, String password, String exp)  //exp=expected result
	{
		logger.info("**** Starting TC_003_LoginDDT *****");
		
		try {
	
		//Home page
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin(); //Login link under MyAccount
				
			//Login page
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin(); //Login button
				
			//My Account Page
			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetPage=macc.isMyAccountPageExists(); 
			

/*Data is valid  - login success - test pass  - logout
                  - login failed - test fail

Data is invalid - login success - test fail  - logout
                - login failed - test pass
*/

			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail("An exception occurred: " + e.getMessage());
		}
			
		logger.info("**** Finished TC_003_LoginDDT *****");
	}
	
}








