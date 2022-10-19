package pageObject;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    static WebDriver driver;

    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }

}
