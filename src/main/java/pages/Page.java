package pages;

import static utils.PropertyLoader.getConfigValue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class Page extends LoadableComponent<Page> {
    protected static Logger log;

    protected final WebDriver driver;

    protected String url;

    public Page(WebDriver driver, String url) {
        log = LogManager.getLogger(this.getClass().getSimpleName());
        this.driver = driver;
        this.url = getConfigValue("url.base") + getConfigValue(url);

        this.get();
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        log.trace("Opening browser with url: " + this.url);
        this.driver.get(this.url);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();

        if (!url.equals(this.url)) {
            throw new Error("Wrong page, want: " + this.url + ", got: " + url);
        }
    }

    public String getUrl() {
        return url;
    }

    /**
     * Avoid raising an exception on resolving the WebElement during the tests
     *
     * @param elem WebElement to test
     * @return Boolean
     */
    public Boolean doesElementExist(WebElement elem) {
        try {
            return elem.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
