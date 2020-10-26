package com.epam.fragments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(css = "main.cabinet-content")
public class WishList extends HtmlElement {
    @FindBy(css = "div.tile-checkbox")
    private WebElement checkProduct;

    @FindBy(css = ".js-goods-delete")
    private Button deleteButton;

    public void checkProductClick() {
        checkProduct.click();
    }

    public void deleteButtonClick() {
        deleteButton.click();
    }

}
