import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
        boolean livreurAssignationTerminee = false;

        while (!livreurAssignationTerminee) {
            try {
                // Attendre la visibilité du bouton "choisir livreur"
                WebElement choisirLivreur = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathchoisirLivreur));

                // Si le bouton "choisir livreur" est visible, exécuter les étapes d'assignation
                if (choisirLivreur.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", choisirLivreur);

                    // Vérifier l'affichage du PopUp d'assignation
                    softAssert.assertTrue(ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathPopUpAssigner));
                    if (ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathPopUpAssigner)) {
                        System.out.println("Test Passed: Un PopUp de choix de livreur est affiché correctement");
                    }

                    // Sélectionner un livreur
                    WebElement selectLivreur = driver.findElement(Project_Xpath.xpathSelectLivreur);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectLivreur);
                    action = new Actions(driver);
                    action.moveToElement(selectLivreur).click().perform();

                    //WebElement selectLivreur = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathSelectLivreur));
                    //selectLivreur.click();
                    System.out.println("Le livreur est sélectionné!");

                    // Cliquer sur le bouton d'assignation
                    WebElement assignerLivreur = driver.findElement(Project_Xpath.xpathassignerLivreur);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignerLivreur);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", assignerLivreur);
                    //WebElement assignerLivreur = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathassignerLivreur));
                    //assignerLivreur.click();
                    softAssert.assertTrue(ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathSuccessAssigner),
                            "Problème lors de l'assignation de livreur à cette commande");
                    if (ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathSuccessAssigner)) {
                        System.out.println("Test Passed: Un Livreur disponible est bien assigné à la commande!");
                    }

                    // Confirmer l'assignation en cliquant sur "OK"
                    Thread.sleep(1000);
                    WebElement OK = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpath_OK_Assignation));
                    OK.click();

                    // Fin de l'assignation pour cette commande
                    livreurAssignationTerminee = true;
                }

            } catch (TimeoutException e) {
                // Si le bouton "choisir livreur" n'est pas trouvé dans le délai, passer à la page suivante
                try {
                    WebElement nextPageButton = driver.findElement(Project_Xpath.xpathNextPage);

                    // Vérifier si le bouton "Next" est activé avant de cliquer
                    if (nextPageButton.isEnabled()) {
                        nextPageButton.click();
                        Thread.sleep(1000); // Attendre le chargement de la page
                    } else {
                        System.out.println("No more pages, 'choisir livreur' button not found.");
                        break;
                    }
                } catch (NoSuchElementException ex) {
                    System.out.println("Le bouton 'Next' n'est pas disponible.");
                    break;
                }
            }
        }

        // Assertion pour consolider les résultats
        softAssert.assertAll();
    }





    /*@Test(priority=2)
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


    @Test(priority=5)
    public void ConsulterClientParAdmin(){
        WebElement Client=driver.findElement(Project_Xpath.xpathClientAdmin);
        Client.click();
        softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlAdminClient),"Un probléme lors de consulatation des clients par Admin!");
        softAssert.assertTrue(ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathListeClient),"Un probléme existe lors de consultation de liste des clients par l'admin!");
        if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlAdminClient)&&ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathListeClient)){
            System.out.println("Test Passed: L'historique est bien consulté par l'administrateur!");
        }
    }
    @Test(priority=6)
    public void ProfileAdmin() throws InterruptedException {
        WebElement EditProfile= driver.findElement(Project_Xpath.xpathEditProfileAdmin);
        EditProfile.click();
        //Page is well displayed
        softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlEditProfilePage),"La page d'edit profile pour livreur ne s'affiche pas correctement!");
        if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlEditProfilePage)){
            System.out.println("Test passed: La page d'edit profile pour le livreur est affichée correctement");
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
        //click on edit icon
        WebElement edit= driver.findElement(Project_Xpath.XpathEditProfile);
        edit.click();
        Thread.sleep(1000);
        //Edit password
        WebElement passwordinput =driver.findElement(Project_Xpath.xpathPasswordInput);
        passwordinput.clear();
        passwordinput.sendKeys("$2a$10$w3PL6XU0VqJjYjrR5RUxhuDcZ3G0aYQwcGlPf5eKz4pBrKHd1VSS2");
        System.out.println("The new Password was entered!");
        //Edit adress
        WebElement adressUpdate= driver.findElement(Project_Xpath.xpathAdressinput);
        String textadress= adressUpdate.getText();
        if(textadress.equals("Rabat")){
            adressUpdate.clear();
            adressUpdate.sendKeys("Fes");
            System.out.println("The new Adress was entered!");
        }
        else{
            adressUpdate.clear();
            adressUpdate.sendKeys("Rabat");
            System.out.println("The new Adress was entered!");
            Thread.sleep(500);
        }
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
    //****************Se déconnecter de l'aspace admin*****************************
//@Test(priority=7)
    public void SeDeconnecter() throws InterruptedException {
        driver.findElement(Project_Xpath.dashAdminXpath).click();
        WebElement Logout= driver.findElement(By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[7]"));
        //WebElement Logout=driver.findElement(Project_Xpath.logoutAdmin);
        Logout.click();
        //Thread.sleep(1000);
    /*Assert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage),"L'admin n'a pas pu se déconnecter");
    if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage)){
        System.out.println("Test Passed: l'admin se déconnecte correctement");
    }*/
    }

}
