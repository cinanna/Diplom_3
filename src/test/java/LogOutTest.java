import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.example.api.Api;
import org.example.pageobjects.MainPage;
import org.example.pageobjects.PrivateCabinet;
import org.example.pageobjects.ToPrivateCabinetPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogOutTest {
    private WebDriver driver;
    private String email = "pompom3@starrail.ru";
    private String name = "PomPom";
    private String password = "qwerty123";
    Api api = new Api(email, password, name);
    @Before
    public void startUp() {
        api.setURL();

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //добавлена настройка их-за ошибки "Unable to establish websocket connection"
    }
    @Test
    @Description("выход по кнопке Выйти в личном кабинете")
    public void profileLogOutTest() {
        api.createUser();
        MainPage mainPage = new MainPage(driver);
        ToPrivateCabinetPage toPrivateCabinetPage = new ToPrivateCabinetPage(driver);
        PrivateCabinet privateCabinet = new PrivateCabinet(driver);

        mainPage.openMainPage();
        mainPage.openPrivateCabinetNoAuth();

        toPrivateCabinetPage.setEmailInput(email);
        toPrivateCabinetPage.setPasswordInput(password);
        toPrivateCabinetPage.pressInButton();
        mainPage.openPrivateCabinetAuth();

        privateCabinet.logOut();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h2[text()='Вход']")));
        WebElement element = driver.findElement(By.xpath(".//h2[text()='Вход']"));
        Assert.assertTrue(element.isDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
        api.removeUser();
    }
}
