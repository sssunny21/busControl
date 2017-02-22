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

import bus.dto.Bus;

public class BusListToExcelFile {
	public static void busListToFile(String fileName, List<Bus> busList) throws Exception{
		Workbook workbook = null;

		if(fileName.endsWith("xlsx")){
			workbook = new XSSFWorkbook();
		}else if(fileName.endsWith("xls")){
			workbook = new HSSFWorkbook();
		}else{
			throw new Exception("invalid file name, should be xls or xlsx");
		}

		Sheet sheet = workbook.createSheet("bus");

		Iterator<Bus> iterator = busList.iterator();

		int rowIndex = 1;
		Row row = sheet.createRow(0);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("차량번호");
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("승차인원");
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("도입년도");
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("상태");
		Cell cell4 = row.createCell(4);
		cell4.setCellValue("배정날짜");

		do{
			Bus bus = iterator.next();
			row = sheet.createRow(rowIndex++);

			cell0 = row.createCell(0);
			cell0.setCellValue(bus.getBus_num());
			cell1 = row.createCell(1);
			cell1.setCellValue(bus.getLimit_passenger());
			cell2 = row.createCell(2);
			cell2.setCellValue(bus.getIntro_year());
			cell3 = row.createCell(3);
			cell3.setCellValue(bus.getState());
			cell4 = row.createCell(4);
			cell4.setCellValue(bus.getAllo_date());

		}while(iterator.hasNext());    

		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();

	}
}
