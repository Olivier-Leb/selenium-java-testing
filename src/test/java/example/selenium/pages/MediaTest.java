package example.selenium.pages;

import example.selenium.Base;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static example.selenium.utils.PropertyLoader.getLink;

public class MediaTest extends Base {

    private Media media;

    @BeforeEach
    public void prepareComponents() {
        this.setDefaultWebDriver();

        this.media = new Media(this.driver);
    }

    @Test
    public void testMediaData() {
        this.media.get();

        log.info(
                "Media tested: {} ({} - {}), {}",
                this.media.getTitle().getText(), this.media.getSecondaryTitle(), this.media.getYear(),
                this.media.getCountry()
        );

        assertTrue(this.media.doesElementExist(this.media.getTitle()), "Title not found");
        assertFalse(this.media.getTitle().getText().isEmpty(), "Title empty");
        assertFalse(this.media.getSecondaryTitle().isEmpty(), "Secondary title empty");
        assertFalse(this.media.getYear().isEmpty(), "Year empty");
        assertFalse(this.media.getCountry().isEmpty(), "Country empty");

        // Genre
        log.info("Media have {} genre", this.media.getGenreList().size());
        assertEquals(2, this.media.getGenreList().size(), "Missing genre: " + this.media.getGenreList().size() + "/2");
        for (WebElement e : this.media.getGenreList()) {
            assertFalse(e.getText().isEmpty(), "Missing genre link text");
        }

        // Credit
        log.info("Media have {} credit", this.media.getCreditList().size());
        assertEquals(5, this.media.getCreditList().size(), "Missing credit: " + this.media.getCreditList().size() + "/5");
        for (WebElement e : this.media.getCreditList()) {
            assertFalse(e.getText().isEmpty(), "Missing credit link text");
        }
        assertTrue(this.media.doesElementExist(this.media.getCreditLink()), "Link to credit list not found");
        assertEquals(getLink("media.credit"), this.media.getCreditLink().getAttribute("href"), "Wrong link to credit");

        // Summary
        assertTrue(this.media.doesElementExist(this.media.getSummaryTitle()), "Summary title not found");
        assertFalse(this.media.getSummaryTitle().getText().isEmpty(), "Summary title empty");
        assertTrue(this.media.doesElementExist(this.media.getSummary()), "Summary not found");
        assertFalse(this.media.getSummary().getText().isEmpty(), "Summary empty");
        log.info("Summary has {} words", this.media.getSummary().getText().split("\\s+").length);
    }

    @Test
    public void testImage() {
        this.media.get();

        assertTrue(this.media.doesElementExist(this.media.getImage()), "Image block not found");

        List<WebElement> images = this.media.getImageList();
        log.info("Image block have {} images", images.size());
        assertFalse(images.isEmpty(), "Missing secondary images");
        assertTrue(this.media.doesElementExist(this.media.getImageLink()), "Link to images list not found");
        assertEquals(getLink("media.images"), this.media.getImageLink().getAttribute("href"), "Wrong link to images");
    }

    @Test
    public void testImagePreview() {
        this.media.get();

        this.media.openPreview();

        String firstImage = this.media.getPreviewImage().getAttribute("src");
        assertTrue(this.media.doesElementExist(this.media.getPreviewImage()), "Image preview not found");
        log.info("Image in preview: {}", firstImage);

        // Cycle through the images
        log.trace("Next image x3");
        this.media.nextPreviewImage()
                .nextPreviewImage()
                .nextPreviewImage();
        String nextImage = this.media.getPreviewImage().getAttribute("src");
        assertTrue(this.media.doesElementExist(this.media.getPreviewImage()), "Image preview not found");
        log.info("Image in preview: {}", nextImage);
        assertNotEquals(firstImage, nextImage, "Image in preview didn't change");

        this.media.closePreview();
        assertFalse(this.media.doesElementExist(this.media.getPreview()), "Preview still displayed");
    }
}