package araliya.pointOfSales.entity;


import org.hibernate.annotations.ManyToAny;

import araliya.pointOfSales.embeddedIDs.Supplier_Item_ID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Supplier_Item {
  
    @EmbeddedId
    private Supplier_Item_ID supplier_Item_ID;

   

    @ManyToOne
    @MapsId("itemID")
    @JoinColumn(name="item")
    private Item item;

    @ManyToOne
    @MapsId("supplierID")
    @JoinColumn(name="supplier")
    private Supplier supplier;
    

}
