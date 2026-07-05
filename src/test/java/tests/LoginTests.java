package tests;

import enums.TitleNaming;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.UserFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {

    @Test(description = "Проверка успешной авторизации")
    public void checkLogin() {

        login();

        assertEquals(
                productsPage.getTitle(),
                TitleNaming.PRODUCTS.getDisplayName(),
                "После авторизации не открылась страница Products."
        );
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] loginData() {

        return new Object[][]{

                {"", "secret_sauce",
                        "Epic sadface: Username is required"},

                {"standard_user", "",
                        "Epic sadface: Password is required"},

                {"Standard_user", "secret_sauce",
                        "Epic sadface: Username and password do not match any user in this service"},

                {
                        UserFactory.withLockedPermission().getName(),
                        UserFactory.withLockedPermission().getPassword(),
                        "Epic sadface: Sorry, this user has been locked out."
                }
        };
    }

    @Test(dataProvider = "incorrectLoginData")
    public void checkIncorrectLogin(String login,
                                    String password,
                                    String expectedError) {

        loginPage.open();

        loginPage.login(login, password);

        assertTrue(loginPage.isErrorDisplayed());

        assertEquals(
                loginPage.getErrorText(),
                expectedError
        );
    }
}
