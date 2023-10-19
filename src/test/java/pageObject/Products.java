package pageObject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Products {
	public WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions action;

	public Products(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}
	String Price1, Price2, QuantityBefore, QuantityAfter;
	@FindBy(xpath = "//a[@href='/products']") private WebElement Products;
	@FindBy(xpath = "//h2[text()='All Products']") private WebElement AllProducts;
	@FindBy(id =  "aswift_9") private WebElement StartFrame;
	@FindBy(id = "ad_iframe") private WebElement AdFrame;
	@FindBy(id = "dismiss-button") private WebElement Close_Btn;
	@FindBy(xpath = "//div[@class='col-sm-9 padding-right']/child::div[1]/child::div") private List<WebElement> ListOfProducts;
	@FindBy(xpath = "//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[2]//ul[1]//li[1]//a[1]") private WebElement FirstViewProduct;
	@FindBy(xpath = "//div[@class='product-information']") private WebElement ProductDetailPage;
	@FindBy(xpath = "//div[@class='product-information']/child::p[1]") private WebElement ProductCategory;
	@FindBy(xpath = "//div[@class='product-information']/child::h2") private WebElement ProductName;
	@FindBy(xpath = "//div[@class='product-information']/descendant::span[2]") private WebElement Price;
	@FindBy(xpath = "//div[@class='product-information']/p[2]") private WebElement AvailabilityText;
	@FindBy(xpath = "//div[@class='product-information']/p[3]") private WebElement ConditionText;
	@FindBy(xpath = "//div[@class='product-information']/p[4]") private WebElement BrandText;

	@FindBy(xpath = "//input[@id='search_product']") private WebElement ProductSearchBox;
	@FindBy(xpath = "//button[@id='submit_search']") private WebElement ProductSearch_Btn;
	@FindBy(xpath = "//h2[text()='Searched Products']") private WebElement SearchedProducts;

	//@FindBy(xpath = "//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[1]//div[2]") private WebElement FirstProduct;
	@FindBy(xpath = "/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]") private WebElement FirstProduct;
	@FindBy(xpath = "//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[1]//div[2]//div[1]//a[1] ") private WebElement FirstProductAddToCart;
	@FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']") private WebElement Continue_Btn;
	@FindBy(xpath = "//div[3]//div[1]//div[1]//div[1]//img[1]") private WebElement SecondProductImage;
	@FindBy(xpath = "//div[3]//div[1]//div[1]//div[2]//div[1]//a[1]") private WebElement SecondProductAddToCart;
	@FindBy(xpath = "//a[normalize-space()='Cart']")private WebElement Cart;
	@FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr") private List<WebElement> NoOfItemsInCart;
	@FindBy(xpath = "//div[@id='cartModal']/following::div[1]/descendant::div[2]/child::div[2]/descendant::h2") private WebElement Product1PriceBeforeCart;
	@FindBy(xpath = "//div[@id='cartModal']/following-sibling::div[2]/descendant::div[2]/child::div[2]/descendant::h2") private WebElement Product2PriceBeforeCart;
	@FindBy(xpath = "//*[@id=\"product-1\"]/td[3]/p") private WebElement CartProductPrice1;
	@FindBy(xpath = "//*[@id=\"product-1\"]/td[4]/button") private WebElement CartProductQuantity1;
	@FindBy(xpath = "//*[@id=\"product-1\"]/td[5]/p") private WebElement CartTotalPrice1;
	@FindBy(xpath = "//*[@id=\"product-2\"]/td[3]/p") private WebElement CartProductPrice2;
	@FindBy(xpath = "//*[@id=\"product-2\"]/td[4]/button") private WebElement CartProductQuantity2;
	@FindBy(xpath = "//*[@id=\"product-2\"]/td[5]/p") private WebElement CartTotalPrice2;

	@FindBy(xpath = "//input[@id='quantity']") private WebElement CartQuantity;
	@FindBy(xpath = "//button[@class='btn btn-default cart']") private WebElement CartAdd_Btn;
	@FindBy(xpath = "//u[normalize-space()='View Cart']") private WebElement ViewCart;
	@FindBy(xpath = "//p[normalize-space()='Your product has been added to cart.']") private WebElement VerifyAddedProduct;

	@FindBy(xpath = "//a[text()='Write Your Review']") private WebElement WriteReview;
	@FindBy(id = "name") private WebElement ReviewName;
	@FindBy(id = "email") private WebElement ReviewEmailAddress;
	@FindBy(id = "review") private WebElement AddReview;
	@FindBy(id = "button-review") private WebElement ReviewSubmit_Btn;
	@FindBy(xpath = "//span[text()='Thank you for your review.']") private WebElement ReviewSuccessMessage;


	public void clickOnProducts() throws IOException, InterruptedException {
		XSSFSheet sheet = TestDataFromExcel.ExcelProductTestData();
		wait.until(ExpectedConditions.visibilityOf(Products));
		Products.click();
		wait.until(ExpectedConditions.visibilityOf(AllProducts));
		assertEquals(AllProducts.getText(), sheet.getRow(1).getCell(0).toString());
		ColorLog.d("navigated to 'ALL PRODUCTS' page successfully");
		System.out.print("products list is visible and size is "+ListOfProducts.size());
	}

	public void productDetailsPage() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelProductTestData();
		wait.until(ExpectedConditions.visibilityOf(FirstViewProduct));
		js.executeScript("arguments[0].scrollIntoView(true);", FirstViewProduct);
		FirstViewProduct.click();
		Assert.assertTrue(ProductDetailPage.isDisplayed());
		ColorLog.d("Landed on to product detail page");
		Assert.assertTrue(ProductName.isDisplayed());
		assertEquals(ProductName.getText(),sheet.getRow(12).getCell(0).toString());
		Assert.assertTrue(ProductCategory.isDisplayed());
		assertEquals(ProductCategory.getText(),sheet.getRow(12).getCell(1).toString());
		Assert.assertTrue(Price.isDisplayed());
		assertEquals(Price.getText(),sheet.getRow(12).getCell(2).toString());
		Assert.assertTrue(AvailabilityText.isDisplayed());
		assertEquals(AvailabilityText.getText(),sheet.getRow(12).getCell(3).toString());
		Assert.assertTrue(ConditionText.isDisplayed());
		assertEquals(ConditionText.getText(),sheet.getRow(12).getCell(4).toString());
		Assert.assertTrue(BrandText.isDisplayed());
		assertEquals(BrandText.getText(),sheet.getRow(12).getCell(5).toString());
	}

	public void searchProducts() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelProductTestData();
		ProductSearchBox.sendKeys("Top");
		ProductSearch_Btn.click();
		assertEquals(SearchedProducts.getText(), sheet.getRow(17).getCell(0).toString());
		ColorLog.d("'SEARCHED PRODUCTS' is visible");
		ColorLog.d("All products related to search are visible");
	}

	public void firstProductAddToCart() {
		js.executeScript("arguments[0].scrollIntoView(true);", FirstProduct);
		action.moveToElement(FirstProduct).perform();
		Price1 = Product1PriceBeforeCart.getText();
		wait.until(ExpectedConditions.visibilityOf(FirstProductAddToCart));
		FirstProductAddToCart.click();
		ColorLog.d("First Product is clicked");		
	}

	public void secondProductAddToCart() {
		js.executeScript("arguments[0].scrollIntoView(true);", SecondProductImage);
		action.moveToElement(SecondProductImage).perform();
		Price2 = Product2PriceBeforeCart.getText();
		wait.until(ExpectedConditions.visibilityOf(SecondProductAddToCart));
		SecondProductAddToCart.click();
		ColorLog.d("Second Product is clicked");
	}
	
	public void clickOnContinue() {
		wait.until(ExpectedConditions.visibilityOf(Continue_Btn));
		Continue_Btn.click();
		ColorLog.d("Clicked on 'Continue Shopping' button");
	}

	public void verifyProductsOnCart() {
		System.out.println("No. of Items in Cart: "+NoOfItemsInCart.size());
		assertEquals(Price1, CartProductPrice1.getText());
		assertEquals(Price1, CartTotalPrice1.getText());
		assertEquals(Price2, CartProductPrice2.getText());
		assertEquals(Price2, CartTotalPrice2.getText());
	}

	public void productQuantityInCart() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelProductTestData();
		js.executeScript("arguments[0].scrollIntoView(true);", FirstViewProduct);
		FirstViewProduct.click();
		if(StartFrame.isDisplayed()) {
			driver.switchTo().frame(StartFrame);
			driver.switchTo().frame(AdFrame);
			Close_Btn.click();
			driver.switchTo().defaultContent();
		}
		wait.until(ExpectedConditions.visibilityOf(ProductDetailPage));
		QuantityBefore = CartQuantity.getAttribute("value");
		System.out.println(QuantityBefore);
		System.out.println(ProductName.getText()+", "+ProductCategory.getText()+", "+Price.getText());
		System.out.println(AvailabilityText.getText()+", "+ConditionText.getText()+", "+BrandText.getText());
		CartQuantity.clear();
		CartQuantity.sendKeys(sheet.getRow(13).getCell(0).getRawValue());
		QuantityAfter = CartQuantity.getAttribute("value");
		CartAdd_Btn.click();
		wait.until(ExpectedConditions.visibilityOf(ViewCart));
		assertEquals(VerifyAddedProduct.getText(), sheet.getRow(13).getCell(1).toString());
		ViewCart.click();
		System.out.println(QuantityAfter);
		assertNotEquals(QuantityAfter, QuantityBefore);
	}

	public void clickOnViewProduct() {
		wait.until(ExpectedConditions.visibilityOf(FirstViewProduct));
		FirstViewProduct.click();
	}

	public void addReviewOnProduct() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		wait.until(ExpectedConditions.visibilityOf(WriteReview));
		assertEquals(WriteReview.getText(), sheet.getRow(18).getCell(0).toString());
		ColorLog.d("'Write Your Review' is visible");
		ReviewName.sendKeys(sheet.getRow(18).getCell(1).toString());
		ReviewEmailAddress.sendKeys(sheet.getRow(18).getCell(2).toString());
		AddReview.sendKeys(sheet.getRow(18).getCell(3).toString());
		ColorLog.d("Entered Name, Email and Review.");
		ReviewSubmit_Btn.click();
		//wait.until(ExpectedConditions.visibilityOf(ReviewSuccessMessage));
		assertEquals(ReviewSuccessMessage.getText(), sheet.getRow(18).getCell(4).toString());
		ColorLog.d("success message 'Thank you for your review.' is visible.");
	}

}
