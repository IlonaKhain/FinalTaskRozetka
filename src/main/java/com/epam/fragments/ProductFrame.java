package com.epam.fragments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@FindBy(xpath = "//section[@class='content content_type_catalog']")
public class ProductFrame extends HtmlElement {
    @FindBy(css = "button.wish-button")
    private List<Button> wishButton;

    @FindBy(xpath = "//button[@class='buy-button goods-tile__buy-button']")
    private List<Button> buyButton;

    @FindBy(css = "a span.goods-tile__title")
    private List<WebElement> productTitle;

    public void wishButtonClick() {
        wishButton.stream().findFirst().get().click();
    }

    public void buyButtonClick() {
        buyButton.stream().findFirst().get().click();
    }

    public String productTitleGetText() {
        return productTitle.stream().findFirst().get().getText();
    }

    public List<Button> getWishButton() {
        return wishButton;
    }
}
