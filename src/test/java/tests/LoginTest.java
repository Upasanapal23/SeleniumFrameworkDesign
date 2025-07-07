package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pages.LoginPage;
import utils.ConfigReader;
// Removed unused import utils.DriverFactory;

public class LoginTest extends BaseTest {
	
	@DataProvider(name = "loginData")
	public static Object[][] loginData() {
		ConfigReader configreader = ConfigReader.getInstance();
		return new Object[][] {
				{configreader.getUsername(), configreader.getPassword()}
		};
	}
	
	    @Test(description = "Login and validate welcome page URL", dataProvider = "loginData")
	    @Description("Valid login with upasana.pal@intellias.com and URL validation after clicking welcome message.")
	    public void loginAndValidateUrl(String username, String password) {
			// Removed redundant ConfigReader instance creation.
			ConfigReader configreader = getConfigReader(); // Get ConfigReader from BaseTest
			LoginPage loginPage = new LoginPage(getDriver());

			navigateTo(configreader.getLoginUrl()); // Navigate to login page using ConfigReader
			loginPage.login(username, password);

			// Add explicit wait for an element on the page after login
			getWait().until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/")); // Example: wait for URL to be the expected URL
			String expectedUrl = "https://demowebshop.tricentis.com/";
			String actualUrl = getDriver().getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl, "Redirected URL after login and welcome link click mismatch.");
	    }
}
