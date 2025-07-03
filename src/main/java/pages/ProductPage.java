package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {

    @FindBy(css = ".inventory_item:first-child button.btn_inventory")
    private WebElement addToCartButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addFirstProductToCart() {
        click(addToCartButton);
    }

    public String getCartBadgeCount() {
        return cartBadge.getText();
    }
}
