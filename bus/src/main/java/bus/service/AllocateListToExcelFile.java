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

import bus.dto.Allocate;

public class AllocateListToExcelFile {
	public static void allocateListToFile(String fileName, List<Allocate> allocateList) throws Exception{
		Workbook workbook = null;

		if(fileName.endsWith("xlsx")){
			workbook = new XSSFWorkbook();
		}else if(fileName.endsWith("xls")){
			workbook = new HSSFWorkbook();
		}else{
			throw new Exception("invalid file name, should be xls or xlsx");
		}

		Sheet sheet = workbook.createSheet("allocate");

		Iterator<Allocate> iterator = allocateList.iterator();

		int rowIndex = 1;
		Row row = sheet.createRow(0);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("차량번호");
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("차량상태");
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("배정된 기사");
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("배차 일자");
		Cell cell4 = row.createCell(4);
		cell4.setCellValue("운행 여부");

		do{
			Allocate allocate = iterator.next();
			row = sheet.createRow(rowIndex++);

			cell0 = row.createCell(0);
			cell0.setCellValue(allocate.getBus_num());
			cell1 = row.createCell(1);
			cell1.setCellValue(allocate.getState());
			cell2 = row.createCell(2);
			cell2.setCellValue(allocate.getName());
			cell3 = row.createCell(3);
			cell3.setCellValue(allocate.getAllo_date());
			cell4 = row.createCell(4);
			if(allocate.isOperate_check() == true){
				cell4.setCellValue("시작");
			}else{
				cell4.setCellValue("대기");
			}
			

		}while(iterator.hasNext());    

		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();

	}
}
