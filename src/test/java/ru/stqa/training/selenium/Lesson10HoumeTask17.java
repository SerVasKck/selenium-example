package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.LongPredicate;
import java.util.logging.Level;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created  on 24.03.2017.
 */
public class Lesson10HoumeTask17 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new ChromeDriver(cap);
        wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));

    }

    @Test
    public void TestExercise17(){
        int n=5;
        //получить логи
        System.out.println( driver.manage().logs().getAvailableLogTypes());
        driver.manage().logs().get("browser").forEach(l-> System.out.println(l));

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)");
        driver.findElement(By.linkText("Subcategory")).click();

        while (n<=driver.findElements(By.cssSelector("tr.row")).toArray().length+1) {

            driver.findElement(By.cssSelector("tr.row:nth-child("+n+") a")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
            List<LogEntry> elements =  driver.manage().logs().get("browser").getAll();//проверяем логи
            assertTrue(elements.isEmpty());
            driver.navigate().back();
            wait.until(ExpectedConditions.textToBe(By.cssSelector("h1"), "Catalog"));

            n++;
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}