package com.gmail.alexandervladimirov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created on 12.06.18.
 *
 * @author Alexander Vladimirov
 * <alexandervladimirov1902@gmail.com>
 */
public class NegativeTests {

    @Test
    public void invalidUser() {
        //Create Driver.
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Open the page.
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);


        //Enter username.
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("ivalidUser");
        //Enter password.
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        // Push login button.
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class= 'flash error']"));
        String expectedError = "Your username is invalid!\n×";
        String actualError = errorMessage.getText();
        Assert.assertEquals(actualError, expectedError);

        //Close Web Browser.
        driver.quit();
    }
}
