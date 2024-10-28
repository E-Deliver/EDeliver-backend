import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LivreurProcess extends Loginlivreur{
    SoftAssert softAssert = new SoftAssert();
@Test(priority=1)
    public void livreurCommandes() throws InterruptedException {
        login_Livreur();
        WebElement commande=driver.findElement(Project_Xpath.xpathcommandeLivreur);
        commande.click();
        softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlLivreurCmd),"Un probléme persiste lors de consultation des commandes assignés au livreur!");
        if (ProjectFunctions.VerifyUrl(driver,Parameters.UrlLivreurCmd)){
            System.out.println("Test Passed: les commandes sont consultables par le client correctement!");
        }
        //Accept some of assigned commands
        if(ProjectFunctions.VerifyElements(driver,Project_Xpath.Xpath_confirmerLivraison)==true){
            //WebElement Confirmer=driver.findElement(Project_Xpath.Xpath_confirmerLivraison);
            //Confirmer.click();
            WebElement confirmer = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.Xpath_confirmerLivraison));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmer);
            softAssert.assertTrue(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathPopUpConfirmLivraison),"Le pop up de demander au livreur s'il est sûr de confirmer la livraison ne s'affiche pas!");
            if(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathPopUpConfirmLivraison)){
                System.out.println("Le pop up de demander au livreur s'il est sûr de confirmer la livraison s'affiche correctement");
            }
            //Annuler la livraison
            WebElement annuler=wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathAnnulerLivraison));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", annuler);
            System.out.println("La livraison est annulée avec succée par le livreur");
            //Accepter la livraison
            WebElement confirmerNouveau = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.Xpath_confirmerLivraison));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmer);
            Thread.sleep(2000);
            WebElement acceptLivraison=wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.Xpath_AccepterLivraison));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", acceptLivraison);
            softAssert.assertTrue(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathsuccessacceptLivraison),"Un probléme persiste lors de l'acceptation de livraison par ce livreur!");
            if(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathsuccessacceptLivraison)){
                System.out.println("La livraison est accéptée par le livreur correctement!");
            }
            Thread.sleep(1000);
            WebElement OKLiv= driver.findElement(Project_Xpath.xpathokafteracceptlivraison);
            OKLiv.click();
        }
        //
        softAssert.assertTrue(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathListecmdLivreur),"Un probléme persiste lors d'affichage des commandes assignés à ce livreur!");
        if(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathListecmdLivreur)){
            System.out.println("Test Passed: La liste des commandes assignés s'affiche correctement");
        }
}
@Test(priority=2)
    public void livreurHistorique() throws InterruptedException {
    WebElement Historique= driver.findElement(Project_Xpath.xpathHistoriqueLivreur);
    Historique.click();
    softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlLivreurHistorique),"Un probléme persiste lors de consultation d'historique de livreur!");
    if (ProjectFunctions.VerifyUrl(driver,Parameters.UrlLivreurHistorique)){
        System.out.println("Test Passed: l'historique des commandes de ce livreur est affiché correctement!");
    }
    softAssert.assertTrue(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathListeHistoriqueLivreur),"Un probléme persiste lors d'affichage des commandes assignés à ce livreur!");
    if(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathListeHistoriqueLivreur)){
        System.out.println("Test Passed: La liste des commandes livrés est affichée correctement comme historique");
    }
    Thread.sleep(1000);
}

/*@Test(priority=3)
public void CarteConsultation(){
    WebElement Cart= driver.findElement(Project_Xpath.xpathCartLivreur);
    Cart.click();
    softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlLivreurCart));
    if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlLivreurCart)){
        System.out.println("Test Passed: la carte de localisation des commandes de ce livreur est correctement consultable");
    }
}*/
/*@Test(priority=3)
    public void EditProfileLivreur(){

}*/

@Test(priority=4)
    public void SeDeconnecter(){
    WebElement Logout=driver.findElement(Project_Xpath.logoutlivreur);
    Logout.click();
    softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage),"Ce livreur n'a pas pu se déconnecter");
    if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage)){
        System.out.println("Test Passed: le livreur se déconnecte correctement");
    }
}


}
