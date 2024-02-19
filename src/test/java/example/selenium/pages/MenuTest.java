package example.selenium.pages;

import example.selenium.Base;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import example.selenium.utils.Media;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static example.selenium.utils.PropertyLoader.getLink;

public class MenuTest extends Base {

    private Menu menu;

    @BeforeEach
    public void prepareComponents() {
        this.setDefaultWebDriver();

        this.menu = new Menu(this.driver);
    }

    @Test
    public void testMenuMain() {
        this.menu.get();

        this.menu.openMenuMain();
        assertTrue(this.menu.doesElementExist(this.menu.getMenuMain()), "Main menu not found");

        List<String> expectedLinks = Arrays.asList("home", "help", "login");
        List<WebElement> links = this.menu.getMenuMainLinks();
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
        this.menu.get();

        this.menu.openMenuMedia();
        assertTrue(this.menu.doesElementExist(this.menu.getMenuMedia()), "Media menu not found");

        List<String> expectedLinks = Media.getLinks();
        List<WebElement> links = this.menu.getMenuMediaSubLinks();
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
