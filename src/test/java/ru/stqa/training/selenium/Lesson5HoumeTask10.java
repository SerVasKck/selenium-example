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

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import java.util.regex.*;

/**
 * Created  on 07.03.2017.
 */
public class Lesson5HoumeTask10 {
    private WebDriver driver;
    private WebDriverWait wait;

    public Boolean isColorRed (String colorCampaign)
    {
    Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
            Matcher matcher=pat.matcher(colorCampaign);
            int col=0;
            String color[] = new String[4];
            while (matcher.find()) {
                color[col] = matcher.group();
                col++;
            };
            if ( (color[0]+color[1]+color[2]).contains("20400")) return true;
            else   return false;

    }

    public Boolean isColorGray(String colorRegular){
        Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher=pat.matcher(colorRegular);
        int col=0;
        String color[] = new String[4];
        while (matcher.find()) {
            color[col] = matcher.group();
            col++;
        };
        if ( color[0].contains(color[1]) && color[1].contains(color[2]) && color[2].contains(color[0]))
          return true;
        else
        return false;
    }

    @Before
    public void start() {
      //  driver = new ChromeDriver();
      //   driver = new InternetExplorerDriver(); //не вышло
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));

    }

    @Test
    public void TestExercise10() {

        int i=0;
        String colorRegular = "0";
        String colorCampaign = "0";

        List<WebElement> elements = driver.findElements(By.cssSelector("#box-campaigns .link[title$=Duck]"));
        String name[] = new String[elements.toArray().length];
        String href[] = new String[elements.toArray().length];
        String regPrice[] = new String[elements.toArray().length];
        String camPrice[] = new String[elements.toArray().length];
        for(WebElement element1: elements){
           System.out.println( element1.findElement(By.cssSelector(".name")).getAttribute("textContent"));
            System.out.println( element1.getAttribute("href"));
            name[i] = element1.findElement(By.cssSelector(".name")).getAttribute("textContent");
            href[i] = element1.getAttribute("href");
            regPrice[i] = element1.findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
            System.out.println(element1.findElement(By.cssSelector(".regular-price")).getAttribute("textContent"));
            camPrice[i] = element1.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");
            //colorRegPris
            System.out.println( "цвет на основной странице обічная цена"+element1.findElement(By.cssSelector(".regular-price"))
                    .getCssValue("color"));
            System.out.println( "цвет на основной странице обічная цена"+element1.findElement(By.cssSelector(".campaign-price"))
                    .getCssValue("color"));
            colorCampaign = element1.findElement(By.cssSelector(".campaign-price"))
                    .getCssValue("color");
            colorRegular = element1.findElement(By.cssSelector(".regular-price"))
                    .getCssValue("color");


            i++;

        }
        for (int j=0; j<i;j++) {
            //переходим по ссілке
            driver.get(href[j]);
            //Сравниваем значение названия по ссылке

            System.out.println(driver.findElement(By.cssSelector("#box-product .title")).getText());
            //проверяем совпадение имени
            assertTrue(name[j].contains(driver.findElement(By.cssSelector("#box-product .title")).getText()));
            //проверяем совпадение цены
            assertTrue(regPrice[j].contains(driver.findElement(By.cssSelector("#box-product .regular-price")).getText()));
            assertTrue(camPrice[j].contains(driver.findElement(By.cssSelector("#box-product .campaign-price")).getText()));

            //проверяем цвет и стиль обычной  цены
            assertTrue(isColorGray(driver.findElement(By.cssSelector("#box-product .regular-price"))
                    .getCssValue("color")));
            assertTrue(isColorGray(colorRegular));

            //проверяем цвет и стиль акционной цены
            assertTrue(isColorRed(driver.findElement(By.cssSelector("#box-product .campaign-price"))
                    .getCssValue("color")));
            assertTrue(isColorRed(colorCampaign));



           // System.out.println("на главной "+regPrice[j].getCssValue("color"));

            //обычная


        }

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
