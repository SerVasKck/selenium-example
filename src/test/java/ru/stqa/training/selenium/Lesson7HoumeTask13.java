package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created  on 15.03.2017.
 */
public class Lesson7HoumeTask13 {

    private WebDriver driver;
    private WebDriverWait wait;
    int i = 0;
    int length;
    String kilString;
    WebElement quantity;

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

   private void getGoods(int i) {
       //выбираем товар
       List<WebElement> goods = driver.findElements(By.cssSelector(".link[title$=Duck]"));
       goods.get(i).click();//клик на товар, при этом учитывая смену места расположения товара на странице, можно всегда, например, первый кликать
       wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#box-product .title")));//ожидаем, что страница товара открылась
   }
    private void checkSize() {
        if (isElementPresent(By.cssSelector("td.options [name*=Size]"))) { //проверяем, если по распродаже, выбираем размер
            Select size = new Select(driver.findElement(By.cssSelector("[name^=options]")));
            size.selectByValue("Small");

        }
    }

    private String waitChangeCart() {
        quantity = driver.findElement(By.cssSelector("span.quantity"));//находим нужный элемент
        int kil = Integer.parseInt(quantity.getText()); //запоминаем количество товара до добавления в корзину
        kil++;
        kilString = String.valueOf(kil);//меняем значение, чтобы проверить добавился ли товар
        return kilString;
    }
    private void AddToCard() {
        driver.findElement(By.cssSelector("[name=add_cart_product]")).click();
    }

    private void  CheckoutCart(){
        driver.findElement(By.linkText("Checkout »")).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h2.title"), "Customer Details"));//ждем появления элемента, чтобы понять что страничка загрузилась
    }

    private void removeGoods(){
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

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));
    }

    @Test
    public void TestExercise13() {
        i = 0;
        while (i<8) {
          /*  //выбираем товар
            List<WebElement> goods = driver.findElements(By.cssSelector(".link[title$=Duck]"));
            goods.get(i).click();//клик на товар, при этом учитывая смену места расположения товара на странице, можно всегда, например, первый кликать
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#box-product .title")));//ожидаем, что страница товара открылась*/

            //используем метод Выбор товара
            getGoods(i);

            //используем метод проверки наличия выбора размера
            checkSize();

        /*   if (isElementPresent(By.cssSelector("td.options [name*=Size]"))) { //проверяем, если по распродаже, выбираем размер
               Select size = new Select(driver.findElement(By.cssSelector("[name^=options]")));
               size.selectByValue("Small");

           }*/

               //ожидаем изменения счетчика корзины
            waitChangeCart();
            /*   WebElement quantity = driver.findElement(By.cssSelector("span.quantity"));//находим нужный элемент
               int kil = Integer.parseInt(quantity.getText()); //запоминаем количество товара до добавления в корзину
               kil++;
               String kilString = String.valueOf(kil);//меняем значение, чтобы проверить добавился ли товар*/

                AddToCard();
              /* driver.findElement(By.cssSelector("[name=add_cart_product]")).click(); //жмем кнопку Add To Card*/

               wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), kilString));//ожидаем пока сменится количество в корзине

               wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.quantity")));//проверяем, что новый появился
              // quantity = driver.findElement(By.cssSelector("span.quantity")); //скорее всего ненужно

            //if (quantity.getText().equals("3")) break; //можно и так выйти из цикла, тогда сделать его бесконечным, а количество товара прописать тут
               i++;

            driver.navigate().back();//вернуться на основную страничку
            wait.until(titleIs("Online Store | My Store"));
        }

        //открыть корзину
        CheckoutCart();
       /* driver.findElement(By.linkText("Checkout »")).click();

        wait.until(ExpectedConditions.textToBe(By.cssSelector("h2.title"), "Customer Details"));//ждем появления элемента, чтобы понять что страничка загрузилась
*/
       removeGoods();
   /*   do {
               List<WebElement> inCheckout = driver.findElements(By.cssSelector("[name=remove_cart_item]"));//выгребаем все кнопки "Remove", чтобы по ним потом кликнуть
               length = inCheckout.toArray().length;//определяем количество оставшегося товара

               List<WebElement> items = driver.findElements(By.cssSelector("td.item"));//запоминаем состояние таблички (её элемент)

               wait.until(ExpectedConditions.visibilityOf(inCheckout.get(0)));//отслеживаем появление нужного для удаления элемента

               inCheckout.get(0).click();//удаляем элемент из корзины кликом на кнопку "Remove"
               wait.until(ExpectedConditions.stalenessOf(items.get(0)));//ожидаем пока список таблички обновится

       }
       while (length>1); //не завязываемся на предполагаемое количество товара, определяем его всегда, выполняем действия вплоть до последнего элемента
        pause(3);*/

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
