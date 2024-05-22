package tests;

import components.BaseClass;
import org.openqa.selenium.*;
import org.testng.Assert;
import java.util.List;

public class BlankFactorAutomationTestRAW extends BaseClass {

   public static void main(String[] args){

       WebDriver driver;
       driver = BaseClass.getDriver();

       driver.get("http://blankfactor.com");
       driver.findElement(By.id("hs-eu-confirmation-button")).click();
       driver.findElement(By.cssSelector(".menu-item-4436")).click();
       driver.findElement(By.xpath("//a[contains(text(),'Go to blog')]")).click();

       // JavaScript Executor
       JavascriptExecutor js = (JavascriptExecutor) driver;
       boolean isElementFound = false;

       // Loop until the element is found
       while (!isElementFound) {
           try {

               WebElement postForScrolling = driver.findElement(By.xpath("//a[contains(text(),'Machine learning in the payments industry, explain')]"));

               // If found, scroll to the postForScrolling
               js.executeScript("arguments[0].scrollIntoView(true);", postForScrolling);

               // highlight the postForScrolling to verify it's in view (for debugging) Interesting!!
               js.executeScript("arguments[0].style.border='3px solid red'", postForScrolling);

               isElementFound = true;
           } catch (NoSuchElementException e) {
               // If the element is not found, scroll down
               js.executeScript("window.scrollBy(0, window.innerHeight);");

               //to avoid rapid scrolling XD
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException ie) {
                   ie.printStackTrace();
               }
           }
       }
       driver.findElement(By.xpath(" //a[normalize-space()='Why fintech in Latin America is booming']")).click();

       Assert.assertEquals(driver.getCurrentUrl(),"https://blankfactor.com/insights/blog/fintech-in-latin-america/");
       Assert.assertEquals(driver.findElement(By.cssSelector("h1.heading-3.post-title")).getText(),"Why fintech in Latin America is booming");

       driver.findElement(By.name("Email")).sendKeys("adrian.trujillo@blankfactor.test.invalid");
       driver.findElement(By.cssSelector(".btn-dark")).click();
       driver.navigate().back();

       List <WebElement> allPostsList= driver.findElements(By.cssSelector("h2.heading-4.post-title"));

       //cycle to print the posts tittles and the IUR attached to them
       allPostsList.stream()
               .map(element -> {
                   WebElement linkElement = element.findElement(By.tagName("a"));
                   String title = linkElement.getText();
                   String url = linkElement.getAttribute("href");
                   return String.format("Title: %s, URL: %s", title, url);
               })
               .forEach(System.out::println);

       driver.close();
   }
}
