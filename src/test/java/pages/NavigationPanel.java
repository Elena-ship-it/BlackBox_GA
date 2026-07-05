package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPanel extends BasePage {

    private final By cartLink = By.className("shopping_cart_link");

    public NavigationPanel(WebDriver driver) {
        super(driver);
    }

    public BasketPage openCart() {

        click(cartLink);

        return new BasketPage(driver);
    }
}
