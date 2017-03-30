package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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



}
