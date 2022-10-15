package pageObject;

import org.openqa.selenium.By;

public class ForgotPassPage extends BasePage{
    public static final String URL ="https://stellarburgers.nomoreparties.site/forgot-password";
    private final By entryButton = By.xpath("//*[text()='Войти']");

    public void openLoginPage(){
        driver.findElement(entryButton).click();
    }
}
