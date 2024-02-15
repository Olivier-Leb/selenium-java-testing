package example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Home extends Page {
    
    @FindBy(className = "home_news")
    private WebElement news;

    @FindBy(css = ".home_news h2")
    private WebElement newsTitle;

    @FindBy(css = ".home_news p:first-of-type")
    private WebElement newsText;

    @FindBy(css = ".home_news p:nth-child(2) a")
    private List<WebElement> authLinks;

    @FindBy(className = "home_media") 
    private WebElement media;

    @FindBy(css = ".home_media .medias")
    private List<WebElement> mediaCategories;

    public Home(WebDriver driver) {
        super(driver, "url.home");
    }

    public WebElement getNews() {
        return this.news;
    }

    public WebElement getNewsTitle() {
        return this.newsTitle;
    }

    public WebElement getNewsText() {
        return this.newsText;
    }

    public List<WebElement> getAuthLinks() {
        return this.authLinks;
    }

    public WebElement getMediaSection() {
        return this.media;
    }

    public List<WebElement> getMediaCategories() {
        return this.mediaCategories;
    }

    public List<WebElement> getMediaByCategory(WebElement e) {
        return e.findElements(By.cssSelector(".media"));
    }

    public String getMediaTitle(WebElement e) {
        return e.findElement(By.cssSelector("h4")).getText().trim();
    }

    public WebElement getMediaImage(WebElement e) {
        return e.findElement(By.cssSelector("img"));
    }
}
