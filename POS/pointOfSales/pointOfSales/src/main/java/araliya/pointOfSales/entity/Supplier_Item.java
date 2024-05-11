package araliya.pointOfSales.entity;


import araliya.pointOfSales.embeddedIDs.Supplier_Item_ID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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


    

}
