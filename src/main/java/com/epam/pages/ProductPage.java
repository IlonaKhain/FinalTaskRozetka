package com.epam.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(css = "h1.product__title")
    private WebElement titleLabel;

    ProductPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check product title")
    public String titleGetText() {
        return titleLabel.getText();
    }
}
