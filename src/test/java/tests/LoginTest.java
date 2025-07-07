package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginTest extends BaseTest {
	
	
	   WebDriver driver;
	    LoginPage loginPage;
	    ConfigReader configreader = ConfigReader.getInstance();
	    
	    
	    @BeforeClass
	    public void setup() {
	        driver = DriverFactory.createDriver(configreader.getBrowser());
	        driver.manage().window().maximize();
	        driver.get("https://demowebshop.tricentis.com/login");
	        loginPage = new LoginPage(driver);
	        
	    }

	    @Test(description = "Login and validate welcome page URL")
	    @Description("Valid login with upasana.pal@intellias.com and URL validation after clicking welcome message.")
	    public void loginAndValidateUrl() {
	    	configreader.getProperty("username1");
	    	configreader.getPassword();       
	    	loginPage.clickLogin();
	    	
	        String expectedUrl = "https://demowebshop.tricentis.com/";
	        String actualUrl = driver.getCurrentUrl();
	        Assert.assertEquals(actualUrl, expectedUrl, "Redirected URL after login and welcome link click mismatch.");
	    }


}
