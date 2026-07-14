package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class BasketPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By productNames = By.className("inventory_item_name");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(pageTitle);
    }

    public List<String> getProducts() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames))
                .stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    public int getProductsCount() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames)).size();
    }

    public boolean containsProduct(String productName) {
        return getProducts().contains(productName);
    }
}
