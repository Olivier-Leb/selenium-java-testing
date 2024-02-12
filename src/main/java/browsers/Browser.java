package browsers;

import org.openqa.selenium.WebDriver;

import static utils.PropertyLoader.getConfigValue;

public class Browser {

    protected WebDriver driver;

    protected final Integer width = 1920;

    protected final Integer height = 1080;

    protected Boolean headless = true;

    public Browser() {
        if (getConfigValue("driver.headless").equals("false")) {
            this.headless = false;
        }
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }
}
