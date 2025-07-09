package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import utils.ConfigReader;


public class LoginTest extends BaseTest {
    @Step("Test01")
    @Test(description = "Login and validate welcome page URL")
    @Description("Valid login with username and password from config.")
    public void loginAndValidateUrl() {
        // // Get ConfigReader from BaseTest
        ConfigReader configreader = getConfigReader();
        LoginPage loginPage = new LoginPage(getDriver());

        navigateTo(configreader.getLoginUrl());
        // Get username and password from ConfigReader and declare the variables
        String username = configreader.getUsername();
        String password = configreader.getPassword();
        loginPage.login(username, password);

        // Add explicit wait for an element on the page after login
        getWait().until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/"));
        String expectedUrl = "https://demowebshop.tricentis.com/";
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Redirected URL after login and welcome link click mismatch.");
    }
}
