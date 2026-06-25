package tests;

import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    @Test
    public void checkProductsPageTitle() {
        System.out.println("ЗАПУСК: Тест checkProductsPageTitle");
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        String expectedTitle = "Products";
        String actualTitle = productsPage.getTitle();

        assertEquals(actualTitle, expectedTitle, "Заголовок страницы продуктов не совпадает!");
        System.out.println("РЕЗУЛЬТАТ: Тест checkProductsPageTitle успешно пройден!");
    }

    @Test
    public void checkAddToCartByProductName() {
        System.out.println("ЗАПУСК: Тест добавления товара в корзину по имени");
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        String productName = "Sauce Labs Backpack";
        productsPage.addToCartByName(productName);

        assertTrue(productsPage.isCartBadgeDisplayed(), "Плашка с количеством товаров не появилась возле корзины!");
        assertEquals(productsPage.getCartBadgeText(), "1", "Количество товаров на плашке корзины не соответствует ожидаемому!");
        System.out.println("РЕЗУЛЬТАТ: Товар успешно добавлен. На плашке отображается корректное количество.");
    }

    @Test
    public void checkCartBadgeColorIsRed() {
        System.out.println("ЗАПУСК: Тест проверки красного цвета плашки корзины");
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addToCartByName("Sauce Labs Bike Light");

        String rawColor = productsPage.getCartBadgeBackgroundColor();
        String hexColor = Color.fromString(rawColor).asHex().toLowerCase();
        System.out.println("ЛОГ: Преобразованный HEX-код цвета плашки: " + hexColor);

        String expectedRedColor = "#e2231a";
        assertEquals(hexColor, expectedRedColor, "Цвет плашки возле корзины не является красным!");
        System.out.println("РЕЗУЛЬТАТ: Проверка завершена. Плашка горит правильным красным цветом (" + hexColor + ").");
    }
}
