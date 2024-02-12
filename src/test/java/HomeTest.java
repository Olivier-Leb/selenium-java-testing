import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.Home;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static utils.PropertyLoader.getLink;

public class HomeTest extends Base {

    @Test
    public void testNewsSection() {
        this.setDefaultWebDriver();
        Home p = new Home(this.driver);

        // The news bloc must be displayed
        assertTrue(p.doesElementExist(p.getNews()), "News bloc not found");
        assertTrue(p.doesElementExist(p.getNewsTitle()), "News title not found");
        assertTrue(p.doesElementExist(p.getNewsText()), "News text not found");

        assertEquals(3, p.getAuthLinks().size(), "Missing auth links: " + p.getAuthLinks().size() + "/3");

        assertEquals(
                "Google",
                p.getAuthLinks().get(0).getText().trim(),
                "Missing Google connect link"
        );
        assertEquals(
                getLink("login"),
                p.getAuthLinks().get(1).getAttribute("href"),
                "Missing login link"
        );
        assertEquals(
                getLink("register"),
                p.getAuthLinks().get(2).getAttribute("href"),
                "Missing registration link"
        );
    }

    @Test
    public void testMediaSection() {
        this.setDefaultWebDriver();
        Home p = new Home(this.driver);

        // The Media bloc must be displayed
        assertTrue(p.doesElementExist(p.getMediaSection()), "Media bloc not found");

        // and contained 6 categories
        List<WebElement> mediaCategories = p.getMediaCategories();
        log.info("Media preview section have {}/6 categories", mediaCategories.size());
        assertEquals(6, mediaCategories.size(), "Missing categories in Media preview");

        // Each category must have 3 media
        for (WebElement c : mediaCategories) {
            List<WebElement> media = p.getMediaByCategory(c);

            log.info("Category '{}' have {}/3 media", p.getMediaTitle(c), media.size());
            assertEquals(3, media.size(), "Missing media in category '" + p.getMediaTitle(c) + "'");

            // Each media must have one image
            for (WebElement m : media) {
                assertTrue(p.doesElementExist(p.getMediaImage(m)), "Missing image for a media");
            }
        }
    }
}
