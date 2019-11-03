package step_definitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.Hooks;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.AuthenticationPage;
import pageObjects.CreateAnAccountPage;
import pageObjects.LandingPage;

import java.util.Map;

import static pageObjects.AuthenticationPage.emailAccountType;

public class HOOQStepDefinitions {

    static WebDriver driver;

    @Given("^User opens URL$")
    public void the_user_hits_DATH_URL() throws Throwable {
        Hooks hooks = new Hooks();
        driver = hooks.init();

    }

    @Given("^User clicks on \"(.*)\"$")
    public void clickOnSignIn(String option) throws Throwable {
        LandingPage landingPage = new LandingPage(driver);
        if (option.equalsIgnoreCase("SignIn")) {
            landingPage.verifySignInLinkIsPresent();
            landingPage.click_SignIn(driver);

        }
    }

    @Given("^User validates and clicks \"(.*)\"$")
    public void createAccountAction(String button) throws Throwable {
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        if (button.equalsIgnoreCase("CreateAccount")) {
            authenticationPage.validateAndClickCreateAccountButtonIsPresent(button, driver);
        }

    }

    @Then("^User is navigated to \"(.*)\" page$")
    public void validateCreateAccount(String button) throws Throwable {
        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        if (button.equalsIgnoreCase("Create An Account")) {
            Assert.assertTrue(createAnAccountPage.validateRegisterButtonIsPresent());
        }

    }

    @When("^User enters \"(.*)\"$")
    public void enterUserDetails(String option, DataTable dataTable) throws Throwable {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        if (option.equalsIgnoreCase("personal information")) {
            createAnAccountPage.enterPersonalInformation(data, driver);
        }
        if (option.equalsIgnoreCase("address")) {
            createAnAccountPage.enterAddress(data);
        }
        if (option.equalsIgnoreCase("login credentials")) {
            authenticationPage.alreadyRegisteredUserLogin(data);
        }

    }

    @When("^User clicks on Register$")
    public void register() {
        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        createAnAccountPage.clickRegisterButton();
    }

    @Then("^Validate user account is created$")
    public void accountCreationValidation() {
        CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage(driver);
        createAnAccountPage.validateMessage();
    }

    @When("^User enters user details$")
    public void enterEmailAddress(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.emailAddressToCreateAccount(data);

        com.cucumber.listener.Reporter.addStepLog("<html>" +
                "<head>" +
                "<style>" +
                "table, th, td {" +
                "    border: 1px solid black;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<table style=\"width:50%\"><tr><th><b>NewEmailId</b></tr><tr>" +
                "    <td>" + emailAccountType + "</td>" +
                "  </tr>" +
                "</table></body>" +
                "</html>");

    }

}
