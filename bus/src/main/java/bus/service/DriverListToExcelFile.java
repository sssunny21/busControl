package bus.service;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import bus.dto.Driver;
public class DriverListToExcelFile {
	public static void driverListToFile(String fileName, List<Driver> driverList) throws Exception{
		Workbook workbook = null;

		if(fileName.endsWith("xlsx")){
			workbook = new XSSFWorkbook();
		}else if(fileName.endsWith("xls")){
			workbook = new HSSFWorkbook();
		}else{
			throw new Exception("invalid file name, should be xls or xlsx");
		}

		Sheet sheet = workbook.createSheet("driver");

		Iterator<Driver> iterator = driverList.iterator();

		int rowIndex = 1;
		Row row = sheet.createRow(0);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("성명");
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("생년월일");
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("입사일자");
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("상태");

		do{
			Driver driver = iterator.next();
			row = sheet.createRow(rowIndex++);

			cell0 = row.createCell(0);
			cell0.setCellValue(driver.getName());
			cell1 = row.createCell(1);
			cell1.setCellValue(driver.getBirth());
			cell2 = row.createCell(2);
			cell2.setCellValue(driver.getJoin_date());
			cell3 = row.createCell(3);
			cell3.setCellValue(driver.getState());

		}while(iterator.hasNext());    

		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();

	}
}
