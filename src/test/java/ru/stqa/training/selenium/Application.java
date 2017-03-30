package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.pages.MainPage;
import ru.stqa.training.selenium.pages.GoodPage;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created  on 30.03.2017.
 */
public class Application {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage manePage;
    private GoodPage goodPage;
    int i = 0;
   // int length;
    String kilString;
    WebElement quantity;

   /* public Application () {

        manePage = new MainPage(driver);
        goodPage = new MainPage(driver);

    }*/


    protected void pause(int sec) {
        long end = System.currentTimeMillis() + (sec * 1000);
        while (System.currentTimeMillis() < end) {
        }
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return true;
        } catch (NoSuchElementException e) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return false;
        }
        catch (ElementNotVisibleException e) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return false;
        }
    }

    protected boolean isElementNotPresent(By by) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            driver.findElement(by);
            // wait.until((WebDriver d) -> d.findElement(by)); //поддерживается java8 Selenium3
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return false;
        } catch (NoSuchElementException e) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return true;
        }
        catch (ElementNotVisibleException e) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return true;
        }
    }

    public void startPage(){

            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 5);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("http://localhost/litecart");
            wait.until(titleIs("Online Store | My Store"));

    }

    public void getGoods(int i) {
        //выбираем товар
       // List<WebElement> goods = driver.findElements(By.cssSelector(".link[title$=Duck]"));
        List<WebElement> goods =   driver.findElements(By.cssSelector(".link[title$=Duck]"));
        goods.get(i).click();//клик на товар, при этом учитывая смену места расположения товара на странице, можно всегда, например, первый кликать

    }

    public void waitUntilGoodPageOpen ()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#box-product .title")));//ожидаем, что страница товара открылась
    }

    public void checkSize() {
        if (isElementPresent(By.cssSelector("td.options [name*=Size]"))) { //проверяем, если поле выбора размера, выбираем размер
           //тут использовать
            // goodPage.selectSize("Small"); //для выбора размера

            Select size = new Select(driver.findElement(By.cssSelector("[name^=options]")));
            size.selectByValue("Small");

        }
    }

    public String waitChangeCart() {
        quantity = driver.findElement(By.cssSelector("span.quantity"));//находим нужный элемент
        int kil = Integer.parseInt(quantity.getText()); //запоминаем количество товара до добавления в корзину
        kil++;
        kilString = String.valueOf(kil);//меняем значение, чтобы проверить добавился ли товар
        return kilString;
    }
    public void AddToCard() {
        driver.findElement(By.cssSelector("[name=add_cart_product]")).click();
    }

    //goodPage.addToCart.click();
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
    public void backOnManePage (){
        driver.navigate().back();//вернуться на основную страничку
        wait.until(titleIs("Online Store | My Store"));
    }

    public String getFinishText(){

        return driver.findElement(By.cssSelector("em")).getText();
    }

    public void  quit() {
        driver.quit();
        driver = null;
    }
  /*  @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));
    }*/

  /*  @Test
    public void TestExercise13() {
        i = 0;
        while (i<8) {

            //используем метод Выбор товара
            getGoods(i);

            //используем метод проверки наличия выбора размера
            checkSize();

            //ожидаем изменения счетчика корзины
            waitChangeCart();

            //жмем кнопку Add To Card
            AddToCard();

            //ожидание смены количества товара

            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), kilString));//ожидаем пока сменится количество в корзине
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.quantity")));//проверяем, что новый появился

            i++;

            driver.navigate().back();//вернуться на основную страничку
            wait.until(titleIs("Online Store | My Store"));
        }

        //открыть корзину
        CheckoutCart();
        //удаляем товары
        removeGoods();


    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }*/
}

