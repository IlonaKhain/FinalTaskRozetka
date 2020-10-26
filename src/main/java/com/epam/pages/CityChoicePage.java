package com.epam.pages;

import com.epam.fragments.TopMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CityChoicePage extends BasePage {
    private TopMenu topMenu;
    @FindBy(css = ".autocomplete__input")
    private TextInput cityField;

    @FindBy(css = ".autocomplete__item")
    private TextInput cityItemProposed;

    @FindBy(css = "a.button_size_medium")
    private Link mainPageLink;

    public CityChoicePage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter city {value}")
    public void fillCityField(String value) {
        cityField.clear();
        cityField.sendKeys(value);
    }

    @Step("Choose city item from proposed")
    public void chooseCityItemProposed() {
        cityItemProposed.click();
    }

    @Step("Open main page")
    public MainPage goToMainPage() {
        mainPageLink.click();
        return new MainPage(driver);
    }

}
