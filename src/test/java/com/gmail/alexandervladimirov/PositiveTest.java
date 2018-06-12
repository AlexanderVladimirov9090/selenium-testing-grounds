package com.gmail.alexandervladimirov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created on 11.06.18.
 *
 * @author Alexander Vladimirov
 * <alexandervladimirov1902@gmail.com>
 */
public class PositiveTest {

    @Test
    public void login() {
        //Create Driver.
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
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
        Assert.assertTrue(logoutButton.isDisplayed(),"No logout is visible.");
        // Success message.
        WebElement successMessage = driver.findElement(By.id("flash"));
        String expectedMessage = "You logged into a secure area!\n×";
        String actualMessage  = successMessage.getText();
        Assert.assertEquals(actualMessage,expectedMessage);
        sleep(3000L);
        //Close Web Browser.
        driver.quit();
    }

    private void sleep(Long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
