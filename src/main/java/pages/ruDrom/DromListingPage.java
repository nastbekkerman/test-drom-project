package pages.ruDrom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DromListingPage extends BasePage {
    public DromListingPage(WebDriver driver) {
        super(driver);
    }
    private final By card = By.xpath("//div[@class=\"css-1nvf6xk eaczv700\"][1]//a[@class=\"css-xb5nz8 ewrty961\"]");//локатор карточки обьявления
    private final By countOfAllAdsHeader = By.xpath("//div[@class=\"css-1ksi09z e1hsrrag2\"]");//локатор карточки обьявления
    int actualPageNumber = 1;

    private int findNumber (By by){
        String countOfAllAdsHeaderString = driver.findElement(by).getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(countOfAllAdsHeaderString);
        int start = 0;
        int number = 0;
        while (matcher.find(start)) {
            String value = countOfAllAdsHeaderString.substring(matcher.start(), matcher.end());
            number = Integer.parseInt(value);
            start = matcher.end();
        }
        return number;
    }
    private Boolean carIsSold (By by){
        String cardHeaderClass = driver.findElement(by).getAttribute("class");//поиск класса заголовка, для определения перечеркнут ли он
        return !Objects.equals(cardHeaderClass, "css-r91w5p e3f4v4l2");
    }
    private Boolean checkCarYear (By by){
        int carHeaderLenght = driver.findElement(by).getText().length();
        String carYearString = driver.findElement(by).getText().substring(carHeaderLenght - 4, carHeaderLenght);
        int carYear = Integer.parseInt(carYearString.trim());
        return carYear >= 2007;
    }
    private String checkCarYearMessage(int a, int b){
        if(a>0){
            return ("Найдено автомобилей до 2007 года: " + a + " || на странице: " + b + "\n");
        }else{
            return "";
        }
    }
    private String checkCarMileageMessage(int a, int b){
        if(a>0){
            return ("Найдено автомобилей без пробега: " + a + " || на странице: " + b + "\n");
        }else{
            return "";
        }
    }
    private String checkSoldCarMessage(int a, int b){
        if(a>0){
            return ("Найдено проданных автомобилей: " + a + " || на странице: " + b + "\n");
        }else{
            return "";
        }
    }

    public DromListingPage checkCarAds() {

        for (int verifiedNumberOfCards = 0; verifiedNumberOfCards < findNumber(countOfAllAdsHeader);) {

            waitElementIsVisible(driver.findElement(card));
            int countCarsWithWongYear = 0;
            int countCarsWithoutMileage = 0;
            int countSoldCars = 0;

            for (int cardNumber = 1; cardNumber <= driver.findElements(card).size(); cardNumber++) {

                By cardHeader = By.xpath("//div[@class=\"css-1nvf6xk eaczv700\"][1]//a[@class=\"css-xb5nz8 ewrty961\"]" + "[" + cardNumber + "]" + "//div[@class=\"css-13ocj84 e727yh30\"]/div[1]/div[1]");
                By cardCarMileage = By.xpath("//div[@class=\"css-1nvf6xk eaczv700\"][1]//a[@class=\"css-xb5nz8 ewrty961\"]" + "[" + cardNumber + "]" + "//div[@data-ftid=\"component_inline-bull-description\"]/span[5]");

                //год авто не меньше 2007;
                if (!checkCarYear(cardHeader)) {
                    countCarsWithWongYear = countCarsWithWongYear+1;
                }

                //проверка наличия пробега у автомобиля
                if (!isDisplayed(cardCarMileage)) {
                    countCarsWithoutMileage = countCarsWithoutMileage + 1;
                }

                //проверка продана ли машина
                if (!carIsSold(cardHeader)) {
                    countSoldCars = countSoldCars + 1;
                }


                verifiedNumberOfCards++;
            }
            System.out.print(checkCarYearMessage(countCarsWithWongYear,actualPageNumber));
            System.out.print(checkCarMileageMessage(countCarsWithoutMileage,actualPageNumber));
            System.out.print(checkSoldCarMessage(countSoldCars,actualPageNumber));

            actualPageNumber++;
            By nextPageButton = By.xpath("//div[@class=\"css-19tk3lt e15hqrm30\"]/a[text()=\"" + actualPageNumber +"\"]");
            Boolean indicatedNextPageButton = isDisplayed(nextPageButton);

            if (indicatedNextPageButton){
                driver.findElement(nextPageButton).click();
            }
        }


        return this;
    }

}
