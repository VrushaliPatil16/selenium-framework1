package tests;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;

    public static String username = "patilvb22nov21";
    public static String access_key = "LT_8RovrbxpqvMTL2tMHieN254DV4e5GxOxnFP4YiDU807SM6U";

    @Parameters({"browser", "browserVersion", "platformName", "testName"})
    @BeforeMethod(alwaysRun = true)
    public void setup(String browser,
                      String browserVersion,
                      String platformName,
                      String testName) throws Exception {

        MutableCapabilities options;

        if (browser.equalsIgnoreCase("Chrome")) {
            options = new ChromeOptions();
            options.setCapability("browserName", "chrome");
        } else if (browser.equalsIgnoreCase("Edge") || browser.equalsIgnoreCase("MicrosoftEdge")) {
            options = new EdgeOptions();
            options.setCapability("browserName", "edge");
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }


        options.setCapability("browserName", browser);
        options.setCapability("browserVersion", browserVersion);
        options.setCapability("platformName", platformName);

        // TestMu AI specific capabilities
        Map<String, Object> testmuOptions = new HashMap<>();
        testmuOptions.put("name", testName);
        testmuOptions.put("build", "Selenium Advanced Assignment");
        testmuOptions.put("networkLogs", true);
        testmuOptions.put("consoleLogs", true);
        testmuOptions.put("video", true);
        testmuOptions.put("screenshots", true);

        options.setCapability("testmu:options", testmuOptions);

        String hubURL = "https://" + username + ":" + access_key + "@hub.testmu.ai/wd/hub";
        System.out.println("Grid URL: " + hubURL);

        
        driver = new RemoteWebDriver(new URL(hubURL), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
