package com.epam.pages;

import com.epam.fragments.Cart;
import com.epam.fragments.TopMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
    @FindBy(xpath = "//button[contains(@class, 'cart-actions__button')][text()=' Удалить из корзины ']")
    private WebElement deleteButton;
    TopMenu topMenu;
    private Cart cart;
    private WebDriverWait wait = new WebDriverWait(driver, 20);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check product count in the cart")
    public String count() {
        if (topMenu.getCartCounter().size() > 0) {
            return topMenu.cartCounterGetText();
        } else {
            return "0";
        }
    }

    @Step("Delete product in the cart")
    public void deleteProduct() {
        deleteButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h4.cart-dummy__heading")));
    }

    @Step("Increase product cunt in the cart")
    public void increaseFirstProductCount() {
        cart.increaseCount();
    }

    @Step("Check first product items number")
    public String getProductCount() {
        return cart.productCounterGetText();
    }

    @Step("Expand actions drop down")
    public void expandActionsDropDown() {
        cart.actionsWithProductClick(0);
    }

    @Step("Clean up basket")
    public void cleanBasket() {
        for (int i = 0; i < cart.differentProductItemsInBasket(); i++) {
            cart.actionsWithProductClick(i);
            wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
            deleteButton.click();
        }
    }
}
