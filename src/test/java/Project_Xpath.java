import org.openqa.selenium.By;

public class Project_Xpath {
//************Login*****************************
    public static final By xpathLogin = By.xpath(" //*[@id=\"nav-menu\"]/ul/li[5]/a");
    public static By xpathEmail= By.id("email");

    public static By xpathPassword= By.id("motDePasse");
    public static By xpathConnect=By.xpath("/html/body/app-root/app-login/section/div/div/div/div/form/div[4]/button");

//*******************SignUp************************

    public static By xpathsignup= By.xpath("//*[@id=\"nav-menu\"]/ul/li[6]/a");
    public static By xpathnom= By.id("nom");
    public static By xpathAdresse= By.id("localisation");
    public static By xpathEmailLivreur=By.xpath("//*[@id=\"email\"]");
    public static By xpathmdp= By.xpath("//*[@id=\"motDePasse\"]");
    public static By xpathsubmit=By.xpath("/html/body/app-root/app-register/section/div/div/div/div/form/button");

    public static By xpathsuccessSignup=By.xpath("//*[text()=\"Success\"]");
}
