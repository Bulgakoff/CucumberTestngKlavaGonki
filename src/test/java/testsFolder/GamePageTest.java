package testsFolder;


import io.cucumber.java.AfterStep;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjectFolder.GamePage;
import pageObjectFolder.LandingPage;
import resources.Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class GamePageTest extends Base {
    public WebDriver driver;
    LandingPage l;
    WebDriverWait wait;
    GamePage gamePage;
    Actions actions;

    @AfterStep
    public void makeScreenshot() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceObj = ts.getScreenshotAs(OutputType.FILE);
        String destinationFilePath = System.getProperty("user.dir")
                + "\\reports\\" + System.currentTimeMillis() + "step.png";
        File fileDestinationObj = new File(destinationFilePath);
        FileUtils.copyFile(sourceObj, fileDestinationObj);
    }


    @BeforeTest
    public void initilizing() throws IOException {
        driver = initilizeDriver();
    }


    @Test
    public void testGamePage() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get(properties.getProperty("url"));
        l = new LandingPage(driver);
        if (wait.until(ExpectedConditions.elementToBeClickable(l.quickStartLink)).isDisplayed()) {
            System.out.println("check 0");
            l.getQuickStartGame().click();
            makeScreenshot();
        }
        l.popUpCloseByButton().click();
        makeScreenshot();
        gamePage = new GamePage(driver);
        if (gamePage.getStartGameLink().isDisplayed()) {
            System.out.println("check 2");
            gamePage.getStartGameLink().click();
            makeScreenshot();
        } else if (gamePage.getHighLight().isDisplayed()) {
            gamePage.getInputField().click();
            System.out.println("check 3");
            makeScreenshot();
        }

        System.out.println("wait for  " + wait
                .until(ExpectedConditions.elementToBeClickable(gamePage.highLight)).getText());
        makeScreenshot();
        Thread.sleep(2000); //00 00
        while (true) {
            Thread.sleep(500);
            String currentWord = getCurrentWords();
            String afterFocusSymbol = gamePage.getAfterFocus().getText();
            gamePage.getInputField().sendKeys(currentWord);
            if (afterFocusSymbol.equals(".")) {
                gamePage.getInputField().sendKeys(".");
                Thread.sleep(3000);
                break;
            }
            gamePage.getInputField().sendKeys(Keys.SPACE);
        }
        System.out.println("check 4");
        String resultSpeedStr = gamePage.getResultText().getText();
        System.out.println(resultSpeedStr);
        int resultSpeedInt = Integer.parseInt(resultSpeedStr);
        System.out.println("Колличество знак == " + resultSpeedInt);
        makeScreenshot();
        Assert.assertTrue(resultSpeedInt < 2000, "Актуальная скорость : " + resultSpeedInt);

    }

    @AfterTest
    public void tearDown() {
        driver.close();
        driver = null;
    }


}
