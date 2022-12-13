package com.example.Group1.Bean;

public class ICRCallAssignment {
    private int ID;
    private int SLID;
    private int itemCode;
    private String itemDesc;
    private int itemQty;
    private int offeredQty;

    private int ICRID;


    public ICRCallAssignment(int ID, int SLID, int itemCode, String itemDesc, int itemQty, int offeredQty, int ICRID) {
        this.ID = ID;
        this.SLID = SLID;
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
        this.itemQty = itemQty;
        this.offeredQty = offeredQty;
        this.ICRID = ICRID;
    }

    public int getSLID() {
        return SLID;
    }

    public void setSLID(int SLID) {
        this.SLID = SLID;
    }


    public int getICRID() {
        return ICRID;
    }

    public void setICRID(int ICRID) {
        this.ICRID = ICRID;
    }

    public int getSLNumber() {
        return SLID;
    }

    public void setPONumber(int PONumber) {
        this.SLID = PONumber;
    }

    public int getOfferedQty() {
        return offeredQty;
    }

    public void setOfferedQty(int offeredQty) {
        this.offeredQty = offeredQty;
    }


    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

