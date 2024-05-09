package araliya.pointOfSales.entity;


import araliya.embeddedIDs.Transaction_Item_ID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Transaction_Item {
    
    @EmbeddedId
    private Transaction_Item_ID transaction_Item_ID;

   

    


    private Long qty;

    private Long amount;

}
