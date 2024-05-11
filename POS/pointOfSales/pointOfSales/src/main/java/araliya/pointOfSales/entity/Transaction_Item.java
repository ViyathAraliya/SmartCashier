package araliya.pointOfSales.entity;



import araliya.pointOfSales.embeddedIDs.Transaction_Item_ID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Transaction_Item {
    
    @EmbeddedId
    private Transaction_Item_ID transaction_Item_ID;

    private Long qty;

    private Long amount;

}
