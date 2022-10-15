import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.BasePage;

public class Driver {

    private static WebDriver driver;

    public static WebDriver setDriver(String browser){
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equals("yandex")) {

        }
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
        return driver;
    }



}
