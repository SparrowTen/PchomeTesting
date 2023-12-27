package PchomeCucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Search {
    WebDriver driver;
    @Before
    public void beforeScenario() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");   // 允許遠端控制瀏覽器
        options.addArguments("--headless");                 // 不開啟實體瀏覽器背景執行
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();                 // 刪除瀏覽器所有 cookie
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
    }
}