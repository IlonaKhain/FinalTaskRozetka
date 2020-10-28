package com.epam.fragments;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

@FindBy(xpath = "//header")
public class TopMenu extends HtmlElement {
    @FindBy(css = "a.header-topline__user-link")
    private WebElement linkToEnterCabinet;
    @FindBy(css = "input.search-form__input")
    private TextInput searchField;
    @FindBy(css = "a.header-actions__button_type_wish")
    private Button wishListButton;
    @FindBy(css = ".header-actions__button_type_basket")
    private Button cartButton;
    @FindBy(xpath = "//a[@class='header-actions__button header-actions__button_type_basket header-actions__button_state_active']" +
            "//span")
    private List<WebElement> cartCounter;
    @FindBy(xpath = "//a[@class='header-actions__button header-actions__button_type_wish header-actions__button_state_active']//span")
    private List<WebElement> wishListCounter;

    @FindBy(css = "a.header-cities__link")
    private Link cityLink;

    public void fillSearchFieldAndPressEnter(String value) {
        searchField.sendKeys(value, Keys.ENTER);
    }

    public void clickEnterPersonalCabinet() {
        linkToEnterCabinet.click();
    }

    public String linkToEnterCabinetGetText() {
        return linkToEnterCabinet.getText();
    }

    public void wishlistButtonClick() {
        wishListButton.click();
    }

    public String wishListCounterGetText() {
        return wishListCounter.stream().findFirst().get().getText();
    }

    public List<WebElement> getWishListCounter() {
        return wishListCounter;
    }

    public String cartCounterGetText() {
        return cartCounter.stream().findFirst().get().getText();
    }

    public void cityLinkClick() {
        cityLink.click();
    }

    public List<WebElement> getCartCounter() {
        return cartCounter;
    }

    public String cityLinkGetText() {
        return cityLink.getText();
    }
}
