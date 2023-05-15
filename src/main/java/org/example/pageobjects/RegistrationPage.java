package org.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;
import java.util.List;

public class RegistrationPage {
    private WebDriver driver;
    private By input = By.cssSelector("input[class='text input__textfield text_type_main-default']");
    //для входных данных
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //кнопка Зарегистрироваться
    private By auth = By.className("Auth_login__3hAey");
    //форма
    private By entrance = By.className("Auth_link__1fOlj");
    //кнопка Войти


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Заполнить поле Имя")
    public void setNameInput(String name) {
        List<WebElement> elements = driver.findElements(input);
        elements.get(0).sendKeys(name);
    }
    @Step("Заполнить поле Email")
    public void setEmailInput(String email) {
        List<WebElement> elements = driver.findElements(input);
        elements.get(1).sendKeys(email);
    }
    @Step("Заполнить поле Пароль")
    public void setPasswordInput(String password) {
        List<WebElement> elements = driver.findElements(input);
        elements.get(2).sendKeys(password);
    }
    @Step("Нажать кнопку Зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(auth));
    }
    @Step("Нажать кнопку Войти")
    public void clickEntranceButton() {
        driver.findElement(entrance).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(auth));
    }
}
