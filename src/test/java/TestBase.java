import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class TestBase {
    public static WebDriver driver;
    public static WebDriverWait wait,longWait;
    public static Actions action;

    @BeforeClass
    public void SetUp() {
        driver=new ChromeDriver();
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        action=new Actions(driver);
        driver.manage().window().maximize();
    }
    public void LoadPage() {
        if (driver != null) {
            driver.get("http://localhost:4200/");
            System.out.println("Client page is displayed");
        } else {
            throw new IllegalStateException("Driver is not initialized.");
        }
    }

    //@AfterClass
    public void tearDown() {
        if(driver!=null) {
            driver.quit();
        }
    }
}

