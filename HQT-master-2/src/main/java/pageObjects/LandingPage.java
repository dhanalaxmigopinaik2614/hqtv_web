package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LandingPage {

    WebDriver driver;

    @FindBy(css = "#header > div.nav > div > div > nav > div.header_user_info > a")
    public WebElement signIn_lnk;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean verifySignInLinkIsPresent() {

        return signIn_lnk.isDisplayed();
    }

    public void click_SignIn(WebDriver driver) {
        signIn_lnk.click();

    }

}
