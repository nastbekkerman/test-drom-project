package pages.ruDrom;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;
import java.util.Arrays;

public class DromHomePage extends BasePage {
    public DromHomePage(WebDriver driver) {
        super(driver);
    }

    private final By brandName = By.xpath("(//input [@placeholder=\"Марка\"])");
    private final By brandBlock = By.xpath("//div[@class=\"css-10zrduq e140pxhy0\"]");
    private final By modelName = By.xpath("(//input [@placeholder=\"Модель\"])");
    private final By fuel = By.xpath("(//div[@aria-label=\"Топливо\"])");
    private final By notSold = By.xpath("(//label)");
    private final By mileageFrom = By.xpath("(//input[@data-ftid=\"sales__filter_mileage-from\"])");
    private final By YearFrom = By.xpath("(//button[text()=\"Год от\"])");
    private final By Search = By.xpath("(//div[text()=\"Показать\"])");
    private final By advancedSearch = By.xpath("(//span[contains(text(),\"Расширенный поиск\")])");
    private final By loginAndRegistration = By.xpath("//a[@data-ftid=\"component_header_login\"]");
    private final By card = By.xpath("//div[@class=\"css-1nvf6xk eaczv700\"]//a[@data-ftid=\"bulls-list_bull\"]");//локатор карточки обьявления
    private final By headerFavorite = By.xpath("//div[@class=\"css-na20rw e1buvaf60\"]//a[@data-ga-stats-name=\"topmenu_accountFavorite\"]");
    private final By headerSelectedAd = By.xpath("//span[@data-ftid=\"bull_title\"]");
    private final By costSelectedAd = By.xpath("//a[@class=\"css-xb5nz8 ewrty961\"][1]//span[@data-ftid=\"bull_price\"]");
    private final By starSelectedAd = By.xpath("//div[@class=\"css-1rozdag\"]");
    private final By headerIconAccount = By.xpath("//div[@data-ftid=\"component_header_user-info-expand-controller\"]");
    private final By accountButton = By.xpath("//a[@data-ftid=\"component_header_my-account\"]");
    private final By regionChangeButton = By.xpath("//a[@data-ga-stats-name=\"HomeRegionChange\"]");

    private int actualBrandNumber = 0;
    private void scrollBrandBlock (By by){
        Actions actionEnterBrandName = new Actions(driver);
        actionEnterBrandName
                .scrollToElement(driver.findElement(by))
                .build()
                .perform();
    }
    private String findBrandName (String brandString){
        String brandName;
        int indexB = brandString.indexOf(" (");
        if (indexB == -1) {
            brandName = brandString;
        }else {

            brandName = brandString.substring(0, indexB);
        }
        return brandName;
    }
    private void printResult(Integer[] array1, String[] array2){
        System.out.println("| Фирма | Количество объявлений |");
        for (int a=array1.length-20; a< array1.length; a++){
            for (String s : array2) {
                if (findNumber(s) == array1[a]) {
                    System.out.println("| " + findBrandName(s) + " | " + array1[a] + " |");
                }
            }
        }
    }
    private int requiredNumber(By by, int num){

        return Math.min(driver.findElements(by).size(), num);
    }
    Actions action= new Actions(driver);

    public DromHomePage enterBrandName (String brand) {
        driver.findElement(brandName).click();
        action
                .sendKeys(brand)
                .pause(1000)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .build()
                .perform();
        return this;
    }
    public DromHomePage enterModelName(String model) {
        driver.findElement(modelName).click();
        action
                .sendKeys(model)
                .pause(1000)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .build()
                .perform();
        return this;
    }
    public DromHomePage enterFuel(String fuelName) {
        By rightFuel = By.xpath("(//div[text()=\"" + fuelName + "\"][1])");
        driver.findElement(fuel).click();
        action
                .pause(2000)
                .build()
                .perform();
        driver.findElement(rightFuel).click();
        return this;
    }
    public DromHomePage enterMileageFrom(String mileageCount) {
        driver.findElement(mileageFrom).click();
        action
                .sendKeys(mileageCount)
                .pause(1000)
                .sendKeys(Keys.RETURN)
                .build()
                .perform();
        return this;
    }
    public DromHomePage enterYearFrom(String year) {
        By Year = By.xpath("(//div[text()=\"" + year + "\"][1])");
        driver.findElement(YearFrom).click();
        action
                .pause(2000)
                .build()
                .perform();
        driver.findElement(Year).click();
        return this;
    }
    public DromHomePage clickNotSold() {
        driver.findElement(notSold).click();
        return this;
    }
    public DromHomePage clickToAdvancedSearch() {
        driver.findElement(advancedSearch).click();
        return this;
    }
    public DromHomePage clickToSearch() {
        driver.findElement(Search).click();
        return this;
    }
    public DromHomePage clickToRegionChangePage(){
        driver.findElement(regionChangeButton).click();
        return this;
    }
    public DromHomePage findPopularCar(){

        driver.findElement(brandName).click();

        String a = driver.findElement(brandBlock).getAttribute("style");
        int countAllCarBrands= (findNumber(a)-222)/35;
        Integer [] numberOfBrandCars = new Integer[countAllCarBrands];
        String [] fullCarInfo= new String[countAllCarBrands];

        for (int countCarBrand = 222; countCarBrand<findNumber(a); countCarBrand= countCarBrand +35 ) {

            By actualBrand =  By.xpath("//div[@style=\"height: 35px; transform: translateY("+ countCarBrand +"px);\"]");
            waitElementIsVisible(driver.findElement(actualBrand));
            scrollBrandBlock(actualBrand);

            fullCarInfo [actualBrandNumber] = driver.findElement(actualBrand).getText();//Массив строк формата: Марка (колличество)
            numberOfBrandCars [actualBrandNumber] = findNumber(driver.findElement(actualBrand).getText()); //Массив количества машин
            actualBrandNumber++;
        }

        Arrays.sort(numberOfBrandCars);//Сортируем количество машин
        printResult(numberOfBrandCars, fullCarInfo);

        return this;
    }
    public DromHomePage clickToLoginAndRegistration() {
        driver.findElement(loginAndRegistration).click();
        return this;
    }
    public DromHomePage clickToFavorite() {
        driver.findElement(headerFavorite).click();
        return this;
    }

    public String[] addAdToFavourites(){
        String [] selectedAdInfo = new String[2];
        if(isDisplayed(costSelectedAd)){
            String adCost = driver.findElement(costSelectedAd).getText().substring(0, driver.findElement(costSelectedAd).getText().length()-1);
            selectedAdInfo [0] = driver.findElement(headerSelectedAd).getText();
            selectedAdInfo [1] = adCost;
            driver.findElement(starSelectedAd).click();
            clickToFavorite();
        }
        return selectedAdInfo;
    }
    public String[] addToFavourites(int numSelectedCars) {
        int actualPageNumber = 1;
        int num = numSelectedCars;
        String [] cardHeaders = new String[numSelectedCars];

        for (int verifiedNumberOfCards = 0; verifiedNumberOfCards < numSelectedCars; ) {

            waitElementIsVisible(driver.findElement(card));

            for (int cardNumber = 1; cardNumber <= requiredNumber(card,num); cardNumber++) {
                By actualStar = By.xpath("//div[@class=\"css-1nvf6xk eaczv700\"]//a["+ cardNumber + "]/div[3]/div[3]");
                By cardHeader = By.xpath("//div[@class=\"css-1nvf6xk eaczv700\"]//a["+ cardNumber + "]//span[@data-ftid=\"bull_title\"]");
                action
                        .pause(700)
                        .build()
                        .perform();
                driver.findElement(actualStar).click();
                cardHeaders [verifiedNumberOfCards] = driver.findElement(cardHeader).getText();
                verifiedNumberOfCards++;
            }

            num = num-requiredNumber(card,num);
            actualPageNumber++;
            By nextPageButton = By.xpath("//div[@class=\"css-19tk3lt e15hqrm30\"]/a[text()=\"" + actualPageNumber +"\"]");
            if (num>0){
                driver.findElement(nextPageButton).click();
            }

        }
        clickToFavorite();
        return cardHeaders;
    }
    public DromHomePage goToPersonalAccount() {

      driver.findElement(headerIconAccount);
      action
                .moveToElement(driver.findElement(headerIconAccount))
                .pause(500)
                .click(driver.findElement(accountButton))
                .build()
                .perform();

        return this;
    }


}
