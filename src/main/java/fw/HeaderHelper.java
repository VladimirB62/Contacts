package fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderHelper extends HelperBase {

    public HeaderHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginLinkPresent(){
        return isElemententPresent(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public void clickOnSignOutButton() {
        click(By.xpath("//button[contains(.,'Sign Out"));
    }

    public boolean isSignOutButtonPresent() {
        return isElemententPresent(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public void clickOnAddLink() {
        click(By.cssSelector("a:nth-child(5)"));
    }


}
