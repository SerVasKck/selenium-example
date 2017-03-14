package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created on 12.03.2017.
 */
public class Lesson6HoumeTask12 {
    private WebDriver driver;
    private WebDriverWait wait;

    protected void pause(int sec) {
        long end = System.currentTimeMillis() + (sec * 1000);
        while (System.currentTimeMillis() < end) {
        }
    }

    protected static String random () { //генерация номера от 1 до 10
        int a = 0; // Начальное значение диапазона - "от"
        int b = 10; // Конечное значение диапазона - "до"

        int random_number1 = a + (int) (Math.random() * b)*(int) (Math.random() * b); // Генерация 1-го числа
        String randomNum = String.valueOf(random_number1);
        return randomNum;
    }


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();


    }

    @Test
    public void TestExercise9Part1a() {

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");

        driver.findElement(By.linkText("Add New Product")).click();//жмем кнопку Добавления товара
        wait.until(titleIs("Add New Product | My Store"));

        //заполняем поля

       driver.findElement(By.cssSelector(".content [name^=status]")).click();
        driver.findElement(By.cssSelector(".content [name^=name]")).sendKeys("New Duck");
        driver.findElement(By.cssSelector(".content [name^=code]")).sendKeys("rd0"+random());

        //Categories
        driver.findElement(By.xpath("//tr[2]/td/input")).click();

        //выпадающий списко Default Category
        driver.findElement(By.cssSelector("select[name=default_category_id]")).click();
        Select category = new Select(driver.findElement(By.cssSelector("[name=default_category_id]")));
        category.selectByValue("1");

        //product_groups[]
        driver.findElement(By.xpath("//tr[3]/td/input")).click();

        driver.findElement(By.cssSelector("select[name=sold_out_status_id]")).click();
        Select soldStatus = new Select(driver.findElement(By.cssSelector("[name=sold_out_status_id]")));
        soldStatus.selectByValue("1");

        //добавить файл
        File f = new File("pictures/duck.jpg");
        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());
        driver.findElement(By.cssSelector("input[type=file]")).sendKeys(f.getAbsolutePath());


        //выбор из календаря
        driver.findElement(By.cssSelector("input[name=date_valid_from]")).sendKeys("12/12/2017");
        driver.findElement(By.cssSelector("input[name=date_valid_to]")).sendKeys("12/12/2017");


        // перейти на Information
        driver.findElement(By.linkText("Information")).click();
        pause(2);

        driver.findElement(By.cssSelector("[name^=short_description]"))
                .sendKeys("Lorem ipsum dolor sit amet,eget ornare libero porta congue");
        driver.findElement(By.cssSelector(".trumbowyg-editor"))
                .sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue.");

        //перейти на Prices
        driver.findElement(By.linkText("Prices")).click();
        pause(2);

        driver.findElement(By.cssSelector("input[name=purchase_price]"))
                .sendKeys("1");
        driver.findElement(By.cssSelector("input[type=number][name*=USD]"))
                .sendKeys("1");
        driver.findElement(By.cssSelector("button[name=save]"))
                .click();

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
