package araliya.pointOfSales.embeddedIDs;

import java.io.Serializable;


import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Transaction_Item_ID implements Serializable {

  
    private Long transactionID;
    private Long itemID;

    
    
}
