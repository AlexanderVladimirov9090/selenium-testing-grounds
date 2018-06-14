package com.gmail.alexandervladimirov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created on 11.06.18.
 *
 * @author Alexander Vladimirov
 * <alexandervladimirov1902@gmail.com>
 */
public class PositiveTest {
    WebDriver driver;

    /**
     * Sets up the driver.
     */
    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;

            default:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
        }

    }

    @Test
    public void login() {

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Open the page.
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);


        //Enter username.
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        //Enter password.
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        // Push login button.
        WebElement logingButton = driver.findElement(By.className("radius"));
        logingButton.click();
        //Verification
        //new URL
        String expectedUrl = "http://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        //Logout out button is visible.
        WebElement logoutButton = driver.findElement(By.xpath("//a[@class= 'button secondary radius']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "No logout is visible.");

        // Success message.
        WebElement successMessage = driver.findElement(By.id("flash"));
        String expectedMessage = "You logged into a secure area!\n×";
        String actualMessage = successMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "actualMessage: "
                        + actualMessage
                        + "\n expectedMessage: "
                        + expectedMessage
                        + "\nare different!");

    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
