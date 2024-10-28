import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

public class Loginlivreur extends TestBase{
    //@Test
    public void login_Livreur() throws InterruptedException {
        LoadPage();
        driver.findElement(Project_Xpath.xpathLogin).click();
        // Wait for the email input field to be present and visible
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathEmail));
        email.sendKeys("livreurFast@gmail.com");//imanliv account
        WebElement pwd=wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathPassword));
        //WebElement pwd = driver.findElement(Project_Xpath.xpathPassword);
        pwd.sendKeys("test");
        driver.findElement(Project_Xpath.xpathConnect).click();
        wait.until(ExpectedConditions.urlToBe(Parameters.UrlDashLivreurPage));

        //Assert and confirm that the page navigates to the expected URL
        Assert.assertTrue(ProjectFunctions.VerifyUrl(driver, Parameters.UrlDashLivreurPage), "Login Page can't be appeared!");
        if (ProjectFunctions.VerifyUrl(driver, Parameters.UrlDashLivreurPage)) {
            System.out.println("Test Passed: Livreur Page is displayed");
        }
        //driver.findElement(By.xpath("/html/body/app-root/app-login/section/div/div/div/div/form/div[4]/button")).click();
        //WebElement connect = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathConnect));
        //WebElement connect = driver.findElement(Project_Xpath.xpathConnect);
        //action.moveToElement(connect).click().perform();
        //connect.click();
        /* Assert and confirm that the page navigates to the expected URL
        wait.until(ExpectedConditions.urlToBe(Parameters.UrlDashLivreurPage));
        Assert.assertTrue(ProjectFunctions.VerifyUrl(driver, Parameters.UrlDashLivreurPage), "Login Page can't be appeared!");
        if (ProjectFunctions.VerifyUrl(driver, Parameters.UrlDashLivreurPage)) {
            System.out.println("Test Passed: Livreur Page is displayed");
        }*/
    }

}
