package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site";
    private final By title = By.xpath("//h1[text()='Соберите бургер']");
    private final By personalAccountButton = By.xpath("//*[text()='Личный Кабинет']");
    private final By entryButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By bunSection = By.xpath("//span[text()='Булки']");
    private final By selectedSection = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span");

    public MainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(title));
    }

    public void openPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }
    public void openLoginPage() {
        driver.findElement(entryButton).click();
    }
    public boolean isMakeOrderButton() {
        return driver.findElements(makeOrderButton).size() == 1;
    }
    public void goToSectionByTypeIngredient(String typeIngredient) {
        driver.findElement(By.xpath("//span[text()='" + typeIngredient + "']")).click();
    }
    public String getTextSelectedSection() {
        return driver.findElement(selectedSection).getText();
    }
}
