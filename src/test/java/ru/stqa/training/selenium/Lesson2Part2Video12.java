package ru.stqa.training.selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
/**
 * Created  on 23.02.2017.
 */
public class Lesson2Part2Video12 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void myFirstTest(){
        driver.get("https://www.google.ru/");
        driver.findElement(By.id("gs_ok0")).click();
        driver.findElement(By.id("K32")).click();
        driver.findElement(By.id("gs_ok0")).click();
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnG")).click();
        wait.until(titleIs("webdriver - Поиск в Google")) ;
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
