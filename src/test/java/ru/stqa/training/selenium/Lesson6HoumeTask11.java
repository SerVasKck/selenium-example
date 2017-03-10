package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created on 09.03.2017.
 */
public class Lesson6HoumeTask11 {
    private WebDriver driver;
    private WebDriverWait wait;

    protected void pause(int sec) {
        long end = System.currentTimeMillis() + (sec * 1000);
        while (System.currentTimeMillis() < end) {
        }
    }

    @Before
    public void start() {
          driver = new ChromeDriver();
       // driver = new InternetExplorerDriver(); //не вышло
      //  driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));

    }


    @Test
    public void TestExercise11() {
        int i=0;
        String USER_EMAIL = "petrov"+new Date().getTime()+"@mail.ru";
        String USER_PASSWORD = "abcd1234";

        driver.findElement(By.linkText("New customers click here")).click();
        wait.until(titleIs("Create Account | My Store"));
        //заполняем поле Tax ID
        driver.findElement(By.cssSelector("[name=tax_id]")).sendKeys("12345");
        driver.findElement(By.cssSelector("[name=company]")).sendKeys("Company");
        driver.findElement(By.cssSelector("[name=firstname]")).sendKeys("Petrov");
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys("Petr");
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys("Moscow");
        driver.findElement(By.cssSelector("[name=address2]")).sendKeys("Moscow");
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys("14785");
        driver.findElement(By.cssSelector("[name=city]")).sendKeys("Moscow");
        driver.findElement(By.cssSelector("[name=country_code] [value=US]")).click(); //выбор страны
        driver.findElement(By.cssSelector("[name=zone_code] [value=GA]")).click(); //выбор зоны
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(USER_EMAIL);
        driver.findElement(By.cssSelector("[name=phone]")).sendKeys("+752153652566");//телефон
        driver.findElement(By.cssSelector("[name=newsletter]")).click();//отключаю рассылку
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(USER_PASSWORD);
        driver.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(USER_PASSWORD);
        driver.findElement(By.cssSelector("[name=create_account]")).click(); //жмем кнопку Create Account

        wait.until(titleIs("Online Store | My Store"));

        driver.findElement(By.linkText("Logout")).click(); //выходим

        //входим повторно
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(USER_EMAIL);
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("abcd1234");
        driver.findElement(By.cssSelector("[name=login]")).click();


        //выходим
        driver.findElement(By.linkText("Logout")).click();
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
