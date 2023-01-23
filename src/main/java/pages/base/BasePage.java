package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.Config.EXPLIVIT_WAIT;

public class BasePage {
    protected WebDriver driver;

    public BasePage (WebDriver driver){

        this.driver = driver;
    }

    public void open(String url) {

        driver.get(url);
    }
    public int findNumber (String string){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        int start = 0;
        int number = 0;
        while (matcher.find(start)) {
            String value = string.substring(matcher.start(), matcher.end());
            number = Integer.parseInt(value);
            start = matcher.end();
        }
        return number;
    }
    public Boolean isDisplayed(By by ){
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {

            return false;
        }
    }

    public WebElement waitElementIsVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLIVIT_WAIT))
                .until(ExpectedConditions.visibilityOf (element));



        return element;
    }

}
