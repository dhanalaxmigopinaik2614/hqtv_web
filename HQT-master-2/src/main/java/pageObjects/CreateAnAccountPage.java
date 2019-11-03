package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateAnAccountPage {

    private final static Logger logger = Logger.getLogger(AuthenticationPage.class.getName());

    public CreateAnAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#submitAccount")
    WebElement createAccount_register_btn;

    @FindBy(css = "#id_gender1")
    WebElement createAccount_mr_radiobtn;

    @FindBy(css = "#id_gender2")
    WebElement createAccount_mrs_radiobtn;

    @FindBy(css = "#customer_firstname")
    WebElement createAccount_firstName_txt;

    @FindBy(css = "#customer_lastname")
    WebElement createAccount_lastName_txt;

    @FindBy(css = "#email")
    WebElement createAccount_emaiaddress_txt;

    @FindBy(css = "#passwd")
    WebElement createAccount_password_txt;

    @FindBy(css = "#days")
    WebElement createAccount_dateofbirth_day_drpdwn;

    @FindBy(css = "#uniform-days")
    WebElement DAYDROP;

    @FindBy(css = "#months")
    WebElement createAccount_dateofbirth_month_drpdwn;

    @FindBy(css = "#years")
    WebElement createAccount_dateofbirth_year_drpdwn;

    @FindBy(css = "#firstname")
    WebElement createAccount_address_firstname_txt;

    @FindBy(css = "#lastname")
    WebElement createAccount_address_lastname_txt;

    @FindBy(css = "#company")
    WebElement createAccount_address_company_txt;

    @FindBy(css = "#address1")
    WebElement createAccount_address_txt;

    @FindBy(css = "#city")
    WebElement createAccount_address_city_txt;

    @FindBy(css = "#id_state")
    WebElement createAccount_address_state_drpdwn;

    @FindBy(css = "#postcode")
    WebElement createAccount_address_postalcode_txt;

    @FindBy(css = "#id_country")
    WebElement createAccount_address_country_drpdwn;

    @FindBy(css = "#other")
    WebElement createAccount_address_addinfo_txt;

    @FindBy(css = "#phone")
    WebElement createAccount_address_homeph_txt;

    @FindBy(css = "#phone_mobile")
    WebElement createAccount_address_mobph_txt;

    @FindBy(css = "#alias")
    WebElement createAccount_address_alias_txt;

    @FindBy(css = "#center_column > p")
    WebElement accountCreationmsg;

    public boolean validateRegisterButtonIsPresent()  {
        logger.info("Validating whether the register button is present");
        return createAccount_register_btn.isDisplayed();
    }

    public void enterPersonalInformation(Map<String, String> data,WebDriver driver) {

        logger.info("Entering Information To Create a New Account");
        if (data.get("Title").equalsIgnoreCase("Mr")) {
            createAccount_mr_radiobtn.click();
        } else {
            createAccount_mrs_radiobtn.click();
        }
        createAccount_firstName_txt.sendKeys(data.get("FirstName"));
        createAccount_lastName_txt.sendKeys(data.get("LastName"));
        createAccount_password_txt.sendKeys(data.get("Password"));
        createAccount_password_txt.sendKeys("\t" + "\t" + "\t" + "\t");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        setDOB(data);
        logger.info("New Account Information Entered Successfully.");
    }

    public void enterAddress(Map<String, String> data) {
        logger.info("Entering Address for New Account Setup.");
        createAccount_address_company_txt.sendKeys(data.get("Company"));
        createAccount_address_txt.sendKeys(data.get("Address"));
        createAccount_address_city_txt.sendKeys(data.get("City"));
        Select selectState = new Select(createAccount_address_state_drpdwn);
        selectState.selectByVisibleText(data.get("State"));
        createAccount_address_postalcode_txt.sendKeys(data.get("PostalCode"));
        createAccount_address_addinfo_txt.sendKeys(data.get("AdditionalInfo"));
        createAccount_address_mobph_txt.sendKeys(data.get("MobilePh"));
        createAccount_address_alias_txt.sendKeys(data.get("AliasAddress"));
        logger.info("Entered Address for New Account Setup Successfully.");

    }


    public void setDOB(Map<String, String> data) {

        logger.info("Set the Date Of Birth.");
        if (DAYDROP.isDisplayed()) {
            DAYDROP.click();
        }
        Select selectDate = new Select(createAccount_dateofbirth_day_drpdwn);
        createAccount_dateofbirth_day_drpdwn.click();
        String[] dob = data.get("DateOfBirth").split("/");
        String dd = dob[0];
        String mm = dob[1];
        String yy = dob[2];
        selectDate.selectByValue(dd);

        Select selectMonth = new Select(createAccount_dateofbirth_month_drpdwn);
        createAccount_dateofbirth_month_drpdwn.click();
        selectMonth.selectByValue("1");

        Select selectYear = new Select(createAccount_dateofbirth_year_drpdwn);
        createAccount_dateofbirth_year_drpdwn.click();
        selectYear.selectByValue(yy);

        logger.info("Successfully set the Date Of Birth.");
    }


    public void clickRegisterButton(){
        if (createAccount_register_btn.isEnabled()) {
            createAccount_register_btn.click();
            logger.info("Successfully clicked on Register Button.");
        } else {
            logger.log(Level.SEVERE, "Seems register button has moved.");
        }
    }

    public void validateMessage() {
        String text= "Welcome to your account. Here you can manage all of your personal information and orders.";
        Assert.assertTrue(accountCreationmsg.getText().contains(text));
        logger.info("Successfully created a new Account.");
    }

}
