package araliya.pointOfSales.embeddedIDs;

import java.io.Serializable;


import araliya.pointOfSales.entity.Supplier;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Supplier_Item_ID implements Serializable{

    private Long itemID;
    private Long supplierID;
    
}
