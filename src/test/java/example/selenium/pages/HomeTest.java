package example.selenium.pages;

import example.selenium.Base;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static example.selenium.utils.PropertyLoader.getLink;

public class HomeTest extends Base {

    private Home home;

    @BeforeEach
    public void prepareComponents() {
        this.setDefaultWebDriver();

        this.home = new Home(this.driver);
    }

    @Test
    public void testNewsSection() {
        this.home.get();

        // The news bloc must be displayed
        assertTrue(this.home.doesElementExist(this.home.getNews()), "News bloc not found");
        assertTrue(this.home.doesElementExist(this.home.getNewsTitle()), "News title not found");
        assertTrue(this.home.doesElementExist(this.home.getNewsText()), "News text not found");

        assertEquals(3, this.home.getAuthLinks().size(), "Missing auth links: " + this.home.getAuthLinks().size() + "/3");

        assertEquals(
                "Google",
                this.home.getAuthLinks().get(0).getText().trim(),
                "Missing Google connect link"
        );
        assertEquals(
                getLink("login"),
                this.home.getAuthLinks().get(1).getAttribute("href"),
                "Missing login link"
        );
        assertEquals(
                getLink("register"),
                this.home.getAuthLinks().get(2).getAttribute("href"),
                "Missing registration link"
        );
    }

    @Test
    public void testMediaSection() {
        this.home.get();

        // The Media bloc must be displayed
        assertTrue(this.home.doesElementExist(this.home.getMediaSection()), "Media bloc not found");

        // and contained 6 categories
        List<WebElement> mediaCategories = this.home.getMediaCategories();
        Base.log.info("Media preview section have {}/6 categories", mediaCategories.size());
        assertEquals(6, mediaCategories.size(), "Missing categories in Media preview");

        // Each category must have 3 media
        for (WebElement c : mediaCategories) {
            List<WebElement> media = this.home.getMediaByCategory(c);

            Base.log.info("Category '{}' have {}/3 media", this.home.getMediaTitle(c), media.size());
            assertEquals(3, media.size(), "Missing media in category '" + this.home.getMediaTitle(c) + "'");

            // Each media must have one image
            for (WebElement m : media) {
                assertTrue(this.home.doesElementExist(this.home.getMediaImage(m)), "Missing image for a media");
            }
        }
    }
}
