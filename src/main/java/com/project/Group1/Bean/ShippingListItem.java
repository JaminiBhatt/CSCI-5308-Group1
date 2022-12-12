package com.project.Group1.Bean;

public class ShippingListItem {
    private int PONumber;
    private int itemCode;
    private String itemDesc;
    private int itemQty;

    public ShippingListItem(int PONumber, int itemCode, String itemDesc, int itemQty) {
        this.PONumber = PONumber;
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
        this.itemQty = itemQty;
    }

    public int getPONumber() {
        return PONumber;
    }

    public void setPONumber(int PONumber) {
        this.PONumber = PONumber;
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
}
