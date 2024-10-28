import org.openqa.selenium.WebElement;
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
        //****************************************************
        // ********************************************************
        //********************************************************
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
}

@Test
    public void EditProfileLivreur(){

}


}
