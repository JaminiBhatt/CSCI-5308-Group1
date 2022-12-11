package com.project.Group1.CommandFactory;

import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

public interface ICommandFactory {
    Command getUploadSPLCommand(IDatabase db, String[] args, Environment env);

    Command getICRCommand(IDatabase db, String[] args, Environment env);

    Command getProjectIDCommand(IDatabase db, String[] args, Environment env);

    Command getVendorIDCommand(IDatabase db, String[] args, Environment env);

    Command getProjectsCommand(IDatabase db, String[] args, Environment env);

    Command getVendorsCommand(IDatabase db, String[] args, Environment env);

    Command getPOListCommand(IDatabase db, String[] args, Environment env);

    Command getSippingListCommand(IDatabase db, String[] args, Environment env);

    Command getICRIDCommand(IDatabase db, String[] args, Environment env);

    Command getICRItemsCommand(IDatabase db, String[] args, Environment env);

    Command getUserDetailsCommand(IDatabase db, String[] args, Environment env);

    Command getRoleIDCommand(IDatabase db, String[] args, Environment env);

    Command getAdminUserCommand(IDatabase db, String[] args, Environment env);

    Command addUserRoleCommand(IDatabase db, String[] args, Environment env);

    Command getUsersBasedOnRoleIDCommand(IDatabase db, String[] args, Environment env);

    Command uploadExcelCommand(IDatabase db, String[] args, Environment env);

    Command getVendorIdCommand(IDatabase db, String[] args, Environment env);

    Command getShippingListID(IDatabase db, String[] args, Environment env);

    Command saveUploadExcel(IDatabase db, String[] args, Environment env);
}
