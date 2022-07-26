import Locators.MainPage;
import Locators.OrderPage;
import Locators.RentPage;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;

//Все Тесты проходят, если нужно что-то переделать или добавить, просьба, написать понятное ТЗ, спасибо

public class Tests {

    private WebDriver driver;

    // Проверяем текст в гармошке Chrome
    @Test
    public void testChrome() { // Проверка происходит в цикле метода
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickAskButtons();
    }

    // Проверяем верхнюю кнопку в Chrome
    @Test
    public void testChrome1() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickUpButtonOrder();

        OrderPage objOrderPage = new OrderPage(this.driver);
        objOrderPage.login("Иван", "Калатушкин", "Улица Колотушкина, Дом Кукушкина", "89288888888");

        RentPage objRentPage = new RentPage(this.driver);
        objRentPage.fill("Привезти как можно скорее");
    }

    // Проверка url при нажатии на лого
    @Test
    public void testLogo() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickLogo();
        String getUrl = driver.getCurrentUrl();
        Assert.assertEquals("Должны совпадать", getUrl, "https://qa-scooter.praktikum-services.ru/");
    }

    // Проверка лого Яндекса
    @Test
    public void testLogoYa() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
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

    // Проверка отображения ошибок
    @Test
    public void testCheckErrors() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
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
    //Проверка картинки в не существующих заказах
    @Test
    public void testCheckNoOrder() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.searchOrder("12343");
        String actual = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/img")).getAttribute("alt");
        Assert.assertEquals("Должна быть картинка", "Not found", actual);
    }

    // Тесты на FireFox
    // Проверяем текст в гармошке FireFox
    @Test
    public void testFirefox() { // Проверка происходит в цикле метода
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickAskButtons();
    }
    // Проверяем нижнюю кнопку в FireFox
    @Test
    public void testFirefox1() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(this.driver);
        objMainPage.clickDownButtonOrder();

        OrderPage objOrderPage = new OrderPage(this.driver);
        objOrderPage.login("Дима", "Матроскин", "Улица Кукушкина, Дом Колотушкина", "89288888888");

        RentPage objRentPage = new RentPage(this.driver);
        objRentPage.fill("Привезти как можно скорее");
    }
    @After
    public void teardown() {
        this.driver.quit();
    }
}