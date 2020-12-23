import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class FindTheRow {
	public static List<Integer> findRows(XSSFSheet sheet, String cellContent) {
		List<Integer> matchedRows = new ArrayList<>();
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == CellType.STRING) {
					if(cell.getRichStringCellValue().getString().trim().contentEquals(cellContent)) {
						matchedRows.add(row.getRowNum());
					}
				}
			}
		}               
		return matchedRows;
	}
}
