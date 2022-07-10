package stepDefinitionsFolder;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.java.AfterStep;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjectFolder.GamePage;
import pageObjectFolder.LandingPage;
import resources.Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Stepdefs extends Base {
    LandingPage l;
    GamePage gamePage;

    private String getCurrentWord() {
        return gamePage.getHighLight().getText().replaceAll("c", "с").
                replaceAll("o", "о");
    }


    @Given("^Initialize test with chrome$")
    public void initialize_test_with_chrome() throws Throwable {
        driver = initilizeDriver();
    }

    @When("^Open site \"([^\"]*)\"$")
    public void openSite(String url) throws Throwable {
        driver.get(url);
        makeScreenshot();
    }

    @And("^Click on game link in landing page for start game$")
    public void clickOnGameLinkInLandingPageForStartGame() throws IOException {
        l = new LandingPage(driver);
        if (l.getPopUpSize().size() > 0) {
            l.popUpCloseByButton().click();
        } else if (l.getQuickStartGame().isDisplayed()) {
            l.getQuickStartGame().click();
        }
        makeScreenshot();
    }

    @And("^Wait for start game$")
    public void waitForStartGame() throws InterruptedException, IOException {
        gamePage = new GamePage(driver);
        if (gamePage.getStartGameLink().isDisplayed()) {
            gamePage.getStartGameLink().click();
        } else if (gamePage.getHighLight().isDisplayed()) {
            gamePage.getInputField().click();
        }
        makeScreenshot();
    }

    @And("^Enter highlight word in loop$")
    public void enterHighlightWordInLoopPlayGame() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        System.out.println("wait for  " + wait
                .until(ExpectedConditions.presenceOfElementLocated(gamePage.highLight)).getText());

        Thread.sleep(2000); //00 00
        while (true) {
            Thread.sleep(500);
            String currentWord = getCurrentWord();
            String afterFocusSymbol = gamePage.getAfterFocus().getText();
            gamePage.getInputField().sendKeys(currentWord);
            if (afterFocusSymbol.equals(".")) {
                gamePage.getInputField().sendKeys(".");
                Thread.sleep(3000);
                break;
            }
            gamePage.getInputField().sendKeys(Keys.SPACE);
        }
        makeScreenshot();
    }

    @And("^We note that there are more than (\\d+) characters per minute$")
    public void endGame(int mimValue) throws IOException {
        System.out.println(mimValue);
        String resultSpeedStr = gamePage.getResultText().getText();
        System.out.println(resultSpeedStr);
        int resultSpeedInt = Integer.parseInt(resultSpeedStr);
        System.out.println("Колличество знак == " + resultSpeedInt);
        makeScreenshot();
        Assert.assertTrue(resultSpeedInt < mimValue, "Актуальная скорость : " + resultSpeedInt);
    }

    @Then("^close driver$")
    public void closeDriver() {
        driver.close();
        driver = null;
    }
}
