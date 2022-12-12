package com.project.Group1.Implementation;

import com.project.Group1.Bean.ShippingList;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.CommandFactory.ICommandFactory;
import com.project.Group1.Database.Database;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UploadExcel {

    private static UploadExcel instance_UploadExcel = null;
    private UploadExcel() {
    }
    public void save(MultipartFile file, Environment env, int projectID, int vendorID, int shippingListID, String UserName) throws SQLException, ClassNotFoundException {
        ICommandFactory factory = new CommandFactory();
        List<ShippingList> spl = null;
        try {
            spl = ExcelToListConverter.getInstance().convertExcelToListOfProduct(file.getInputStream(), projectID, vendorID);
            String[] args1 = new String[6];
            for (ShippingList shippingList : spl) {
                args1[0] = String.valueOf(shippingListID);
                args1[1] = String.valueOf(shippingList.getItem_code());
                args1[2] = shippingList.getItemDescription();
                args1[3] = String.valueOf(shippingList.getItemQuantity());
                args1[4] = String.valueOf(shippingList.getPo_Number());
                args1[5] = UserName;
                Command saveUploadExcel = factory.saveUploadExcel(Database.getInstance(), args1, env);
                saveUploadExcel.execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static UploadExcel getInstance() {
        if (instance_UploadExcel == null) {
            instance_UploadExcel = new UploadExcel();
        }
        return instance_UploadExcel;
    }
}
