package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
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

       //перебор в цикле
        int n=1;
        while (n<=driver.findElements(By.xpath("(//li[@id='app-']/a/span[2])")).toArray().length) {
            driver.findElement(By.xpath("(//li[@id='app-']/a/span[2])["+n+"]")).click();
            driver.findElement(By.tagName("h1"));
            //int m = driver.findElements(By.xpath("//li[5]/ul/li[3]/a/span")).toArray().length;
            int m = driver.findElements(By.xpath("//li["+n+"]/ul/li/a/span")).toArray().length;
            System.out.println(m);//li[2]/ul/li[2]/a/span
            if(driver.findElements(By.xpath("//li["+n+"]/ul/li/a/span")).toArray().length!=0)
            {int i=1;
                while (i<=driver.findElements(By.xpath("//li["+n+"]/ul/li/a/span")).toArray().length)
                {
                    driver.findElement(By.xpath("//li["+n+"]/ul/li["+i+"]/a/span")).click();
                    driver.findElement(By.tagName("h1"));
                    i++;
                }
            }
            n++;
        }


    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
