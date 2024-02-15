package example.selenium.pages;

import static org.junit.jupiter.api.Assertions.*;

import example.selenium.Base;
import org.junit.jupiter.api.Test;

public class LoginTest extends Base {

    @Test
    public void testLoginOk() {
        this.setDefaultWebDriver();
        Login p = new Login(this.driver);

        Home homePage = p.submitOk();

        // Login is successful if we return to the home page
        String url = this.driver.getCurrentUrl();
        assertEquals(homePage.getUrl(), url, "Failed redirection after login");
        log.trace("Login done redirected to: " + url);
    }

    @Test
    public void testLoginKo() {
        this.setDefaultWebDriver();
        Login p = new Login(this.driver);

        p.submitKo();
        assertNotNull(p.getErrorMessage(), "Missing error message");
    }
}
