package tests;


import demoEuroBank.Ajax;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.b2b.testfactory.TestFactoryUtils;


/**
 * Unit test for simple App.
 */
public class AppTestTF {
    private WebDriver driver;
    private boolean init = true;
    private Ajax method;

    private void init() {
        if (init) {
//            driver = TestFactoryUtils.getDriver("chromeDriver", ChromeDriver.class);
//        this.method = new TestMethods(driver);
//            PageFactory.initElements(driver, App.class);
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\weronika\\IdeaProjects\\tf\\ProjectDemoEuroBank\\src\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
            PageFactory.initElements(driver, this);

            method = new Ajax(driver);
//                        driver.manage().window().maximize();
            init = false;
        }
    }

    @Test
    @Parameters({"id", "password"})
    public void loginPageTest(String id, String password) {

        init();

        driver.get("http://localhost:8080/#!login");
        signIn(id, password);
        close();
    }


    //    @After
    public void close() {
        driver.quit();
    }

    //LOGIN PAGE
    @FindBy(xpath = "//*[@id=\"gwt-uid-5\"]")
    private WebElement idText;

    @FindBy (xpath = "//*[@id=\"login_next\"]")
    private WebElement nextButton;

    @FindBy (xpath = "//*[@id=\"gwt-uid-7\"]")
    private WebElement passwordText;
    @FindBy (css = "#ROOT-2521314 > div > div.v-verticallayout.v-layout.v-vertical.v-widget.v-has-width.v-has-height.v-margin-top.v-margin-right.v-margin-bottom.v-margin-left > div > div:nth-child(1) > div > div.v-panel-content.v-scrollable > div > div.v-slot.v-slot-primary.v-align-center > div")
    private WebElement loginButton;

    private void enterId(String id){
        idText.sendKeys(id);
    }

    private void clickNextButton(){
        nextButton.click();
    }

    private void enterPassword(String password){
        passwordText.sendKeys(password);
    }

    private void clickLoginButton(){
        method.waitForIt(passwordText);
        loginButton.click();
    }

    public void signIn(String id, String password){
        method.waitForIt(idText);
        enterId(id);
//        clickNextButton();
//        method.waitForIt(passwordText);
        enterPassword(password);
//        method
        clickLoginButton();
    }

}
