package tests;

import enums.TitleNaming;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    private static final String BADGE_COLOR = "#e2231a";

    @Test(description = "Проверка открытия страницы Products")
    public void checkProductsPageOpened() {

        login();

        assertEquals(
                productsPage.getTitle(),
                TitleNaming.PRODUCTS.getDisplayName()
        );
    }

    @Test(description = "Проверка добавления товара")
    public void checkAddProductToCart() {

        login();

        productsPage.addToCart("Sauce Labs Backpack");

        assertTrue(productsPage.isCartBadgeDisplayed());

        assertEquals(
                productsPage.getCartBadgeText(),
                "1"
        );

        String color = Color
                .fromString(productsPage.getCartBadgeBackgroundColor())
                .asHex()
                .toLowerCase();

        assertEquals(color, BADGE_COLOR);
    }
}
