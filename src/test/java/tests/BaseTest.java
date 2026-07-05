package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import pages.BasketPage;
import pages.LoginPage;
import pages.ProductsPage;
import user.User;
import user.UserFactory;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;

    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected BasketPage basketPage;

    protected final List<String> goodsList = Arrays.asList(
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket"
    );

    @Parameters("browser")
    @BeforeMethod
    public void setUp(Method method,
                      @Optional("chrome") String browser) {

        System.out.println("\n====================================");
        System.out.println("Test: " + method.getName());
        System.out.println("Browser: " + browser);
        System.out.println("Thread: " + Thread.currentThread().getId());
        System.out.println("====================================");

        switch (browser.toLowerCase()) {

            case "chrome":

                ChromeOptions options = new ChromeOptions();

                Map<String, Object> prefs = new HashMap<>();

                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                options.setExperimentalOption("prefs", prefs);

                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");

                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        basketPage = new BasketPage(driver);
    }

    protected void login() {

        User admin = UserFactory.withAdminPermission();

        loginPage.open();

        productsPage = loginPage.login(admin);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {

        Thread.sleep(5000);

        if (driver != null) {
            driver.quit();
        }
    }
}