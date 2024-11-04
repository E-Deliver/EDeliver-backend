import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ClientProcess extends Loginclient{
    SoftAssert softAssert = new SoftAssert();
    @Test(priority = 1)
    public void ClientCommandes() throws InterruptedException {
        login_Client();
        WebElement commandes=driver.findElement(Project_Xpath.xpathcommandes);
        commandes.click();
        softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlClientCommande),"La page de commande de ce clients n'est pas consultable correctement!");
        if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlClientCommande)){
            System.out.println("Test Passed: la page des commandes de ce client est bien consultable");
        }
        softAssert.assertTrue(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathcommandesclient),"La liste des commandes ne s'affiche pas correctement!");
        if(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathcommandesclient)){
            System.out.println("Test Passed: La liste des commandes de ce client s'affiche correctement");
        }
        Thread.sleep(1000);
    }

    @Test(priority = 2)
    public void HistoriqueClient() throws InterruptedException {
        WebElement HistoriqueClient = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.Xpath_historiqueclient));
        HistoriqueClient.click();
        softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlClientHistorique),"La page d'historique de ce clients n'est pas consultable correctement!");
        if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlClientHistorique)){
            System.out.println("Test Passed: la page d'historique de ce client est bien consultable");
        }
        softAssert.assertTrue(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathHistoriqueclient),"La liste des commandes ne s'affiche pas correctement!");
        if(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathHistoriqueclient)){
            System.out.println("Test Passed: La liste d'historique de ce client s'affiche correctement");
        }
        Thread.sleep(1000);
    }
    @Test(priority=3)
    public void editProfile() throws InterruptedException {
        WebElement profile=driver.findElement(Project_Xpath.xpathEditProfileClient);
        profile.click();
        //Page is well displayed
        softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlEditProfileClientPage),"La page d'edit profile pour client ne s'affiche pas correctement!");
        if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlEditProfileClientPage)){
            System.out.println("Test passed: La page d'edit profile pour le client est affichée correctement");
        }
        //Coté notification
        WebElement notifications = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.AppearingOfNotification));

        //WebElement notifications= driver.findElement(Project_Xpath.AppearingOfNotification);
        softAssert.assertTrue(notifications.isDisplayed(),"la liste des notifications n'apparait pas correctemet!");
        if(notifications.isDisplayed()){
            System.out.println("Test Passed: La liste des notifications s'affiche correctement");
        }
        Thread.sleep(1000);
        //Coté edit

        WebElement edit= driver.findElement(Project_Xpath.XpathEditProfile);
        edit.click();
        Thread.sleep(1000);
        //Edit password
        WebElement passwordinput =driver.findElement(Project_Xpath.xpathPasswordInput);
        passwordinput.clear();
        passwordinput.sendKeys("$2a$10$w3PL6XU0VqJjYjrR5RUxhuDcZ3G0aYQwcGlPf5eKz4pBrKHd1VSS2");
        System.out.println("The new Password was entered!");
        //Edit client's name
        WebElement nameUpdate= driver.findElement(Project_Xpath.xpathNameinput);
        nameUpdate.clear();
        nameUpdate.sendKeys("ClientTest");
        System.out.println("The new client's name was entered!");

        //Save updates
        WebElement SAVE = driver.findElement(Project_Xpath.XpathSaveUpdateProfile);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SAVE);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SAVE);

        // Check if update was successful
        softAssert.assertTrue(ProjectFunctions.VerifyElements(driver, Project_Xpath.XpathSuccessUpdateProfile), "La modification n'est pas faite correctement!");
        if (ProjectFunctions.VerifyElements(driver, Project_Xpath.XpathSuccessUpdateProfile)) {
            System.out.println("Test Passed: la modification est bien faite de l'adresse et donc les modifications travaille correctement!");
        }
        // Click on OK button
        WebElement OK_Modif = driver.findElement(Project_Xpath.xpathOKUpdateProfil);
        OK_Modif.click();
        Thread.sleep(500);
    }
    //@Test(priority = 4)
    public void SeDeconnecter(){
        //Cick on dashboard before logout
        driver.findElement(By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[1]")).click();
        WebElement Logout=driver.findElement(Project_Xpath.logoutClient);
        Logout.click();
        Assert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage),"Le client n'a pas pu se déconnecter");
        if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage)){
            System.out.println("Test Passed: le client se déconnecte correctement");
        }
    }
}
