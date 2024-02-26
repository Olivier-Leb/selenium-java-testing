package example.selenium.pages;

import example.selenium.Base;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediaUserTest extends Base {

    private MediaUser mediaUser;

    @BeforeEach
    public void prepareComponents() {
        this.setDefaultWebDriver();

        this.mediaUser = new MediaUser(this.driver, new AutoLogin(this.driver));
    }

    @Test
    public void testRatingSet() {
        this.mediaUser.get();

        log.info("Current rating: {}", this.mediaUser.getRatingGiven().getText());

        int score = 2;
        this.mediaUser
                .openRating()
                .setRating(score);
        assertEquals(score, Integer.parseInt(this.mediaUser.getRatingGiven().getText()), "Wrong rating set");
        log.info("Change for: {}", score);

        score = 10;
        this.mediaUser
                .openRating()
                .setRating(score);
        assertEquals(score, Integer.parseInt(this.mediaUser.getRatingGiven().getText()), "Wrong rating set");
        log.info("Change for: {}", score);
    }

    @Test
    public void testRatingCancel() {
        this.mediaUser.get();

        String currentRating = this.mediaUser.getRatingGiven().getText();
        // Reset media state: if no rating, set one
        if (currentRating.equals("-")) {
            this.mediaUser
                    .openRating()
                    .setRating(7);
        }
        log.info("Rating was: {}", currentRating);

        this.mediaUser
                .openRating()
                .cancelRating();

        assertEquals("-", this.mediaUser.getRatingGiven().getText(), "Rating not canceled");
        log.info("Rating is now: {}", this.mediaUser.getRatingGiven().getText());
    }

    @Test
    public void testUserListAdd() {
        this.mediaUser.get();

        // Reset media state: if media already in user list, remove it
        if (this.mediaUser.isInUserList()) {
            this.mediaUser.removeFromUserList();
        }

        this.mediaUser.addInUserList();
        assertTrue(this.mediaUser.doesElementExist(this.mediaUser.getUserListRemove()), "Error, media not in user list");
    }

    @Test
    public void testUserListRemove() {
        this.mediaUser.get();

        // Reset media state: if media not in user list, add it
        if (!this.mediaUser.isInUserList()) {
            this.mediaUser.addInUserList();
        }

        this.mediaUser.removeFromUserList();
        assertTrue(this.mediaUser.doesElementExist(this.mediaUser.getUserListAdd()), "Error, media not removed from user list");
    }

    @Test
    public void testWatchListAdd() {
        this.mediaUser.get();

        // Reset media state: if media in watch list, remove it
        if (this.mediaUser.isInWatchList()) {
            this.mediaUser.removeFromWatchList();
        }

        this.mediaUser.addInWatchList();
        assertTrue(this.mediaUser.doesElementExist(this.mediaUser.getWatchListRemove()), "Error, media not in watch list");
    }

    @Test
    public void testWatchListRemove() {
        this.mediaUser.get();

        // Reset media state: if media not in watch list, add it
        if (!this.mediaUser.isInWatchList()) {
            this.mediaUser.addInWatchList();
        }

        this.mediaUser.removeFromWatchList();
        assertTrue(this.mediaUser.doesElementExist(this.mediaUser.getWatchListAdd()), "Error, media not removed from watch list");
    }
}