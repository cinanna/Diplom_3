package org.example.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrivateCabinet {
    WebDriver driver;
    private By constructor = By.className("AppHeader_header__link__3D_hX");
    //Конструктор
    private By logo = By.className("AppHeader_header__logo__2D0X2");
    //Логотип
    private By exit = By.xpath(".//button[text()='Выход']");
    //кнопка Выход
    private By burger = By.xpath(".//h1[text()='Соберите бургер']");
    //текст Соберите бургер
    private By entrance = By.xpath(".//h2[text()='Вход']");
    //кнопка Вход

    public PrivateCabinet(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Переход на Гланую страницу через Конструктор")
    public void toMainPage() {
        driver.findElement(constructor).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(burger));
    }
    @Step("Переход на Главную страницу через Логотип")
    public void toMainPageLogo() {
        driver.findElement(logo).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(burger));
    }
    @Step("Выход из личного кабинета")
    public void logOut() {
        driver.findElement(exit).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(entrance));
    }
}
