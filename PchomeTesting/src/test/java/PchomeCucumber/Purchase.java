package PchomeCucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Purchase {
    WebDriver driver;

    @Given("初始化 Purchase 測試案列")
    public void beforeScenario() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");   // 允許遠端控制瀏覽器
        // options.addArguments("--headless");                 // 不開啟瀏覽器
        options.addArguments("--window-position=2000,0");    // 設定瀏覽器位置
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();                // 最大化瀏覽器
        // 設定 2 sec 的搜尋等待時間 (for driver.findElement)
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.get("https://24h.pchome.com.tw/");           // 進入網址內容
        WebElement element = driver.findElement(By.xpath("//a[text()='登入']"));
        element.click();
        element = driver.findElement(By.id("loginAcc"));
        element.sendKeys("matt920404@gmail.com");
        element = driver.findElement(By.id("btnKeep"));
        element.click();
        element = driver.findElement(By.id("loginPwd"));
        element.sendKeys("m2788493");
        element = driver.findElement(By.id("btnLogin"));
        element.click();
        while (true) {
            try {
                element = driver.findElement(By.xpath("//a[text()='登出']"));
                if (element.isDisplayed()) break;
            } catch (Exception e) {
                TimeUnit.SECONDS.sleep(1);
            }
        }

    }

    @Given("使用者查看購物車")
    public void userCheckCart() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//a[text()='購物車']"));
        element.click();
    }

    @And("使用者使用LINE PAY付款")
    public void userClickLinePay() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        WebElement element = driver.findElement(By.xpath("//a[text()='LINE Pay']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Then("使用者應該按下確認送出")
    public void userClickSubmit() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        WebElement element = driver.findElement(By.xpath("//a[text()='確定送出']"));
        if(element.isDisplayed()) driver.quit();
    }
}
