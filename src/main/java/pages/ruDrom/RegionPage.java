package pages.ruDrom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;


public class RegionPage extends BasePage {
    public RegionPage(WebDriver driver) {
        super(driver);
    }
    public RegionPage findDesiredRegion (String region) {
        By desiredRegion = By.xpath("//a[text()=\"" + region + "\"]");
        waitElementIsVisible(driver.findElement(desiredRegion));
        driver.findElement(desiredRegion).click();

        return this;
    }

}
