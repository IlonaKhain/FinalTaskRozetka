package com.epam;

import com.epam.dataprovider.DataProvider;
import com.epam.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PositiveTests {
    WebDriver driver;
    private MainPage mainPage;
    LogInPage logInPage;
    SearchResultsPage searchResultsPage;
    CartPage cartPage;
    WishListPage wishListPage;
    AccountPage accountPage;
    ProductPage productPage;
    CityChoicePage cityChoicePage;
    DataProvider dataProvider = new DataProvider();

    @BeforeMethod
    public void start() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver85.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        this.driver.get(dataProvider.ROZETKA_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @Story("Log in account")
    @Description("successful log in to the account")
    public void logInTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.validPassword);
        logInPage.submitLogIn();
        assertThat(mainPage.getPersonalInfoText(), is(notNullValue()));
    }

    @Test
    @Story("Log out account")
    @Description("Successful log out from the account")
    public void logOutTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.validPassword);
        logInPage.submitLogIn();
        assertThat(mainPage.getPersonalInfoText(), is(notNullValue()));
        mainPage.goToSignOutURL();
        accountPage = new AccountPage(driver);
        accountPage.logOut();
        assertTrue(mainPage.getLogOutText().equals(dataProvider.messageAfterLogOut));
    }

    @Test
    @Story("Add to wish list")
    @Description("user can add product to the wish list")
    public void addToWishListTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.validPassword);
        logInPage.submitLogIn();
        assertThat(mainPage.getPersonalInfoText(), is(notNullValue()));
        searchResultsPage = mainPage.search(dataProvider.whatToSearch);
        assertThat(searchResultsPage.getProducts(), is(notNullValue()));
        String wishesCountBefore = searchResultsPage.wishesCount();
        searchResultsPage.addToWishList();
        wishListPage = searchResultsPage.openWishList();
        assertEquals(Integer.parseInt(wishesCountBefore) + 1, Integer.parseInt(wishListPage.wishesCount()));
    }

    @Test
    @Story("Delete from wish list")
    @Description("user can delete previously added product from the wish list")
    public void deleteFromWishListTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.validPassword);
        logInPage.submitLogIn();
        assertThat(mainPage.getPersonalInfoText(), is(notNullValue()));
        searchResultsPage = mainPage.search(dataProvider.whatToSearch);
        assertThat(searchResultsPage.getProducts(), is(notNullValue()));
        String wishesCountBefore = searchResultsPage.wishesCount();
        searchResultsPage.addToWishList();
        wishListPage = searchResultsPage.openWishList();
        assertEquals(Integer.parseInt(wishesCountBefore) + 1, Integer.parseInt(wishListPage.wishesCount()));
        String wishesCountBeforeDelete = wishListPage.wishesCount();
        wishListPage.checkItem();
        wishListPage.deleteItem();
        wishListPage.refreshPage();
        assertEquals(Integer.parseInt(wishesCountBeforeDelete) - 1, Integer.parseInt(wishListPage.wishesCount()));
    }

    @Test
    @Story("Add to cart")
    @Description("user can add product to cart")
    public void addToCartTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.validPassword);
        logInPage.submitLogIn();
        assertThat(mainPage.getPersonalInfoText(), is(notNullValue()));
        searchResultsPage = mainPage.search(dataProvider.whatToSearch);
        assertThat(searchResultsPage.getProducts(), is(notNullValue()));
        String productsInCartCountBefore = searchResultsPage.productsInCartCount();
        searchResultsPage.addToCart();
        assertEquals(Integer.parseInt(productsInCartCountBefore) + 1, Integer.parseInt(searchResultsPage.productsInCartCount()));
        searchResultsPage.openCart();
        cartPage = new CartPage(driver);
        cartPage.cleanBasket();
    }

    @Test
    @Story("Delete from cart")
    @Description("user can delete previously added product from the cart")
    public void deleteFromCartTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.validPassword);
        logInPage.submitLogIn();
        assertThat(mainPage.getPersonalInfoText(), is(notNullValue()));
        searchResultsPage = mainPage.search(dataProvider.whatToSearch);
        assertThat(searchResultsPage.getProducts(), is(notNullValue()));
        String productsInCartCountBefore = searchResultsPage.productsInCartCount();
        searchResultsPage.addToCart();
        assertEquals(Integer.parseInt(productsInCartCountBefore) + 1, Integer.parseInt(searchResultsPage.productsInCartCount()));
        cartPage = searchResultsPage.openCart();
        cartPage.expandActionsDropDown();
        cartPage.deleteProduct();
        assertTrue(productsInCartCountBefore.equals(cartPage.count()));
    }

    @Test
    @Story("Add to cart")
    @Description("User can increase number of items of chosen product")
    public void increaseCountInCartTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.validPassword);
        logInPage.submitLogIn();
        assertThat(mainPage.getPersonalInfoText(), is(notNullValue()));
        searchResultsPage = mainPage.search(dataProvider.whatToSearch);
        assertThat(searchResultsPage.getProducts(), is(notNullValue()));
        String productsInCartCountBefore = searchResultsPage.productsInCartCount();
        searchResultsPage.addToCart();
        assertEquals(Integer.parseInt(productsInCartCountBefore) + 1, Integer.parseInt(searchResultsPage.productsInCartCount()));
        cartPage = searchResultsPage.openCart();
        String firstProductCountBefore = cartPage.getProductCount();
        cartPage.increaseFirstProductCount();
        assertEquals(Integer.parseInt(firstProductCountBefore) + 1, Integer.parseInt(cartPage.getProductCount()));
        searchResultsPage.openCart();
        cartPage = new CartPage(driver);
        cartPage.cleanBasket();
    }

    @Test
    @Story("Add to cart")
    @Description("user can add to cart chosen model")
    public void addToCartChosenModel() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.validPassword);
        logInPage.submitLogIn();
        assertThat(mainPage.getPersonalInfoText(), is(notNullValue()));
        searchResultsPage = mainPage.search(dataProvider.whatToSearch);
        assertThat(searchResultsPage.getProducts(), is(notNullValue()));
        searchResultsPage.chooseModel();
        String productsInCartCountBefore = searchResultsPage.productsInCartCount();
        searchResultsPage.addToCart();
        assertEquals(Integer.parseInt(productsInCartCountBefore) + 1, Integer.parseInt(searchResultsPage.productsInCartCount()));
        searchResultsPage.openCart();
        cartPage = new CartPage(driver);
        cartPage.cleanBasket();
    }

    @Test
    @Story("View product")
    @Description("User can chose product from search")
    public void choseProductTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnterPersonalCabinet();
        logInPage = new LogInPage(driver);
        logInPage.fillEmailField(dataProvider.validEmail);
        logInPage.fillPasswordField(dataProvider.validPassword);
        logInPage.submitLogIn();
        assertThat(mainPage.getPersonalInfoText(), is(notNullValue()));
        searchResultsPage = mainPage.search(dataProvider.whatToSearch);
        assertThat(searchResultsPage.getProducts(), is(notNullValue()));
        productPage = searchResultsPage.chooseProduct();
        assertTrue(productPage.titleGetText().toLowerCase().contains(dataProvider.whatToSearch));
    }

    @Test
    @Story("Select city")
    @Description("User can change city")
    public void changeCityTest() {
        mainPage = new MainPage(driver);
        assertFalse(mainPage.getCityName().contains(dataProvider.whereToSearchUpdated));
        cityChoicePage = mainPage.goToCityChoice();
        cityChoicePage.fillCityField(dataProvider.whereToSearchUpdated);
        cityChoicePage.chooseCityItemProposed();
        mainPage = cityChoicePage.goToMainPage();
        assertTrue(mainPage.getCityName().contains(dataProvider.whereToSearchUpdated));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
