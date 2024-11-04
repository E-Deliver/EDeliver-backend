import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LivreurProcess extends Loginlivreur{
    SoftAssert softAssert = new SoftAssert();

    @Test(priority=1)
    public void livreurCommandes() throws InterruptedException {
        login_Livreur();
        WebElement commande = driver.findElement(Project_Xpath.xpathcommandeLivreur);
        commande.click();
        softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver, Parameters.UrlLivreurCmd), "Un problème persiste lors de consultation des commandes assignées au livreur!");

        if (ProjectFunctions.VerifyUrl(driver, Parameters.UrlLivreurCmd)) {
            System.out.println("Test Passed: les commandes sont consultables par le client correctement!");
        }

        boolean confirButtonClicked = false;
        while (!confirButtonClicked) {
            try {
                // Attendre la visibilité du bouton "confirmer"
                WebElement confirmLivraison = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.Xpath_confirmerLivraison));

                // Si le bouton "choisir livreur" est visible, exécuter les étapes d'assignation
                if (confirmLivraison.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmLivraison);

                    // Vérifier l'affichage du PopUp de confirmation
                    softAssert.assertTrue(ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathPopUpConfirmLivraison), "Le pop-up de confirmation ne s'affiche pas!");
                    if (ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathPopUpConfirmLivraison)) {
                        System.out.println("Le pop-up de confirmation s'affiche correctement.");
                    }

                    // Annuler la livraison
                    WebElement annuler = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathAnnulerLivraison));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", annuler);
                    System.out.println("La livraison est annulée avec succès par le livreur.");

                    // Accepter à nouveau la livraison
                    WebElement confirmerNouveau = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.Xpath_confirmerLivraison));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmerNouveau);
                    Thread.sleep(2000);
                    WebElement acceptLivraison = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.Xpath_AccepterLivraison));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", acceptLivraison);
                    softAssert.assertTrue(ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathsuccessacceptLivraison), "Un problème persiste lors de l'acceptation de la livraison par ce livreur!");
                    if (ProjectFunctions.VerifyElements(driver, Project_Xpath.xpathsuccessacceptLivraison)) {
                        System.out.println("La livraison est acceptée par le livreur correctement!");
                    }

                    Thread.sleep(1000);
                    WebElement OKLiv = driver.findElement(Project_Xpath.xpathokafteracceptlivraison);
                    OKLiv.click();

                    confirButtonClicked = true; // Le bouton "Confirmer" a été trouvé et traité
                }

            } catch (TimeoutException e) {
                // Si le bouton "confirmer" n'est pas trouvé dans le délai, passer à la page suivante
                try {
                    WebElement nextPageButton = driver.findElement(Project_Xpath.Xpath_Next);

                    // Vérifier si le bouton "Next" est activé avant de cliquer
                    if (nextPageButton.isEnabled()) {
                        nextPageButton.click();
                        Thread.sleep(1000); // Attendre le chargement de la page
                    } else {
                        System.out.println("No more pages, 'confirmer' button not found, so all commands are accepted or rejected!.");
                        break;
                    }
                } catch (NoSuchElementException ex) {
                    System.out.println("Le bouton 'Next' n'est pas disponible.");
                    break;
                }
            }
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

    @Test(priority=3)
    public void CarteConsultation(){
        driver.findElement(Project_Xpath.dashxpathLivreur).click();
        WebElement Cart= driver.findElement(Project_Xpath.xpathCartLivreur);
        Cart.click();
        softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlLivreurCart));
        if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlLivreurCart)){
            System.out.println("Test Passed: la carte de localisation des commandes de ce livreur est correctement consultable");
        }
    }

    @Test(priority=4)
    public void EditProfileLivreur() throws InterruptedException {

        //WebElement EditProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(Project_Xpath.xpathEditProfileLivreur));
        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditProfile);
        //driver.findElement(By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[5]")).click();
        WebElement EditProfile= driver.findElement(Project_Xpath.xpathEditProfileLivreur);
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

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert text: " + alert.getText());
            alert.accept();// Accepter l'alerte
            System.out.println("Alert accepted.");
        } catch (NoAlertPresentException e) {
            System.out.println("No alert found after clicking save.");
        }

        // Check if update was successful
        softAssert.assertTrue(ProjectFunctions.VerifyElements(driver, Project_Xpath.XpathSuccessUpdateProfile), "La modification n'est pas faite correctement!");
        if (ProjectFunctions.VerifyElements(driver, Project_Xpath.XpathSuccessUpdateProfile)) {
            System.out.println("Test Passed: la modification est bien faite de l'adresse et donc les modifications travaille correctement!");
        }

        // Click on OK button
        //WebElement OK_Modif = driver.findElement(Project_Xpath.xpathOKUpdateProfil);
        //OK_Modif.click();
        Thread.sleep(500);
    }

    @Test(priority=5)
    public void SeDeconnecter(){
        driver.findElement(Project_Xpath.XpathdashlivreurButton).click(); //Click on dashboard button
        WebElement Logout=driver.findElement(Project_Xpath.logoutlivreur);
        Logout.click();
        softAssert.assertTrue(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage),"Ce livreur n'a pas pu se déconnecter");
        if(ProjectFunctions.VerifyUrl(driver,Parameters.UrlHomePage)){
            System.out.println("Test Passed: le livreur se déconnecte correctement");
        }
    }


}
