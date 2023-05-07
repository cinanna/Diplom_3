package org.example.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class MainPage {
    private By privateCabinet = By.xpath(".//p[text()='Личный Кабинет']");
    //кнопка Личный Кабинет
    private By entrance = By.xpath(".//h2[text()='Вход']");
    //кнопка Вход
    private By profile = By.xpath(".//a[text()='Профиль']");
    //кнопка Профиль
    private By account = By.xpath(".//button[text()='Войти в аккаунт']");
    //кнопка Войти в аккаунт
    private By bun = By.xpath(".//span[text()='Булки']");
    //кнопка с разделом Булки
    private By sauces = By.xpath(".//span[text()='Соусы']");
    //кнопка с разделом Соусы
    private By ingredients = By.xpath(".//span[text()='Начинки']");
    //кнопка с разделом Начинки
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(privateCabinet));
    }
    public void openPrivateCabinetNoAuth() {
        driver.findElement(privateCabinet).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(entrance));
    }
    public void openPrivateCabinetAuth() {
        driver.findElement(privateCabinet).click();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(profile));
    }
    public void openAccount() {
        driver.findElement(account).click();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(entrance));
    }
    public void bunClick() {
        driver.findElement(bun).click();
    }
    public void saucesClick() {
        driver.findElement(sauces).click();
    }
    public void ingredientsClick() {
        driver.findElement(ingredients).click();
    }
}