package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    public LoginPage loginPage;
    public ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        System.out.println("\n=== СТАРТ ТЕСТА: Инициализация браузера Chrome ===");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("=== ЗАВЕРШЕНИЕ ТЕСТА: Закрытие браузера ===");
            driver.quit();
        }
    }
}
