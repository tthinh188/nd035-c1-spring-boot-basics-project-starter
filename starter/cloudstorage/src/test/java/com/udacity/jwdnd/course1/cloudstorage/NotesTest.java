package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTest {
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
    public void testAddNote() {
        driver.get(baseURL + "/home");

        HomePage homePage = new HomePage(driver);
        homePage.openNoteTab(webDriverWait);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("add-note-button")));

        homePage.addNote(webDriverWait, driver, baseURL);
    }

    @Test
    public void testDeleteNote() {
        driver.get(baseURL + "/home");

        HomePage homePage = new HomePage(driver);
        homePage.openNoteTab(webDriverWait);

        homePage.addNote(webDriverWait, driver, baseURL);

        driver.get(baseURL + "/home");
        homePage.openNoteTab(webDriverWait);
        homePage.deleteNote(webDriverWait, driver, baseURL);
    }

    @Test
    public void testEditNote() {
        driver.get(baseURL + "/home");
        HomePage homePage = new HomePage(driver);
        homePage.openNoteTab(webDriverWait);
        homePage.addNote(webDriverWait, driver, baseURL);
        driver.get(baseURL + "/home");
        homePage.openNoteTab(webDriverWait);

        String editedTitle = "To Do List 2";
        String editedDescription = "Review\nTake Exam\nSuperDuperDrive";
        homePage.editNote(editedTitle, editedDescription, webDriverWait, driver, baseURL);
    }
}
