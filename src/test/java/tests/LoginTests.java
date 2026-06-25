package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {

    @Test
    public void checkLogin() {
        System.out.println("ЗАПУСК: Тест checkLogin (Успешная авторизация)");
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertEquals(productsPage.getTitle(), "Products", "Заголовок страницы не соответствует");
        System.out.println("РЕЗУЛЬТАТ: Тест checkLogin успешно пройден!");
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"Standard_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Test(dataProvider = "incorrectLoginData")
    public void checkIncorrectLogin(String user, String password, String errorMessage) {
        System.out.println("ЗАПУСК: Негативный тест авторизации для пользователя: '" + user + "'");
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed(), "Ошибка не отобразилась на странице!");
        assertEquals(loginPage.getErrorText(), errorMessage, "Текст ошибки не совпадает с ожидаемым!");
        System.out.println("РЕЗУЛЬТАТ: Проверка ошибки для пользователя '" + user + "' успешна.");
    }
}
