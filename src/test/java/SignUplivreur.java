import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;

public class SignUplivreur extends TestBase {
    @Test
    public void SignUplivreur() throws SQLException, InterruptedException {
        LoadPage();
        // Wait for the signup to appears
        WebElement signup = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathsignup));
        signup.click();
        //Wait for until the signup form be appeared
        WebElement nom = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathnom));
        nom.sendKeys("livreur4");
        WebElement adresse = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathAdresse));
        adresse.sendKeys("Rabat");
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathEmailLivreur));
        email.sendKeys("livreur4@gmail.com");
        WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathmdp));
        pwd.sendKeys("test");

        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathsubmit));
        submit.click();

        // Assert and confirm that the signup is well done
        Assert.assertTrue(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathsuccessSignup), "Le livreur n'est pas bien inscrit!");
        if(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathsuccessSignup)){
            System.out.println("Le nouveau livreur est bien inscrit !");
        }

    }
}

