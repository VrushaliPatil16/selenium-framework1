
package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestScenario1 extends BaseTest {

    @Test
    public void seleniumAdvancedTest() throws InterruptedException {

        // 1. Navigate to testmuai.com
        driver.get("https://www.testmuai.com/");

        // 2. Explicit wait (simple sleep for demo; replace with WebDriverWait)
        Thread.sleep(3000); // replace with proper WebDriverWait in real tests

        // 3. Scroll to 'Explore Agentic Clouds'
        WebElement exploreClouds = driver.findElement(By.xpath("//a[text()='Explore Agentic Clouds']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", exploreClouds);

        // 4. Click link (opens new tab)
        exploreClouds.click();
        Thread.sleep(2000);

        // 5. Save window handles
        Set<String> handles = driver.getWindowHandles();
        List<String> windows = new ArrayList<>(handles);
        System.out.println("Window handles: " + windows);

        // Switch to new tab
        driver.switchTo().window(windows.get(1));
        String expectedUrl = "https://www.testmuai.com/agentic-clouds"; // Example expected URL
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL mismatch on new tab!");
    }
}
