package PchomeCucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Login {
    WebDriver driver;

    @Given("初始化 Login 測試案列")
    public void initScenario() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");   // 允許遠端控制瀏覽器
        // options.addArguments("--headless");                 // 不開啟瀏覽器
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();                 // 刪除瀏覽器所有 cookie
        driver.manage().window().maximize();                // 最大化瀏覽器
        // 設定 2 sec 的搜尋等待時間 (for driver.findElement)
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.get("https://24h.pchome.com.tw/");           // 進入網址內容
    }

    @Given("使用者導航至登入頁面")
    public void userGotoLoginPage() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//a[text()='登入']"));
        element.click();
    }

    @When("使用者在電子郵件欄位中輸入有效的電子郵件地址 {string}")
    public void userEntersAnd(String email) throws InterruptedException {
        WebElement element = driver.findElement(By.id("loginAcc"));
        element.sendKeys(email);
    }

    @When("使用者在密碼欄位中輸入有效的密碼 {string}")
    public void userEntersPass(String password) throws InterruptedException {
        WebElement element = driver.findElement(By.id("loginPwd"));
        element.sendKeys(password);
    }

    @And("使用者點擊繼續按鈕")
    public void userClickContinue() throws InterruptedException {
        WebElement element = driver.findElement(By.id("btnKeep"));
        element.click();
    }

    @And("使用者點擊登入按鈕")
    public void userClickLogin() throws InterruptedException {
        WebElement element = driver.findElement(By.id("btnLogin"));
        element.click();
    }

    @Then("使用者應該登入成功")
    public void userShouldBeLoggedIn() throws InterruptedException {
        /*
        // 檢查是否有 google reCAPTCHA
        boolean hasGoogleReCAPTCHA = false;
        try {
            WebElement element = driver.findElement(By.xpath("//input[@value='authRecaptcha']"));
            element.click();
            WebElement element2 = driver.findElement(By.xpath("//button[@id='btnChoice']"));
            element2.click();
            hasGoogleReCAPTCHA = true;
        } catch (Exception e) {
            System.out.println("No google reCAPTCHA");
        }
        // 手動驗證
        if (hasGoogleReCAPTCHA) {
            while (true) {
                try {
                    WebElement element3 = driver.findElement(By.xpath("//input[@recaptcha-token!=' ']"));
                    if (element3.isDisplayed()) break;
                } catch (Exception e) {
                    TimeUnit.SECONDS.sleep(1);
                }
            }
            WebElement element4 = driver.findElement(By.xpath("//button[@id='btnLogin']"));
            element4.click();
        }
        */
        // 檢查是否有登出按鈕
        while (true) {
            try {
                WebElement element = driver.findElement(By.xpath("//a[text()='登出']"));
                if (element.isDisplayed()) break;
            } catch (Exception e) {
                TimeUnit.SECONDS.sleep(1);
            }
        }
        driver.quit();
    }

    @Then("使用者應該登入失敗")
    public void userShouldBeLoggedNotIn() throws InterruptedException {
        // 檢查是否出現錯誤訊息
        WebElement element = driver.findElement(By.xpath("//div[text()='請輸入密碼']"));
        if (element.isDisplayed()) {
            assert true;
        } else {
            assert false;
        }
        driver.quit();
    }
}
