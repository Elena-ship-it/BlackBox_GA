package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By loginInput = By.xpath("//*[@placeholder='Username']");
    private final By passwordInput = By.xpath("//*[@placeholder='Password']");
    private final By submitButton = By.cssSelector("#login-button");
    private final By error = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String login, String password) {
        writeText(loginInput, login);
        writeText(passwordInput, password);
        clickElement(submitButton);
    }

    public boolean isErrorDisplayed() {
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(error)).isDisplayed();
        return isDisplayed;
    }

    public String getErrorText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(error)).getText();
        return text;
    }
}