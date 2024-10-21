import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProjectFunctions {
    //Verify URL
    public static boolean VerifyUrl(WebDriver driver, String url)
    {
        return driver.getCurrentUrl().equals(url);
    }

    //Verify existance of some elements
    public static boolean VerifyElements(WebDriver driver, By xpath)
    {
        return driver.findElements(xpath)!=null;
    }
}
