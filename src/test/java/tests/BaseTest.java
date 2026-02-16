
package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    @Parameters({"browser", "os"})
    @BeforeClass
    public void setup(@Optional("chrome") String browser, @Optional("Windows 10") String os) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser);       // Chrome / Edge
        caps.setPlatformName(os);           // Windows 10 / macOS Ventura
        caps.setCapability("name", "Selenium Advanced Assignment"); // Optional: session name

        // TestMu AI Remote WebDriver URL
        String username = "patilvb22nov21";
        String accessToken = "LT_8RovrbxpqvMTL2tMHieN254DV4e5GxOxnFP4YiDU807SM6U";
        String gridUrl = "https://" + username + ":" + accessToken + "@hub.testmuai.ai/wd/hub";

        driver = new RemoteWebDriver(new URL(gridUrl), caps);

        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
