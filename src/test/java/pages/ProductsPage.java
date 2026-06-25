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
        System.out.println("ЛОГ: Успешно перешли. Заголовок текущей страницы: '" + titleText + "'");
        return titleText;
    }

    public void addToCartByName(String productName) {
        System.out.println("ДЕЙСТВИЕ: Поиск товара '" + productName + "' и добавление в корзину");
        String xpathExpression = String.format("//div[@class='inventory_item'][.//div[@class='inventory_item_name ' and text()='%s']]//button", productName);
        By addToCartButton = By.xpath(xpathExpression);
        clickElement(addToCartButton);
    }

    public boolean isCartBadgeDisplayed() {
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).isDisplayed();
        System.out.println("ПРОВЕРКА: Отображается ли круглая плашка корзины? Ответ: " + isDisplayed);
        return isDisplayed;
    }

    public String getCartBadgeText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
        System.out.println("ЛОГ: Число на плашке корзины: " + text);
        return text;
    }

    public String getCartBadgeBackgroundColor() {
        String color = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getCssValue("background-color");
        System.out.println("ЛОГ: Получен CSS-цвет фона плашки: " + color);
        return color;
    }
}
