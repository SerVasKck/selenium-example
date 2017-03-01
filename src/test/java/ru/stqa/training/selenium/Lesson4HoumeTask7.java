package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created  on 01.03.2017.
 */
public class Lesson4HoumeTask7 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store")) ;

    }

    @Test
    public void TestExercise7(){

      /*  driver.findElement(By.className("name")).click();
        driver.findElement(By.tagName("h1"));
        //запрашиваем по id
        driver.findElement(By.id("doc-template")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.id("doc-logotype")).click();
        driver.findElement(By.tagName("h1"));*/

        //запрашиваем по css
     /*   driver.findElement(By.xpath("(//li[@id='app-']/a/span[2])[2]")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.cssSelector("li #doc-catalog")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.cssSelector("li #doc-product_groups")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.cssSelector("li #doc-option_groups")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.cssSelector("li #doc-manufacturers")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.cssSelector("li #doc-suppliers")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.cssSelector("li [id = doc-delivery_statuses]")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.cssSelector("li [id = doc-sold_out_statuses]")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.cssSelector("#doc-quantity_units > a > span.name")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.cssSelector("#doc-csv > a > span.name")).click();
        driver.findElement(By.tagName("h1"));*/

        //запрашиваем по xpath
        driver.findElement(By.xpath("(//li[@id='app-']/a/span[2])[3]")).click();
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.xpath("(//li[@id='app-']/a/span[2])[4]")).click();
       // System.out.println(driver.findElement(By.xpath("(//li[@id='app-']/a/span[2])[4]")).getText());
        driver.findElement(By.tagName("h1"));










    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
