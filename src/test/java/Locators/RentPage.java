package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RentPage {
    private WebDriver driver;
    private By when = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input");
    private By date = By.className("Dropdown-placeholder");
    private By colour = By.id("grey");
    private By comment = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input");
    private By buttonNext = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]");
    private By buttonConfirm = By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]");
    private By buttonComplete = By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button");

    public RentPage(WebDriver driver){
        this.driver = driver;
    }
    public void setWhen() {
        driver.findElement(when).click();
        driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/div[2]/div[5]/div[3]")).click();
    }
    public void setDate() {
        driver.findElement(date).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[3]")).click();
    }
    public void setColour() {
        driver.findElement(colour).click();
    }
    public void setComment(String comm) {
        driver.findElement(comment).sendKeys(comm);
    }
    public void setButtonNext() {
        driver.findElement(buttonNext).click();
    }
    public void setButtonConfirm() {
        driver.findElement(buttonConfirm).click();
    }
    public void setButtonComplete() {
        driver.findElement(buttonComplete).click();
    }
    public void fill(String comm){
        setWhen();
        setDate();
        setColour();
        setComment(comm);
        setButtonNext();
        setButtonConfirm();
        setButtonComplete();
    }
}