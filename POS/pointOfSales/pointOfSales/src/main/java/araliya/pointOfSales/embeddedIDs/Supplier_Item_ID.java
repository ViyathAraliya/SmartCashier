package araliya.pointOfSales.embeddedIDs;

import java.io.Serializable;

import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.entity.Supplier;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Supplier_Item_ID implements Serializable{

  
     @ManyToOne
    @JoinColumn(name="itemID")
    private Item item;

   
    @ManyToOne
    @JoinColumn(name="supplierID")
    private Supplier supplier;
    
}
