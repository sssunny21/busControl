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

import bus.dto.Allocate;

public class AllocateListToExcelFile {
	public static void allocateListToFile(String sfileName, List<Allocate> allocateList,  HttpServletRequest request, HttpServletResponse response) throws Exception{
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
		cell4.setCellValue("배차 여부");
		Cell cell5 = row.createCell(5);
		cell5.setCellValue("취소 사유");

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
			if(allocate.isCancel_check() == true){
				cell4.setCellValue("취소");
			}else{
				cell4.setCellValue("완료");
			}
			cell5 = row.createCell(5);
			cell5.setCellValue(allocate.getCancel_reason());
			

		}while(iterator.hasNext());    

		fileOut = response.getOutputStream();
		workbook.write(fileOut);
		fileOut.close();

	}
}
