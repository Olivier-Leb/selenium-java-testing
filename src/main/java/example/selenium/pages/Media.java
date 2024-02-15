package example.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Media extends Page {

    @FindBy(css = "h1.md_t")
    private WebElement title;

    // Multiple element are mixed in this one, need to be separated
    @FindBy(css = ".md_st")
    private WebElement subTitleData;

    @FindBy(css = ".md_gen a")
    private List<WebElement> genreList;

    // Another mixed content here
    @FindBy(css = ".md_gen")
    private WebElement countryData;

    @FindBy(css = ".md_2 .media_is a.aa:not(.bo)")
    private List<WebElement> creditList;

    @FindBy(css = ".md_2 .media_is a.bo")
    private WebElement creditLink;

    @FindBy(css = "h4:has(+ .resume)")
    private WebElement summaryTitle;

    @FindBy(css = ".resume")
    private WebElement summary;

    @FindBy(css = ".fiche .i160 img")
    private WebElement image;

    @FindBy(css = ".fancybox-overlay")
    private WebElement preview;

    @FindBy(css = ".fancybox-overlay img.fancybox-image")
    private WebElement previewImage;

    @FindBy(css = ".fancybox-overlay .fancybox-next")
    private WebElement previewNext;

    @FindBy(css = ".fancybox-overlay .fancybox-close")
    private WebElement previewClose;

    @FindBy(css = ".md_img_bis .i45 img")
    private List<WebElement> imageList;

    @FindBy(css = ".md_img_bis a:not(.izoom)")
    private WebElement imageLink;

    //@TODO add Movie, Album category sub-classes for handling their specific page details
    public Media(WebDriver driver) {
        super(driver, "url.media");
    }

    public WebElement getTitle() {
        return this.title;
    }

    public String getSecondaryTitle() {
        return this.subTitleData.getAttribute("innerHTML")
                .split("<br>")[0];
    }

    public String getYear() {
        return this.subTitleData.getAttribute("innerHTML")
                .split("<br>")[1]
                .split("&nbsp;")[0];
    }

    public List<WebElement> getGenreList() {
        return this.genreList;
    }

    public String getCountry() {
        return this.countryData.getAttribute("innerHTML")
                .split("<br>")[1];
    }

    public List<WebElement> getCreditList() {
        return this.creditList;
    }

    public WebElement getCreditLink() {
        return this.creditLink;
    }

    public WebElement getSummaryTitle() {
        return this.summaryTitle;
    }

    public WebElement getSummary() {
        return this.summary;
    }

    public WebElement getImage() {
        return this.image;
    }

    public WebElement getPreview() {
        return this.preview;
    }

    public void openPreview() {
        this.image.click();

        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofSeconds(1));
        wait.until(d -> this.previewImage.isDisplayed());
    }

    public WebElement getPreviewImage() {
        return this.previewImage;
    }

    public Media nextPreviewImage() {
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(500));
        wait.until(d -> this.previewNext.isDisplayed());

        this.previewNext.click();
        return this;
    }

    public void closePreview() {
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(500));
        wait.until(d -> this.previewClose.isDisplayed());

        this.previewClose.click();

        // Wait preview closing
        wait.until(ExpectedConditions.invisibilityOf(this.preview));
    }

    public List<WebElement> getImageList() {
        return this.imageList;
    }

    public WebElement getImageLink() {
        return this.imageLink;
    }
}
