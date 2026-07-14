import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void checkLogin() {
        WebDriver browser = new ChromeDriver();
        try {
            browser.get("https://www.saucedemo.com/");
            browser.findElement(By.id("user-name")).sendKeys("standard_user");
            browser.findElement(By.id("password")).sendKeys("secret_sauce");
            browser.findElement(By.id("login-button")).click();
            Assert.assertTrue(browser.getCurrentUrl().contains("inventory"));
        } finally {
            browser.quit();
        }
    }
}
