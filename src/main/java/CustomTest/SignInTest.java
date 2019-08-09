package CustomTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInTest {
    private WebDriver driver;


    @FindBy(css = "#email")
    private WebElement loginField;

    @FindBy(css = "#passwd")
    private WebElement passwordField;

    @FindBy(css = "#SubmitLogin")
    private WebElement submitLogin;

    @FindBy(css = "#center_column > div.alert.alert-danger")
    private WebElement logInErrAlert;

    public SignInTest(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
        this.driver = webdriver;
    }

    public void enterLoginAndPass(String login, String password) {
        loginField.clear();
        loginField.sendKeys(login);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSignInButton() {
        submitLogin.click();
    }

    public boolean logInErrorPresents() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(logInErrAlert)).isDisplayed();
    }


}
