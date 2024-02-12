package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.PropertyLoader.getConfigValue;

public class Login extends Page {

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "pass")
    private WebElement password;

    @FindBy(css = "form#log input[type='submit']")
    private WebElement submit;


    @FindBy(id = "mesg_log")
    private WebElement error;

    public Login(WebDriver driver) {
        super(driver, "url.login");
    }

    public Login setCredentials(String login, String password) {
        this.email.sendKeys(login);
        this.password.sendKeys(password);
        return this;
    }

    public Home submitOk() {
        this.setCredentials(getConfigValue("credential.login"), getConfigValue("credential.password"));
        this.submit.click();

        return new Home(this.driver);
    }

    public Login submitKo() {
        this.setCredentials("Bad login", "*");
        this.submit.click();

        return this;
    }

    public String getErrorMessage() {
        return this.error.getText();
    }
}