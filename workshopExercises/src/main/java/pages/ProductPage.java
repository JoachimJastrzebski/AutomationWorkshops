package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ProductPage {
    private static WebDriver driver;

    @FindBy(id = "group_1")
    WebElement selectSize;
    @FindBy(name = "qty")
    WebElement wantedQuantity;
    @FindBy(xpath = "//button[@class='btn btn-primary add-to-cart']")
    WebElement cartButton;
    @FindBy(xpath = "//div[@class = \"cart-content-btn\"]/a")
    WebElement proceedToCheckoutButton;
    @FindBy(xpath = "//div/a[text()='Proceed to checkout']")
    WebElement confirmProceedToCheckout;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectSize(String size) {
        Select drop = new Select(selectSize);
        drop.selectByVisibleText(size);
    }

    public void inputQuantity(String number){
        wantedQuantity.clear();
        wantedQuantity.sendKeys(number);

    }

    public void addToCart() {
        cartButton.click();
    }

    public void proceedToCheckout() throws InterruptedException {
        proceedToCheckoutButton.click();
        Thread.sleep(2000);
        confirmProceedToCheckout.click();
    }



}
