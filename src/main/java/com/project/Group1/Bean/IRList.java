package com.project.Group1.Bean;

import java.util.ArrayList;
import java.util.List;

public class IRList {
    List<IRData> list;

    IRList() {
        list = new ArrayList<>();
    }

    public List<IRData> getList() {
        return list;
    }

    public void setList(List<IRData> list) {
        this.list = list;
    }


}
