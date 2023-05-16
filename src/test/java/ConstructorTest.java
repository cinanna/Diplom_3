import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.example.pageobjects.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ConstructorTest {
    private WebDriver driver;
    @Before
    public void startUp() {
                WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //добавлена настройка их-за ошибки "Unable to establish websocket connection"
    }

    @Test
    @Description("Раздел Конструктор - переход к разделу Булки")
    public void bunScrollTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.ingredientsClick();
        mainPage.bunClick();

        WebElement element = driver.findElement(By.xpath(".//p[text()='Флюоресцентная булка R2-D3']"));
        Assert.assertTrue(element.isDisplayed());
    }
    @Test
    @Description("Раздел Конструктор - переход к разделу Соусы")
    public void saucesScrollTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.ingredientsClick();
        mainPage.saucesClick();

        WebElement element = driver.findElement(By.xpath(".//p[text()='Соус Spicy-X']"));
        Assert.assertTrue(element.isDisplayed());
    }
    @Test
    @Description("Раздел Конструктор - переход к разделу Начинки")
    public void ingredientsScrollTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.ingredientsClick();

        WebElement element = driver.findElement(By.xpath(".//p[text()='Мясо бессмертных моллюсков Protostomia']"));
        Assert.assertTrue(element.isDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
