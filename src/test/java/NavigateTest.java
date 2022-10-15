import api.Requests.RegisterUserRequest;
import api.Responses.RegisteredUserResponse;
import api.TestData;
import api.Utils;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import pageObject.*;

public class NavigateTest {
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    PersonalAccountPage personalAccountPage;
    RegisterUserRequest user;
    RegisteredUserResponse registeredUser;

    @Before
    public void setup() {
        Utils.setReqSpec(TestData.HOST_URL, ContentType.JSON);
        user = new RegisterUserRequest(TestData.USER_EMAIL, TestData.USER_PASS, TestData.USER_NAME);
        Utils.deleteUser(user);
        Utils.registerUser(user);
        registeredUser = Utils.getRegisteredUser(user);
        driver = Driver.setDriver("chrome");
        driver.get(MainPage.URL);
        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        localStorage.setItem("accessToken", registeredUser.getAccessToken());
        localStorage.setItem("refreshToken", registeredUser.getRefreshToken());
    }

    @After
    public void teardown() {
        Utils.deleteUser(user);
        if (driver != null)
            driver.quit();    }

    @Test
    public void openPersonalAccountSuccessTest() {
        mainPage = new MainPage();
        mainPage.openPersonalAccount();
        personalAccountPage = new PersonalAccountPage();
        Assert.assertTrue(personalAccountPage.isPersonalAccountMessage());
    }

    @Test
    public void openMainPageFromPersonalAccountByConstructorSuccessTest() {
        driver.get(PersonalAccountPage.URL);
        personalAccountPage = new PersonalAccountPage();
        personalAccountPage.openMainPageByConstructorButton();
        mainPage = new MainPage();
        Assert.assertTrue(mainPage.isMakeOrderButton());
    }

    @Test
    public void openMainPageFromPersonalAccountByLogoSuccessTest() {
        driver.get(PersonalAccountPage.URL);
        personalAccountPage = new PersonalAccountPage();
        personalAccountPage.openMainPageByConstructorLogo();
        mainPage = new MainPage();
        Assert.assertTrue(mainPage.isMakeOrderButton());
    }

    @Test
    public void logoutSuccessTest() {
        driver.get(PersonalAccountPage.URL);
        personalAccountPage = new PersonalAccountPage();
        personalAccountPage.logout();
        loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isTitle());
    }
}
