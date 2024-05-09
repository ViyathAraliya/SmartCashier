package araliya.pointOfSales.entity;

import araliya.embeddedIDs.Item_Supplier_ID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Data
@Entity
public class Item_Supplier {
  
    @EmbeddedId
    private Item_Supplier_ID id;


    

}
