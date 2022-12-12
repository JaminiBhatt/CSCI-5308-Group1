package com.project.Group1.Bean;

import java.util.ArrayList;
import java.util.List;

public class IRDataList {
    private List<IRData> irDataList;

    public IRDataList() {
        irDataList = new ArrayList<>();
    }

    public void addIRData(IRData irData) {
        this.irDataList.add(irData);
    }

    public List<IRData> getIrDataList() {
        return irDataList;
    }

    public void setIrDataList(List<IRData> irDataList) {
        this.irDataList = irDataList;
    }

    @Override
    public String toString() {
        return "IRDataList{" +
                "irDataList=" + irDataList.toString() +
                '}';
    }
}
