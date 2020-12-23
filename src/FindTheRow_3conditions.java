import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class FindTheRow_3conditions {
	public static List<Integer> findRows_3conditions(XSSFSheet sheet, String cellContent1, String cellContent2, String cellContent3) {
		List<Integer> matchedRows = new ArrayList<>();
		List<Integer> matchedRows1 = new ArrayList<>();
		List<Integer> matchedRows2 = new ArrayList<>();
		for (Row row1 : sheet) {
			for (Cell cell1 : row1) {
				if (cell1.getCellType() == CellType.STRING) {
					if(cell1.getRichStringCellValue().getString().trim().contentEquals(cellContent1)) {
						matchedRows1.add(row1.getRowNum());
					}
				}
			}
		}
		for(int i : matchedRows1) {
			for (Cell cell2 : sheet.getRow(i)) {
				if (cell2.getCellType() == CellType.STRING) {
					if(cell2.getRichStringCellValue().getString().trim().contentEquals(cellContent2)) {
						matchedRows2.add(sheet.getRow(i).getRowNum());
					}
				}
			}
		}
		for (int j : matchedRows2) {
			for (Cell cell3 : sheet.getRow(j)) {
				if (cell3.getCellType() == CellType.STRING) {
					if (cell3.getRichStringCellValue().getString().trim().contentEquals(cellContent3)) {
						matchedRows.add(sheet.getRow(j).getRowNum());
					}
				}
			}
		}
		return matchedRows;
	}
}
