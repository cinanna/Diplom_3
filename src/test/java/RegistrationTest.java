import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.example.api.Api;
import org.example.pageobjects.RegistrationPage;
import org.example.pageobjects.ToPrivateCabinetPage;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.pageobjects.MainPage;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationTest {
    private WebDriver driver;
    private String email = "vincent2@boom.ru";
    private String name = "vincent";
    private String password = "qwerty123";
    private String wrongPassword = "qwert";
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
    @Description("Успешная регистрация")
    public void registrationSuccessTest() {
        MainPage mainPage = new MainPage(driver);
        ToPrivateCabinetPage toPrivateCabinetPage = new ToPrivateCabinetPage(driver);
        RegistrationPage registrationTest = new RegistrationPage(driver);
        mainPage.openMainPage();
        mainPage.openPrivateCabinetNoAuth();
        toPrivateCabinetPage.pressButtonRegistration();

        registrationTest.setEmailInput(email);
        registrationTest.setNameInput(name);
        registrationTest.setPasswordInput(password);

        registrationTest.clickRegistrationButton();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h2[text()='Вход']")));
        WebElement element = driver.findElement(By.xpath(".//h2[text()='Вход']"));
        Assert.assertTrue(element.isDisplayed());

        api.removeUser();
    }
    @Test
    @Description("Неуспешная регистрация: некорректный пароль")
    public void registrationErrorTest() {
        MainPage mainPage = new MainPage(driver);
        ToPrivateCabinetPage toPrivateCabinetPage = new ToPrivateCabinetPage(driver);
        RegistrationPage registrationTest = new RegistrationPage(driver);
        mainPage.openMainPage();
        mainPage.openPrivateCabinetNoAuth();
        toPrivateCabinetPage.pressButtonRegistration();

        registrationTest.setEmailInput(email);
        registrationTest.setNameInput(name);
        registrationTest.setPasswordInput(wrongPassword);

        registrationTest.clickRegistrationButton();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//p[text()='Некорректный пароль']")));
        WebElement element = driver.findElement(By.xpath(".//p[text()='Некорректный пароль']"));
        Assert.assertTrue(element.isDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
