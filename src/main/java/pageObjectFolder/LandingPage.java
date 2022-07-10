package pageObjectFolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage {
    //attributes Class:
    public WebDriver driver;
    public  By quickStartLink = By.xpath("//a[text()='Быстрый старт']");
    public  By popUpButtonClose = By.xpath("//div[@id='howtoplay']//input[@value='Закрыть']");
    //constructor:
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }
    //methods class:
    public WebElement popUpCloseByButton() {
        return driver.findElement(popUpButtonClose);
    }
    public List<WebElement> getPopUpSize() {
        return driver.findElements(popUpButtonClose);
    }
    public WebElement getQuickStartGame() {
        return driver.findElement(quickStartLink);
    }
}
