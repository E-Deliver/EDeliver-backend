import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminCommande extends Loginadmin{
    SoftAssert softAssert = new SoftAssert();

    @Test(priority=1)
    public void Gestion_Cmd_Admin() throws InterruptedException {
        loginAdmin();
        //Clicking on Commandes button
        WebElement commandes=wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathcmdadmin));
        commandes.click();
        Thread.sleep(1000);
        softAssert.assertTrue(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathSuccesCmdClick));
        if (ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathSuccesCmdClick)) {
            System.out.println("Test Passed: Liste des commandes est affichée correctement");
        }
        //Clicking on choisir livreur button

    }

@Test(priority=2)
    public void AssignerLivreur() throws InterruptedException {
        WebElement choisirLivreur = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathchoisirLivreur));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", choisirLivreur);

        softAssert.assertTrue(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathPopUpAssigner));
        if (ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathPopUpAssigner)) {
            System.out.println("Test Passed: Un PopUp de choix de livreur est affiché correctement");
        }

        //Selection d'un livreur
        WebElement selectLivreur = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathSelectLivreur));
        selectLivreur.click();
        System.out.println("Le livreur est sélectionné!");

        //Assigner click button
        WebElement assignerLivreur = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathassignerLivreur));
        assignerLivreur.click();
        softAssert.assertTrue(ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathSuccessAssigner),"Probléme lors d'assignation de livreur à cette commande");
        if (ProjectFunctions.VerifyElements(driver,Project_Xpath.xpathSuccessAssigner)) {
            System.out.println("Test Passed: Un Livreur disponible est bien assigné au commande!");
        }
        //Click on ok
    Thread.sleep(1000);
    WebElement OK= wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpath_OK_Assignation));
        //WebElement OK=driver.findElement(Project_Xpath.xpath_OK_Assignation);
        OK.click();
       // softAssert.assertAll();
}
@Test(priority=4)
    public void ConsulterLivreur(){
        //Click on Livreur button in the side-bar
        WebElement livreur=driver.findElement(Project_Xpath.xpathLivreur);
        livreur.click();
        softAssert.assertTrue(ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathListeLivreur),"Un probléme existe lors de consultation de liste des livreurs par l'admin!");
        if(ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathListeLivreur)){
            System.out.println("Test Passed: La liste des livreurs s'affiche correctement!");
        }
}
/*@Test(priority=3)
    public void ChangeStatus(){
    // Verifying status change to "En cours"
    WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathStatusElement));
    String updatedStatus = statusElement.getText();
    Assert.assertEquals(updatedStatus, "En cours", "Le statut de la commande n'a pas changé correctement.");
    System.out.println("Test Passed: Le statut de la commande a changé à 'En cours' après l'assignation du livreur.");
    }*/
@Test(priority=3)
public void ConsulterHistoriqueAdmin(){
    //Click on Livreur button in the side-bar
    WebElement historique=driver.findElement(Project_Xpath.xpathHistorique);
    historique.click();
    softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlAdminHistorique),"Un probléme lors de consulatation d'historique par Admin!");
    softAssert.assertTrue(ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathListeHistorique),"Un probléme existe lors de consultation de liste de l'historique par l'admin!");
    if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlAdminHistorique)&&ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathListeHistorique)){
        System.out.println("Test Passed: L'historique est bien consulté par l'administrateur!");
    }
}
//@Test(priority=5)
    public void ConsulterClientParAdmin(){
    //Main Code

}
//@Test(priority=6)
    public void ProfileAdmin(){

}
//****************Se déconnecter de l'aspace admin*****************************
@Test(priority=7)
public void SeDeconnecter(){
    WebElement Logout=driver.findElement(Project_Xpath.logoutAdmin);
    Logout.click();
    Assert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage),"L'admin n'a pas pu se déconnecter");
    if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage)){
        System.out.println("Test Passed: l'admin se déconnecte correctement");
    }
}

}
