import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SelectSheets {
	public static ArrayList<XSSFSheet> selectSheets () throws InvalidFormatException {
		File myExcel = null;
		try {
		myExcel = new File("resources/ContractManagement.xlsx");
		}
		catch(NullPointerException npe) {
			System.exit(0);
		}
			ArrayList<XSSFSheet> sheets = new ArrayList<XSSFSheet>();
		
		// Finds the workbook instance for XLSX file 

		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook (myExcel);
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		XSSFSheet sheet_contracte1 = myWorkBook.getSheet("Contracte");			// Return the sheet "Contracte" from the .xlsx workbook  
		sheets.add(sheet_contracte1);
		XSSFSheet sheet_facturi1 = myWorkBook.getSheet("Facturi");				// Return the sheet "Facturi" from the .xlsx workbook 
		sheets.add(sheet_facturi1);
		XSSFSheet sheet_ListaProduse1 = myWorkBook.getSheet("Lista_produse");	// Return the sheet "Lista_produse" from the .xlsx workbook 
		sheets.add(sheet_ListaProduse1);
		return sheets;				
	}
}
