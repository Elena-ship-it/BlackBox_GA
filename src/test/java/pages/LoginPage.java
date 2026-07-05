package pages;

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

    public LoginPage open() {

        driver.get(PropertyReader.getProperty("saucedemo.url"));

        return this;
    }

    public ProductsPage login(String username, String password) {

        type(usernameInput, username);
        type(passwordInput, password);

        click(loginButton);

        return new ProductsPage(driver);
    }

    public ProductsPage login(User user) {

        return login(
                user.getName(),
                user.getPassword()
        );
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorText() {
        return getText(errorMessage);
    }
}
