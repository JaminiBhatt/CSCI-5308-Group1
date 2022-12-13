package com.project.Group1.Controller;

import com.project.Group1.Implementation.ExcelGenerator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class DownloadExcelController {

    @PostMapping("/DownloadExcel")
    public void downloadExcel(@RequestParam String project, @RequestParam String PO, @RequestParam String vendor, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("UploadSpl");
        String fileType = "attachment; filename=shippingList_Details" + ".xlsx";
        response.setHeader("Content-Disposition", fileType);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
        ExcelGenerator.getInstance().downloadExcel(response, project, vendor, PO);

    }
}
