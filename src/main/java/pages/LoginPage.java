package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


public class LoginPage extends BasePage {

    @FindBy(id = "Email")
    private WebElement emailInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(css = "input[type='submit'][value='Log in']")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container") // Example selector for an error message container
    private WebElement errorMessageContainer;

    @FindBy(id = "shopping_cart_container")
    // Example selector for an element present after successful login (e.g., shopping cart icon)
    private WebElement shoppingCartIcon; // Assuming this element is visible after successful login

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }



    public void clickLogin() {
        waitForVisibility(loginButton, Duration.ofSeconds(10)); // Wait for login button to be visible
        click(loginButton);
    }

    public void login(String username, String password) {
        enterTextInField(emailInput, username);
        enterTextInField(passwordInput, password);
        clickLogin();
    }
}
