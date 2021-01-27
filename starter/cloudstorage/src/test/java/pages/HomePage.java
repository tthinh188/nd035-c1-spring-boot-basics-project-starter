package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;
    @FindBy(id="nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id="add-note-button")
    private WebElement addNoteButton;
    @FindBy(id="add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="save-change")
    private WebElement noteSubmitButton;
    @FindBy(id="submitCredential")
    private WebElement credentialSubmitButton;

    @FindBy(id="back-to-home-from-success")
    private WebElement changeSuccess;

    @FindBy(id="delete-button")
    private WebElement deleteButton;
    @FindBy(id="delete-credential-button")
    private WebElement deleteCredentialButton;

    @FindBy(id="edit-note-button")
    private WebElement editNoteButton;
    @FindBy(id="edit-credential-button")
    private WebElement editCredentialButton;

    @FindBy(id="credential-url")
    private WebElement credentialURL;

    @FindBy(id="credential-username")
    private WebElement credentialUsername;

    @FindBy(id="credential-password")
    private WebElement credentialPassword;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }


    public void openNoteTab(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(notesTab));
        notesTab.click();
    }

    public void openCredentialTab(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialTab));
        credentialTab.click();
    }

    public void addNote(WebDriverWait webDriverWait, WebDriver driver, String baseURL) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
        addNoteButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(noteSubmitButton));

        noteTitle.sendKeys("To Do List");
        noteDescription.sendKeys("Super Duper Drive \nReview \nTake Exam");

        noteSubmitButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(changeSuccess));

        driver.get(baseURL + "/result?isSuccess=true");

        changeSuccess.click();
    }

    public void deleteNote(WebDriverWait webDriverWait, WebDriver driver, String baseURL) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteButton));

        deleteButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(changeSuccess));
        driver.get(baseURL + "/result?isSuccess=true");

        changeSuccess.click();

    }

    public void editNote(String title, String description, WebDriverWait webDriverWait, WebDriver driver, String baseURL) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editNoteButton));
        editNoteButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(noteSubmitButton));
        noteTitle.clear();
        noteDescription.clear();
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        noteSubmitButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(changeSuccess));

        driver.get(baseURL + "/result?isSuccess=true");

        changeSuccess.click();
    }

    public void addCredential(WebDriverWait webDriverWait, WebDriver driver, String baseURL) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
        addCredentialButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialSubmitButton));

        credentialURL.sendKeys("https://www.google.com/");
        credentialUsername.sendKeys("ptthinh");
        credentialPassword.sendKeys("mypassword");

        credentialSubmitButton.click();


        webDriverWait.until(ExpectedConditions.elementToBeClickable(changeSuccess));

        driver.get(baseURL + "/result?isSuccess=true");

        changeSuccess.click();
    }

    public void editCredential(String URL, String userName, String password,
                               WebDriverWait webDriverWait, WebDriver driver, String baseURL) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editCredentialButton));
        editCredentialButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialSubmitButton));

        credentialURL.clear();
        credentialUsername.clear();
        credentialPassword.clear();

        credentialURL.sendKeys(URL);
        credentialUsername.sendKeys(userName);
        credentialPassword.sendKeys(password);
        credentialSubmitButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(changeSuccess));

        driver.get(baseURL + "/result?isSuccess=true");

        changeSuccess.click();
    }

    public void deleteCredential(WebDriverWait webDriverWait, WebDriver driver, String baseURL) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteCredentialButton));

        deleteCredentialButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(changeSuccess));
        driver.get(baseURL + "/result?isSuccess=true");

        changeSuccess.click();
    }
}
