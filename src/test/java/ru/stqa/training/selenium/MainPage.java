package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by i.shapoval on 30.03.2017.
 */
public class MainPage extends Page{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> goods (){
        return driver.findElements(By.cssSelector(".link[title$=Duck]"));
    }

    public WebElement getGoods(int i){
        List<WebElement> goods =  goods();
        return goods.get(i);
    }
    public void startPage (){
      //  driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));
    }

    public void backOnManePage (){
        driver.navigate().back();//вернуться на основную страничку
        wait.until(titleIs("Online Store | My Store"));
    }




}
