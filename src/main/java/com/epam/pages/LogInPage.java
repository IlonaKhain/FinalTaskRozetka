package com.epam.pages;

import com.epam.fragments.LogInForm;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePage {
    public LogInPage(WebDriver driver) {
        super(driver);
    }

    private LogInForm logInForm;

    @Step("Submit log in via clicking on the button")
    public void submitLogIn() {
        logInForm.logInButtonClick();
    }

    @Step("Check error message for email")
    public String getErrorTextForEmail() {
        return logInForm.getErrorForEmailText();
    }

    @Step("Check error message for password")
    public String getErrorTextForPassword() {
        return logInForm.getErrorForPasswordText();
    }

    @Step("Enter email {value}")
    public void fillEmailField(String value) {
        logInForm.fillEmailField(value);
    }

    @Step("Enter password {value}")
    public void fillPasswordField(String value) {
        logInForm.fillPasswordField(value);
    }

    @Step("Open registration form")
    public RegistrationPage goToRegistration() {
        logInForm.registrationButtonClick();
        return new RegistrationPage(driver);
    }
}
