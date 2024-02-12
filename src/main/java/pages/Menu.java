package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Menu extends Page {

    public String urlSerieSearch = "/serie/search";

    @FindBy(css = "#header .menuc[data-id*='menu']")
    private WebElement menuMainIcon;

    @FindBy(id = "menu")
    private WebElement menuMain;

    @FindBy(css = "#header .menuc[data-id*='menu_media']")
    private WebElement menuMediaIcon;

    @FindBy(id = "menu_media")
    private WebElement menuMedia;

    public Menu(WebDriver driver) {
        super(driver, "url.home");
    }

    public Menu openMenuMain() {
        this.menuMainIcon.click();

        // Wait for menu to be displayed
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(500));
        wait.until(d -> this.menuMain.isDisplayed());

        return this;
    }

    public WebElement getMenuMain() {
        return this.menuMain;
    }

    public List<WebElement> getMenuMainLinks() {
        return this.menuMain.findElements(By.cssSelector("a"));
    }

    public Menu openMenuMedia() {
        this.menuMediaIcon.click();

        // Wait for menu to be displayed
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofMillis(500));
        wait.until(d -> this.menuMedia.isDisplayed());

        return this;
    }

    public WebElement getMenuMedia() {
        return this.menuMedia;
    }

    public List<WebElement> getMenuMediaSubLinks() {
        return this.menuMedia.findElements(By.cssSelector("a"));
    }
}
