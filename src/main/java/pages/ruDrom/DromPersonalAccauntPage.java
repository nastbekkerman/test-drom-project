package pages.ruDrom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class DromPersonalAccauntPage extends BasePage {
    public DromPersonalAccauntPage(WebDriver driver) {
        super(driver);
    }

    private final By settings = By.xpath("//a[text()=\"Настройки\"]");

    public DromPersonalAccauntPage goToAccountSettings(){
        driver.findElement(settings).click();

        return this;
    }
}
