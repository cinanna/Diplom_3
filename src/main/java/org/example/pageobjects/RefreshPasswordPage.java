package org.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RefreshPasswordPage {
    WebDriver driver;
    private By entrance = By.className("Auth_link__1fOlj");
    //кнопка Войти
    private By auth = By.className("Auth_login__3hAey");
    //форма


    public RefreshPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void toEntrance() {
        driver.findElement(entrance).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(auth));
    }
}
