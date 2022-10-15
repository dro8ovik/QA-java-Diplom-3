package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    private final By title = By.xpath("//h2[text()='Вход']");
    private final By registration = By.xpath("//*[text()='Зарегистрироваться']");
    private final By emailField = By.xpath("//*[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//*[text()='Пароль']/following-sibling::input");
    private final By submitButton = By.xpath("//button[text()='Войти']");

    public LoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(title));
    }

    public void openRegisterPage() {
        driver.findElement(registration).click();
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPass(String pass) {
        driver.findElement(passwordField).sendKeys(pass);
    }

    public void submit() {
        driver.findElement(submitButton).click();
    }

    public void login(String email, String pass) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(submitButton));
        setEmail(email);
        setPass(pass);
        submit();
    }

    public boolean isTitle(){
        return driver.findElements(title).size()==1;
    }

}
