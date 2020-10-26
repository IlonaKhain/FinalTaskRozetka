package com.epam.pages;

import com.epam.fragments.TopMenu;
import com.epam.fragments.WishList;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage {
    private TopMenu topMenu;
    private WishList wishList;

    @FindBy(xpath = "//body")
    private WebElement page;

    WishListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check wishes count")
    public String wishesCount() {
        return topMenu.wishListCounterGetText();
    }

    public void refreshPage() {
        page.sendKeys(Keys.F5);
    }

    @Step("Check item")
    public void checkItem() {
        wishList.checkProductClick();
    }

    @Step("Delete item from the wish list")
    public void deleteItem() {
        wishList.deleteButtonClick();
    }
}
