package test.favoritesAdsAndAuthorization;

import org.junit.Test;
import test.base.BaseTest;

public class FavoritesAdsAndAuthorizationTest  extends BaseTest {
    @Test
    public void checkingCorrectAuthorization() {
        basePage.open("http://auto.drom.ru/");
        dromHomePage
                .clickToLoginAndRegistration();
        dromAuthorizationPage
                //.enterPhoneNumber("+79964244116")
                //.enterLogin("kotik4")
                .enterEmail("test_drom@mail.ru")
                .enterPassword("prisoska123")
                .clickSignButton();
        dromHomePage
                .goToPersonalAccount();
        dromPersonalAccauntPage
                .goToAccountSettings();
        dromAccountSettingsPage
                .checkLoginMatches("kotik4")
                .checkEmailMatches("test_drom@mail.ru")
                .checkPhoneNumberMatches("+79964244116")
                .backToHomePage();
        dromFavouritesPage
                .checkAdCorrectAddition(dromHomePage.addAdToFavourites());

        //ниже идет проверка на то, добавилось ли правильное колличество объявлений
        dromHomePage
                .clickToFavorite();
        dromFavouritesPage
                .cleanFavourites();
        dromFavouritesPage
                .checkCorrectAddition(10,dromHomePage.addToFavourites(10));

    }
}
