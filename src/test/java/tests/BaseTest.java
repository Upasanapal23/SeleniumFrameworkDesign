package tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ConfigReader configReader;

    @BeforeMethod
    public void setUp() {
        configReader = ConfigReader.getInstance();

        // Only initialize ChromeDriver for now
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // You can still check if the config specifies chrome, but it won't change execution
        if (!configReader.getBrowser().equalsIgnoreCase("chrome")) {
            System.out.println(" Configuration specifies a browser other than Chrome, but only Chrome is supported in this setup.");
        }

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(configReader.getExplicitWaitTimeout()));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    protected void navigateTo(String url) {
        driver.get(url);
    }


    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait() {
        return wait;
    }

    protected ConfigReader getConfigReader() {
        return configReader;
    }
}