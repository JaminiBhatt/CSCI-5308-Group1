package com.example.Group1.Bean;

public class VendorDashboardItems {
    private String projectName;
    private String vendorName;
    private int PONumber;
    private int ICRNumber;
    private int statusID;
    private String CreatedOn;
    private String CreatedBy;
    private int ICRID;

    public int getICRID() {
        return ICRID;
    }

    public void setICRID(int ICRID) {
        this.ICRID = ICRID;
    }

    public VendorDashboardItems(String projectName, String vendorName, int PONumber,
                                int ICRNumber, int statusID, String CreatedOn, String CreatedBy, int ICRID) {

        this.ICRID = ICRID;
        this.projectName = projectName;
        this.vendorName = vendorName;
        this.PONumber = PONumber;
        this.ICRNumber = ICRNumber;
        this.statusID = statusID;
        this.CreatedOn = CreatedOn;
        this.CreatedBy = CreatedBy;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getPONumber() {
        return PONumber;
    }

    public void setPONumber(int PONumber) {
        this.PONumber = PONumber;
    }

    public int getICRNumber() {
        return ICRNumber;
    }

    public void setICRNumber(int ICRNumber) {
        this.ICRNumber = ICRNumber;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        this.CreatedBy = CreatedBy;
    }
}
