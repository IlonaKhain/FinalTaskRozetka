package com.epam.fragments;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

@FindBy(css = "div.auth-modal")
public class RegistrationForm extends HtmlElement {

    @FindBy(xpath = "//input[@formcontrolname='surname']")
    private TextInput surnameField;

    @FindBy(xpath = "//input[@formcontrolname='name']")
    private TextInput nameField;

    @FindBy(xpath = "//input[@formcontrolname='username']")
    private TextInput emailField;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    private TextInput passwordField;

    @FindBy(css = "button.auth-modal__submit")
    private Button signUpButton;

    @FindBy(css = "p.validation-message")
    private TextBlock errorValidationMessage;

    @FindBy(css = "input.ng-invalid")
    private List<TextInput> errorValidationField;

    public void fillSurnameField(String value) {
        surnameField.sendKeys(value);
    }

    public void fillNameField(String value) {
        nameField.sendKeys(value);
    }

    public void fillEmailField(String value) {
        emailField.sendKeys(value);
    }

    public void fillPasswordField(String value) {
        passwordField.sendKeys(value);
    }

    public void registrationButtonClick() {
        signUpButton.click();
    }

    public String getValidationErrorText() {
        return errorValidationMessage.getText();
    }

    public List<TextInput> getErrorValidationField() {
        return errorValidationField;
    }
}
