import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TestClass {
    private static WebDriver driver;

    @Test
    public void E1_verifyThatContactUsFormSendsSuccessfully() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
        WebElement ContactUs = driver.findElement(By.cssSelector("#contact-link > a"));
        ContactUs.click();

        WebElement subjectHeading = driver.findElement(By.cssSelector("#id_contact > option:nth-child(2)"));
        subjectHeading.click();

        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        emailField.sendKeys("arsgrendal@yandex.ru");

        WebElement id_orderField = driver.findElement(By.cssSelector("#id_order"));
        id_orderField.sendKeys("758185");

        WebElement fileUploadField = driver.findElement(By.cssSelector("#fileUpload"));
        fileUploadField.sendKeys("C:\\Users\\Ars\\Desktop\\123.txt");

        WebElement textMessageField = driver.findElement(By.cssSelector("#message"));
        textMessageField.sendKeys("lalala");

        WebElement sendButton = driver.findElement(By.cssSelector("#submitMessage"));
        sendButton.click();

        WebElement successfulSent = (new WebDriverWait(driver, 15).until(presenceOfElementLocated(By.cssSelector("#center_column > p"))));
        Assert.assertEquals("Your message has been successfully sent to our team.", successfulSent.getText());
        System.out.println("1st test in task passed");

        driver.quit();
    }

    @Test
    public void E2_verifyThatErrorMessageAppearsIfMessageAreaIsempty() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        WebElement ContactUs = driver.findElement(By.cssSelector("#contact-link > a"));
        ContactUs.click();

        WebElement subjectHeading = driver.findElement(By.cssSelector("#id_contact > option:nth-child(2)"));
        subjectHeading.click();

        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        emailField.sendKeys("arsgrendal@yandex.ru");

        WebElement id_orderField = driver.findElement(By.cssSelector("#id_order"));
        id_orderField.sendKeys("758185");

        WebElement fileUploadField = driver.findElement(By.cssSelector("#fileUpload"));
        fileUploadField.sendKeys("C:\\Users\\Ars\\Desktop\\123.txt");

        //WebElement textMessageField = driver.findElement(By.cssSelector("#message"));
        //textMessageField.sendKeys("lalala");

        WebElement sendButton = driver.findElement(By.cssSelector("#submitMessage"));
        sendButton.click();

        WebElement sentWithMessageErr = (new WebDriverWait(driver, 15).until(presenceOfElementLocated(By.cssSelector("#center_column > div > ol > li"))));
        Assert.assertEquals("The message cannot be blank.", sentWithMessageErr.getText());
        System.out.println("2nd test in task passed");

        driver.quit();

    }

    @Test
    public void E3_ProceedToCheckout() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");

        WebElement cardButton = driver.findElement(By.cssSelector("#header > div:nth-child(3) > div > div > div:nth-child(3) > div > a"));
        cardButton.click();

        WebElement messageShoppingCardEmpty = (new WebDriverWait(driver, 15).until(presenceOfElementLocated(By.cssSelector("#center_column > p"))));
        Assert.assertEquals("Your shopping cart is empty.", messageShoppingCardEmpty.getText());

        WebElement t_shirtsButton = driver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(3) > a"));
        t_shirtsButton.click();

        /*Actions action = new Actions(driver);
        action.moveByOffset(-114,605).click().build().perform();// so much pain with ajax add to card button*/

        WebElement clickOnProductImage = driver.findElement(By.cssSelector("#center_column > ul > li"));
        clickOnProductImage.click();

        WebElement addToCardButton = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#add_to_cart > button > span"))));
        addToCardButton.click();

        WebElement proceedToCheckoutPopUp = new WebDriverWait(driver, 10).until(visibilityOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")));
        proceedToCheckoutPopUp.click();

        WebElement proceedToCheckout = driver.findElement(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium > span"));
        proceedToCheckout.click();

        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        emailField.sendKeys("arsgrendal@yandex.ru");

        WebElement passwField = driver.findElement(By.cssSelector("#passwd"));
        passwField.sendKeys("Ars1984");

        WebElement submitLogin = driver.findElement(By.cssSelector("#SubmitLogin"));
        submitLogin.click();

        WebElement proceedToCheckoutAfterLogin = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#center_column > form > p > button"))));
        proceedToCheckoutAfterLogin.click();

        WebElement acceptTerms = driver.findElement(By.cssSelector("#cgv"));
        acceptTerms.click();

        WebElement proceedToCheckoutAfterAcceptTerms = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#form > p > button"))));
        proceedToCheckoutAfterAcceptTerms.click();

        WebElement payByBankWire = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#HOOK_PAYMENT > div:nth-child(1) > div > p > a"))));
        payByBankWire.click();

        WebElement confirmOrder = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#cart_navigation > button"))));
        confirmOrder.click();

        /*String orderDetails = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/text()[6]")).getText();
        System.out.println(orderDetails);
        Assert.assertTrue("Text not found!", orderDetails.contains("GPRNLYYMD"));//need get text for comparison in order history*/

        WebElement accountPage = driver.findElement(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(1) > a"));
        accountPage.click();

        WebElement orderHistory = driver.findElement(By.cssSelector("#center_column > div > div:nth-child(1) > ul > li:nth-child(1) > a"));
        orderHistory.click();

        WebElement orderRef = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#order-list > thead > tr > th.first_item.footable-first-column"))));
        Assert.assertEquals("Order reference", orderRef.getText());
        System.out.println("3nd test in task passed");

        driver.quit();


    }

    //------------------firefox

    @Test
    public void E1_verifyThatContactUsFormSendsSuccessfullyFirefox() {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\webdrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
        WebElement ContactUs = driver.findElement(By.cssSelector("#contact-link > a"));
        ContactUs.click();

        WebElement subjectHeading = driver.findElement(By.cssSelector("#id_contact > option:nth-child(2)"));
        subjectHeading.click();

        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        emailField.sendKeys("arsgrendal@yandex.ru");

        WebElement id_orderField = driver.findElement(By.cssSelector("#id_order"));
        id_orderField.sendKeys("758185");

        WebElement fileUploadField = driver.findElement(By.cssSelector("#fileUpload"));
        fileUploadField.sendKeys("C:\\Users\\Ars\\Desktop\\123.txt");

        WebElement textMessageField = driver.findElement(By.cssSelector("#message"));
        textMessageField.sendKeys("lalala");

        WebElement sendButton = driver.findElement(By.cssSelector("#submitMessage"));
        sendButton.click();

        WebElement successfulSent = (new WebDriverWait(driver, 15).until(presenceOfElementLocated(By.cssSelector("#center_column > p"))));
        Assert.assertEquals("Your message has been successfully sent to our team.", successfulSent.getText());
        System.out.println("1st test in task passed");

        driver.quit();
    }

    @Test
    public void E2_verifyThatErrorMessageAppearsIfMessageAreaIsemptyFirefox() {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\webdrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        WebElement ContactUs = driver.findElement(By.cssSelector("#contact-link > a"));
        ContactUs.click();

        WebElement subjectHeading = driver.findElement(By.cssSelector("#id_contact > option:nth-child(2)"));
        subjectHeading.click();

        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        emailField.sendKeys("arsgrendal@yandex.ru");

        WebElement id_orderField = driver.findElement(By.cssSelector("#id_order"));
        id_orderField.sendKeys("758185");

        WebElement fileUploadField = driver.findElement(By.cssSelector("#fileUpload"));
        fileUploadField.sendKeys("C:\\Users\\Ars\\Desktop\\123.txt");

        //WebElement textMessageField = driver.findElement(By.cssSelector("#message"));
        //textMessageField.sendKeys("lalala");

        WebElement sendButton = driver.findElement(By.cssSelector("#submitMessage"));
        sendButton.click();

        WebElement sentWithMessageErr = (new WebDriverWait(driver, 15).until(presenceOfElementLocated(By.cssSelector("#center_column > div > ol > li"))));
        Assert.assertEquals("The message cannot be blank.", sentWithMessageErr.getText());
        System.out.println("2nd test in task passed");

        driver.quit();

    }

    @Test
    public void E3_ProceedToCheckoutFirefox() {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\webdrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");

        WebElement cardButton = driver.findElement(By.cssSelector("#header > div:nth-child(3) > div > div > div:nth-child(3) > div > a"));
        cardButton.click();

        WebElement messageShoppingCardEmpty = (new WebDriverWait(driver, 15).until(presenceOfElementLocated(By.cssSelector("#center_column > p"))));
        Assert.assertEquals("Your shopping cart is empty.", messageShoppingCardEmpty.getText());

        WebElement t_shirtsButton = driver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(3) > a"));
        t_shirtsButton.click();

        /*Actions action = new Actions(driver);
        action.moveByOffset(-114,605).click().build().perform();// so much pain with ajax add to card button*/

        WebElement clickOnProductImage = driver.findElement(By.cssSelector("#center_column > ul > li"));
        clickOnProductImage.click();

        WebElement addToCardButton = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#add_to_cart > button > span"))));
        addToCardButton.click();

        WebElement proceedToCheckoutPopUp = new WebDriverWait(driver, 10).until(visibilityOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")));
        proceedToCheckoutPopUp.click();

        WebElement proceedToCheckout = driver.findElement(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium > span"));
        proceedToCheckout.click();

        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        emailField.sendKeys("arsgrendal@yandex.ru");

        WebElement passwField = driver.findElement(By.cssSelector("#passwd"));
        passwField.sendKeys("Ars1984");

        WebElement submitLogin = driver.findElement(By.cssSelector("#SubmitLogin"));
        submitLogin.click();

        WebElement proceedToCheckoutAfterLogin = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#center_column > form > p > button"))));
        proceedToCheckoutAfterLogin.click();

        WebElement acceptTerms = driver.findElement(By.cssSelector("#cgv"));
        acceptTerms.click();

        WebElement proceedToCheckoutAfterAcceptTerms = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#form > p > button"))));
        proceedToCheckoutAfterAcceptTerms.click();

        WebElement payByBankWire = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#HOOK_PAYMENT > div:nth-child(1) > div > p > a"))));
        payByBankWire.click();

        WebElement confirmOrder = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#cart_navigation > button"))));
        confirmOrder.click();

        /*String orderDetails = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/text()[6]")).getText();
        System.out.println(orderDetails);
        Assert.assertTrue("Text not found!", orderDetails.contains("GPRNLYYMD"));//need get text for comparison in order history*/

        WebElement accountPage = driver.findElement(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(1) > a"));
        accountPage.click();

        WebElement orderHistory = driver.findElement(By.cssSelector("#center_column > div > div:nth-child(1) > ul > li:nth-child(1) > a"));
        orderHistory.click();

        WebElement orderRef = (new WebDriverWait(driver, 10).until(presenceOfElementLocated(By.cssSelector("#order-list > thead > tr > th.first_item.footable-first-column"))));
        Assert.assertEquals("Order reference", orderRef.getText());
        System.out.println("3nd test in task passed");

        driver.quit();

    }
}

