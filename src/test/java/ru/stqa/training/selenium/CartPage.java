package ru.stqa.training.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created  on 30.03.2017.
 */
public class CartPage extends Page{
    String kilString;
    WebElement quantity;

    public CartPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void waitUntilAddToKard(String kilString){
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), kilString));//ожидаем пока сменится количество в корзине
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.quantity")));//проверяем, что новый появился
    }
    public void  CheckoutCart(){
        driver.findElement(By.linkText("Checkout »")).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h2.title"), "Customer Details"));//ждем появления элемента, чтобы понять что страничка загрузилась
    }

    public void removeGoods(){
        int length;
        do {
            List<WebElement> inCheckout = driver.findElements(By.cssSelector("[name=remove_cart_item]"));//выгребаем все кнопки "Remove", чтобы по ним потом кликнуть
            length = inCheckout.toArray().length;//определяем количество оставшегося товара

            List<WebElement> items = driver.findElements(By.cssSelector("td.item"));//запоминаем состояние таблички (её элемент)

            wait.until(ExpectedConditions.visibilityOf(inCheckout.get(0)));//отслеживаем появление нужного для удаления элемента

            inCheckout.get(0).click();//удаляем элемент из корзины кликом на кнопку "Remove"
            wait.until(ExpectedConditions.stalenessOf(items.get(0)));//ожидаем пока список таблички обновится

        }
        while (length>1); //не завязываемся на предполагаемое количество товара, определяем его всегда, выполняем действия вплоть до последнего элемента

    }
    @FindBy(tagName="em")
    public WebElement end;

}
