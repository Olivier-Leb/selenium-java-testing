package example.selenium.pages;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static example.selenium.utils.PropertyLoader.getConfigValue;

import example.selenium.Base;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import example.selenium.pages.component.MediaInList;

public class SearchTest extends Base {

    private Search search;

    @BeforeEach
    public void prepareComponents() {
        this.setDefaultWebDriver();

        this.search = new Search(this.driver);
    }

    /**
     * Test if base form components and interactions are here (search field, select, tooltip hover, ...)
     */
    @Test
    public void testForm() {
        this.search.get();

        assertTrue(this.search.doesElementExist(this.search.getForm()), "Form not found");
        assertTrue(this.search.doesElementExist(this.search.getSearchInput()), "Search field not found");
        assertTrue(this.search.doesElementExist(this.search.getYearInput()), "Year field not found");
        assertTrue(this.search.doesElementExist(this.search.getGenreInput()), "Genre field not found");
        assertTrue(this.search.doesElementExist(this.search.getCreditInput()), "Credit field not found");
        assertTrue(this.search.doesElementExist(this.search.getTooltipIcon()), "Input Tooltip not found");

        WebElement e = this.search
                .displayTooltip()
                .getTooltip();
        assertTrue(this.search.doesElementExist(e), "Tooltip not found");
    }

    /**
     * Test the advanced search engine
     */
    @Test
    public void testSearch() {
        this.search.get();

        // Fill the form
        log.trace("Fill the form with valid data");

        String title = getConfigValue("search.title");
        String year = getConfigValue("search.year");
        String creditName = getConfigValue("search.credit");

        this.search.getSearchInput().sendKeys(title);
        this.search.getYearInput().sendKeys(year);
        new Select(this.search.getGenreInput()).selectByValue(getConfigValue("search.genre"));

        // Interact with autocompletion
        this.search.setCredit(getConfigValue("search.creditStartWith"), creditName);
        log.info("Credit set is: '{}'", this.search.getChosenCredit().getText());
        assertEquals(creditName, this.search.getChosenCredit().getText(), "Credit not set or not found.");

        log.trace("Submit");
        this.search.doSubmit();

        // Validate result, should found 1 item
        List<MediaInList> media = this.search.getResults();
        assertEquals(1, media.size(), "Wrong result, expected 1, got: " + media.size());

        String resultTitle = media.get(0).getTitle();
        String resultYear = media.get(0).getYear();
        String resultCredit = media.get(0).getCredit();
        log.info("Media found is: '{}' - '{}' - '{}'", resultTitle, resultYear, resultCredit);

        assertTrue(resultTitle.contains(title), "Wrong title, expected '" + title + "', got: '" + resultTitle + "'");
        assertEquals(year, resultYear, "Wrong year");
        assertEquals(creditName, resultCredit, "Wrong credit");
    }

    @Test
    public void testSearchKo() {
        this.search.get();

        // Fill the form
        log.trace("Fill the form with random data");

        this.search.getSearchInput().sendKeys("1234567890");
        this.search.getYearInput().sendKeys("1234567890");

        log.trace("Submit");
        this.search.doSubmit();

        // Validate result, should have 0 result and an error message
        List<MediaInList> media = this.search.getResults();
        assertEquals(0, media.size(), "Wrong result, expected 0, got: " + media.size());

        assertTrue(this.search.doesElementExist(this.search.getErrorMesg()), "Error message not found");
        log.info("Search KO message: {}", this.search.getErrorMesg().getText());
    }
}