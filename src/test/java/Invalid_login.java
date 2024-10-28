import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Invalid_login extends TestBase{
    @Test
    public void invalid_login(){
        LoadPage();
        driver.findElement(Project_Xpath.xpathLogin).click();
        // Wait for the email input field to be present and visible
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathEmail));
        email.sendKeys("adm@gmail.com");
        WebElement pwd = driver.findElement(Project_Xpath.xpathPassword);
        pwd.sendKeys("test");
        WebElement connect = driver.findElement(Project_Xpath.xpathConnect);
        connect.click();

        // Assert and confirm that the page can't navigates to the expected URL
        //wait.until(ExpectedConditions.urlToBe(Parameters.UrlDashAdminPage));
        Assert.assertFalse(ProjectFunctions.VerifyUrl(driver, Parameters.UrlDashAdminPage), "Login Page can't be appeared!");
        if (!ProjectFunctions.VerifyUrl(driver, Parameters.UrlDashAdminPage)) {
            System.out.println("Test Passed: Admin Page can't displayed with an invalid email or password");
        }
    }
}
