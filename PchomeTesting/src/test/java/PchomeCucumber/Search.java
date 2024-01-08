package PchomeCucumber;

import io.cucumber.java.Before;
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

public class Search {
    WebDriver driver;

    @Given("初始化 Search 測試案列")
    public void initScenario() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");   // 允許遠端控制瀏覽器
        // options.addArguments("--headless");                 // 不開啟瀏覽器
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();                 // 刪除瀏覽器所有 cookie
        driver.manage().window().maximize();                // 最大化瀏覽器
        // 設定 2 sec 的搜尋等待時間 (for driver.findElement)
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.get("https://24h.pchome.com.tw/");           // 進入網址內容
    }

    @Given("使用者在搜尋欄位中輸入 {string}")
    public void userEnters(String keyword) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//input[@class='l-header__siteSearchInput']"));
        element.sendKeys(keyword);
    }

    @When("使用者點擊搜尋按鈕")
    public void userClickSearch() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//button[@class='btn btn--sm gtmClickV2']"));
        element.click();
    }

    @Then("使用者應該看到搜尋結果")
    public void userShouldSeeResult() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//div[@id='layoutBread']"));
        assert element.isDisplayed();
        driver.quit();
    }

    // 回到頁首
    @When("使用者在頁面底部")
    public void userScrollToBottom() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @Given("使用者點擊回到頁首按鈕")
    public void userClickBackToTop() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//span[text()='Top']"));
        element.click();
    }

    @Given("使用者應該看到頁面最上方")
    public void userShouldSeeTop() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        Long value = (Long) executor.executeScript("return window.pageYOffset;");
        assert value == 0;
        driver.quit();
    }

    // 限時瘋搶
    @When("使用者查看限時瘋搶")
    public void userSeeFlashSale() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//h3[text()='限時瘋搶']"));
        assert  element.isDisplayed();
    }

    @Then("使用者查看計時器倒數")
    public void userSeeCountdown() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//div[text()='距離結束']"));
        assert element.isDisplayed();
    }

    @Given("使用者點擊0800")
    public void userClick0800() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//span[text()='08:00 現正瘋搶']"));
        element.click();
    }

    @Given("使用者點擊1200")
    public void userClick1200() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//span[text()='12:00 準時開搶']"));
        element.click();
        driver.quit();
    }
}
