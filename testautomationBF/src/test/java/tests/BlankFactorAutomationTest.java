package tests;

import components.BaseClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InisghtsPage;
import pages.HomePage;
import pages.PostPage;

public class BlankFactorAutomationTest{
    WebDriver driver;
    HomePage homePage;
    InisghtsPage insightsPage;
    PostPage postPage;

    @BeforeClass
    public void setUp(){
        driver = BaseClass.getDriver();
        driver.get("http://blankfactor.com");
        homePage = new HomePage(driver);
        insightsPage = new InisghtsPage(driver);
        postPage = new PostPage(driver);
    }

    @Test
    public void testBlankfactorSiteNavigationAndSubscrition(){
        homePage.acceptCookies();
        homePage.navigateToInsights();
        insightsPage.goToBlog();
        insightsPage.scrollToPost();
        insightsPage.clickOnPost("Why fintech in Latin America is booming");

        Assert.assertEquals(postPage.getCurrentURL(),"https://blankfactor.com/insights/blog/fintech-in-latin-america/");
        Assert.assertEquals(postPage.getPostTitle(),"Why fintech in Latin America is booming");

        postPage.enterEmail("adrian.trujillo@blankfactor.test.invalid");
        postPage.clickSubscribe();
        driver.navigate().back();
        insightsPage.scrollToPost();
        insightsPage.totalNumberOfPosts();
        insightsPage.printAllPostTitlesAndUrls();
    }

    @AfterClass
    public void closeDriver(){
        driver.close();
    }
}
