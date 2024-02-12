import browsers.Chrome;
import browsers.Firefox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;

import static utils.PropertyLoader.getConfigValue;

public abstract class Base {
    private String source;
    protected static Logger log;
    protected WebDriver driver;

    public Base() {
        this.source = this.getClass().getSimpleName();
        log = LogManager.getLogger(this.source);
    }

    protected void setDefaultWebDriver() {
        String driverDefault = getConfigValue("driver.default");
        log.trace("Browser default: " + driverDefault);

        if (driverDefault.equals("firefox")) {
            this.driver = new Firefox().getWebDriver();
        } else {
            this.driver = new Chrome().getWebDriver();
        }
    }

    protected void setChromeWebDriver() {
        this.driver = new Chrome().getWebDriver();
    }

    protected void setFirefoxWebDriver() {
        this.driver = new Firefox().getWebDriver();
    }

    @BeforeEach
    public void traceStartTest(TestInfo testInfo) {
        log.trace("[ " + testInfo.getDisplayName() + " ]");
    }

    @AfterEach
    public void endTest(TestInfo testInfo) {
        if (this.driver != null) {
            // capture screen
            try {
                String filename = "screen-" + this.source + "-" + testInfo.getDisplayName().replaceAll("[()]", "");
                File SrcFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
                File DestFile = new File("test-reports/screenshots/" + filename + ".png");
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (Exception e) {
                log.error("Screenshot failed: " + e);
            }

            // cleanup
            this.driver.quit();
        }
    }
}