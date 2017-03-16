package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    protected void pause(int sec) {
        long end = System.currentTimeMillis() + (sec * 1000);
        while (System.currentTimeMillis() < end) {
        }
    }



    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));
    }

    @Test
    public void TestExercise9Part1a() {
        i = 0;
        while (i<3) {
            //выбираем товар
            List<WebElement> goods = driver.findElements(By.cssSelector(".link[title$=Duck]"));
            goods.get(i).click();//клик на товар, при этом учитывая смену места расположения товара на странице, можно всегда, например, первый кликать
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#box-product .title")));//ожидаем, что страница товара открылась

           if (driver.findElement(By.cssSelector("#box-product .sticker")).getAttribute("textContent").equals("Sale")) { //проверяем, если по распродаже, выбираем размер
               Select size = new Select(driver.findElement(By.cssSelector("[name^=options]")));
               size.selectByValue("Small");
           }

               //ожидаем изменения счетчика корзины
               WebElement quantity = driver.findElement(By.cssSelector("span.quantity"));//находим нужный элемент
               int kil = Integer.parseInt(quantity.getText()); //запоминаем количество товара до добавления в корзину
               kil++;
               String kilString = String.valueOf(kil);//меняем значение, чтобы проверить добавился ли товар

               driver.findElement(By.cssSelector("[name=add_cart_product]")).click(); //жмем кнопку Add To Card
               // wait.until(textToBePresentInElement(quantity,kilString));//можно и так и так
               wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), kilString));//ожидаем пока сменится количество в корзине
            // (не учтено, что товара можно бы добавить 2-3 одного вида)

               wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.quantity")));//проверяем, что новый появился
               quantity = driver.findElement(By.cssSelector("span.quantity"));

            //if (quantity.getText().equals("3")) break; //можно и так выйти из цикла, тогда сделать его бесконечным, а количество товара прописать тут
               i++;

            driver.navigate().back();//вернуться на основную страничку
            wait.until(titleIs("Online Store | My Store"));
        }

        //открыть корзину
        driver.findElement(By.linkText("Checkout »")).click();

        wait.until(ExpectedConditions.textToBe(By.cssSelector("h2.title"), "Customer Details"));//ждем появления элемента, чтобы понять что страничка загрузилась

      do {
               List<WebElement> inCheckout = driver.findElements(By.cssSelector("[name=remove_cart_item]"));//выгребаем все кнопки "Remove", чтобы по ним потом кликнуть
               length = inCheckout.toArray().length;//определяем количество оставшегося товара

               List<WebElement> items = driver.findElements(By.cssSelector("td.item"));//запоминаем состояние таблички (её элемент)

               wait.until(ExpectedConditions.visibilityOf(inCheckout.get(0)));//отслеживаем появление нужного для удаления элемента

               inCheckout.get(0).click();//удаляем элемент из корзины кликом на кнопку "Remove"
               wait.until(ExpectedConditions.stalenessOf(items.get(0)));//ожидаем пока список таблички обновится

       }
       while (length>1); //не завязываемся на предполагаемое количество товара, определяем его всегда, выполняем действия вплоть до последнего элемента
        pause(3);

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
