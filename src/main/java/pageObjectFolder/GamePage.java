package pageObjectFolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GamePage {
    //attributes Class:
    public WebDriver driver;
    public By startGameLink = By.xpath("//a[text()='Начать игру']");
    public By inputField = By.xpath("//input[@id='inputtext']");
    public By highLight = By.xpath("//span[@id='typefocus']");//KJHJJJJJJJJJJJJJJJJJJJJJJJJJJ
    public By afterFocusWord = By.xpath("//span[@id='afterfocus']");
    public By resultText = By.xpath("//td[text()='Это вы']//ancestor-or-self::div//div[@class='stats']/div[2]/span/span");
    public By waitingTimeout = By.xpath("//span[@id='waiting_timeout']");

    //constructor:
    public GamePage(WebDriver driver) {
        this.driver = driver;
    }
    //methods class:

    public WebElement getStartGameLink() {
        return driver.findElement(startGameLink);
    }

    public WebElement getHighLight() {
        return driver.findElement(highLight);
    }

    public WebElement getInputField() {
        return driver.findElement(inputField);
    }

    public WebElement getAfterFocus() {
        return driver.findElement(afterFocusWord);
    }

    public WebElement getResultText() {
        return driver.findElement(resultText);
    }

    public WebElement getWaitingTimeout() {
        return driver.findElement(waitingTimeout);
    }
}
