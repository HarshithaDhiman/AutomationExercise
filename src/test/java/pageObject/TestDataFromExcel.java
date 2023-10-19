package pageObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataFromExcel {
	static File file = new File(".\\Data\\AutomationExercise_Data.xlsx");
	@SuppressWarnings("resource")
	public static XSSFSheet ExcelData() throws IOException {
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Data");
		return sheet;
	}
	public static XSSFSheet ExcelProductTestData() throws IOException {
		FileInputStream fis = new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("ProductTestContactData");
		return sheet;
	}
	@SuppressWarnings("resource")
	public static XSSFSheet ExcelPlaceOrderData() throws IOException {
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("PlaceOrder");
		return sheet;
	}
}
