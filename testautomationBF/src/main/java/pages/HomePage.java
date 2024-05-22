package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    By acceptCookiesButton = By.id("hs-eu-confirmation-button");
    By blogMenuItem = By.cssSelector(".menu-item-4436");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void acceptCookies(){
        driver.findElement(acceptCookiesButton).click();
    }

    public void navigateToInsights(){
        driver.findElement(blogMenuItem).click();
    }
}
