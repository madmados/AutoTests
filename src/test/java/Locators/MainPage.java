package Locators;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    WebElement element;
    JavascriptExecutor jse;
    Actions actions;
    By button;
    private By takeOrder = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/button[2]");
    private By goButton = By.xpath("//*[@id='root']/div/div[1]/div[1]/div[3]/button");
    private By searchOrder = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[3]/div/input");

    static String[] expected = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области.",
    };

    public MainPage(WebDriver driver) {
        this.driver = driver;
        jse = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    public void clickAskButtons() {

        for (int i = 0; i < 8; i++) {
            button = By.id("accordion__heading-" + String.valueOf(i));
            element = driver.findElement(button);

            jse.executeScript("arguments[0].scrollIntoView()", element);

            actions.moveToElement(element).click().perform();

            driver.findElement(By.id("accordion__panel-" + String.valueOf(i))).getText();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement save = driver.findElement(By.id("accordion__panel-" + String.valueOf(i)));
            wait.until(ExpectedConditions.elementToBeClickable(save));
            Assert.assertEquals("Должны совпадать", expected[i], driver.findElement(By.id("accordion__panel-" + String.valueOf(i))).getText());

        }
    }

    public void clickUpButtonOrder() {
        button = By.className("Button_Button__ra12g");
        driver.findElement(button).click();
    }

    public void clickDownButtonOrder() {
        button = By.xpath("//*[@id='root']/div/div[1]/div[4]/div[2]/div[5]/button");
        element = driver.findElement(button);
        jse.executeScript("arguments[0].scrollIntoView()", element);
        driver.findElement(button).click();
    }

    public void clickLogo() {
        button = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[1]/a[2]/img");
        driver.findElement(button).click();
    }

    public void clickLogoYa() {
        button = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[1]/a[1]/img");
        driver.findElement(button).click();
    }

    public void setTakeOrder() {
        driver.findElement(takeOrder).click();
    }

    public void setSearchOrder(String Order) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(searchOrder));
        driver.findElement(searchOrder).sendKeys(Order);
    }

    public void setGoButton() {
        driver.findElement(goButton).click();
    }

    public void searchOrder(String Order) {
        setTakeOrder();
        setSearchOrder(Order);
        setGoButton();
    }
}