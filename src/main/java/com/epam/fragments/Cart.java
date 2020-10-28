package com.epam.fragments;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

@FindBy(css = ".cart-page__holder")
public class Cart extends HtmlElement {
    @FindBy(css = "button.cart-counter__button")
    List<Button> increaseDecreaseButton;
    @FindBy(css = "button.modal__close")
    private Button closeButton;
    @FindBy(css = "input.cart-counter__input")
    private List<TextInput> countProduct;
    @FindBy(css = ".cart-actions__toggle")
    private List<Button> actionsWithProduct;

    public void actionsWithProductClick(int i) {
        actionsWithProduct.get(i).click();
    }

    public void increaseCount() {
        increaseDecreaseButton.get(1).click();
    }

    public String productCounterGetText() {
        return countProduct.get(0).getText();
    }

    public int differentProductItemsInBasket() {
        if (actionsWithProduct == null) {
            return 0;
        } else {
            return actionsWithProduct.size();
        }
    }
}
