package example.selenium.browsers;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import static example.selenium.utils.PropertyLoader.getConfigValue;

public class Firefox extends Browser {

    public Firefox() {
        super();

        String driver = getConfigValue("driver.firefox");
        if (!driver.isEmpty()) {
            System.setProperty("webdriver.gecko.driver", driver);
        }

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .build();

        FirefoxOptions opts = new FirefoxOptions();
        opts.addArguments("--width=" + this.width);
        opts.addArguments("--height=" + this.height);
        if (this.headless) {
            opts.addArguments("--headless");
        }

        this.driver = new FirefoxDriver(service, opts);
    }
}
