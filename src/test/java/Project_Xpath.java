import org.openqa.selenium.By;

public class Project_Xpath {
    //************Login*****************************
    public static final By xpathLogin = By.xpath(" //*[@id=\"nav-menu\"]/ul/li[5]/a");
    public static By xpathEmail = By.id("email");

    public static By xpathPassword = By.id("motDePasse");
    public static By xpathConnect = By.xpath("/html/body/app-root/app-login/section/div/div/div/div/form/div[4]/button");

//*******************SignUp************************

    public static By xpathsignup = By.xpath("//*[@id=\"nav-menu\"]/ul/li[6]/a");
    public static By xpathnom = By.id("nom");
    public static By xpathAdresse = By.id("localisation");
    public static By xpathEmailLivreur = By.xpath("//*[@id=\"email\"]");
    public static By xpathmdp = By.xpath("//*[@id=\"motDePasse\"]");
    public static By xpathsubmit = By.xpath("/html/body/app-root/app-register/section/div/div/div/div/form/button");
    public static By xpathsuccessSignup = By.xpath("//*[text()=\"Success\"]");

    //******************Gestion des commandes par admin**************************
    public static By xpathcmdadmin = By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[2]");
    public static By xpathSuccesCmdClick = By.xpath("//*[text()=\"Liste des commandes\"]");
    public static By logoutAdmin=By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[7]");

    //Assigner une commande à un Livreur disponible
    public static By xpathchoisirLivreur = By.xpath("//*[text()=\"Choisir livreur\"]");

    public static By xpathPopUpAssigner = By.xpath("//*[text()=\"Choisir livreur\"]");
    public static By xpathassignerLivreur = By.xpath("/html/body/div/div/div[6]/button[1]");
    public static By xpathSuccessAssigner = By.xpath("//*[text()=\"Succès\"]");

    public static By xpathSelectLivreur= By.xpath("//*[@id=\"livreurForm\"]/label[34]/input");
   //public static By xpathSelectLivreur= By.xpath("//*[@id=\"livreurForm\"]/label[4]/input");

    public static By xpath_OK_Assignation= By.xpath("/html/body/div/div/div[6]/button[1]");
    //Changement de statut aprés assignement de livreur
    //public static By xpathStatusElement=By.xpath("//*[@id=\"app\"]/div[2]/div/div[2]/div/table/tr[36]/td[5]");

//**********************Gestion de Livreur par Admin********************************************
    public static By xpathLivreur = By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[4]");
    public static By xpathListeLivreur=By.xpath("//*[text()=\"Liste des Livreurs\"]");

//**********************Consultation de l'Historique par admin****************************************
    public static By xpathHistorique=By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[3]");

    public static By xpathListeHistorique=By.xpath("//*[text()=\"Liste des commandes\"]");
//*********************Espace de livreur*****************************************
    public static By xpathcommandeLivreur= By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[2]");
    public static By xpathListecmdLivreur=By.xpath("//*[text()=\"Liste des commandes\"]");
    public static By xpathHistoriqueLivreur=By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[3]");

    public static By xpathListeHistoriqueLivreur=By.xpath("//*[text()=\"Liste des commandes\"]");
    public static By logoutlivreur=By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[6]");
    public static By Xpath_confirmerLivraison=By.xpath("//*[text()=\"Confirmer\"]");
    public static By xpathPopUpConfirmLivraison=By.xpath("//*[text()=\"Êtes-vous sûr ?\"]");

    public static By xpathAnnulerLivraison=By.xpath("/html/body/div/div/div[6]/button[3]");

    public static By Xpath_AccepterLivraison=By.xpath("/html/body/div/div/div[6]/button[1]");
    public static By xpathsuccessacceptLivraison=By.xpath("//*[text()=\"Livrée!\"]");

    public static By xpathokafteracceptlivraison=By.xpath("/html/body/div/div/div[6]/button[1]");
    //public static By xpathCartLivreur=By.xpath("");
    //********************Client Process********************************************
    public static By xpathcommandes=By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[2]");
    public static By xpathcommandesclient=By.xpath("//*[text()=\"Liste des commandes\"]");
    public static By Xpath_historiqueclient=By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[3]");

    public static By xpathHistoriqueclient=By.xpath("//*[text()=\"Liste des commandes\"]");
    public static By logoutClient=By.xpath("//*[@id=\"mySidebar\"]/div[2]/a[5]");

}
