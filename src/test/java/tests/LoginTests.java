package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {

    // Успешный вход в систему
    @Test
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertEquals(productsPage.getTitle(), "Products", "Заголовок страницы не соответствует");
    }

    // 1) Оставляем логин пустым, а пароль заполняем
    @Test
    public void testEmptyLoginWithPassword() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
    }

    // 2) Логин заполняем, пароль пустой
    @Test
    public void testLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required");
    }

    // 3) Заблокированный пользователь
    @Test
    public void testLockedOutUser() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    // 4) Регистр в логине (Standard_user) и проверки паролей
    @Test
    public void testCapitalizedLoginWithEmptyPassword() {
        loginPage.open();

        // Проверка №1: пустой пароль
        loginPage.login("Standard_user", "");
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required");

        // Проверка №2: неверные данные (из-за большой буквы)
        loginPage.login("Standard_user", "secret_sauce");
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
    }
}
