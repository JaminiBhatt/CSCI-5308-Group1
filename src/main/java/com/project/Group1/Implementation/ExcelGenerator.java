package com.project.Group1.Implementation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExcelGenerator {

    private static ExcelGenerator instance_Excel_Generator = null;

    private ExcelGenerator() {
    }

    public void downloadExcel(HttpServletResponse response, String projects, String vendors, String PO) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("SPL");

            Row row = sheet.createRow(0);

            Cell cell = row.createCell(0);
            cell.setCellValue("Project_Name");

            Cell cell1 = row.createCell(1);
            cell1.setCellValue("Vendor_Name");

            Cell cell2 = row.createCell(2);
            cell2.setCellValue("PO_Number");

            Cell cell3 = row.createCell(3);
            cell3.setCellValue("ItemCode");

            Cell cell4 = row.createCell(4);
            cell4.setCellValue("ItemDescription");

            Cell cell5 = row.createCell(5);
            cell5.setCellValue("ItemQuantity");

            Row row1 = sheet.createRow(1);

            Cell cell11 = row1.createCell(0);
            cell11.setCellValue(projects);

            Cell cell12 = row1.createCell(1);
            cell12.setCellValue(vendors);

            Cell cell13 = row1.createCell(2);
            cell13.setCellValue(Integer.parseInt(PO));


            //write output to response
            workbook.write(response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExcelGenerator getInstance() {
        if (instance_Excel_Generator == null) {
            instance_Excel_Generator = new ExcelGenerator();
        }
        return instance_Excel_Generator;
    }
}
