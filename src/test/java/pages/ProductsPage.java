package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    private final By title = By.xpath("//*[@class='title']");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        String titleText = wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
        return titleText;
    }

    public void addToCartByName(String productName) {
        String xpathExpression = String.format("//div[@class='inventory_item'][.//div[@class='inventory_item_name' and text()='%s']]", productName);
        By addToCartButton = By.xpath(xpathExpression);
        clickElement(addToCartButton);
    }

    public boolean isCartBadgeDisplayed() {
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).isDisplayed();
        return isDisplayed;
    }

    public String getCartBadgeText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
        return text;
    }

    public String getCartBadgeBackgroundColor() {
        String color = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getCssValue("background-color");
        return color;
    }
}

