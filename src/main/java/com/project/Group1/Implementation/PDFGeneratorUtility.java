package com.project.Group1.Implementation;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.project.Group1.Bean.IRData;
import com.project.Group1.Bean.IRDataList;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PDFGeneratorUtility {

    public static void IRReport(HttpServletResponse response, IRDataList irDataList) throws IOException {

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocument;
        pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        try {
            Table table = new Table(new float[]{30f, 30f, 30F, 30F, 30F, 30F});
            table.setWidthPercent(100)
                    .setPadding(0)
                    .setFontSize(9);

            Cell cell1 = new Cell(1, 6);
            cell1.setTextAlignment(TextAlignment.CENTER);
            cell1.add("Inspection Report").setBold();
            table.addCell(cell1);

            table.addCell(new Cell().add("Item Code").setBold());
            table.addCell(new Cell().add("Item Description").setBold());
            table.addCell(new Cell().add("Item Quantity").setBold());
            table.addCell(new Cell().add("Assigned Quantity").setBold());
            table.addCell(new Cell().add("Accepted Quantity").setBold());
            table.addCell(new Cell().add("Rejected Quantity").setBold());

            for (IRData irData : irDataList.getIrDataList()) {
                table.addCell(new Cell().add(String.valueOf(irData.itemCode)));
                table.addCell(new Cell().add(String.valueOf(irData.itemDesc)));
                table.addCell(new Cell().add(String.valueOf(irData.itemQty)));
                table.addCell(new Cell().add(String.valueOf(irData.assignedQty)));
                table.addCell(new Cell().add(String.valueOf(irData.acceptedQty)));
                table.addCell(new Cell().add(String.valueOf(irData.rejectedQty)));
            }

            document.add(table);

            document.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}