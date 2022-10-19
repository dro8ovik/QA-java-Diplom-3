import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObject.MainPage;

@RunWith(Parameterized.class)
public class ConstructorTest {
    WebDriver driver;
    MainPage mainPage;

    private final String typeIngredientsStart;
    private final String typeIngredientsTarget;

    public ConstructorTest(String typeIngredientsStart, String typeIngredientsTarget) {
        this.typeIngredientsStart = typeIngredientsStart;
        this.typeIngredientsTarget = typeIngredientsTarget;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Соусы", "Булки"},
                {"Соусы", "Начинки"},
                {"Начинки", "Соусы"},
        };
    }

    @Before
    public void setup() {
        Utils.setReqSpec(TestData.HOST_URL, ContentType.JSON);
        driver = Driver.setDriver(System.getProperty("browser"));
    }

    @After
    public void teardown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void changeIngredientSectionSuccessTest() {
        driver.get(MainPage.URL);
        mainPage = new MainPage();
        mainPage.goToSectionByTypeIngredient(typeIngredientsStart);
        mainPage.goToSectionByTypeIngredient(typeIngredientsTarget);
        Assert.assertEquals(typeIngredientsTarget, mainPage.getTextSelectedSection());
    }
}
