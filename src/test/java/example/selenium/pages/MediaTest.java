package example.selenium.pages;

import example.selenium.Base;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static example.selenium.utils.PropertyLoader.getLink;

public class MediaTest extends Base {

    @Test
    public void testMediaData() {
        this.setDefaultWebDriver();
        Media p = new Media(this.driver);

        log.info(
                "Media tested: {} ({} - {}), {}",
                p.getTitle().getText(), p.getSecondaryTitle(), p.getYear(),
                p.getCountry()
        );

        assertTrue(p.doesElementExist(p.getTitle()), "Title not found");
        assertFalse(p.getTitle().getText().isEmpty(), "Title empty");
        assertFalse(p.getSecondaryTitle().isEmpty(), "Secondary title empty");
        assertFalse(p.getYear().isEmpty(), "Year empty");
        assertFalse(p.getCountry().isEmpty(), "Country empty");

        // Genre
        log.info("Media have {} genre", p.getGenreList().size());
        assertEquals(2, p.getGenreList().size(), "Missing genre: " + p.getGenreList().size() + "/2");
        for (WebElement e : p.getGenreList()) {
            assertFalse(e.getText().isEmpty(), "Missing genre link text");
        }

        // Credit
        log.info("Media have {} credit", p.getCreditList().size());
        assertEquals(5, p.getCreditList().size(), "Missing credit: " + p.getCreditList().size() + "/5");
        for (WebElement e : p.getCreditList()) {
            assertFalse(e.getText().isEmpty(), "Missing credit link text");
        }
        assertTrue(p.doesElementExist(p.getCreditLink()), "Link to credit list not found");
        assertEquals(getLink("media.credit"), p.getCreditLink().getAttribute("href"), "Wrong link to credit");

        // Summary
        assertTrue(p.doesElementExist(p.getSummaryTitle()), "Summary title not found");
        assertFalse(p.getSummaryTitle().getText().isEmpty(), "Summary title empty");
        assertTrue(p.doesElementExist(p.getSummary()), "Summary not found");
        assertFalse(p.getSummary().getText().isEmpty(), "Summary empty");
        log.info("Summary has {} words", p.getSummary().getText().split("\\s+").length);
    }

    @Test
    public void testImage() {
        this.setDefaultWebDriver();
        Media p = new Media(this.driver);

        assertTrue(p.doesElementExist(p.getImage()), "Image block not found");

        List<WebElement> images = p.getImageList();
        log.info("Image block have {} images", images.size());
        assertFalse(images.isEmpty(), "Missing secondary images");
        assertTrue(p.doesElementExist(p.getImageLink()), "Link to images list not found");
        assertEquals(getLink("media.images"), p.getImageLink().getAttribute("href"), "Wrong link to images");
    }

    @Test
    public void testImagePreview() {
        this.setDefaultWebDriver();
        Media p = new Media(this.driver);

        p.openPreview();

        String firstImage = p.getPreviewImage().getAttribute("src");
        assertTrue(p.doesElementExist(p.getPreviewImage()), "Image preview not found");
        log.info("Image in preview: {}", firstImage);

        // Cycle through the images
        log.trace("Next image x3");
        p.nextPreviewImage()
                .nextPreviewImage()
                .nextPreviewImage();
        String nextImage = p.getPreviewImage().getAttribute("src");
        assertTrue(p.doesElementExist(p.getPreviewImage()), "Image preview not found");
        log.info("Image in preview: {}", nextImage);
        assertNotEquals(firstImage, nextImage, "Image in preview didn't change");

        p.closePreview();
        assertFalse(p.doesElementExist(p.getPreview()), "Preview still displayed");
    }
}
