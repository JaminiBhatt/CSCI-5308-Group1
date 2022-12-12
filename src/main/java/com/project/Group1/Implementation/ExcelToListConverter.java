package com.project.Group1.Implementation;

import com.project.Group1.Bean.ShippingList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelToListConverter {

    private static ExcelToListConverter instance_Excel_To_ListConverter = null;

    private ExcelToListConverter() {
    }

    public boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }

    public List<ShippingList> convertExcelToListOfProduct(InputStream is, int projectId, int vendorID) {
        List<ShippingList> list = new ArrayList<>();
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("SPL");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                ShippingList spl = new ShippingList();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            spl.setProduct_ID(projectId);
                            break;
                        case 1:
                            spl.setVendor_ID(vendorID);
                            break;
                        case 2:
                            spl.setPo_Number((int) cell.getNumericCellValue());
                            break;
                        case 3:
                            spl.setItem_code((int) cell.getNumericCellValue());
                            break;

                        case 4:
                            spl.setItemDescription(cell.getStringCellValue());
                            break;

                        case 5:
                            spl.setItemQuantity((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(spl);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public static ExcelToListConverter getInstance() {
        if (instance_Excel_To_ListConverter == null) {
            instance_Excel_To_ListConverter = new ExcelToListConverter();
        }
        return instance_Excel_To_ListConverter;
    }


}
