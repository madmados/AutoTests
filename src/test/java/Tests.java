import Locators.MainPage;
import Locators.OrderPage;
import Locators.RentPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;


public class Tests {

    private static WebDriver driver;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void checkTextTest() {
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickAskButtons();
    }
    @Test
    public void upButtonOrderTest() {
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickUpButtonOrder();

        OrderPage objOrderPage = new OrderPage(this.driver);
        objOrderPage.login("Иван", "Калатушкин", "Улица Колотушкина, Дом Кукушкина", "89288888888");

        RentPage objRentPage = new RentPage(this.driver);
        objRentPage.fill("Привезти как можно скорее");
        String actual = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[5]/div[1]")).getText().substring(0,14);
        Assert.assertEquals("Должны совпадать", "Заказ оформлен", actual);
    }
    @Test
    public void logoTest() {
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickLogo();
        String getUrl = driver.getCurrentUrl();
        Assert.assertEquals("Должны совпадать", getUrl, "https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void logoYaTest() {
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickLogoYa();

        String window1 = driver.getWindowHandle(); // Есть способ лучше?:)
        Set<String> currentWindows = driver.getWindowHandles();
        String nextWindow = null;
        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                nextWindow = window;
                break;
            }
        }
        driver.switchTo().window(nextWindow);
        String getUrl = driver.getCurrentUrl();
        Assert.assertEquals("Должны совпадать", "https://yandex.ru/", getUrl);
    }
    @Test
    public void checkErrorsTest() {
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickUpButtonOrder();
        OrderPage objOrderPage = new OrderPage(this.driver);
        objOrderPage.login("123", "123", "123", "123");
        String firstError = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div")).getText();
        Assert.assertEquals("Должны совпадать", "Введите корректное имя", firstError);
        String secondError = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div")).getText();
        Assert.assertEquals("Должны совпадать", "Введите корректную фамилию", secondError);
        String thirdError = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/div")).getText();
        Assert.assertEquals("Должны совпадать", "Введите корректный адрес", thirdError);
        String fourthError = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/div")).getText();
        Assert.assertEquals("Должны совпадать", "Введите корректный номер", fourthError);
    }
    @Test
    public void checkNoOrderTest() {
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.searchOrder("12343");
        String actual = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/img")).getAttribute("alt");
        Assert.assertEquals("Должна быть картинка", "Not found", actual);
    }
    @Test
    public void downButtonOrderTest() {
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickDownButtonOrder();

        OrderPage objOrderPage = new OrderPage(this.driver);
        objOrderPage.login("Дима", "Матроскин", "Улица Кукушкина, Дом Колотушкина", "89288888888");

        RentPage objRentPage = new RentPage(this.driver);
        objRentPage.fill("Привезти как можно скорее");
        String actual = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[5]/div[1]")).getText().substring(0,14);
        Assert.assertEquals("Должны совпадать", "Заказ оформлен", actual);
    }
    @After
    public void teardown() {
        this.driver.quit();
    }
}