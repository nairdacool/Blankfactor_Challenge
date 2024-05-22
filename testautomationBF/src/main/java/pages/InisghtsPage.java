package pages;

import org.openqa.selenium.*;
import java.util.List;

public class InisghtsPage {

    WebDriver driver;
    JavascriptExecutor js;

    By goToBlogLink = By.xpath("//a[contains(text(),'Go to blog')]");
    By postToScrollTo = By.xpath("//a[contains(text(),'Machine learning in the payments industry, explain')]");

    public InisghtsPage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void goToBlog(){
        driver.findElement(goToBlogLink).click();
    }
    public void scrollToPost() {
        boolean isElementFound = false;

        while (!isElementFound) {
            try {
                WebElement postForScrolling = driver.findElement(postToScrollTo);
                js.executeScript("arguments[0].scrollIntoView(true);", postForScrolling);
                js.executeScript("arguments[0].style.border='3px solid red'", postForScrolling);
                isElementFound = true;
            } catch (NoSuchElementException e) {
                js.executeScript("window.scrollBy(0, window.innerHeight);");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
    public void clickOnPost(String postTitle){
        driver.findElement(By.xpath("//a[normalize-space()='" + postTitle + "']")).click();
    }
    public void printAllPostTitlesAndUrls() {
        List<WebElement> allPostsList = driver.findElements(By.cssSelector("h2.heading-4.post-title"));

        allPostsList.stream()
                .map(element -> {
                    WebElement linkElement = element.findElement(By.tagName("a"));
                    String title = linkElement.getText();
                    String url = linkElement.getAttribute("href");
                    return String.format("Title: %s, URL: %s", title, url);
                })
                .forEach(System.out::println);
    }
}
