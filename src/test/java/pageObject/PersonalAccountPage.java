package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage extends BasePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/account";
    private final By constructorButton = By.xpath("//*[text()='Конструктор']");
    private final By logo = By.cssSelector(".AppHeader_header__logo__2D0X2");
    private final By personalAccountMessage = By.xpath("//*[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public PersonalAccountPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(personalAccountMessage));
    }

    public boolean isPersonalAccountMessage() {
        return driver.findElements(personalAccountMessage).size() == 1;
    }

    public void openMainPageByConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void openMainPageByConstructorLogo() {
        driver.findElement(logo).click();
    }

    public void logout() {
        driver.findElement(logoutButton).click();
    }
}
