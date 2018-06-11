package com.gmail.alexandervladimirov;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        sleep(3000L);
        driver.manage().window().maximize();
        //Open the page.
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);

    }

    /**
     * Sleep for 3 seconds.
     */
    private void sleep(Long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
