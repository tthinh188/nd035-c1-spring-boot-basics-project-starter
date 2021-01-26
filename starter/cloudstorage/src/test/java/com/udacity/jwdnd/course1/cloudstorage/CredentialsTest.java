package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsTest {
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

        String username = "ptthinh";
        String password = "password147963";

        driver.get(baseURL + "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Thinh", "Phan", username, password);

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Test
    public void testDeleteCredential() {
        driver.get(baseURL + "/home");

        HomePage homePage = new HomePage(driver);
        homePage.openCredentialTab(webDriverWait);

        homePage.addCredential(webDriverWait, driver, baseURL);

        driver.get(baseURL + "/home");
        homePage.openCredentialTab(webDriverWait);
        homePage.deleteCredential(webDriverWait, driver, baseURL);
    }

    @Test
    public void testAddCredential() {
        driver.get(baseURL + "/home");

        HomePage homePage = new HomePage(driver);
        homePage.openCredentialTab(webDriverWait);

        homePage.addCredential(webDriverWait, driver, baseURL);
    }

    @Test
    public void testEditCredential() {
        driver.get(baseURL + "/home");
        HomePage homePage = new HomePage(driver);
        homePage.openCredentialTab(webDriverWait);
        homePage.addCredential(webDriverWait, driver, baseURL);
        driver.get(baseURL + "/home");
        homePage.openCredentialTab(webDriverWait);

        String URL = "https://www.youtube.com/";
        String userName = "youtubeusername";
        String password = "husky123";

        homePage.editCredential(URL, userName, password, webDriverWait, driver, baseURL);
    }
}
