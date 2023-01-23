package test.base;
import common.CommonAction;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import pages.base.BasePage;
import pages.ruDrom.DromPersonalAccauntPage;
import pages.ruDrom.DromAccountSettingsPage;
import pages.ruDrom.DromAuthorizationPage;
import pages.ruDrom.DromFavouritesPage;
import pages.ruDrom.DromHomePage;
import pages.ruDrom.DromListingPage;
import pages.ruDrom.RegionPage;

public class BaseTest {
    protected WebDriver driver = CommonAction.createDriver();
    protected BasePage basePage = new BasePage(driver);
    protected DromHomePage dromHomePage = new DromHomePage(driver);
    protected DromListingPage dromListingPage = new DromListingPage(driver);
    protected RegionPage regionPage = new RegionPage(driver);
    protected DromAuthorizationPage dromAuthorizationPage = new DromAuthorizationPage(driver);
    protected DromFavouritesPage dromFavouritesPage = new DromFavouritesPage(driver);
    protected DromPersonalAccauntPage dromPersonalAccauntPage = new DromPersonalAccauntPage(driver);
    protected DromAccountSettingsPage dromAccountSettingsPage = new DromAccountSettingsPage(driver);

    @AfterSuite(alwaysRun = true)
    public void quit(){
        driver.quit();
    }
}
