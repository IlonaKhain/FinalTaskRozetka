package com.epam.pages;

import com.epam.fragments.RegistrationForm;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {
    RegistrationForm registrationForm;

    RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter surname {value}")
    public void fillSurnameField(String value) {
        registrationForm.fillSurnameField(value);
    }

    @Step("Enter name {value}")
    public void fillNameField(String value) {
        registrationForm.fillNameField(value);
    }

    @Step("Enter email or phone number {value}")
    public void fillEmailOrPhoneField(String value) {
        registrationForm.fillEmailField(value);
    }

    @Step("Enter password {value}")
    public void fillPasswordField(String value) {
        registrationForm.fillPasswordField(value);
    }

    @Step("Sign up")
    public void signUp() {
        registrationForm.registrationButtonClick();
    }

    @Step("Check error message")
    public String validationErrorText() {
        return registrationForm.getValidationErrorText();
    }

    @Step("Check error")
    public int errorPasswordField() {
        return registrationForm.getErrorValidationField().size();
    }
}
