package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created  on 02.03.2017.
 */
public class LessonHoumeTask8 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));

    }

    @Test
    public void TestExercise8() {

        List<WebElement> elements = driver.findElements(By.cssSelector(".link[title$=Duck]"));
        for(WebElement element1: elements){
          assertTrue(element1.findElements(By.cssSelector("[class^=sticker]"))
                        .toArray().length==1);

        }


    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}