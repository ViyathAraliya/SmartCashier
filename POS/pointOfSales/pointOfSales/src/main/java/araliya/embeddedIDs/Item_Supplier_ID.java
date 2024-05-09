package araliya.embeddedIDs;

import java.io.Serializable;

import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.entity.Supplier;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Data
@Embeddable
public class Item_Supplier_ID implements Serializable{

  
     @ManyToOne
    @JoinColumn(name="itemID")
    private Item item;

   
    @ManyToOne
    @JoinColumn(name="supplierID")
    private Supplier supplier;
    
}
