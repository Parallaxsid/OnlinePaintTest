package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest(){
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    void teardown(){
      driver.quit();
    }

    @Test
    void test(){
        driver.get("https://jspaint.app/");
        WebElement canvas = driver.findElement((By.xpath("//canvas[@class='main-canvas']")));

        driver.findElement(By.xpath("//div[@title='Кисть']/span")).click();

        Actions builder = new Actions(driver);
        builder.clickAndHold(canvas).moveByOffset(0, -100).
                moveByOffset(-100,0).
                moveByOffset(0,100).
                moveByOffset(100,0).release().perform();

        driver.findElement(By.xpath("//div[@class='menu-button файл-(f)-menu-button']")).click();
        driver.findElement(By.xpath("//tr[3]")).click();
        WebElement input = driver.findElement(By.xpath("//input[@type='text']"));
        input.sendKeys(Keys.BACK_SPACE + "Квадрат");
        driver.findElement(By.xpath("//button[.='Сохранить']")).click();

    }
}
