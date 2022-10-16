package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterPage extends BasePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/register";
    private final By nameField = By.xpath("//*[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//*[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//*[text()='Пароль']/following-sibling::input");
    private final By submitButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By wrongPassErrorMessage = By.xpath("//*[text()='Некорректный пароль']");
    private final By entryButton = By.xpath("//*[text()='Войти']");

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
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
    public void register(String name, String email, String pass) {
        setName(name);
        setEmail(email);
        setPass(pass);
        submit();
    }
    public boolean isWrongPassErrorMessage() {
        return driver.findElements(wrongPassErrorMessage).size() == 1;
    }
    public void openLoginPage() {
        driver.findElement(entryButton).click();
    }
}
