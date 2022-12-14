package com.project.Group1.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor

public class ICRItemsList {
    private List<ICRItems> icrItemsList;

    public ICRItemsList() {
        icrItemsList = new ArrayList<>();
    }

    public void addICRItem(ICRItems icrItems) {
        this.icrItemsList.add(icrItems);
    }

    public List<ICRItems> getIcrItemsList() {
        return icrItemsList;
    }

    public void setIcrItemsList(List<ICRItems> icrItemsList) {
        this.icrItemsList = icrItemsList;
    }

}
