package com.project.Group1.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingListItem {
    private int PONumber;
    private int itemCode;
    private String itemDesc;
    private int itemQty;

}
