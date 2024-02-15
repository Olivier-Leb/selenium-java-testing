package example.selenium.pages.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MediaInList {
    protected WebElement root;

    public MediaInList(WebElement root) {
        this.root = root;
    }

    public String getTitle() {
        return root.findElement(By.cssSelector(".st_lst_txt h2")).getText();
    }

    public String getYear() {
        return root.findElement(By.xpath("//div[contains(@class, 'stelem')]"))
                .getText()
                .split(",")[0];
    }
    public String getCredit() {
        return root.findElement(By.cssSelector(".stelem p a")).getText();
    }
}
