package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import static java.util.Arrays.sort;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created on 06.03.2017.
 */
public class Lesson5Task9 {
    private WebDriver driver;
    private WebDriverWait wait;



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

       List<WebElement> elements = driver.findElements(By.cssSelector("tr.row td:nth-child(5)"));

        List<WebElement> list = driver.findElements(By.cssSelector("tr.row td:nth-child(5)"));
        Collections.sort(list, new Comparator<WebElement>() {
            public int compare(WebElement o1, WebElement o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });

       assertTrue( list.containsAll(elements)); //проверяем что списки идентичны
       }

       @Test
    public void TestExercise9Part1b() {
        String [] countryName = new String[10]; //для наименования страны
        int i=0;
        List<WebElement> elements2 = driver.findElements(By.cssSelector("tr.row"));

        for(WebElement element2: elements2) {

            if (!element2.findElement(By.cssSelector("td:nth-child(6)")).getText().equals("0")) {
                countryName[i] = element2.findElement(By.cssSelector("td:nth-child(5)")).getText();
                i++;

            }
        }
        while (i-1>=0)
        {
            driver.findElement(By.linkText(countryName[i-1])).click();

            List<WebElement> elements = driver.findElements(By.cssSelector("#table-zones td:nth-child(3)"));
            List<WebElement> list2 = driver.findElements(By.cssSelector("#table-zones td:nth-child(3)"));
            Collections.sort(list2, new Comparator<WebElement>() {
                public int compare(WebElement o1, WebElement o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
            assertTrue(elements.containsAll(list2)); //проверяем что списки идентичны
            driver.navigate().back();
            i--;
        }
        }

        @Test
        public void  TestExercise9Part2() {
            int i=0;
            int j;

           driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
           String [] countryName = new String[driver.findElements(By.cssSelector("tr.row")).toArray().length];

            //отбираем страны
            List<WebElement> elements2 = driver.findElements(By.cssSelector("tr.row"));
            for(WebElement element2: elements2) {

                countryName[i] = element2.findElement(By.cssSelector("td:nth-child(3)")).getText();
                i++;
            }

            while (i>=1) {

                j = 0;

                driver.findElement(By.linkText(countryName[i - 1])).click();
                String [] contentName = new String[driver.findElements(By.cssSelector("td:nth-child(3)")).toArray().length-1];
                List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(3)"));
                List<String> contentNameList = new ArrayList<String>();
                List<String> contentNameListSort = new ArrayList<String>();
                for (WebElement element : elements) {
                    String [] zoneName = new String[elements.toArray().length];

                    zoneName[j] = element.findElement(By.cssSelector("[name^=zones]")).getAttribute("value");


                    if (j > 0) {
                        try {

                            driver.findElement(By.cssSelector("[value = " + zoneName[j] + "]")).getAttribute("selected");
                            contentNameList.add(driver.findElement(By.cssSelector("[value = " + zoneName[j] + "]")).getAttribute("textContent"));
                            contentNameListSort.add(driver.findElement(By.cssSelector("[value = " + zoneName[j] + "]")).getAttribute("textContent"));

                        }/* catch (NullPointerException e) {
                        } */catch (NoSuchElementException e) {
                        } catch (InvalidSelectorException e) {
                        }
                    }
                    //перегнать в лист для сортировки и сравнения

                    j++;

                }

                   Collections.sort(contentNameListSort, new Comparator<String>() {
                    public int compare(String o1, String o2) {
                        return o1.toString().compareTo(o2.toString());
                    }
                });
                assertTrue(contentNameList.containsAll(contentNameListSort));


                driver.navigate().back();
                i--;
            }

        }



    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
