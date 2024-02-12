
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.PropertyLoader.getConfigValue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pages.Search;
import pages.component.MediaInList;

public class SearchTest extends Base {

    /**
     * Test if base form components and interactions are here (search field, select, tooltip hover, ...)
     */
    @Test
    public void testForm() {
        this.setDefaultWebDriver();
        Search p = new Search(this.driver);

        assertTrue(p.doesElementExist(p.getForm()), "Form not found");
        assertTrue(p.doesElementExist(p.getSearchInput()), "Search field not found");
        assertTrue(p.doesElementExist(p.getYearInput()), "Year field not found");
        assertTrue(p.doesElementExist(p.getGenreInput()), "Genre field not found");
        assertTrue(p.doesElementExist(p.getCreditInput()), "Credit field not found");
        assertTrue(p.doesElementExist(p.getTooltipIcon()), "Input Tooltip not found");

        WebElement e = p
                .displayTooltip()
                .getTooltip();
        assertTrue(p.doesElementExist(e), "Tooltip not found");
    }

    /**
     * Test the advanced search engine
     */
    @Test
    public void testSearch() {
        this.setDefaultWebDriver();
        Search p = new Search(this.driver);

        // Fill the form
        log.trace("Fill the form");

        String title = getConfigValue("search.title");
        String year = getConfigValue("search.year");
        String creditName = getConfigValue("search.credit");

        p.getSearchInput().sendKeys(title);
        p.getYearInput().sendKeys(year);
        new Select(p.getGenreInput()).selectByValue(getConfigValue("search.genre"));

        // Interact with autocompletion
        p.setCredit(getConfigValue("search.creditStartWith"), creditName);
        log.info("Credit set is: '{}'", p.getChosenCredit().getText());
        assertEquals(creditName, p.getChosenCredit().getText(), "Credit not set or not found.");

        log.trace("Submit");
        p.doSubmit();

        // Validate result, should found 1 item
        List<MediaInList> media = p.getResults();
        assertEquals(1, media.size(), "Wrong result, expected 1, got: " + media.size());

        String resultTitle = media.get(0).getTitle();
        String resultYear = media.get(0).getYear();
        String resultCredit = media.get(0).getCredit();
        log.info("Media found is: '{}' - '{}' - '{}'", resultTitle, resultYear, resultCredit);

        assertTrue(resultTitle.contains(title), "Wrong title, expected '" + title + "', got: '" + resultTitle + "'");
        assertEquals(year, resultYear, "Wrong year");
        assertEquals(creditName, resultCredit, "Wrong credit");
    }
}