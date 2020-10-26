package com.epam.pages;

import com.epam.dataprovider.DataProvider;
import com.epam.fragments.TopMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    private TopMenu topMenu;
    @FindBy(css = ".main-auth__links")
    private WebElement personalInfo;
    DataProvider dataProvider = new DataProvider();

    private WebDriverWait wait = new WebDriverWait(driver, 20);

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open log in form")
    public void clickEnterPersonalCabinet() {
        topMenu.clickEnterPersonalCabinet();
    }

    @Step("Search for {whatToSearch}")
    public SearchResultsPage search(String whatToSearch) {
        topMenu.fillSearchFieldAndPressEnter(whatToSearch);
        return new SearchResultsPage(driver);
    }

    @Step("Check log in")
    public String getPersonalInfoText() {
        return personalInfo.getText();
    }

    @Step("Open log out page")
    public void goToSignOutURL() {
        driver.get(dataProvider.LOGOUT_URL);
    }

    @Step("Check log out")
    public String getLogOutText() {
        return topMenu.linkToEnterCabinetGetText();
    }

    @Step("Open form to choose city")
    public CityChoicePage goToCityChoice() {
        topMenu.cityLinkClick();
        return new CityChoicePage(driver);
    }

    @Step("Check city name")
    public String getCityName() {
        return topMenu.cityLinkGetText();
    }
}
