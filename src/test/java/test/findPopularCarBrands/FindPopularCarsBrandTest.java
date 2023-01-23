package test.findPopularCarBrands;

import org.junit.Test;
import test.base.BaseTest;

public class FindPopularCarsBrandTest extends BaseTest {

    @Test
    public void FindPopularCarsBrand (){
        basePage.open("http://auto.drom.ru/");
        dromHomePage
                .clickToRegionChangePage();
        regionPage
                .findDesiredRegion("Приморский край");
        dromHomePage
                .findPopularCar();

    }
}
