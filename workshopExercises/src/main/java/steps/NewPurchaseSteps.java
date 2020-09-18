package steps;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import pages.*;
import java.io.File;



public class NewPurchaseSteps {
    HomePage homePage;
    LoginPage loginPage;
    ProductPage productPage;
    OrderPage orderPage;
    WebDriver driver;


    @Given("Opened https://prod-kurs.coderslab.pl/index.php page$")
    public void openApplicationPage() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php");
    }

    @And("^User is signed in$")
    public void logInUser() {
        homePage = new HomePage(driver);
        homePage.goGoToSignInPage();
        loginPage = new LoginPage(driver);
        loginPage.loginAs("AndrewJFreeman@rhyta.com", "freeman");
    }

    @When("^(.*) is searched$")
    public void searchItem(String item) {
//        homePage = new HomePage(driver);
        homePage.searchItemInput(item);
    }

    @And("^Clicked (.*) to move to its page$")
    public void moveToSearchedItemPage(String item) {
        homePage.selectSearchedItem(item);
    }

    @And("^size (.*) and (.*) pieces are chosen$")
    public void selectSize(String size, String number) throws InterruptedException {
        productPage = new ProductPage(driver);
        productPage.selectSize(size);
        Thread.sleep(2000);
        productPage.inputQuantity(number);
        Thread.sleep(2000);
    }

    @And("^Products added to basket$")
    public void addToBasket() throws InterruptedException {
        productPage.addToCart();
        Thread.sleep(2000);

    }

    @And("^Proceeded to checkout$")
    public void proceedToCheckout() throws InterruptedException {
        productPage.proceedToCheckout();

    }

    @And("^Confirmed address$")
    public void confirmAddress() {
        orderPage = new OrderPage(driver);
        orderPage.confirmAddress();
    }

    @And("\"Presta shop\" option is chosen")
    public void confirmDeliveryOption() {
        Assert.assertTrue(orderPage.isDeliveryMethodSelected());
        orderPage.confirmDelivery();
    }

    @And("Payment by check is chosen, agreed to terms of service")
    public void confirmPayment() throws InterruptedException {
        orderPage.selectPaymentMethod();
        orderPage.checkTermOfService();
        Thread.sleep(1000);
        orderPage.confirmOrder();
    }

    @Then("^Items were successfully ordered, \"(.*)\" message has popped up$")
    public void messageCheck(String message) {
        String actualMessageText = orderPage.getOrderConfirmationMessage();

        assert message.equals(actualMessageText);
    }

    @And("^Taken screenshot$")
    public void getScreenshot() throws Exception {
        File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(scr, new File("/home/himei_v2/Desktop/screen.png"));
    }

    @And("closed browser")
    public void tearDown() {
        driver.quit();
    }

}
