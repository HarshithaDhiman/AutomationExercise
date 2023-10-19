package com.ExcelRProject.AutomationExcerciseProject;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.AddSearchProductsToCart;
import pageObject.ContactUsForm;
import pageObject.PlaceOrder;
import pageObject.Products;
import pageObject.RecommendedItems;
import pageObject.SignUpAndLoginPage;
import pageObject.SubscriptionPage;
import pageObject.TestCases;
import pageObject.ViewCategory;
import pageObject.ViewProductBrands;
import pageObject.WindowHandles;

public class TestCasesImplementation {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	WindowHandles handles;
	SignUpAndLoginPage signUpLoginPageObject;
	ContactUsForm contact;
	Products product;
	TestCases testcase;
	SubscriptionPage subscript;
	PlaceOrder placeorder; ViewCategory view; ViewProductBrands brand;
	AddSearchProductsToCart searchproduct;RecommendedItems recommend;

	@BeforeSuite
	public void initialisation() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> chromePref = new HashMap<>();
		chromePref.put("download.default_directory", ".\\Downloads\\");
		chromePref.put("profile.password_manager_enabled", false);
		options.addExtensions(new File("./Extensions/AdBlock.crx"));
		options.setExperimentalOption("prefs", chromePref);
		options.addArguments("start-maximized");
		options.addArguments("--disable-notifications");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(35));
		js = (JavascriptExecutor)driver;
		handles = new WindowHandles(driver);

		signUpLoginPageObject = new SignUpAndLoginPage(driver, wait,js);
		contact = new ContactUsForm(driver,wait,js);
		testcase = new TestCases(driver,wait);
		product =  new Products(driver,wait,js);
		subscript = new SubscriptionPage(driver,wait,js);
		placeorder = new PlaceOrder(driver, wait, js);
		view = new ViewCategory(driver, wait, js);
		brand = new ViewProductBrands(driver, wait);
		searchproduct = new AddSearchProductsToCart(driver, wait, js);
		recommend = new RecommendedItems(driver, wait, js);
	}

	@Test(priority = 1)
	public void registerUser_Validation() throws IOException, InterruptedException{
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		signUpLoginPageObject.clickOnSignUp();
		signUpLoginPageObject.registerUser();
		signUpLoginPageObject.deleteAccount();
	}

	@Test(priority = 2)
	public void valid_Login_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		signUpLoginPageObject.clickOnSignUp();
		signUpLoginPageObject.createAccount();
		signUpLoginPageObject.logout();
		signUpLoginPageObject.validLoginCredentials();
		signUpLoginPageObject.logout();
	}

	@Test(priority = 3)
	public void invalid_Login_Validation() throws IOException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		signUpLoginPageObject.clickOnSignUp();
		signUpLoginPageObject.invalidLoginCredentials();
	}

	@Test(priority = 4)
	public void logout_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		signUpLoginPageObject.clickOnSignUp();
		signUpLoginPageObject.validLoginCredentials();
		signUpLoginPageObject.logout();
	}

	@Test(priority = 5)
	public void registerUserWithExistingEmailId_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		signUpLoginPageObject.clickOnSignUp();
		signUpLoginPageObject.registerUserWithExistingEmailId();
	}

	@Test(priority = 6)
	public void contactUs_Validation() throws IOException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		contact.clickOnContactUs();
	}

	@Test(priority = 7)
	public void testCasesPage_Validataion() throws IOException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		testcase.clickOnTestCases();
	}

	@Test(priority = 8)
	public void productDetailsPage_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		product.clickOnProducts();
		product.productDetailsPage();
	}

	@Test(priority = 9)
	public void searchProduct_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		product.clickOnProducts();
		product.searchProducts();
	}

	@Test(priority = 10)
	public void subscription_Validation() throws IOException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		subscript.subscription();
	}

	@Test(priority = 11)
	public void cartSubscription_Validation() throws IOException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		subscript.clickOnCart();
		subscript.subscription();
	}

	@Test(priority = 12)
	public void addProductsToCart_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		product.clickOnProducts();
		product.firstProductAddToCart();
		product.clickOnContinue();
		product.secondProductAddToCart();
		product.clickOnContinue();
		subscript.clickOnCart();
		product.verifyProductsOnCart();
	}

	@Test(priority = 13)
	public void productQuantityInCart_Validation() throws IOException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		product.productQuantityInCart();
	}

	@Test(priority = 14)
	public void placeOrder_RegisterWhileCheckOut_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		placeorder.addProductsOnHomePage();
		placeorder.registerWhileCheckOut();
		signUpLoginPageObject.createAccount();
		placeorder.clickOnCart();
		String[] CartValues = placeorder.verifyCartAndReviewOrder();
		placeorder.clickOnProceedToCart();
		placeorder.verifyAddress();
		String[] ReviewOrderValues = placeorder.verifyCartAndReviewOrder();
		for(int i=0; i<CartValues.length;i++) {
			assertEquals(CartValues[i], ReviewOrderValues[i]);
		}
		placeorder.placeOrder();
		placeorder.deleteAccount();
	}

	@Test(priority = 15)
	public void placeOrder_RegisterBeforeCheckOut_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		signUpLoginPageObject.clickOnSignUp();
		signUpLoginPageObject.createAccount();
		placeorder.addProductsOnHomePage();
		String[] CartValues = placeorder.verifyCartAndReviewOrder();
		placeorder.clickOnProceedToCart();
		placeorder.verifyAddress();
		String[] ReviewOrderValues = placeorder.verifyCartAndReviewOrder();
		for(int i=0; i<CartValues.length;i++) {
			assertEquals(CartValues[i], ReviewOrderValues[i]);
		}
		placeorder.placeOrder();
		placeorder.deleteAccount();
	}

	@Test(priority = 16)
	public void loginBeforeCheckout_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		signUpLoginPageObject.clickOnSignUp();
		signUpLoginPageObject.createAccount();
		signUpLoginPageObject.logout();
		signUpLoginPageObject.validLoginCredentials();
		placeorder.addProductsOnHomePage();
		placeorder.clickOnProceedToCart();
		String[] CartValues = placeorder.verifyCartAndReviewOrder();
		placeorder.verifyAddress();
		String[] ReviewOrderValues = placeorder.verifyCartAndReviewOrder();
		for(int i=0; i<CartValues.length;i++) {
			assertEquals(CartValues[i], ReviewOrderValues[i]);
		}
		placeorder.placeOrder();
		placeorder.deleteAccount();
	}

	@Test(priority = 17)
	public void removeProductsFromCart_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		placeorder.addProductsOnHomePage();
		placeorder.beforeDeletingItemsInCart();
		placeorder.afterDeletingItemsInCart();
	}

	@Test(priority = 18)
	public void viewCategoryProducts_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		view.categoryVerification();
		view.clickOnWomenCategory();
		view.clickOnMenCategory();
	}

	@Test(priority = 19)
	public void viewAndCartBrandProducts_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		product.clickOnProducts();
		brand.itemsInBrands();
		brand.BibaAndItsProducts();
		brand.BabyHugAndItsProducts();
	}

	@Test(priority = 20)
	public void searchProductsAndVerifyCart_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		product.clickOnProducts();
		searchproduct.searchProducts();
		searchproduct.addSearchProducts();
		signUpLoginPageObject.clickOnSignUp();
		signUpLoginPageObject.createAccount();
		signUpLoginPageObject.logout();
		signUpLoginPageObject.validLoginCredentials();
		placeorder.clickOnCart();
		searchproduct.verifySeachAndAddProductsInCart();
	}

	@Test(priority = 21)
	public void addReview_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		product.clickOnProducts();
		product.clickOnViewProduct();
		product.addReviewOnProduct();
	}

	@Test(priority = 22)
	public void addRecommendedItemsToCart_Validation() throws IOException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		recommend.recommendedItems();
		recommend.addRecommendedItemsToCart();
	}

	@Test (priority = 23)
	public void checkoutVerifyAddress_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		signUpLoginPageObject.clickOnSignUp();
		signUpLoginPageObject.createAccount();
		placeorder.addProductsOnHomePage();
		placeorder.clickOnCart();
		placeorder.clickOnProceedToCart();
		placeorder.deliveryAddressCheck();
		placeorder.billingAddressCheck();
		placeorder.deleteAccount();
	}

	@Test(priority = 24)
	public void invoiceDownload_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		placeorder.addProductsOnHomePage();
		placeorder.clickOnCart();
		placeorder.registerWhileCheckOut();
		signUpLoginPageObject.clickOnSignUp();
		signUpLoginPageObject.createAccount();
		placeorder.clickOnCart();
		String[] CartData = placeorder.verifyCartAndReviewOrder();
		for (String element : CartData)
			System.out.print(element+", ");
		System.out.println();
		placeorder.clickOnProceedToCart();
		placeorder.verifyAddress();
		String[] ReviewOrderData = placeorder.verifyCartAndReviewOrder();
		for (String element : ReviewOrderData)
			System.out.print(element+", ");
		System.out.println();
		placeorder.placeOrder();
		placeorder.downloadInvoice();
		placeorder.isInvoiceDownloded();
		placeorder.deleteAccount();
	}

	@Test(priority = 25)
	public void ScrollUpAndScrollDownUsingArrow_Validation() throws IOException, InterruptedException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		subscript.scrollUoDownWithArrow();
	}
	
	@Test(priority = 26)
	public void ScrollUpAndScrollDown_Validation() throws IOException {
		signUpLoginPageObject.launchBrowser();
		handles.handledWindowSwitch();
		subscript.scrollUpDown();
	}
}
