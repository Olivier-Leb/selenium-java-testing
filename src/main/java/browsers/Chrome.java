package browsers;

import static utils.PropertyLoader.getConfigValue;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome extends Browser {

    public Chrome() {
        super();

        String driver = getConfigValue("driver.chrome");
        if (!driver.isEmpty()) {
            System.setProperty("webdriver.chrome.driver", driver);
        }

        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--window-size=" + this.width + "," + this.height);
        if (this.headless) {
            opts.addArguments("--headless=new");
        }

        this.driver = new ChromeDriver(opts);
    }
}
