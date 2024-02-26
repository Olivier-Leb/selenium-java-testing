package example.selenium.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MediaUser extends Page {

    private final AutoLogin login;

    @FindBy(id = "optu")
    private WebElement ratingOption;

    @FindBy(css = "#optu .rating_give")
    private WebElement ratingGiven;

    @FindBy(css = "#optu .ico_star_off")
    private WebElement ratingCancel;

    @FindBy(css = "#optu .optu_1")
    private WebElement userListAdd;

    @FindBy(css = "#optu .optu_1_a")
    private WebElement userListRemove;

    @FindBy(css = "#optu .optu_2")
    private WebElement watchListAdd;

    @FindBy(css = "#optu .optu_2_a")
    private WebElement watchListRemove;

    public MediaUser(WebDriver driver, AutoLogin login) {
        super(driver, "url.media");
        this.login = login;
    }

    @Override
    protected void load() {
        this.login.get();
        super.load();
    }

    public MediaUser openRating() {
        new Actions(driver)
                .moveToElement(this.ratingOption)
                .perform();
        return this;
    }

    public void setRating(int score) {
        this.ratingOption
                .findElement(By.cssSelector(".star .star-" + score + "-"))
                .click();
    }

    public WebElement getRatingGiven() {
        return this.ratingGiven;
    }

    public void cancelRating() {
        this.ratingCancel.click();
    }

    public WebElement getUserListAdd() {
        return this.userListAdd;
    }

    public WebElement getUserListRemove() {
        return this.userListRemove;
    }

    public boolean isInUserList() {
        return this.doesElementExist(this.userListRemove);
    }

    public void addInUserList() {
        this.userListAdd.click();

        // Wait response for icon switch
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(300));
        wait.until(d -> this.userListRemove.isDisplayed());
    }

    public void removeFromUserList() {
        this.userListRemove.click();

        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(300));

        // Wait for confirmation alert popup
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        // Wait response for icon switch
        wait.until(d -> this.userListAdd.isDisplayed());
    }

    public WebElement getWatchListAdd() {
        return this.watchListAdd;
    }

    public WebElement getWatchListRemove() {
        return this.watchListRemove;
    }

    public boolean isInWatchList() {
        return this.doesElementExist(this.watchListRemove);
    }

    public void addInWatchList() {
        this.watchListAdd.click();

        // Wait response for icon switch
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(300));
        wait.until(d -> this.watchListRemove.isDisplayed());
    }

    public void removeFromWatchList() {
        this.watchListRemove.click();

        // Wait response for icon switch
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(300));
        wait.until(d -> this.watchListAdd.isDisplayed());
    }
}
