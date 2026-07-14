import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void checkLogin() {
        //1. открыть браузер+
        //2. зайти https://www.saucedemo.com/
        // 3. Нажать кнопку Login 
        // 4. Закрыть браузер после теста

        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");

        // Ввод логина
        browser.findElement(By.xpath("//*[@placeholder='Username']")).sendKeys("standard_user");

        // Ввод пароля
        browser.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("secret_sauce");

        // Нажать кнопку Login
        browser.findElement(By.id("login-button")).click();

        // Закрыть браузер после теста
        browser.quit();

    }
}
