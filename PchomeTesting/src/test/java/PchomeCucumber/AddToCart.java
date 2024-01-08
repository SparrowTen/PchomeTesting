package PchomeCucumber;

import com.google.common.collect.Lists;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class AddToCart {
    WebDriver driver;

    @Given("初始化 AddToCart 測試案列")
    public void initScenario() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");   // 允許遠端控制瀏覽器
        options.addArguments("--headless");                 // 不開啟瀏覽器
        options.addArguments("--window-position=2000,0");    // 設定瀏覽器位置
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();                // 最大化瀏覽器
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000)); // 設定 2 sec 的搜尋等待時間 (for driver.findElement)
        driver.get("https://24h.pchome.com.tw/");           // 進入網址內容
        WebElement element = driver.findElement(By.xpath("//input[@class='l-header__siteSearchInput']"));
        element.sendKeys("apple");
        element = driver.findElement(By.xpath("//button[@class='btn btn--sm gtmClickV2']"));
        element.click();
        element = driver.findElement(By.xpath("//div[@id='layoutBread']"));
    }

    @Given("使用者找到商品 {string}")
    public void userFoundProduct(String keyword) throws InterruptedException {
        keyword = "//a[text()='" + keyword + "']";
        WebElement element = driver.findElement(By.xpath(keyword));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    @When("使用者點擊連結到商品頁面 {string}")
    public void userEntersProductPage(String keyword) throws InterruptedException {
        keyword = "//a[text()='" + keyword + "']";
        WebElement element = driver.findElement(By.xpath(keyword));
        element.click();
        List<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
    }

    @And("使用者選擇規格")
    public void userChooseSpec() throws InterruptedException {
        List<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        WebElement element = driver.findElement(By.xpath("//div[text()='請選擇規格']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        element.click();
        element = driver.findElement(By.xpath("//ul[@class='c-dropdown__menu']/li[1]"));
        element.click();
    }

    @And("使用者點擊加入購物車")
    public void userAddToCart() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//span[text()='加入購物車']"));
        element.click();
    }

    @Then("使用者應該看到成功加入購物車")
    public void productShouldInCart() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//div[text()='商品已加入購物車！']"));
        if(element.isDisplayed()) {
            driver.quit();
        }
    }
}
