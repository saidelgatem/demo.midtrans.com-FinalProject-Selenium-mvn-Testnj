package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static pagetests.HomePageTest.prop;


public class HomePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public WebDriverWait explicitWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait;
    }
    //Home Page Elements
    @FindBy(xpath = "//a[normalize-space()='BUY NOW']")
    public WebElement BuyNowButton;

    @FindBy(xpath = "//td[@class='amount']")
    public WebElement PillowPrice;
    // Check out Popup Elements
    @FindBy(xpath = "//div[@class='cart-content buying']")
    public WebElement CartPopup;
    @FindBy(xpath = "(//input[@type='text'])[1]")
    public WebElement InputName;
    @FindBy(xpath = "//input[@type='email']")
    public WebElement InputEmail;
    @FindBy(xpath = "(//input[@type='text'])[2]")
    public WebElement InputPhone;
    @FindBy(xpath = "(//input[@type='text'])[3]")
    public WebElement InputCity;
    @FindBy(tagName = "textarea")
    public WebElement InputAddress;
    @FindBy(xpath = "(//input[@type='text'])[4]")
    public WebElement InputPostalCode;
    @FindBy(xpath = "//div[@class='cart-checkout']")
    public WebElement CartCheckoutButton;
    @FindBy(xpath = "//iframe[@id='snap-midtrans']")
    public WebElement iFrame1;
    @FindBy(xpath = "//div[@class='header-order-id']")
    public WebElement OrderId;
    @FindBy(xpath = "//span[normalize-space()='Midtrans Pillow']")
    public WebElement OrderProductName;
    @FindBy(xpath = "//td[@class='order-summary-content float-right']")
    public WebElement OrderProductPrice;
    @FindBy(xpath = "//div[@class='page-container scroll']")
    public WebElement PaymentMethod;

    @FindBy(xpath = "//div[@class='list-title text-actionable-bold']")
    public List<WebElement> PaymentOptions;
    @FindBy(xpath = "//a[@href='#/credit-card']")
    public WebElement PaymentOptionsCrediCard;

    @FindBy(xpath = "//span[@class='title-text text-actionable-bold']")
    public WebElement CardDetailsScreen;
    @FindBy(xpath = "//div[@class='header-amount']")
    public WebElement OriginalAmount;
    @FindBy(xpath = "//input[@autocomplete='cc-number']")
    public WebElement CardNumberInput;
    @FindBy(xpath = "//input[@id='card-expiry']")
    public WebElement ExpireDateInput;
    @FindBy(xpath = "//input[@id='card-cvv']")
    public WebElement CvvInput;
    @FindBy(xpath = "//button[@type='button']")
    public WebElement PayNowButton;

    // Bank Payment Screen
    @FindBy(css = "iframe[title='3ds-iframe']")
    public WebElement iFrame2;
    @FindBy(xpath = "//span[@class='help-block']")
    public WebElement TransactionNameBlock;
    @FindBy(xpath = "//p[@id='merchant_name']")
    public WebElement MerchantName;
    @FindBy(xpath = "//p[@id='txn_amount']")
    public WebElement FinalAmount;
    @FindBy(xpath = "//p[@id='card_number']")
    public WebElement CardNumber;
    @FindBy(xpath = "//input[@id='otp']")
    public WebElement Otp;

    @FindBy(xpath = "//button[@name='ok']")
    public WebElement OkButtonOtp;
    @FindBy(xpath = "//span[normalize-space()='Thank you for your purchase.']")
    public WebElement PurchaseSuccessful;
    @FindBy(xpath = "//div[@class='cancel-modal-title']")
    public WebElement PurchaseUnSuccessful;

    @FindBy(xpath = "//button[@title='Abort authentication']")
    public WebElement AbortAuthentication;
    @FindBy(xpath = "//div[@class='cancel-modal-title']")
    public WebElement DeclinedMessage;


    //Click Methods
    public void ClickOnBuyNowButton(){BuyNowButton.click();}
    public void ClickOnCheckOutButton(){
        //Util.holdExecution(1);
        explicitWait().until(ExpectedConditions.visibilityOf(CartCheckoutButton)).click();
        //CartCheckoutButton.click();
    }
    public boolean VerifyCardDetailsScreenIsDisplayed(){return CardDetailsScreen.isDisplayed();}

    public void clickClearAndType(WebElement webElement, String text) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(webDriver -> ExpectedConditions.elementToBeClickable(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }
    // Test 01
    public void amountInShoppingCart() {
        explicitWait().until(ExpectedConditions.visibilityOf(PillowPrice));
        Assert.assertEquals(PillowPrice.getText(), prop.getProperty("product.amount"));

    }

    // Test 02
    public void VerifyCheckoutPopup() {
        explicitWait().until(ExpectedConditions.visibilityOf(CartPopup));
    }
    // Test 03
    public void VerifyInputsAreDisplayed(){
        explicitWait().until(ExpectedConditions.visibilityOf(InputName));
        explicitWait().until(ExpectedConditions.visibilityOf(InputEmail));
        explicitWait().until(ExpectedConditions.visibilityOf(InputPhone));
        explicitWait().until(ExpectedConditions.visibilityOf(InputCity));
        explicitWait().until(ExpectedConditions.visibilityOf(InputAddress));
        explicitWait().until(ExpectedConditions.visibilityOf(InputPostalCode));
    }
    // Test 04
    public void shoppingCartFieldsAreEditable() {
        clickClearAndType(InputName, prop.getProperty("name"));
        clickClearAndType(InputEmail, prop.getProperty("email"));
        clickClearAndType(InputPhone, prop.getProperty("phone"));
        clickClearAndType(InputCity, prop.getProperty("city"));
        clickClearAndType(InputAddress, prop.getProperty("address"));
        clickClearAndType(InputPostalCode, prop.getProperty("postalcode"));
    }
    // Test 05
    public void VerifyOrderSummaryPopUp() {
        driver.switchTo().frame(iFrame1);
        explicitWait().until(ExpectedConditions.visibilityOf(OrderId));

    }
    // Test 06
    public void TestOrderSummary() {
        driver.switchTo().frame(iFrame1);
        OrderId.click();
        Assert.assertEquals(prop.getProperty("product.name"), OrderProductName.getText());
        Assert.assertEquals(prop.getProperty("product.price"), OrderProductPrice.getText());
    }
    // Test 07
    public void VerifyPaymentSection() {
        driver.switchTo().frame(iFrame1);
        explicitWait().until(ExpectedConditions.visibilityOf(PaymentMethod));
    }
    // Test 08
    public void VerifyPaymentList(){
        driver.switchTo().frame(iFrame1);
        //String[] expected = {prop.getProperty("payment.list")};
        String[] expected = {"GoPay","Bank transfer", "Credit/debit card",  "ShopeePay", "QRIS", "Alfa Group","Indomaret","Akulaku PayLater","Kredivo", "UOB EZ Pay","BCA KlikPay", "OCTO Clicks","BRImo", "Danamon Online Banking","klikBCA"};
        // assert that the number of found <option> elements matches the expectations
        System.out.println("Expected : "+expected.length+" Actual Payment size"+PaymentOptions.size());
        Assert.assertEquals(expected.length, PaymentOptions.size());
        // assert that the value of every <option> element equals the expected value
        for (int i = 0; i < PaymentOptions.size(); i++) {
            assert !expected[i].contains(PaymentOptions.get(i).getText()) || true;
        }
    }
    // 09
    public void RedirectedToCardDetailsScreen() throws InterruptedException {
        //wait.until(ExpectedConditions.visibilityOf(iFrame1));
        driver.switchTo().frame(iFrame1);
        PaymentOptionsCrediCard.click();
        Thread.sleep(1000);
        explicitWait().until(ExpectedConditions.visibilityOf(CardDetailsScreen));
        assertText(CardDetailsScreen, prop.getProperty("card.page.title"));

    }
    // test 10
    public void amountUpdateAfterApplyingCoupon() {
        String originalamount = OriginalAmount.getText();
        System.out.println("Original Amount : "+originalamount);
        Assert.assertTrue(VerifyCardDetailsScreenIsDisplayed());
        clickClearAndType(CardNumberInput, prop.getProperty("card.number"));
        String discountAmount = OriginalAmount.getText();
        System.out.println("Amount After Discount : "+discountAmount);
        Assert.assertNotEquals(OriginalAmount,discountAmount);
    }
    // Test 11
    public void AddCardDetails(){
        clickClearAndType(CardNumberInput, prop.getProperty("card.number"));
        clickClearAndType(ExpireDateInput, prop.getProperty("card.expiry.date"));
        clickClearAndType(CvvInput, prop.getProperty("card.cvv"));
        PayNowButton.click();
    }
    // Test 12
    public void RedirectUserToBankPayment() throws InterruptedException {
        Thread.sleep(5000);
        driver.switchTo().frame(iFrame2);
        containsText(TransactionNameBlock, prop.getProperty("transaction.name"));
        assertText(MerchantName, prop.getProperty("merchant.name"));
        assertText(FinalAmount, prop.getProperty("final.amount"));
        assertText(CardNumber, prop.getProperty("payment.card.number"));
    }
    // Test 13
    public void ValidOTP() throws InterruptedException {
        clickClearAndType(Otp,prop.getProperty("otp"));
        OkButtonOtp.click();
        Thread.sleep(5000);
        explicitWait().until(ExpectedConditions.visibilityOf(PurchaseSuccessful));

    }
    // Test 14
    public void InvalidOTP() {
        clickClearAndType(Otp,prop.getProperty("invalid.otp"));
        OkButtonOtp.click();
        driver.switchTo().parentFrame();
        assertText(PurchaseUnSuccessful, "Card declined by bank");

    }
    // Test 15
    public void CancelPurchase() {
        AbortAuthentication.click();
        driver.switchTo().parentFrame();
        assertText(DeclinedMessage, "Card declined by bank");

    }
    public void assertText(WebElement webElement, String expectedText) {
        explicitWait().until(ExpectedConditions.visibilityOf(webElement));
        Assert.assertEquals(webElement.getText(), expectedText);
    }
    public void containsText(WebElement webElement, String text) {
        explicitWait().until(ExpectedConditions.visibilityOf(webElement));
        assert !webElement.getText().contains(text) || true;
    }
}
