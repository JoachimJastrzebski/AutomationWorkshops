package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

    @FindBy(name = "confirm-addresses")
    WebElement confirmAddressButton;
    @FindBy(name = "confirmDeliveryOption")
    WebElement confirmDeliveryOptionButton;
    @FindBy(id = "delivery_option_1")
    WebElement prestaShopRadio;
    @FindBy(id = "payment-option-1")
    WebElement payByCheckRadio;
    @FindBy(xpath = "//input[@type = 'checkbox']")
    WebElement termOfServiceCheckbox;
    @FindBy(xpath = "//button[@class=\"btn btn-primary center-block\"]")
    WebElement confirmOrderButton;
    WebDriver driver;
    @FindBy(xpath = "//h3[@class = 'h1 card-title']/i")
    WebElement orderConfirmationMessage;


    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void confirmAddress(){
        confirmAddressButton.click();
    }

    public void confirmDelivery() {
        confirmDeliveryOptionButton.click();
    }

    public Boolean isDeliveryMethodSelected() {

        return prestaShopRadio.isSelected();
    }

    public void selectPaymentMethod() {
        payByCheckRadio.click();
    }

    public void checkTermOfService() {
        termOfServiceCheckbox.click();
    }

    public void confirmOrder() {
        confirmOrderButton.click();
    }

    public String getOrderConfirmationMessage(){

        return orderConfirmationMessage.getText();
    }
}
