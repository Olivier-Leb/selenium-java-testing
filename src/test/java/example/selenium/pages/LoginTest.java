package example.selenium.pages;

import static org.junit.jupiter.api.Assertions.*;

import example.selenium.Base;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest extends Base {
    
    private Login login;

    @BeforeEach
    public void prepareComponents() {
        this.setDefaultWebDriver();

        this.login = new Login(this.driver);
    }
    
    @Test
    public void testLoginOk() {
        this.login.get();

        Home homePage = this.login.submitOk();

        // Login is successful if we return to the home page
        String url = this.driver.getCurrentUrl();
        assertEquals(homePage.getUrl(), url, "Failed redirection after login");
        log.trace("Login done redirected to: " + url);
    }

    @Test
    public void testLoginKo() {
        this.login.get();

        this.login.submitKo();
        assertNotNull(this.login.getErrorMessage(), "Missing error message");
    }
}
