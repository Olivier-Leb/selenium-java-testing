package example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static example.selenium.utils.PropertyLoader.getConfigValue;
import static example.selenium.utils.PropertyLoader.getLink;

/**
 * Nested components for pages that need logged user
 */
public class AutoLogin extends Page {

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "pass")
    private WebElement password;

    @FindBy(css = "form#log input[type='submit']")
    private WebElement submit;

    @FindBy(css = ".home")
    private WebElement homeScreen;

    public AutoLogin(WebDriver driver) {
        super(driver, "url.login");
    }

    @Override
    protected void load() {
        super.load();

        // Set credentials for login
        this.email.sendKeys(getConfigValue("credential.login"));
        this.password.sendKeys(getConfigValue("credential.password"));
        this.submit.click();

        // Wait for redirection to home page
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(500));
        wait.until(d -> this.homeScreen.isDisplayed());
    }

    @Override
    protected void isLoaded() throws Error {
        String url = this.driver.getCurrentUrl();

        // Successful login go back to home
        if (!url.equals(getLink("home"))) {
            throw new Error("Wrong page, want: " + this.url + ", got: " + url);
        }
    }
}
