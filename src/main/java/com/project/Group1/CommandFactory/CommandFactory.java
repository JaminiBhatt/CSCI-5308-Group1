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
        return new ICRCommand(db, args, env);
    }

    @Override
    public Command getUserDetailsCommand(IDatabase db, String[] args, Environment env) {
        return new UserDetailsCommand(db, args, env);
    }

    @Override
    public Command getRoleIDCommand(IDatabase db, String[] args, Environment env) {
        return new RoleIDCommand(db, args, env);
    }

    @Override
    public Command getAdminUserCommand(IDatabase db, String[] args, Environment env) {
        return new AdminUserCommand(db, args, env);
    }

    @Override
    public Command addUserRoleCommand(IDatabase db, String[] args, Environment env) {
        return new UserRoleCommand(db, args, env);
    }

    @Override
    public Command getUsersBasedOnRoleIDCommand(IDatabase db, String[] args, Environment env) {
        return new UsersBasedOnRoleIDCommand(db, args, env);
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
        return new ShippingListIDCommand(db, args, env);
    }

    @Override
    public Command saveUploadExcel(IDatabase db, String[] args, Environment env) {
        return new UploadExcelCommand(db, args, env);
    }

    @Override
    public Command getICRCallAssignmentCommand(IDatabase db, String[] args, Environment env) {
        return new ICRCallAssignmentCommand(db, args, env);
    }

    @Override
    public Command getInspectorCommand(IDatabase db, String[] args, Environment env) {
        return new InspectorCommand(db, args, env);
    }

    @Override
    public Command getInspectorEmailCommand(IDatabase db, String[] args, Environment env) {
        return new InspectorEmailCommand(db, args, env);
    }

    @Override
    public Command getICRVendorDashboardCommand(IDatabase db, String[] args4, Environment env) {
        return new ICRVendorDashboardCommand(db, args4, env);
    }

    @Override
    public Command getUpdateICRStatusCommand(IDatabase db, String[] args, Environment env) {
        return new UpdateICRStatusCommand(db, args, env);
    }

    @Override
    public Command getUpdateICR(IDatabase db, String[] args, Environment env) {
        return new UpdateICRCommand(db, args, env);
    }

    @Override
    public Command getUpdateICRItems(IDatabase db, String[] args, Environment env) {
        return new UpdateICRItems(db, args, env);
    }

    @Override
    public Command getIRDataCommand(IDatabase db, String[] args, Environment env) {
        return new IRDataCommand(db, args, env);
    }

    @Override
    public Command getUpdateIR(IDatabase db, String[] args, Environment env) {
        return new UpdateIRCommand(db, args, env);
    }

    @Override
    public Command getUpdateIRItems(IDatabase db, String[] args, Environment env) {
        return new UpdateIRItems(db, args, env);
    }

    @Override
    public Command getAllIRItems(IDatabase db, String[] args, Environment env) {
        return new ALLIRDataCommand(db, args, env);
    }
}
