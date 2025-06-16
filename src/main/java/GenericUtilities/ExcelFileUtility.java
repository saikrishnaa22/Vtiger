package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	 FileInputStream efis = null;
	     Workbook wb = null;

	public String fetchThedatafromExcel(String sheetname, int rowindex, int cellindex)
			throws EncryptedDocumentException, IOException {

		efis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(efis);
		String data = wb.getSheet(sheetname).getRow(rowindex).getCell(cellindex).toString();
		return data;
	}

	public void writeBackDataToExcelFile(String sheetname, int rowindex, int cellindex, String value)
			throws EncryptedDocumentException, IOException {
		efis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(efis);
		Cell c = wb.getSheet(sheetname).getRow(cellindex).getCell(cellindex);
		c.setCellValue(value);
		FileOutputStream efos = new FileOutputStream("./src/test/resources/VtigerTestData.xlsx");
		wb.write(efos);

	}

	public String fetchMultipleDatafromExcel(String sheetname) throws EncryptedDocumentException, IOException {
		String data = null;
		efis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(efis);
		Sheet sh = wb.getSheet(sheetname);
		for (int i = 0; i <= sh.getLastRowNum(); i++) {
			for (int j = 0; j < sh.getRow(i).getLastCellNum(); j++) {
				data = sh.getRow(i).getCell(j).toString();
			}
		}
		return data;

	}

	public void closeExcelWorkbook() throws IOException {
		wb.close();

	}

}
