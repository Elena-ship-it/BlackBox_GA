package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.BasketPage;
import pages.LoginPage;
import pages.ProductsPage;
import user.User;
import user.UserFactory;
import utils.TestListener;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Listeners(TestListener.class)
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

    @Step("Подготовка браузера и страниц")
    @Parameters("browser")
    @BeforeMethod
    public void setUp(Method method, @Optional("chrome") String browser, ITestContext context) {
        printTestInfo(method, browser);
        driver = createDriver(browser);
        context.setAttribute("driver", driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        basketPage = new BasketPage(driver);
    }

    private WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                options.addArguments(
                        "--disable-save-password-bubble",
                        "--disable-notifications",
                        "--disable-popup-blocking"
                );
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(options);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
    }

    private void printTestInfo(Method method, String browser) {
        System.out.println("====================================");
        System.out.println("Test: " + method.getName());
        System.out.println("Browser: " + browser);
        System.out.println("Thread: " + Thread.currentThread().getId());
        System.out.println("====================================");
    }

    @Step("Авторизация пользователя")
    protected void login() {
        User admin = UserFactory.withAdminPermission();
        loginPage.open();
        productsPage = loginPage.login(admin);
    }

    @Step("Закрытие браузера")
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
