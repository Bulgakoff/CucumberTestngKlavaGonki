package resources;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjectFolder.GamePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public static WebDriver driver;
    public Properties properties;

    public WebDriver initilizeDriver() throws IOException {
        properties = new Properties();
        FileInputStream fis =
                new FileInputStream
                        (System.getProperty("user.dir")
                                + "\\src\\main\\java\\resources\\data.properties");
        properties.load(fis);
        String browserName = properties.getProperty("browser");//for testng.xml
//        String browserName = System.getProperty("browser"); //for console
        if (browserName.contains("chrome")) {
            //execute chrome driver chrome:
            System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriverjava\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equals("firefox")) {

        } else if (browserName.equals("IE")) {

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
    public void makeScreenshot() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceObj = ts.getScreenshotAs(OutputType.FILE);
        String destinationFilePath = System.getProperty("user.dir")
                + "\\reports\\" + System.currentTimeMillis() + "step.png";
        File fileDestinationObj = new File(destinationFilePath);
        FileUtils.copyFile(sourceObj, fileDestinationObj);
    }
    public String getCurrentWords() {
        return new GamePage(driver).getHighLight().getText().replaceAll("c", "с").
                replaceAll("o", "о");
    }


}
