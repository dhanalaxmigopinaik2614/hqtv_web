package managers;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Hooks {
    public static WebDriver driver;
    public final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    public final String INTERNETEXPLORER_DRIVER_PROPERTY = "webdriver.ie.driver";
    public static Properties prop = new Properties();
    private static Logger Log = Logger.getLogger(Hooks.class.getName());
    public static String sFileName = "automationpractice";
    public static String failedOutputPath = "src/test/Failed_Screenshot/";


    public void loadconfigurations() throws IOException {
        File file = new File(
                System.getProperty("src/main/resources/configs/Configuration.properties"));
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);
    }

    @Before
    public static void initializeMethod(Scenario scenario) {
        Log.info("***********  Test Started-" + scenario.getName() + "  ****************");
    }

    public WebDriver init() throws Throwable {
        driver = createDriver();
        getUrl(prop.getProperty("url"));
        return driver;
    }


    public void getUrl(String url) {
        if (url != null) {
            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        } else {
            throw new RuntimeException("url is not specified in Configuration.properties file");
        }
    }


    private WebDriver createDriver() throws Throwable {
        FileInputStream fn = new FileInputStream("src/main/resources/configs/Configuration.properties");
        prop.load(fn);
        if (prop.getProperty("browser").equals("chrome")) {
            System.setProperty(CHROME_DRIVER_PROPERTY, prop.getProperty("chromedriverPath"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--test-type", "--disable-popup-blocking");
            options.addArguments("--incognito");
            options.setAcceptInsecureCerts(true);
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else if (prop.getProperty("browser").equals("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else if (prop.getProperty("browser").equals("iexplorer")) {
            System.setProperty(INTERNETEXPLORER_DRIVER_PROPERTY, prop.getProperty("iedriverPath"));
            driver = new InternetExplorerDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().deleteAllCookies();
        return driver;
    }


    public static void tearDown() {
        driver.quit();
    }

    public static String datetimegen() {
        String datetime = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        datetime = dtf.format(now);
        return datetime;
    }

    @After
    /**
     * Embed a screenshot in test report if scenario is marked as failed
     */
    public void embedScreenshot(Scenario scenario) throws Throwable {

        if (scenario.isFailed()) {
            String imgfileExt = "screenshot.png";
            String randomdatetime = datetimegen().toString();
            String FileName = failedOutputPath + randomdatetime + imgfileExt;
            try {
                Log.info("************" + scenario.getName() + "End**************");
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(FileName));
                tearDown();

            } catch (WebDriverException e) {
                System.out.println(e.getMessage());
            }

        } else {
            tearDown();
        }

    }
}
