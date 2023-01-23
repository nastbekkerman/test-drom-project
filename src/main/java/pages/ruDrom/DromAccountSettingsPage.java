package pages.ruDrom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

import static org.testng.Assert.*;

public class DromAccountSettingsPage extends BasePage {
    public DromAccountSettingsPage(WebDriver driver) {
        super(driver);
    }
    private final By actualPhoneNumber = By.xpath("//div[@class=\"item canEdit\"][1]//td[@class=\"value\"]");
    private final By actualLogin = By.xpath("//div[@class=\"item\"]//td[@class=\"value\"]");
    private final By actualEmail = By.xpath("//div[@class=\"item canEdit\"][2]//td[@class=\"value\"]");

    private String cleareString (String str){
        String charsToRemove = " -";
        for (char c : charsToRemove.toCharArray()) {
            str = str.replace(String.valueOf(c), "");
        }
        return str;
    }
    public DromAccountSettingsPage checkPhoneNumberMatches(String phoneNumber) {
        String clearActualPhone = cleareString(driver.findElement(actualPhoneNumber).getText());
        assertEquals(phoneNumber, clearActualPhone, "Введенный номер телефона не совпадает с номером телефона указанным в аккаунте");
        return this;
    }
    public DromAccountSettingsPage checkLoginMatches(String login) {
        assertEquals(login, driver.findElement(actualLogin).getText(), "Введенный логин не совпадает с логином указанным в аккаунте");
        return this;
    }
    public DromAccountSettingsPage checkEmailMatches(String email) {
        assertEquals(email, driver.findElement(actualEmail).getText(), "Введенная почта не совпадает с почтой указанной в аккаунте");
        return this;
    }
    public DromAccountSettingsPage backToHomePage(){
        driver.navigate().back();
        driver.navigate().back();

        return this;
    }
}
