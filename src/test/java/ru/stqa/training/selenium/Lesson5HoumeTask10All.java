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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created  on 09.03.2017.
 */
public class Lesson5HoumeTask10All {
    private WebDriver driver;
    private WebDriverWait wait;
    int i=0;
    String colorRegular = "0";
    String colorCampaign = "0";
    String styleCampaign = "0";
    String styleRegular = "0";
    String sizeRegular = "0";
    String sizeCampaign = "0";

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

    public double fontSize(String size){
        Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher=pat.matcher(size);
        int num=0;

        while (matcher.find()) {
            size = matcher.group();
            num++;
        };
        double sizeInt = Double.parseDouble(size);
        return sizeInt;
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        //   driver = new InternetExplorerDriver(); //не вышло
        //  driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));



    }

    @Test
    public void TestExercise10All() {
        int i=0;


        List<WebElement> elements = driver.findElements(By.cssSelector("#box-campaigns .link[title$=Duck]"));
        String name[] = new String[elements.toArray().length];
        String href[] = new String[elements.toArray().length];
        String regPrice[] = new String[elements.toArray().length];
        String camPrice[] = new String[elements.toArray().length];
        for(WebElement element1: elements){
            name[i] = element1.findElement(By.cssSelector(".name")).getAttribute("textContent");
            href[i] = element1.getAttribute("href");
            regPrice[i] = element1.findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
            camPrice[i] = element1.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");

            //getColor
            colorRegular = element1.findElement(By.cssSelector(".regular-price"))
                    .getCssValue("color");
            colorCampaign = element1.findElement(By.cssSelector(".campaign-price"))
                    .getCssValue("color");


            //getStyle
            styleRegular = element1.findElement(By.cssSelector(".regular-price"))
                    .getCssValue("text-decoration");
            styleCampaign = element1.findElement(By.cssSelector(".campaign-price"))
                    .getCssValue("font-weight");

            //getSize
            sizeRegular = element1.findElement(By.cssSelector(".regular-price"))
                    .getCssValue("font-size");
            sizeCampaign = element1.findElement(By.cssSelector(".campaign-price"))
                    .getCssValue("font-size");         

            i++;

        }
        for (int j=0; j<i;j++) {
            //переходим по ссылке
            driver.get(href[j]);
            //Сравниваем значение названия по ссылке
            //проверяем совпадение имени
            assertTrue(name[j].contains(driver.findElement(By.cssSelector("#box-product .title")).getText()));
            //проверяем совпадение цены
            assertTrue(regPrice[j].contains(driver.findElement(By.cssSelector("#box-product .regular-price")).getText()));
            assertTrue(camPrice[j].contains(driver.findElement(By.cssSelector("#box-product .campaign-price")).getText()));

            //проверяем цвет и стиль обычной  цены
            //на странице товара
            assertTrue(isColorGray(driver.findElement(By.cssSelector("#box-product .regular-price"))
                    .getCssValue("color")));
            assertTrue(driver.findElement(By.cssSelector("#box-product .regular-price"))
                    .getCssValue("text-decoration").contains("line-through"));

            //на общей странице
            assertTrue(isColorGray(colorRegular));
            assertTrue(styleRegular.contains("line-through"));

            //проверяем цвет и стиль акционной цены
            //на странице товара
            assertTrue(isColorRed(driver.findElement(By.cssSelector("#box-product .campaign-price"))
                    .getCssValue("color")));
            assertTrue(driver.findElement(By.cssSelector("#box-product .campaign-price"))
                    .getCssValue("font-weight").contains("bold"));

            //на общей странице
            assertTrue(isColorRed(colorCampaign));
            assertTrue(styleCampaign.contains("bold"));

            //задание г (сравнение размеров)
            //сравниваем размер шрифта для основной страницы
            assertTrue(fontSize(sizeRegular)<fontSize(sizeCampaign));

            //сравниваем размер для страницы товара
            assertTrue(fontSize(driver.findElement(By.cssSelector("#box-product .regular-price"))
                    .getCssValue("font-size"))<fontSize(driver.findElement(By.cssSelector("#box-product .campaign-price"))
                    .getCssValue("font-size")));


            driver.navigate().back();


        }


    }





    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
