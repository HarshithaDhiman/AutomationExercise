package pageObject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlaceOrder {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions action;

	public PlaceOrder(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}
	int CountBeforeDeletion, CountAfterDeletion;
	@FindBy(xpath = "//div[@id='cartModal']/following::div[1]/child::div[1]") private WebElement FirstProduct;
	@FindBy(xpath = "//div[@id='cartModal']/following::div[1]/descendant::div[3]/descendant::a") private WebElement FirstProductAddToCart;
	@FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']") private WebElement ContinueShopping_Btn;
	@FindBy(xpath = "//a[text()='Continue']") private WebElement Continue_Btn;
	@FindBy(xpath = "//*[@id='cartModal']/following-sibling::div[2]") private WebElement SecondProduct;
	@FindBy(xpath = "//div[@id='cartModal']/following-sibling::div[2]//div[2]/descendant::a[@data-product-id='2']") private WebElement SecondProductAddToCart;
	@FindBy(xpath = "//*[@id='cartModal']/following-sibling::div[3]") private WebElement ThirdProduct;
	@FindBy(xpath = "//div[@id='cartModal']/following-sibling::div[3]//div[2]/descendant::a[@data-product-id='3']") private WebElement ThirdProductAddToCart;
	@FindBy(xpath = "//a[normalize-space()='Cart']")private WebElement Cart;
	@FindBy(xpath = "//li[@class='active']") private WebElement ShoppingCart;
	@FindBy(xpath = "//tr[@id='product-3']//a[@class='cart_quantity_delete']") private WebElement CartDeleteSymbol;
	@FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr") private List<WebElement> NoOfItemsInCart;
	@FindBy(xpath = "//a[@class='btn btn-default check_out']") private WebElement ProceedToCheckOut_Btn;
	@FindBy(xpath = "//u[normalize-space()='Register / Login']") private WebElement RegisterLogin_Link;

	@FindBy(xpath = "//textarea[@name='message']") private WebElement CommentTextArea;
	@FindBy(xpath = "//a[text()='Place Order']") private WebElement PlaceOrder_Btn;
	@FindBy(xpath = "//input[@name='name_on_card']") private WebElement NameOnCard;
	@FindBy(xpath = "//input[@name='card_number']") private WebElement CardNumber;
	@FindBy(xpath = "//input[@name='cvc']") private WebElement CVC;
	@FindBy(xpath = "//input[@name='expiry_month']") private WebElement MM;
	@FindBy(xpath = "//input[@name='expiry_year']") private WebElement YYYY;
	@FindBy(xpath = "//button[@id='submit']") private WebElement PayAndConfirm;
	@FindBy(linkText = "Delete Account") private WebElement DeleteAccount;
	@FindBy(xpath = "//b[text()='Account Deleted!']") private WebElement AccountDeleted;
	@FindBy(xpath = "//div[contains(text(),'Your order has been placed successfully!')]") private WebElement SuccessAlertMsg;
	@FindBy(xpath = "//b[text()='Order Placed!']") private WebElement OrderPlaced;
	@FindBy(xpath = "//p[text()='Congratulations! Your order has been confirmed!']")private WebElement ConfirmationMsg;

	@FindBy(xpath = "//tr[@id='product-1']/td[@class='cart_description']/descendant::a") private WebElement FirstProductName;
	@FindBy(xpath = "//tr[@id='product-1']/td[@class='cart_description']/descendant::p") private WebElement FirstProductCategory;
	@FindBy(xpath = "//tr[@id='product-1']/td[@class='cart_price']/descendant::p") private WebElement FirstProductPrice;
	@FindBy(xpath = "//tr[@id='product-1']/td[@class='cart_quantity']/descendant::button") private WebElement FirstProductQuantity;
	@FindBy(xpath = "//tr[@id='product-1']/td[@class='cart_total']/descendant::p") private WebElement FirstProductTotal;
	@FindBy(xpath = "//tr[@id='product-2']/td[@class='cart_description']/descendant::a") private WebElement SecondProductName;
	@FindBy(xpath = "//tr[@id='product-2']/td[@class='cart_description']/descendant::p") private WebElement SecondProductCategory;
	@FindBy(xpath = "//tr[@id='product-2']/td[@class='cart_price']/descendant::p") private WebElement SecondProductPrice;
	@FindBy(xpath = "//tr[@id='product-2']/td[@class='cart_quantity']/descendant::button") private WebElement SecondProductQuantity;
	@FindBy(xpath = "//tr[@id='product-2']/td[@class='cart_total']/descendant::p") private WebElement SecondProductTotal;
	@FindBy(xpath = "//tr[@id='product-3']/td[@class='cart_description']/descendant::a") private WebElement ThirdProductName;
	@FindBy(xpath = "//tr[@id='product-3']/td[@class='cart_description']/descendant::p") private WebElement ThirdProductCategory;
	@FindBy(xpath = "//tr[@id='product-3']/td[@class='cart_price']/descendant::p") private WebElement ThirdProductPrice;
	@FindBy(xpath = "//tr[@id='product-3']/td[@class='cart_quantity']/descendant::button") private WebElement ThirdProductQuantity;
	@FindBy(xpath = "//tr[@id='product-3']/td[@class='cart_total']/descendant::p") private WebElement ThirdProductTotal;

	@FindBy(xpath = "//ul[@id='address_delivery']/li[1]") private WebElement DeliveryAddress;
	@FindBy(xpath = "//ul[@id='address_delivery']/li[2]") private WebElement DelNameWithTitle;
	@FindBy(xpath = "//ul[@id='address_delivery']/li[3]") private WebElement DelCompany;
	@FindBy(xpath = "//ul[@id='address_delivery']/li[4]") private WebElement DelAddressLine1;
	@FindBy(xpath = "//ul[@id='address_delivery']/li[5]") private WebElement DelAddressLine2;
	@FindBy(xpath = "//ul[@id='address_delivery']/li[6]") private WebElement DelCityStatePinCode;
	@FindBy(xpath = "//ul[@id='address_delivery']/li[7]") private WebElement DelCountry;
	@FindBy(xpath = "//ul[@id='address_delivery']/li[8]") private WebElement DelMobileNum;

	@FindBy(xpath = "//ul[@id='address_invoice']/li[1]") private WebElement BillingAddress;
	@FindBy(xpath = "//ul[@id='address_invoice']/li[2]") private WebElement BillNameWithTitle;
	@FindBy(xpath = "//ul[@id='address_invoice']/li[3]") private WebElement BillCompany;
	@FindBy(xpath = "//ul[@id='address_invoice']/li[4]") private WebElement BillAddressLine1;
	@FindBy(xpath = "//ul[@id='address_invoice']/li[5]") private WebElement BillAddressLine2;
	@FindBy(xpath = "//ul[@id='address_invoice']/li[6]") private WebElement BillCityStatePinCode;
	@FindBy(xpath = "//ul[@id='address_invoice']/li[7]") private WebElement BillCountry;
	@FindBy(xpath = "//ul[@id='address_invoice']/li[8]") private WebElement BillMobileNum;
	
	@FindBy(xpath = "//a[text()='Download Invoice']") private WebElement DownloadInvoice;	
	
	public void addProductsOnHomePage() throws InterruptedException, IOException {
		js.executeScript("arguments[0].scrollIntoView(true);", FirstProduct);
		wait.until(ExpectedConditions.visibilityOf(FirstProduct));
		action.moveToElement(FirstProduct).perform();
		js.executeScript("arguments[0].click()", FirstProductAddToCart);
		wait.until(ExpectedConditions.visibilityOf(ContinueShopping_Btn));
		ContinueShopping_Btn.click();
		wait.until(ExpectedConditions.visibilityOf(SecondProduct));
		action.moveToElement(SecondProduct).perform();
		wait.until(ExpectedConditions.visibilityOf(SecondProductAddToCart));
		js.executeScript("arguments[0].click()", SecondProductAddToCart);
		wait.until(ExpectedConditions.visibilityOf(ContinueShopping_Btn));
		ContinueShopping_Btn.click();
		wait.until(ExpectedConditions.visibilityOf(ThirdProduct));
		action.moveToElement(ThirdProduct).perform();
		wait.until(ExpectedConditions.visibilityOf(ThirdProductAddToCart));
		js.executeScript("arguments[0].click()", ThirdProductAddToCart);
		wait.until(ExpectedConditions.visibilityOf(ContinueShopping_Btn));
		ContinueShopping_Btn.click();
		ColorLog.d("Products added to cart!");

		Cart.click();
		wait.until(ExpectedConditions.visibilityOf(ShoppingCart));
		assertEquals(ShoppingCart.getText(), "Shopping Cart");
		ColorLog.d("Landed on to Cart Page!");
	}

	public void registerWhileCheckOut() {
		wait.until(ExpectedConditions.visibilityOf(ProceedToCheckOut_Btn));
		ProceedToCheckOut_Btn.click();
		wait.until(ExpectedConditions.visibilityOf(RegisterLogin_Link));
		RegisterLogin_Link.click();
	}

	public void clickOnCart() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelProductTestData();
		wait.until(ExpectedConditions.visibilityOf(Cart));
		Cart.click();
		wait.until(ExpectedConditions.visibilityOf(ShoppingCart));
		assertEquals(ShoppingCart.getText(), sheet.getRow(15).getCell(0).toString());
	}

	public void clickOnProceedToCart() {
		wait.until(ExpectedConditions.visibilityOf(ProceedToCheckOut_Btn));
		ProceedToCheckOut_Btn.click();
	}

	public void beforeDeletingItemsInCart() {
		wait.until(ExpectedConditions.visibilityOfAllElements(NoOfItemsInCart));
		CountBeforeDeletion = NoOfItemsInCart.size();
		System.out.println("Total Items before Delete: "+CountBeforeDeletion);
		System.out.println("ThirdProduct Name before deletion: "+ThirdProductName.getText());
	}

	public void afterDeletingItemsInCart() throws InterruptedException {
		CartDeleteSymbol.click();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfAllElements(NoOfItemsInCart));
		//wait.withTimeout(Duration.ofMillis(4000));
		CountAfterDeletion = NoOfItemsInCart.size();
		System.out.println("Total Items after Delete: "+CountAfterDeletion);
		assertNotEquals(CountAfterDeletion, CountBeforeDeletion);
		try {
			System.out.println(ThirdProductName.getText());
		}
		catch(Exception e) {
			System.out.println("The third element of the row cant be accessed as its deleted");
		}
	}

	public void verifyAddress() throws InterruptedException, IOException {
		assertEquals(DelNameWithTitle.getText(), BillNameWithTitle.getText());
		assertEquals(DelCompany.getText(), BillCompany.getText());
		assertEquals(DelAddressLine1.getText(), BillAddressLine1.getText());
		assertEquals(DelAddressLine2.getText(), BillAddressLine2.getText());
		assertEquals(DelCityStatePinCode.getText(), BillCityStatePinCode.getText());
		assertEquals(DelCountry.getText(), BillCountry.getText());
		assertEquals(DelMobileNum.getText(), BillMobileNum.getText());
		ColorLog.d("Delivery address and Billing address are both same");

	}
	
	public static ArrayList<String> expectedAddress() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelData();
		ArrayList<String> ExpectedAddressReturn = new ArrayList<String>();
		ExpectedAddressReturn.add((SignUpAndLoginPage.TitleValue+". "+sheet.getRow(7).getCell(0).toString())+" "+(sheet.getRow(7).getCell(1).toString()));
		ExpectedAddressReturn.add((sheet.getRow(7).getCell(7).toString()).concat(" ").concat(sheet.getRow(7).getCell(6).toString()).concat(" ").concat(sheet.getRow(7).getCell(8).toString()));
		return ExpectedAddressReturn;
	}

	public void deliveryAddressCheck() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelData();
		ArrayList<String> ExpectedAddress = PlaceOrder.expectedAddress();
		Iterator<String> itr = ExpectedAddress.iterator();
		String ExpectedDelNameWithTitle = itr.next();
		String ExpectedCityStatePinCode = itr.next();
		assertEquals(DelNameWithTitle.getText(), ExpectedDelNameWithTitle);
		assertEquals(DelCompany.getText(), sheet.getRow(7).getCell(2).toString());
		assertEquals(DelAddressLine1.getText(), sheet.getRow(7).getCell(3).toString());
		assertEquals(DelAddressLine2.getText(), sheet.getRow(7).getCell(4).toString());
		assertEquals(DelCityStatePinCode.getText(), ExpectedCityStatePinCode);
		assertEquals(DelCountry.getText(), sheet.getRow(7).getCell(5).toString());
		assertEquals(DelMobileNum.getText(), sheet.getRow(7).getCell(9).getRawValue());
		ColorLog.d("The Billing Address is same address filled at the time registration of account");
	}

	public void billingAddressCheck() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelData();
		ArrayList<String> ExpectedAddress = PlaceOrder.expectedAddress();
		Iterator<String> itr = ExpectedAddress.iterator();
		String ExpectedDelNameWithTitle = itr.next();
		String ExpectedCityStatePinCode = itr.next();
		assertEquals(BillNameWithTitle.getText(), ExpectedDelNameWithTitle);
		assertEquals(BillCompany.getText(), sheet.getRow(7).getCell(2).toString());
		assertEquals(BillAddressLine1.getText(), sheet.getRow(7).getCell(3).toString());
		assertEquals(BillAddressLine2.getText(), sheet.getRow(7).getCell(4).toString());
		assertEquals(BillCityStatePinCode.getText(), ExpectedCityStatePinCode);
		assertEquals(BillCountry.getText(), sheet.getRow(7).getCell(5).toString());
		assertEquals(BillMobileNum.getText(), sheet.getRow(7).getCell(9).getRawValue());
		ColorLog.d("The Billing Address is same address filled at the time registration of account");
	}

	public String[] verifyCartAndReviewOrder() {
		String[] Array = {FirstProductName.getText(),FirstProductCategory.getText(),FirstProductPrice.getText(),FirstProductQuantity.getText(), FirstProductTotal.getText(),
				SecondProductName.getText(),SecondProductCategory.getText(), SecondProductPrice.getText(),SecondProductQuantity.getText(), SecondProductTotal.getText(),
				ThirdProductName.getText(),ThirdProductCategory.getText(),ThirdProductPrice.getText(),ThirdProductQuantity.getText(),ThirdProductPrice.getText()};
		 return Array;
	}

	public void placeOrder() throws IOException {
		XSSFSheet sheet1 = TestDataFromExcel.ExcelPlaceOrderData();
		CommentTextArea.sendKeys("Hey! deliver the ordered stuff by tomorrow");
		PlaceOrder_Btn.click();
		NameOnCard.sendKeys(sheet1.getRow(1).getCell(0).toString());
		CardNumber.sendKeys(sheet1.getRow(1).getCell(1).getRawValue());
		CVC.sendKeys(sheet1.getRow(1).getCell(2).getRawValue());
		MM.sendKeys(sheet1.getRow(1).getCell(3).getRawValue());
		YYYY.sendKeys(sheet1.getRow(1).getCell(4).getRawValue());
		PayAndConfirm.click();
		wait.until(ExpectedConditions.visibilityOf(ConfirmationMsg));
		assertEquals(OrderPlaced.getText(), sheet1.getRow(2).getCell(1).toString());
		assertEquals(ConfirmationMsg.getText(), sheet1.getRow(2).getCell(2).toString());
	}

	public void deleteAccount() throws IOException {
		XSSFSheet sheet1 = TestDataFromExcel.ExcelPlaceOrderData();
		wait.until(ExpectedConditions.visibilityOf(DeleteAccount));
		DeleteAccount.click();
		wait.until(ExpectedConditions.visibilityOf(AccountDeleted));
		assertEquals(AccountDeleted.getText(), sheet1.getRow(2).getCell(0).toString());
		ColorLog.d("'Account Deleted!' is visible.");
		wait.until(ExpectedConditions.visibilityOf(Continue_Btn));
		Continue_Btn.click();
	}

	public void downloadInvoice() {
		wait.until(ExpectedConditions.visibilityOf(DownloadInvoice));
		js.executeScript("arguments[0].click()", DownloadInvoice);
	}

	public void isInvoiceDownloded() {
		String home = System.getProperty("user.home");
		File Path = new File(home+"\\Downloads\\");
		File[] FolderContent = Path.listFiles();
		for (File element : FolderContent) {
			if(element.getName().equals("invoice")) {
				System.out.println("File "+element.getName()+" downloaded and found in Downloads folder");
				element.delete();
			}
		}

	}

}
