package tests;

import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    @Test
    public void checkProductsPageTitle() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        String expectedTitle = "Products";
        String actualTitle = productsPage.getTitle();

        assertEquals(actualTitle, expectedTitle, "Заголовок страницы продуктов не совпадает!");
    }

    @Test
    public void checkAddToCartByProductName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        String productName = "Sauce Labs Backpack";
        productsPage.addToCartByName(productName);

        assertTrue(productsPage.isCartBadgeDisplayed(), "Плашка с количеством товаров не появилась возле корзины!");
        assertEquals(productsPage.getCartBadgeText(), "1", "Количество товаров на плашке корзины не соответствует");
    }

    @Test
    public void checkCartBadgeColorIsRed() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addToCartByName("Sauce Labs Bike Light");

        String rawColor = productsPage.getCartBadgeBackgroundColor();
        String hexColor = Color.fromString(rawColor).asHex().toLowerCase();

        String expectedRedColor = "#e2231a";
        assertEquals(hexColor, expectedRedColor, "Цвет плашки возле корзины не является красным!");
    }
}
