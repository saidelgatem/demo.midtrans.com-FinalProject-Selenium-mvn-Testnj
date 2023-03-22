package pagetests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import setup.SetupDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class HomePageTest extends SetupDriver {
    //public HomePage homePage;
    public static Properties prop = new Properties();

    public HomePageTest(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public HomePageTest(){
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ File.separator+"resources"+File.separator+"testdata"+File.separator+"config.properties");
            prop.load(file);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e1) {
            e1.printStackTrace();
        }
    }

    // TC_01 - Verify "Midtrans Pillow" has added into cart with cost of 20000/- Regression
    @Test(testName = "Test Case 1", priority = 1, groups = {"regression"})
    void VerifyPillowPrice(){
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.amountInShoppingCart();
    }
    // TC_02 - Verify clicking on Buy Now button redirect user to Checkout popup Regression, Smoke
    @Test(testName = "Test Case 2", priority = 2, groups = {"smoke", "regression"})
    public void VerifyCheckoutPopup(){
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.VerifyCheckoutPopup();
    }
    // TC_03 - Verify Name, email, phone, city, Address, postal code on checkout page Regression
    @Test(testName = "Test Case 3", priority = 3, groups = {"regression"})
    public void VerifyInputsAreVisible(){
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.VerifyInputsAreDisplayed();
    }
    // TC_04 - Verify Name, email, phone, city, Address, postal code fields are editable and user can enter details in it Regression
    @Test(testName = "Test Case 4", priority = 4, groups = {"regression"})
    public void VerifyInputIsEditable(){
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.shoppingCartFieldsAreEditable();
    }
    // TC_05 - Verify clicking on Checkout button redirect user on Order Summary popup Regression, Smoke
    @Test(testName = "Test Case 5", priority = 5, groups = {"smoke", "regression"})
    public void VerifyClickOnCheckout(){
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.VerifyOrderSummaryPopUp();
    }
    // TC_06 - Verify all the product details on order summery popup - price, product name Regression
    @Test(testName = "Test Case 6", priority = 6, groups = {"regression"})
    public void VerifyProductDetailsonOrderSummeryPopup(){
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.TestOrderSummary();
    }
    // TC_07 - Verify clicking on continue button on order summery redirect user to "Select payment" Page Regression, Smoke
    @Test(testName = "Test Case 7", priority = 7, groups = {"smoke","regression"})
    public void VerifyPaymentPageFromOrderSummary(){
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.VerifyPaymentSection();
    }
    // TC_08 - Verify all the payment options listed on payment page Regression
    @Test(testName = "Test Case 8", priority = 8, groups = {"regression"})
    public void VerifyAllPaymentOptionsListedOnPayment(){
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.VerifyPaymentList();
    }
    // TC_09 - Verify user can click on Credit/Debit Card payment method which redirected to card details screen Regression, Smoke
    @Test(testName = "Test Case 9", priority = 9, groups = {"smoke","regression"})
    public void VerifyUserClickingOnDebitOrCreditCardRedirectsToCardDetailsScreen() throws InterruptedException {
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.RedirectedToCardDetailsScreen();
    }
    // TC_10 - Verify order amount on card details screen and apply coupon code and validate changes in amount Regression
    @Test(testName = "Test Case 10", priority = 10, groups = {"regression"})
    public void verifyOrderAmountAfterApplyingCoupon() throws InterruptedException {
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.RedirectedToCardDetailsScreen();
        homePage.amountUpdateAfterApplyingCoupon();
    }
    //TC_11 - Enter Valid card details - and click pay now - Smoke regression
    @Test(testName = "Test Case 11", priority = 11, groups = {"smoke","regression"})
    public void EnterValidCardDetailsAndClickPayNow() throws InterruptedException {
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.RedirectedToCardDetailsScreen();
        homePage.AddCardDetails();
    }
    //TC_12 - Verify Clicking on Pay now redirect user to Bank Payment Screen, verify all the exiting details -Regression
    @Test(testName = "Test Case 12", priority = 12, groups = {"regression"})
    public void VerifyClickingOnPaynowRedirectUserToBankPaymentScreen() throws InterruptedException {
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.RedirectedToCardDetailsScreen();
        homePage.AddCardDetails();
        homePage.RedirectUserToBankPayment();
    }
    //TC_13 - Verify clicking on OK button with Valid OTP (112233) should redirect user to order succssfull screen Regression, Smoke
    @Test(testName = "Test Case 13", priority = 13, groups = {"smoke","regression"})
    public void VerifyClickingOnOKButtonWithValidOTPRedirectToOrderSuccssfullscreen() throws InterruptedException{
        homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.RedirectedToCardDetailsScreen();
        homePage.AddCardDetails();
        homePage.RedirectUserToBankPayment();
        homePage.ValidOTP();
    }
    //TC_14 - Verify clicking on OK button with Invalid OTP should redirect user to order failed screen Regression
    @Test(testName = "Test Case 14", priority = 14, groups = {"regression"})
    public void VerifyClickingOnOKButtonWithInValidOTPRedirectToOrderSuccssfullscreen()throws InterruptedException{
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.RedirectedToCardDetailsScreen();
        homePage.AddCardDetails();
        homePage.RedirectUserToBankPayment();
        homePage.InvalidOTP();
    }
    // TC_15 - Verify clicking on CANCLE button should redirect user to order failed screen Regression
    @Test(testName = "Test Case 15", priority = 15, groups = {"regression"})
    public void VerifyClickingOnCancleButtonShouldRedirectUserToOrderFailedScreen()throws InterruptedException{
        //homePage = new HomePage(driver);
        homePage.ClickOnBuyNowButton();
        homePage.ClickOnCheckOutButton();
        homePage.RedirectedToCardDetailsScreen();
        homePage.AddCardDetails();
        homePage.RedirectUserToBankPayment();
        homePage.CancelPurchase();
    }
}
