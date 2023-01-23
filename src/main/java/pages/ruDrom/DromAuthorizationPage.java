package pages.ruDrom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;
import static org.testng.Assert.assertFalse;

public class DromAuthorizationPage extends BasePage {
    public DromAuthorizationPage(WebDriver driver) {
        super(driver);
    }

    private final By signInput = By.xpath("//input[@name=\"sign\"]");
    private final By passwordInput = By.xpath("//input[@name=\"password\"]");
    private final By signButton = By.xpath("//button[@class=\"bigbutton em-button signbutton\"]");
    private final By alert = By.xpath("//div[text()=\"Данные для входа неверны\"]");

    private void enterUserData(By by, String userData){
        Actions actionEnter = new Actions(driver);
        driver.findElement(by).click();
        actionEnter
                .sendKeys(userData)
                .build()
                .perform();
    }
    public DromAuthorizationPage enterEmail(String email) {
        enterUserData(signInput, email);
        return this;
    }
    public DromAuthorizationPage enterPhoneNumber(String phoneNumber) {
        enterUserData(signInput, phoneNumber);
        return this;
    }
    public DromAuthorizationPage enterLogin(String login) {
        enterUserData(signInput, login);
        return this;
    }
    public DromAuthorizationPage enterPassword(String password) {
        enterUserData(passwordInput, password);
        return this;
    }
    public DromAuthorizationPage clickSignButton() {
        driver.findElement(signButton).click();
        assertFalse(isDisplayed(alert), "Данные для входа не верны");
        return this;
    }

}
