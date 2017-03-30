package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created  on 29.03.2017.
 */
public class Lesson11HoumeTask19 extends TestBase{
   /* int i = 0;*/


    @Test
    public void TestExercise13() {
      int  i = 0;
        app.startPage();

        while (i<8) {

            //используем метод Выбор товара
            app.getGoods(i);

          /*  //ждем, пока страница товара откроется
            app.waitUntilGoodPageOpen ();*/

          /*  //используем метод проверки наличия выбора размера
            app.checkSize();

            //ожидаем изменения счетчика корзины
            app.waitChangeCart();

          //жмем кнопку Add To Card
            app.AddToCard();*/
          app.addGoods(); //(заменила )

            //ожидание смены количества товара
           // app.waitUntilAddToKard(app.kilString);

            i++;

            app.backOnManePage();


        }

       /* //открыть корзину
        app.CheckoutCart();
        //удаляем товары
        app.removeGoods();*/
        app.removeGoodsOutOfCart();

       // System.out.println(app.getFinishText());
        assertTrue(app.getFinishText().contains("There are no items in your cart."));
    }



}
