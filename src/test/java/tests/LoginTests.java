package tests;

import enums.TitleNaming;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("SauceDemo")
@Feature("Авторизация")
@Owner("Измайлова Елена телеграм @Elena07101983")
public class LoginTests extends BaseTest {
    @Story("Успешная авторизация пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("BlackBox_GA")
    @Issue("BlackBox_GA")
    @Test(description = "Проверка успешной авторизации")
    public void checkLogin() {
        login();
        assertEquals(productsPage.getTitle(), TitleNaming.PRODUCTS.getDisplayName(), "После авторизации не открылась страница Products.");
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {new User("", "secret_sauce"), "Epic sadface: Username is required"},
                {new User("standard_user", ""), "Epic sadface: Password is required"},
                {new User("Standard_user", "secret_sauce"), "Epic sadface: Username and password do not match any user in this service"},
                {UserFactory.withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Story("Авторизация с некорректными учетными данными")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("BlackBox_GA")
    @Issue("BlackBox_GA")
    @Test(dataProvider = "incorrectLoginData", description = "Проверка ошибок при некорректных данных авторизации")
    public void checkIncorrectLogin(User user, String expectedError) {
        loginPage.open();
        loginPage.login(user.getName(), user.getPassword());
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), expectedError);
    }
}
