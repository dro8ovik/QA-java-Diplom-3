import api.Requests.RegisterUserRequest;
import api.TestData;
import api.Utils;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.ForgotPassPage;
import pageObject.MainPage;
import pageObject.LoginPage;
import pageObject.RegisterPage;

public class LoginTest {
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ForgotPassPage forgotPassPage;
    RegisterUserRequest user;

    @Before
    public void setup() {
        Utils.setReqSpec(TestData.HOST_URL, ContentType.JSON);
        user = new RegisterUserRequest(TestData.USER_EMAIL, TestData.USER_PASS, TestData.USER_NAME);
        Utils.deleteUser(user);
        Utils.registerUser(user);
        driver = Driver.setDriver("chrome");
    }

    @After
    public void teardown(){
        Utils.deleteUser(user);
        if (driver != null)
            driver.quit();    }

    @Test
    public void loginFromPersonalAccountButtonSuccessTest() {
        driver.get(MainPage.URL);
        mainPage = new MainPage();
        mainPage.openPersonalAccount();
        loginPage = new LoginPage();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage = new MainPage();
        Assert.assertTrue(mainPage.isMakeOrderButton());
    }

    @Test
    public void loginFromMainPageSuccessTest() {
        driver.get(MainPage.URL);
        mainPage = new MainPage();
        mainPage.openLoginPage();
        loginPage = new LoginPage();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage = new MainPage();
        Assert.assertTrue(mainPage.isMakeOrderButton());
    }

    @Test
    public void loginFromRegisterPageSuccessTest() {
        driver.get(RegisterPage.URL);
        registerPage = new RegisterPage();
        registerPage.openLoginPage();
        loginPage = new LoginPage();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage = new MainPage();
        Assert.assertTrue(mainPage.isMakeOrderButton());
    }

    @Test
    public void loginFromForgotPassPageSuccessTest() {
        driver.get(ForgotPassPage.URL);
        forgotPassPage = new ForgotPassPage();
        forgotPassPage.openLoginPage();
        loginPage = new LoginPage();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage = new MainPage();
        Assert.assertTrue(mainPage.isMakeOrderButton());
    }
}
