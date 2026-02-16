package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestScenario1 extends BaseTest {

    @Test(timeOut = 20000)
    public void runScenario1() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://www.testmuai.com/");

        wait.until(d ->
                ((JavascriptExecutor) d)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        // Locator 1 → XPath
        WebElement exploreBtn = driver.findElement(
                By.xpath("//a[contains(text(),'Explore Agentic Clouds')]"));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", exploreBtn);

        String parentWindow = driver.getWindowHandle();
        exploreBtn.click();

        wait.until(d -> driver.getWindowHandles().size() == 2);

        Set<String> handles = driver.getWindowHandles();
        List<String> windowList = new ArrayList<>(handles);

        String childWindow = windowList.stream()
                .filter(h -> !h.equals(parentWindow))
                .findFirst().get();

        driver.switchTo().window(childWindow);

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.testmuai.com/agentic-cloud/");

        // Locator 2 → CSS Selector
        WebElement tryNow = driver.findElement(
                By.cssSelector("a[href*='signup']"));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", tryNow);

        tryNow.click();

        Assert.assertEquals(driver.getTitle(),
                "Sign up for free | Cross Browser Testing Tool");

        driver.close();
        driver.switchTo().window(parentWindow);

        driver.get("https://www.testmuai.com/blog");

        // Locator 3 → LinkText
        driver.findElement(By.linkText("Community")).click();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://community.testmuai.com/");

        driver.close();
    }
}