package ru.stqa.training.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by i.shapoval on 30.03.2017.
 */
public class NewApplication {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private GoodPage goodPage;
    private CartPage cartPage;

    String kilString;

  public NewApplication () {
         driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        goodPage = new GoodPage(driver);
      cartPage = new CartPage(driver);

    }


    public void startPage(){

      mainPage.startPage();
       /* wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));*/

    }

    public void getGoods(int i) {
        //выбираем товар
       //  List<WebElement> goods = driver.findElements(By.cssSelector(".link[title$=Duck]"));
      /*  List<WebElement> goods =  mainPage.goods();
        goods.get(i).click();//клик на товар, при этом учитывая смену места расположения товара на странице, можно всегда, например, первый кликать*/
        mainPage.getGoods(i).click();

    }

 public void addGoods(){
     goodPage.waitUntilGoodPageOpen ();
     goodPage.checkSize();
     kilString = goodPage.waitChangeCart();
     goodPage.addToCart.click();
     goodPage.waitUntilAddToKard(kilString);
 }

    public void backOnManePage (){
         mainPage.backOnManePage ();
    }


   /* public void waitUntilGoodPageOpen ()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#box-product .title")));//ожидаем, что страница товара открылась
    }*/

   /* public void checkSize() {
        if (isElementPresent(By.cssSelector("td.options [name*=Size]"))) { //проверяем, если поле выбора размера, выбираем размер
            //тут использовать
           //  goodPage.selectSize("Small"); //для выбора размера

           Select size = new Select(driver.findElement(By.cssSelector("[name^=options]")));
            size.selectByValue("Small");

        }
    }*/

  /*  public String waitChangeCart() {
        quantity = driver.findElement(By.cssSelector("span.quantity"));//находим нужный элемент
        int kil = Integer.parseInt(quantity.getText()); //запоминаем количество товара до добавления в корзину
        kil++;
        kilString = String.valueOf(kil);//меняем значение, чтобы проверить добавился ли товар
        return kilString;
    }*/
 /*  public void AddToCard() {
        driver.findElement(By.cssSelector("[name=add_cart_product]")).click();
     //   goodPage.addToCart.click();
    }*/



   /* public void waitUntilAddToKard(String kilString){
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), kilString));//ожидаем пока сменится количество в корзине
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.quantity")));//проверяем, что новый появился
    }*/
    public void removeGoodsOutOfCart(){
        cartPage.CheckoutCart();
        cartPage.removeGoods();
        //cartPage.getFinishText();

    }
/*
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

    }*/



    public String getFinishText(){

        //return driver.findElement(By.cssSelector("em")).getText();
        return cartPage.end.getText();
    }

    public void  quit() {
        driver.quit();
        driver = null;
    }

}
