package com.epam.fragments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(xpath = "//div[@class='modal__holder modal__holder_show_animation modal__holder_size_medium']")
public class LogInForm extends HtmlElement {
    @FindBy(id = "auth_email")
    private TextInput emailField;

    @FindBy(id = "auth_pass")
    private TextInput passwordField;

    @FindBy(css = "button.auth-modal__submit")
    private Button logInButton;

    @FindBy(css = ".auth-modal__register-link")
    private Button registrationButton;

    @FindBy(css = ".error-message")
    private WebElement errorLabel;

    @FindBy(xpath = "//div[@class = 'form__row form__hint form__hint_type_warning']")
    private WebElement errorPasswordLabel;

    public void fillEmailField(String value) {
        emailField.sendKeys(value);
    }

    public void fillPasswordField(String value) {
        passwordField.sendKeys(value);
    }

    public void logInButtonClick() {
        logInButton.click();
    }

    public String getErrorForEmailText() {
        return errorLabel.getText();
    }

    public String getErrorForPasswordText() {
        return errorPasswordLabel.getText();
    }

    public void registrationButtonClick() {
        registrationButton.click();
    }
}
