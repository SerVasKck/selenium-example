package ru.stqa.training.selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created  on 30.03.2017.
 */
public class NewApplication {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private GoodPage goodPage;
    private CartPage cartPage;

  //  String kilString;

  public NewApplication () {
         driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        goodPage = new GoodPage(driver);
      cartPage = new CartPage(driver);

    }


    public void startPage(){
      mainPage.startPage();
    }

    public void getGoods(int i) {
        //выбираем товар
        mainPage.getGoods(i).click();
    }

 public void addGoods(){
     goodPage.waitUntilGoodPageOpen ();
     goodPage.checkSize();
    String kilString = goodPage.waitChangeCart();
     goodPage.addToCart.click();
     goodPage.waitUntilAddToKard(kilString);
 }

    public void backOnManePage (){
         mainPage.backOnManePage ();
    }

    public void removeGoodsOutOfCart(){
        cartPage.CheckoutCart();
        cartPage.removeGoods();

    }

    public String getFinishText(){

        return cartPage.end.getText();
    }

    public void  quit() {
        driver.quit();
        driver = null;
    }

}
