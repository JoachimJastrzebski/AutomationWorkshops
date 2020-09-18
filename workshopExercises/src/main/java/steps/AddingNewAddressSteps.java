package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AddressesPage;
import pages.HomePage;
import pages.LoginPage;

public class AddingNewAddressSteps {
    HomePage homePage;
    LoginPage loginPage;
    AddressesPage addressesPage;
    WebDriver driver;

    @Given("^An browser with opened https://prod-kurs.coderslab.pl/index.php page$")
    public void openApplicationPage() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php");
    }

    @And("^User is logged in$")
    public void logInUser() {
       homePage = new HomePage(driver);
       homePage.goGoToSignInPage();
       loginPage = new LoginPage(driver);
       loginPage.loginAs("AndrewJFreeman@rhyta.com", "freeman");
    }

    @When("^User clicked an \"Addresses\" button$")
    public void moveToAddressesPage(){
        loginPage.goToAddressesPage();
    }

    @And("^User clicked an \"Create new address\" button$")
    public void moveToAddressForm() {
        addressesPage = new AddressesPage(driver);
        addressesPage.createNewAddress();
    }

//  I tried with ([a-zA-Zs]+) regex for country and (d+) for phone parameters but it wasn't working
    @And("Form is filled with with data:(.*), (.*), (.*), (.*), (.*), (.*) and submitted")
    public void fillForm(String alias, String address, String city,
                         String postcode, String country, String phone) {

        AddressesPage addressesPage = new AddressesPage(driver);
        addressesPage.setAlias(alias);
        addressesPage.setAddress(address);
        addressesPage.setCity(city);
        addressesPage.setPostCode(postcode);
        addressesPage.selectCountry(country);
        addressesPage.setPhone(phone);
        addressesPage.submitForm();

    }

    @Then("^\"(.*)\" message is shown$")
    public void messageCheck(String message) {
        String actualMessageText = addressesPage.getSuccessAlertMessage();
        assert message.equals(actualMessageText);
    }

    @And("^Close browser$")
    public void tearDown() {
        driver.close();
    }


}
