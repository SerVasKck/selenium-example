package ru.stqa.training.selenium;


import org.junit.Test;
import static junit.framework.TestCase.assertTrue;


/**
 * Created  on 29.03.2017.
 */
public class Lesson11HoumeTask19 extends TestBase{

    @Test
    public void TestExercise19() {
      int  i = 0;
        app.startPage();

        while (i<8) {
            app.getGoods(i); //выбрать товар для добавления
            app.addGoods(); //добавление в корзину из карточки товара
            i++;
            app.backOnManePage();

        }
        app.removeGoodsOutOfCart();//удаление всего товара из корзины

        assertTrue(app.getFinishText().contains("There are no items in your cart."));
    }



}
