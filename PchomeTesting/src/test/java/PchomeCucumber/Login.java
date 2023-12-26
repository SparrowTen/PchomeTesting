package PchomeCucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Login {
    WebDriver driver;

    @Before
    public void beforeScenario() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");   // 允許遠端控制瀏覽器
        options.addArguments("--headless");                 // 不開啟瀏覽器
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();                 // 刪除瀏覽器所有 cookie
        driver.manage().window().maximize();                // 最大化瀏覽器
        // 設定 2 sec 的搜尋等待時間 (for driver.findElement)
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.get("https://24h.pchome.com.tw/");           // 進入網址內容
    }

    @Given("使用者導航至登入頁面")
    public void userGotoLoginPage() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//a[text()='登入']"));
        element.click();
    }

    @Given("使用者在電子郵件欄位中輸入有效的電子郵件地址 {string}")
    public void userEntersAnd(String email) throws InterruptedException {
        WebElement element = driver.findElement(By.id("loginAcc"));
        element.sendKeys(email);
    }

    @Given("使用者在密碼欄位中輸入有效的密碼 {string}")
    public void userEntersPass(String password) throws InterruptedException {
        WebElement element = driver.findElement(By.id("loginPwd"));
        element.sendKeys(password);
    }

    @Given("使用者點擊繼續按鈕")
    public void userClickContinue() throws InterruptedException {
        WebElement element = driver.findElement(By.id("btnKeep"));
        element.click();
    }

    @Given("使用者點擊登入按鈕")
    public void userClickLogin() throws InterruptedException {
        WebElement element = driver.findElement(By.id("btnLogin"));
        element.click();
    }

    @Then("使用者應該登入成功")
    public void userShouldBeLoggedIn() {
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='登出']")).isDisplayed());
        driver.quit();
    }
}
