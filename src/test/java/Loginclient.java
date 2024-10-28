import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Loginclient extends TestBase{
    //@Test
    public void login_Client() {
        LoadPage();
        driver.findElement(Project_Xpath.xpathLogin).click();
        // Wait for the email input field to be present and visible
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathEmail));
        email.sendKeys("clientTest@gmail.com");
        WebElement pwd = driver.findElement(Project_Xpath.xpathPassword);
        pwd.sendKeys("test");
        WebElement connect = driver.findElement(Project_Xpath.xpathConnect);
        connect.click();
        driver.findElement(Project_Xpath.xpathConnect).click();
        //driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div/div/div/form/div[4]/button")).click();
        // Assert and confirm that the page navigates to the expected URL
        wait.until(ExpectedConditions.urlToBe(Parameters.UrlDashClientPage));
        Assert.assertTrue(ProjectFunctions.VerifyUrl(driver, Parameters.UrlDashClientPage), "Login Page can't be appeared!");
        if (ProjectFunctions.VerifyUrl(driver, Parameters.UrlDashClientPage)) {
            System.out.println("Test Passed: Client Page is displayed");
        }
    }
}
