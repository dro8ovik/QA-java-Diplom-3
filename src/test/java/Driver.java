import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.BasePage;

import java.io.FileWriter;
import java.io.IOException;

public class Driver {

    private static WebDriver driver;

    public static WebDriver setDriver(String browser){
        FileWriter writer = null;
        try {
            writer = new FileWriter("target/allure-results/environment.properties",false);
            writer.write("browser="+browser);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
        } else if (browser.equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
        return driver;
    }
}
