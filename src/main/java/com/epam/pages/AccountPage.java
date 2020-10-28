package com.epam.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {
    @FindBy(css = ".profile-m-edit-signout")
    private WebElement logOutButton;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Log out")
    public void logOut() {
        logOutButton.click();
    }
}
