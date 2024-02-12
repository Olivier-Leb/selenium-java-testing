package pages;

import java.util.List;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.component.MediaInList;

public class Search extends Page {

    @FindBy(id = "f_rech")
    @CacheLookup
    private WebElement form;

    @FindBy(css = "#f_rech input[name='s']")
    private WebElement searchInput;

    @FindBy(css = "#f_rech input[name='year']")
    private WebElement yearInput;

    @FindBy(css = "#f_rech select[name='gen']")
    private WebElement genreInput;

    @FindBy(css = "#f_rech #_credit")
    @CacheLookup
    private WebElement creditInput;

    @FindBy(css = "#f_rech .h_bul")
    @CacheLookup
    private WebElement tooltipIcon;

    @FindBy(css = "#f_rech input[type='submit']")
    private WebElement submit;

    public Search(WebDriver driver) {
        super(driver, "url.search");
    }

    public WebElement getForm() {
        return this.form;
    }

    public WebElement getSearchInput() {
        return this.searchInput;
    }

    public WebElement getYearInput() {
        return this.yearInput;
    }

    public WebElement getGenreInput() {
        return this.genreInput;
    }

    public WebElement getCreditInput() {
        return this.creditInput;
    }

    public WebElement getTooltipIcon() {
        return this.tooltipIcon;
    }

    public void setCredit(String Search, String expect) {
        // Set first letter of autocompletion
        this.getCreditInput().sendKeys(Search);

        // Wait for results to display
        WebElement autocomplete = this.driver.findElement(By.cssSelector(".ui-autocomplete"));

        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(800));
        wait.until(d -> autocomplete.isDisplayed());

        // Select the expected result
        List<WebElement> elements = autocomplete.findElements(By.cssSelector(".ui-menu-item .txt"));
        for (WebElement e : elements) {
            if (expect.equals(e.getText())) {
                new Actions(this.driver)
                        .click(e)
                        .perform();
            }
        }
    }

    // Get the set credit for validation
    public WebElement getChosenCredit() {
        return this.driver.findElement(By.cssSelector("#cred_choice span"));
    }

    public Search displayTooltip() {
        new Actions(this.driver)
                .moveToElement(this.tooltipIcon)
                .perform();

        return this;
    }

    public WebElement getTooltip() {
        WebElement tooltip = this.tooltipIcon.findElement(By.cssSelector(".h_txt"));
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(300));
        wait.until(d -> tooltip.isDisplayed());

        return tooltip;
    }

    public void doSubmit() {
        this.submit.click();
    }

    public List<MediaInList> getResults() {
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofSeconds(1));
        wait.until(d -> this.driver.findElement(By.className("lst")).isDisplayed());

        return this.driver.findElements(By.className("st_lst"))
                .stream()
                .map(e -> new MediaInList(e))
                .toList();
    }
}
