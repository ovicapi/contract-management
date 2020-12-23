import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ArrayFromExcelToFeedCombo {
	public static ArrayList<String> GetExcelTableIntoArrayListString(XSSFSheet s, boolean debug) throws InvalidFormatException {
		ArrayList<String> OUT = new ArrayList<String>(); 
		ArrayList<XSSFSheet> mySheet = new ArrayList<XSSFSheet>(SelectSheets.selectSheets());
		XSSFSheet sheet_ListaProduse = mySheet.get(2);
		Iterator<Row> rowIterator = sheet_ListaProduse.iterator(); 				// Get iterator to all the rows in current sheet 
		int count = 1;															// Traversing over each row of the .xlsx file 
		while (rowIterator.hasNext()) { 
			Row row = rowIterator.next();
			if(debug)System.out.print(count + ". \t");
			Cell cell = row.getCell(0);
			String c = cell.getStringCellValue();
			if(debug)System.out.print(c + "\t");
			OUT.add(c);
			count++; 
		}
		return OUT;
	}
}
