package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static managers.Hooks.datetimegen;


public class AuthenticationPage {
    private final static Logger logger = Logger.getLogger(AuthenticationPage.class.getName());

    public AuthenticationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#SubmitCreate")
    WebElement createaccount_btn;

    @FindBy(css = "#email_create")
    WebElement createaccount_emailaddress_txt;

    @FindBy(css = "#columns > div.breadcrumb.clearfix > a > i")
    WebElement homeicon_lnk;

    @FindBy(css = "#email")
    WebElement alreadyRegistered_emaiaddress_txt;

    @FindBy(css = "#passwd")
    WebElement alreadyRegistered_password_txt;

    @FindBy(css = "#login_form > div > p.lost_password.form-group > a")
    WebElement alreadyRegistered_forgotPassword_lnk;

    @FindBy(css = "#SubmitLogin")
    WebElement alreadyRegistered_signIn_btn;

    public static String emailAccountType = "";

    public void validateAndClickCreateAccountButtonIsPresent(String button, WebDriver driver) {
        if (button.equalsIgnoreCase("CreateAccount")) {
            createaccount_btn.click();
        } else {
            logger.warning("Page not loaded");
        }
    }

    public void emailAddressToCreateAccount(Map<String, String> data) {
        emailAccountType = data.get("email address");
        if (emailAccountType.equalsIgnoreCase("auto")) {
            emailAccountType = "test" + datetimegen() + "@test.com";
        }
        logger.info("Email Address: " + emailAccountType);
        createaccount_emailaddress_txt.sendKeys(emailAccountType);
    }

    public void alreadyRegisteredUserLogin(Map<String, String> data) {
        alreadyRegistered_emaiaddress_txt.sendKeys(data.get("Email"));
        alreadyRegistered_password_txt.sendKeys(data.get("Password"));
        if (alreadyRegistered_signIn_btn.isEnabled()) {
            alreadyRegistered_signIn_btn.click();
        } else {
            logger.warning("sign-in not enabled");
        }
    }


}
