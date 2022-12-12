package com.project.Group1.Bean;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IRData {

    public int itemCode;
    public String itemDesc;
    public int itemQty;

    public int assignedQty;
    public int acceptedQty;

    public int rejectedQty;

    public int quantityHold;


}
