package example.selenium.pages;

import example.selenium.Base;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import example.selenium.utils.Media;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static example.selenium.utils.PropertyLoader.getLink;

public class MenuTest extends Base {

    @Test
    public void testMenuMain() {
        this.setDefaultWebDriver();
        Menu p = new Menu(this.driver);

        p.openMenuMain();
        assertTrue(p.doesElementExist(p.getMenuMain()), "Main menu not found");

        List<String> expectedLinks = Arrays.asList("home", "help", "login");
        List<WebElement> links = p.getMenuMainLinks();
        log.info("Main menu have {}/{} links}", links.size(), expectedLinks.size());
        assertEquals(expectedLinks.size(), links.size(), "Missing links in main menu: " + links.size() + "/" + expectedLinks.size());

        for (int i = 0; i < expectedLinks.size(); i++) {
            assertEquals(
                    getLink(expectedLinks.get(i)),
                    links.get(i).getAttribute("href"),
                    "Missing " + expectedLinks.get(i) + " link"
            );
        }
    }

    /**
     * Media menu should have 6 categories, each having the same 3 sub-menus
     */
    @Test
    public void testMenuMedia() {
        this.setDefaultWebDriver();
        Menu p = new Menu(this.driver);

        p.openMenuMedia();
        assertTrue(p.doesElementExist(p.getMenuMedia()), "Media menu not found");
        
        List<String> expectedLinks = Media.getLinks();
        List<WebElement> links = p.getMenuMediaSubLinks();
        log.info("Media menu have {}/{} links", links.size(), expectedLinks.size());
        assertEquals(expectedLinks.size(), links.size(), "Missing links in main menu: " + links.size() + "/" + expectedLinks.size());

        for (int i = 0; i < expectedLinks.size(); i++) {
            assertEquals(
                    expectedLinks.get(i),
                    links.get(i).getAttribute("href"),
                    "Missing " + expectedLinks.get(i) + " link"
            );
        }
    }
}
