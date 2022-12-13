package com.project.Group1.Bean;

public class ICR {
    private int ID;
    private String ICR_number;
    private int projectId;
    private int VendorId;
    private int StatusId;
    private String POnumber;
    private String Remarks;
    private String location;
    private String proposedFromDate;
    private String proposedToDate;
    private String createdOnDate;
    private String createdToDate;
    private String ModifiedOnDate;
    private String ModifiedToDate;

    public int getID() {
        return ID;
    }

    public String getICR_number() {
        return ICR_number;
    }

    public void setICR_number(String ICR_number) {
        this.ICR_number = ICR_number;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getVendorId() {
        return VendorId;
    }

    public void setVendorId(int vendorId) {
        VendorId = vendorId;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public String getPOnumber() {
        return POnumber;
    }

    public void setPOnumber(String POnumber) {
        this.POnumber = POnumber;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProposedFromDate() {
        return proposedFromDate;
    }

    public void setProposedFromDate(String proposedFromDate) {
        this.proposedFromDate = proposedFromDate;
    }

    public String getProposedToDate() {
        return proposedToDate;
    }

    public void setProposedToDate(String proposedToDate) {
        this.proposedToDate = proposedToDate;
    }

    public String getCreatedOnDate() {
        return createdOnDate;
    }

    public void setCreatedOnDate(String createdOnDate) {
        this.createdOnDate = createdOnDate;
    }

    public String getCreatedToDate() {
        return createdToDate;
    }

    public void setCreatedToDate(String createdToDate) {
        this.createdToDate = createdToDate;
    }

    public String getModifiedOnDate() {
        return ModifiedOnDate;
    }

    public void setModifiedOnDate(String modifiedOnDate) {
        ModifiedOnDate = modifiedOnDate;
    }

    public String getModifiedToDate() {
        return ModifiedToDate;
    }

    public void setModifiedToDate(String modifiedToDate) {
        ModifiedToDate = modifiedToDate;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
