package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(pageTitle);
    }

    public void addToCart(String productName) {

        By button = By.xpath(
                "//div[text()='" + productName +
                        "']/ancestor::div[@class='inventory_item']//button");

        click(button);
    }

    public boolean isCartBadgeDisplayed() {
        return isDisplayed(cartBadge);
    }

    public String getCartBadgeText() {
        return getText(cartBadge);
    }

    public String getCartBadgeBackgroundColor() {
        return driver.findElement(cartBadge)
                .getCssValue("background-color");
    }

    public BasketPage openCart() {

        click(cartLink);

        return new BasketPage(driver);
    }
}