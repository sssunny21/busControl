package bus.service;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import bus.dto.Bus;

public class BusListToExcelFile {
	public static void busListToFile(String sfileName, List<Bus> busList,HttpServletRequest request, HttpServletResponse response) throws Exception{
		sfileName = new String ( sfileName.getBytes("KSC5601"), "8859_1");

		response.reset();//한글 깨짐 방지

		String strClient = request.getHeader("User-Agent");

		String fileName = sfileName;

		if (strClient.indexOf("MSIE 5.5") > -1) {
			response.setHeader("Content-Disposition", "filename=" + fileName + ";");
		} else {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ";");
		}

		OutputStream fileOut = null;
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
		}while(iterator.hasNext());    
		
		fileOut = response.getOutputStream();
		workbook.write(fileOut);
		fileOut.close();

	}
}
