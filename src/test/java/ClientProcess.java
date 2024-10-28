import org.openqa.selenium.WebElement;
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

    @Test(priority = 4)
    public void SeDeconnecter(){
        WebElement Logout=driver.findElement(Project_Xpath.logoutClient);
        Logout.click();
        Assert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage),"Le client n'a pas pu se déconnecter");
        if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage)){
            System.out.println("Test Passed: le client se déconnecte correctement");
        }
    }
}
