package com.epam;

import com.epam.dataprovider.DataProvider;
import com.epam.pages.LogInPage;
import com.epam.pages.MainPage;
import com.epam.pages.RegistrationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NegativeTests {
    WebDriver driver;
    private MainPage mainPage;
    private LogInPage logInPage;
    DataProvider dataProvider = new DataProvider();
    private RegistrationPage registrationPage;

    @BeforeMethod
    public void start() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver85.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        this.driver.get(dataProvider.ROZETKA_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @Story("Log in account")
    @Description("Negative test for log in: invalid email, valid password")
    public void LogInWrongEmailTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.invalidEmail);
        logInPage.fillPasswordField(dataProvider.validPassword);
        logInPage.submitLogIn();
        assertTrue(logInPage.getErrorTextForEmail().contains(dataProvider.errorMessageInvalidEmail));
    }

    @Test
    @Story("Log in account")
    @Description("Negative test for log in: valid email, invalid password")
    public void LogInWrongPasswordTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.invalidPassword);
        logInPage.submitLogIn();
        assertTrue(logInPage.getErrorTextForPassword().contains(dataProvider.errorMessageInvalidPassword));
    }

    @Test
    @Story("Log in account")
    @Description("Negative test for log in: invalid email, invalid password")
    public void LogInWrongEmailPasswordTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.invalidEmail);
        logInPage.fillPasswordField(dataProvider.invalidPassword);
        logInPage.submitLogIn();
        assertTrue(logInPage.getErrorTextForEmail().contains(dataProvider.errorMessageInvalidEmail));
    }

    @Test
    @Story("Register account")
    @Description("Negative test for registration: use existing email")
    public void registerExistingEmail() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        registrationPage = logInPage.goToRegistration();
        registrationPage.fillSurnameField(dataProvider.surname);
        registrationPage.fillNameField(dataProvider.name);
        registrationPage.fillEmailOrPhoneField(dataProvider.validEmail);
        registrationPage.fillPasswordField(dataProvider.validPassword);
        registrationPage.signUp();
        String error = MessageFormat.format("Пользователь с логином {0} уже зарегистрирован", dataProvider.validEmail);
        assertTrue(registrationPage.validationErrorText().equals(error));
    }

    @Test
    @Story("Register account")
    @Description("Negative test for registration: without email")
    public void registerWithoutEmail() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        registrationPage = logInPage.goToRegistration();
        registrationPage.fillSurnameField(dataProvider.surname);
        registrationPage.fillNameField(dataProvider.name);
        registrationPage.fillEmailOrPhoneField("");
        registrationPage.fillPasswordField(dataProvider.validPassword);
        registrationPage.signUp();
        assertTrue(registrationPage.validationErrorText().equals(dataProvider.errorMessageForEmailOrPhone));
    }

    @Test
    @Story("Register account")
    @Description("Negative test for registration: without password")
    public void registerWithoutPassword() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        registrationPage = logInPage.goToRegistration();
        registrationPage.fillSurnameField(dataProvider.surname);
        registrationPage.fillNameField(dataProvider.name);
        registrationPage.fillEmailOrPhoneField(dataProvider.validEmail);
        registrationPage.fillPasswordField("");
        registrationPage.signUp();
        assertEquals(registrationPage.errorPasswordField(), 1);
    }

    @Test
    @Story("Register account")
    @Description("Negative test for registration: without name")
    public void registerWithoutName() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        registrationPage = logInPage.goToRegistration();
        registrationPage.fillSurnameField(dataProvider.surname);
        registrationPage.fillNameField("");
        registrationPage.fillEmailOrPhoneField(dataProvider.validEmail);
        registrationPage.fillPasswordField(dataProvider.validPassword);
        registrationPage.signUp();
        assertTrue(registrationPage.validationErrorText().equals(dataProvider.errorMessageForName));
    }

    @Test
    @Story("Register account")
    @Description("Negative test for registration: without surname")
    public void registerWithoutSurName() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        registrationPage = logInPage.goToRegistration();
        registrationPage.fillSurnameField("");
        registrationPage.fillNameField(dataProvider.name);
        registrationPage.fillEmailOrPhoneField(dataProvider.validEmail);
        registrationPage.fillPasswordField(dataProvider.validPassword);
        registrationPage.signUp();
        assertTrue(registrationPage.validationErrorText().equals(dataProvider.errorMessageForSurname));
    }

    @Test
    @Story("Log in account")
    @Description("Negative test for log in: use right password another register")
    public void logInAnotherRegister() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.validPassword.toLowerCase());
        logInPage.submitLogIn();
        assertTrue(logInPage.getErrorTextForPassword().contains(dataProvider.errorMessageInvalidPassword));
    }

    @Test
    @Story("Register account")
    @Description("Negative test for registration: invalid phone number")
    public void registerInvalidPhoneNumber() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        registrationPage = logInPage.goToRegistration();
        registrationPage.fillSurnameField(dataProvider.surname);
        registrationPage.fillNameField(dataProvider.name);
        registrationPage.fillEmailOrPhoneField(dataProvider.invalidPhoneNumber);
        registrationPage.fillPasswordField(dataProvider.validPassword);
        registrationPage.signUp();
        assertTrue(registrationPage.validationErrorText().equals(dataProvider.errorMessageForEmailOrPhone));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
