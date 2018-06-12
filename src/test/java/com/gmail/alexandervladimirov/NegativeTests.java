package com.gmail.alexandervladimirov;

import com.beust.jcommander.Parameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created on 12.06.18.
 *
 * @author Alexander Vladimirov
 * <alexandervladimirov1902@gmail.com>
 */
public class NegativeTests {

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

    @Parameters({"username", "password", "expectedError"})
    @Test
    public void invalidUserCredentials(String username, String password, String expectedError) {
        //Open the page.
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);


        //Enter username.
        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.sendKeys(username);
        //Enter password.
        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys(password);
        // Push login button.
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class= 'flash error']"));
        //String expectedError = "Your username is invalid!\n×";
        String actualError = errorMessage.getText();
        Assert.assertTrue(actualError.contains(expectedError),
                "actualMessage: "
                        + actualError
                        + "\n expectedMessage: "
                        + expectedError
                        + "\nare different!");
        driver.quit();
    }
}
