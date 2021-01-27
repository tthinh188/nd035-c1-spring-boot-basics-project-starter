package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginAndSignupTest {
    @LocalServerPort
    public int port;

    public static WebDriver driver;
    private WebDriverWait webDriverWait;

    public String baseURL;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
        driver = new ChromeDriver();
        this.webDriverWait = new WebDriverWait(driver, 1000);
    }

    @Test
    @Order(1)
    public void unauthorizedCanNotAccessHomePage() {
        this.driver.get(baseURL + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    @Order(2)
    public void testUserSignupLogin() {
        String username = "ptthinh";
        String password = "password147963";

        driver.get(baseURL + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Thinh", "Phan", username, password);

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("signup-link")));

        Assertions.assertEquals("Home", driver.getTitle());

        WebElement logoutButton = driver.findElement(By.id("signup-link"));

        while(driver.getTitle().equals("Home")) {
            logoutButton.click();
        }

        webDriverWait.until(ExpectedConditions.titleContains("Login"));

        Assertions.assertEquals("Login",driver.getTitle());


    }
}
