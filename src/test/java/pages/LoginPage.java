package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;
import utils.PropertyReader;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу авторизации")
    public LoginPage open() {
        driver.get(PropertyReader.getProperty("saucedemo.url"));
        return this;
    }

    @Step("Авторизоваться пользователем '{username}'")
    public ProductsPage login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
        return new ProductsPage(driver);
    }

    @Step("Авторизоваться пользователем")
    public ProductsPage login(User user) {
        return login(
                user.getName(),
                user.getPassword()
        );
    }

    @Step("Проверить отображение сообщения об ошибке")
    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    @Step("Получить текст сообщения об ошибке")
    public String getErrorText() {
        return getText(errorMessage);
    }
}
