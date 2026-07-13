package tests;

import enums.TitleNaming;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BasketTest extends BaseTest {
    @Test(description = "Проверка корзины")
    public void checkCartContentAfterAddingProducts() {
        // Указываем реальные названия товаров с сайта SauceDemo
        List<String> goodsList = Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt"
        );
        login();
        for (String product : goodsList) {
            productsPage.addToCart(product);
        }
        basketPage = productsPage.openCart();
        assertEquals(
                basketPage.getTitle(),
                TitleNaming.CART.getDisplayName()
        );
        assertEquals(
                basketPage.getProductsCount(),
                goodsList.size()
        );
        for (String product : goodsList) {
            assertTrue(
                    basketPage.containsProduct(product),
                    "Отсутствует товар: " + product
            );
        }
    }
}
