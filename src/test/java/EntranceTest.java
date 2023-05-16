import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.example.api.Api;
import org.example.pageobjects.MainPage;
import org.example.pageobjects.RefreshPasswordPage;
import org.example.pageobjects.RegistrationPage;
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

public class EntranceTest {
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
    @Description("вход по кнопке Войти в аккаунт на главной")
    public void openAccountTest() {
        api.createUser();
        MainPage mainPage = new MainPage(driver);
        ToPrivateCabinetPage toPrivateCabinetPage = new ToPrivateCabinetPage(driver);

        mainPage.openMainPage();
        mainPage.openAccount();

        toPrivateCabinetPage.setEmailInput(email);
        toPrivateCabinetPage.setPasswordInput(password);
        toPrivateCabinetPage.pressInButton();
        mainPage.openPrivateCabinetAuth();

        WebElement element = driver.findElement(By.xpath(".//a[text()='Профиль']"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    @Description("вход через кнопку Личный кабинет")
    public void openCabinetTest() {
        api.createUser();
        MainPage mainPage = new MainPage(driver);
        ToPrivateCabinetPage toPrivateCabinetPage = new ToPrivateCabinetPage(driver);

        mainPage.openMainPage();
        mainPage.openPrivateCabinetNoAuth();

        toPrivateCabinetPage.setEmailInput(email);
        toPrivateCabinetPage.setPasswordInput(password);
        toPrivateCabinetPage.pressInButton();
        mainPage.openPrivateCabinetAuth();

        WebElement element = driver.findElement(By.xpath(".//a[text()='Профиль']"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    @Description("вход через кнопку в форме регистрации")
    public void openAccountFromReg() {
        api.createUser();
        MainPage mainPage = new MainPage(driver);
        ToPrivateCabinetPage toPrivateCabinetPage = new ToPrivateCabinetPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        mainPage.openMainPage();
        mainPage.openPrivateCabinetNoAuth();

        toPrivateCabinetPage.pressButtonRegistration();
        registrationPage.clickEntranceButton();

        toPrivateCabinetPage.setEmailInput(email);
        toPrivateCabinetPage.setPasswordInput(password);
        toPrivateCabinetPage.pressInButton();
        mainPage.openPrivateCabinetAuth();

        WebElement element = driver.findElement(By.xpath(".//a[text()='Профиль']"));
        Assert.assertTrue(element.isDisplayed());
    }
    @Test
    @Description("вход через кнопку в форме восстановления пароля")
    public void openAccountFromPassword() {
        api.createUser();
        MainPage mainPage = new MainPage(driver);
        ToPrivateCabinetPage toPrivateCabinetPage = new ToPrivateCabinetPage(driver);
        RefreshPasswordPage refreshPasswordPage = new RefreshPasswordPage(driver);

        mainPage.openMainPage();
        mainPage.openPrivateCabinetNoAuth();
        toPrivateCabinetPage.pressButtonNewPassword();
        refreshPasswordPage.toEntrance();

        toPrivateCabinetPage.setEmailInput(email);
        toPrivateCabinetPage.setPasswordInput(password);
        toPrivateCabinetPage.pressInButton();
        mainPage.openPrivateCabinetAuth();

        WebElement element = driver.findElement(By.xpath(".//a[text()='Профиль']"));
        Assert.assertTrue(element.isDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
        api.removeUser();
    }
}
