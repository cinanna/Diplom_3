package org.example.pageobjects;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ToPrivateCabinetPage {
    private By inputEmail = By.cssSelector("input[type='text']");
    //для почты
    private By inputPassword = By.cssSelector("input[type='password']");
    //для пароля
    private By inButton = By.xpath(".//button[text()='Войти']");
    //кнопка Войти
    private By RegistrationButton = By.className("Auth_link__1fOlj");
    //кнопка Зарегистрировать
    private By burger = By.xpath(".//h1[text()='Соберите бургер']");
    //текст Соберите бургер
    private By cabinet = By.xpath(".//p[text()='Личный Кабинет']");
    //кнопка Личный кабинет
    private By newPassword = By.className("Auth_link__1fOlj");
    //кнопка Восстановить пароль
    private By refreshPassword = By.className("Auth_login__3hAey");
    //форма
    private WebDriver driver;

    public ToPrivateCabinetPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Заполнить поле Email")
    public void setEmailInput(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }
    @Step("Заполнить поле Пароль")
    public void setPasswordInput(String password) {
        driver.findElement(inputPassword).sendKeys(password);
     }
    @Step("Нажать кнопку Войти")
    public void pressInButton() {
        driver.findElement(inButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(burger));
    }
    @Step("Нажать кнопку Зарегистрироваться")
    public void pressButtonRegistration() {
        driver.findElement(RegistrationButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(cabinet));
    }
    @Step("Нажать кнопку Восстановить пароль")
    public void pressButtonNewPassword() {
        driver.findElement(newPassword).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(refreshPassword));
    }
}
