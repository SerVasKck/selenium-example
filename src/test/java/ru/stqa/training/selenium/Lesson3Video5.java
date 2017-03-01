package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created  on 27.02.2017.
 */
public class Lesson3Video5 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
       ChromeOptions options = new ChromeOptions();
     //  options.addArguments("start-fullscreen");
        options.addArguments("start-maximized");

       // driver = new ChromeDriver(options);
       // driver = new InternetExplorerDriver(); //не вышло
        // driver = new FirefoxDriver();
        //WebDriver firefoxDriver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void myFirstTest(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store")) ;
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
