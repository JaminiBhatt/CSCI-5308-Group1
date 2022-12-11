package com.project.Group1.CommandFactory;

import com.project.Group1.Dao.*;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

public class CommandFactory implements ICommandFactory {
    @Override
    public Command getUploadSPLCommand(IDatabase db, String[] args, Environment env) {
        return new UploadSPLCommand(db, args, env);
    }

    @Override
    public Command getICRCommand(IDatabase db, String[] args, Environment env) {
        return new ICRCommand(db, args, env);
    }

    @Override
    public Command getProjectIDCommand(IDatabase db, String[] args, Environment env) {
        return new ProjectIDCommand(db, args, env);
    }

    @Override
    public Command getVendorIDCommand(IDatabase db, String[] args, Environment env) {
        return new VendorIDCommand(db, args, env);
    }

    @Override
    public Command getProjectsCommand(IDatabase db, String[] args, Environment env) {
        return new ProjectsCommand(db, args, env);
    }

    @Override
    public Command getVendorsCommand(IDatabase db, String[] args, Environment env) {
        return new VendorsCommand(db, args, env);
    }

    @Override
    public Command getPOListCommand(IDatabase db, String[] args, Environment env) {
        return new POListCommand(db, args, env);
    }

    @Override
    public Command getSippingListCommand(IDatabase db, String[] args, Environment env) {
        return new ShippingListCommand(db, args, env);
    }

    @Override
    public Command getICRIDCommand(IDatabase db, String[] args, Environment env) {
        return new ICRIDCommand(db, args, env);
    }

    @Override
    public Command getICRItemsCommand(IDatabase db, String[] args, Environment env) {
        return new ICRItemsData(db, args, env);
    }

    @Override
    public Command getUserDetailsCommand(IDatabase db, String[] args, Environment env) {
        return new GetUserDetailsCommand(db, args, env);
    }

    @Override
    public Command getRoleIDCommand(IDatabase db, String[] args, Environment env) {
        return new GetRoleIDCommand(db, args, env);
    }

    @Override
    public Command getAdminUserCommand(IDatabase db, String[] args, Environment env) {
        return new GetAdminUserCommand(db, args, env);
    }

    @Override
    public Command addUserRoleCommand(IDatabase db, String[] args, Environment env) {
        return new AddUserRoleCommand(db, args, env);
    }

    @Override
    public Command getUsersBasedOnRoleIDCommand(IDatabase db, String[] args, Environment env) {
        return new GetUsersBasedOnRoleIDCommand(db, args, env);
    }

    @Override
    public Command uploadExcelCommand(IDatabase db, String[] args, Environment env) {
        return new UploadExcelCommand(db, args, env);
    }

    @Override
    public Command getVendorIdCommand(IDatabase db, String[] args, Environment env) {
        return new GetVendorIdCommand(db, args, env);
    }

    @Override
    public Command getShippingListID(IDatabase db, String[] args, Environment env) {
        return new GetShippingListIDCommand(db, args, env);
    }

    @Override
    public Command saveUploadExcel(IDatabase db, String[] args, Environment env) {
        return new SaveUploadExcelCommand(db, args, env);
    }

}