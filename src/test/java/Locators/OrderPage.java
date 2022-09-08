package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private WebDriver driver;
    private By nameField = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input");
    private By secondNameField = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input");
    private By clientAdress = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input");
    private By chooseMetro = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div/input");
    private By choosePhone = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input");
    private By buttonNext = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    public void setSecondNameField(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }
    public void setClientAdress(String adress) {
        driver.findElement(clientAdress).sendKeys(adress);
    }
    public void setChooseMetro() {
        driver.findElement(chooseMetro).click();
        WebElement select = driver.findElement(By.cssSelector("button[value='112']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", select);
        select.click();
    }
    public void setChoosePhone(String phone) {
        driver.findElement(choosePhone).sendKeys(phone);
    }
    public void setButtonNext() {
        driver.findElement(buttonNext).click();
    }
    public void login(String name, String secondName, String adress, String phone){
        setNameField(name);
        setSecondNameField(secondName);
        setClientAdress(adress);
        setChooseMetro();
        setChoosePhone(phone);
        setButtonNext();
    }
}
