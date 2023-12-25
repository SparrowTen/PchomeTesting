package PchomeCucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
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
        WebElement element = driver.findElement(By.xpath("//a[@title='登入']"));
        element.click();
    }
}
