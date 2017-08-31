import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class testSearchField {
    private WebDriver driver;
    private String baseUrl;


    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "http://dori.basheg.org.ua";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void test() throws Exception {
        driver.get(baseUrl + "/product-category/hair-care/");

        List<WebElement> products= driver.findElements(By.className("item-choose"));
        WebElement product= products.get(0);
        String searchPhrase = product.findElement(By.cssSelector(".layerUp>a>span")).getText();

        WebElement searchField = driver.findElement(By.id("search"));
        searchField.click();
        searchField.sendKeys(searchPhrase);
        searchField.submit();

        List<WebElement> searchResult = driver.findElements(By.xpath("//*[text()[contains(.,'"+ searchPhrase +"')]]"));
        Assert.assertTrue(
                "Test is failed. The search phrase is not found in the search results",
                searchResult.size() >= 1
        );
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}