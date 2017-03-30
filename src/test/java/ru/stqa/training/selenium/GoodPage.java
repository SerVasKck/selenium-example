package ru.stqa.training.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by i.shapoval on 30.03.2017.
 */
public class GoodPage extends Page {
    String kilString;
    WebElement quantity;

    public GoodPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

    public void selectSize(String sizeDuck) {
    Select size = new Select(driver.findElement(By.cssSelector("[name^=options]")));
            size.selectByValue(sizeDuck);
}


    @FindBy(name="add_cart_product")
    public WebElement addToCart;


    public void waitUntilGoodPageOpen ()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#box-product .title")));//ожидаем, что страница товара открылась
    }

    public void checkSize() {
        if (isElementPresent(By.cssSelector("td.options [name*=Size]"))) { //проверяем, если поле выбора размера, выбираем размер
            //тут использовать
            //  goodPage.selectSize("Small"); //для выбора размера

           /* Select size = new Select(driver.findElement(By.cssSelector("[name^=options]")));
            size.selectByValue("Small");*/
            selectSize("Small");

        }
    }

    public String waitChangeCart() {
        quantity = driver.findElement(By.cssSelector("span.quantity"));//находим нужный элемент
        int kil = Integer.parseInt(quantity.getText()); //запоминаем количество товара до добавления в корзину
        kil++;
        kilString = String.valueOf(kil);//меняем значение, чтобы проверить добавился ли товар
        return kilString;
    }
    public void waitUntilAddToKard(String kilString){
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), kilString));//ожидаем пока сменится количество в корзине
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.quantity")));//проверяем, что новый появился
    }


}
