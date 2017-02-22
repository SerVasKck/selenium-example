package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


/**
 * Created by i.shapoval on 22.02.2017.
 */
public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void myFirstTest(){
        driver.get("https://github.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btng")).click();
        wait.until(titleIs("webdriver - Поиск в Google")) ;
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
