import api.Requests.RegisterUserRequest;
import api.TestData;
import api.Utils;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.*;

public class RegisterTest {

    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    RegisterUserRequest user;

    @Before
    public void setup() {
        Utils.setReqSpec(TestData.HOST_URL, ContentType.JSON);
        user = new RegisterUserRequest(TestData.USER_EMAIL, TestData.USER_PASS, TestData.USER_NAME);
        Utils.deleteUser(user);
        driver = Driver.setDriver("chrome");
    }

    @After
    public void tearDown(){
        if (driver != null)
            driver.quit();    }

    @Test
    public void registerSuccessTest() {
        driver.get(MainPage.URL);
        mainPage = new MainPage();
        mainPage.openPersonalAccount();
        loginPage = new LoginPage();
        loginPage.openRegisterPage();
        registerPage = new RegisterPage();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage = new MainPage();
        Assert.assertTrue(mainPage.isMakeOrderButton());
        Utils.deleteUser(user);
    }

    @Test
    public void registerPassLessSixErrorMessageTest() {
        driver.get(RegisterPage.URL);
        registerPage = new RegisterPage();
        registerPage.register("", "", user.getPassword().substring(0,5));
        Assert.assertTrue(registerPage.isWrongPassErrorMessage());
    }

    @Test
    public void registerPassSixSymbolsTest() {
        driver.get(RegisterPage.URL);
        registerPage = new RegisterPage();
        registerPage.register("", "", user.getPassword().substring(0,6));
        Assert.assertFalse(registerPage.isWrongPassErrorMessage());
    }
}
