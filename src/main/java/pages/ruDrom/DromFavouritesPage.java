package pages.ruDrom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;
import java.util.Objects;

import static org.testng.Assert.assertEquals;

public class DromFavouritesPage extends BasePage {
    public DromFavouritesPage(WebDriver driver) {
        super(driver);
    }
    private final By headerAddedAd = By.xpath("//a[@class=\"bulletinLink bull-item__self-link auto-shy\"]");
    private final By costAddedAd = By.xpath("//span[@class=\"price-block__price\"]");
    private final By buttonDelete = By.xpath("//a[@class=\"removeBookmark\"]");
    private final Actions action = new Actions(driver);
    public DromFavouritesPage cleanFavourites() {
        int num = driver.findElements(buttonDelete).size();
        for( int a= 1; a <= num;a++){
            action
                    .pause(700)
                    .build()
                    .perform();
            driver.findElement(buttonDelete).click();
        }
        driver.navigate().back();

        return this;
    }

    public DromFavouritesPage checkAdCorrectAddition(String[] selectedAdArray) {
           String headerAd = driver.findElement(headerAddedAd).getText();
           String costAd = driver.findElement(costAddedAd).getText().substring(0, driver.findElement(costAddedAd).getText().length() - 1);


           if (Objects.equals(selectedAdArray[0], headerAd) & Objects.equals(selectedAdArray[1], costAd)) {
               System.out.println("Обьявление добавлено корректно");
           }
               assertEquals(selectedAdArray[0], headerAd, "Обьявление не добавлено");
               assertEquals(selectedAdArray[1], costAd, "Обьявление не добавлено");
           driver.navigate().back();
        return this;
    }
    public DromFavouritesPage checkCorrectAddition(int numSelectedCars, String[] array1) {

        int num = driver.findElements(buttonDelete).size();
        if (num==numSelectedCars){
           System.out.println("Количество добавленных обьявлений в избранное совпадает с количеством всех автомобилей в избранном");
        }
        assertEquals(num, numSelectedCars, "Количество добавленных обьявлений в избранное не совпадает с количеством всех автомобилей в избранном");

        return this;
    }

}
