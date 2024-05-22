package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PostPage {
    WebDriver driver;

    By emailField = By.name("Email");
    By subscriptionButton = By.cssSelector(".btn-dark");

    public PostPage(WebDriver driver){
        this.driver = driver;
    }
    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }
    public String getPostTitle(){
        return driver.findElement(By.cssSelector("h1.heading-3.post-title")).getText();
    }
    public void enterEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    public void clickSubscribe(){
        driver.findElement(subscriptionButton).click();
    }
}
