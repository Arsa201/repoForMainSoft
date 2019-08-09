package Steps;

import CustomTest.SignInTest;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.qatools.allure.annotations.Attachment;


public class Steps {

    private static final String MAIN_URL = "http://automationpractice.com/index.php";
    private static final String LOGIN = "aaaaaaa";
    private static final String PASSWORD = "bbb123";
    private SignInTest loginPage;
    private WebDriver webDriver;

    public Steps() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\webdrivers\\chromedriver.exe");
        webDriver = new ChromeDriver();
        loginPage = new SignInTest(webDriver);

    }

    @Given("^I am on the main application page$")
    public void firstDigit() throws Throwable {
        webDriver.get(MAIN_URL);

    }

    @When("^I login as correct user$")
    public void secondDigit() throws Throwable {
        loginPage.enterLoginAndPass(LOGIN, PASSWORD);
        loginPage.clickSignInButton();
    }

    @Then("^I see error message$")
    public void sum() throws Throwable {

        Assert.assertTrue("Invalid email address.", loginPage.logInErrorPresents());
    }

    @Then("^I pass$")
    public void pass() throws Throwable {

        Assert.assertTrue(true);
    }

    @Then("^I fail$")
    public void fail() throws Throwable {

        Assert.assertTrue(false);
    }

    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    @After
    public void afterClass() {
        makeScreenshot();
        webDriver.quit();
    }
}

