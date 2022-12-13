package com.project.Group1.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingList {

    private Integer Vendor_ID;
    private Integer Po_Number;
    private Integer item_code;
    private String itemDescription;
    private Integer itemQuantity;
    private Integer Product_ID;


}
