package com.epam.pages;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public abstract class BasePage {
    WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
        HtmlElementLoader.populate(this, this.driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

}
