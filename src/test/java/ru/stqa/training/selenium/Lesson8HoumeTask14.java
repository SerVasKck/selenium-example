package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


/**
 * Created  on 16.03.2017.
 */
public class Lesson8HoumeTask14 {
    private WebDriver driver;
    private WebDriverWait wait;

    protected void pause(int sec) {
        long end = System.currentTimeMillis() + (sec * 1000);
        while (System.currentTimeMillis() < end) {
        }
    }


    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store")) ;

    }

    @Test
    public void TestExercise14(){
        String newWindow;
        String mainWindow;
        String[] myArray = {};
        String[] myArrayOld = {};

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(titleIs("Countries | My Store")) ;

        driver.findElement(By.linkText("Add New Country")).click();

        //найти все поля со ссылкой
        List<WebElement> links = driver.findElements(By.cssSelector("i.fa.fa-external-link"));
        for(WebElement link: links){
            mainWindow = driver.getWindowHandle();
            newWindow =  mainWindow;
            Set<String> oldWindows = driver.getWindowHandles();
            myArrayOld = oldWindows.toArray(new String[oldWindows.size()]); //формируем матрицу из значений
          //  System.out.println("старое окно "+oldWindows);

            link.click();
            pause(2);


            //ожидаем появления окна
           Set<String> newWindows = driver.getWindowHandles();
           // System.out.println(newWindows.toString());


            myArray = newWindows.toArray(new String[newWindows.size()]);//формируем матрицу
           for (int i=0;i<newWindows.size();i++) {
               if (myArrayOld[0].contains(myArray[i])){}//сравниваем значения старого и нового окна
               else {
                   newWindow = myArray[i];
                //   System.out.println("новое окно " + newWindow);
               }
           }

            driver.switchTo().window(newWindow);

           //добавить ожидание загрузки страницы
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
           // System.out.println(driver.findElement(By.cssSelector("h1")).getText());

            driver.close();

            driver.switchTo().window(mainWindow);

        }

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
