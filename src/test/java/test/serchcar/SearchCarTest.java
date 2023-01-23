package test.serchcar;

import org.junit.Test;
import test.base.BaseTest;

public class SearchCarTest extends BaseTest {
    @Test
    public void checkingCorrecCarSearch() {
        basePage.open("http://auto.drom.ru/");
        dromHomePage
                .enterBrandName("Toyota")
                .enterFuel("Гибрид")
                .enterModelName("Harrier")
                .enterYearFrom("2007")
                .clickNotSold()
                .clickToAdvancedSearch()
                .enterMileageFrom("1000")
                .clickToSearch();

        dromListingPage
                .checkCarAds();
    }

}
