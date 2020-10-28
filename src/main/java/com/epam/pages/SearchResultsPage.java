package com.epam.pages;

import com.epam.dataprovider.DataProvider;
import com.epam.fragments.ProductFrame;
import com.epam.fragments.TopMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPage extends BasePage {
    @FindBy(css = "div.goods-tile__inner")
    List<WebElement> products;
    @FindBy(xpath = "//label[@for='iPhone 11']")
    WebElement checkBoxForModel;
    @FindBy(css = "p.email-verification__bottom")
    List<WebElement> verificationEmailButton;
    ProductFrame productFrame;
    private WebDriverWait wait = new WebDriverWait(driver, 20);
    private TopMenu topMenu;
    DataProvider dataProvider = new DataProvider();

    SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open wish list")
    public WishListPage openWishList() {
        if (verificationEmailButton.size() > 0) {
            verificationEmailButton.get(0).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.header-actions__button_type_wish")));
        topMenu.wishlistButtonClick();
        return new WishListPage(driver);
    }

    @Step("Check presence of the products")
    public List<WebElement> getProducts() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.goods-tile__inner")));
        return products;
    }

    @Step("Choose product")
    public ProductPage chooseProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
        products.get(0).click();
        return new ProductPage(driver);
    }

    @Step("Add to wish list")
    public void addToWishList() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.wish-button")));
        productFrame.wishButtonClick();
    }

    @Step("Check products count in wish list")
    public String wishesCount() {
        if (topMenu.getWishListCounter().size() > 0) {
            return topMenu.wishListCounterGetText();
        } else {
            return "0";
        }
    }

    @Step("Check products count in the cart")
    public String productsInCartCount() {
        if (topMenu.getCartCounter().size() > 0) {
            return topMenu.cartCounterGetText();
        } else {
            return "0";
        }
    }

    @Step("Add to cart")
    public void addToCart() {
        productFrame.buyButtonClick();
    }

    @Step("Choose product model")
    public void chooseModel() {
        checkBoxForModel.click();
    }

    @Step("Open cart")
    public CartPage openCart() {
        driver.get(dataProvider.CART_URL);
        return new CartPage(driver);
    }
}
