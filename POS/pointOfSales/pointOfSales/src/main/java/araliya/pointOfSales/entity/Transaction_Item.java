package araliya.pointOfSales.entity;



import araliya.pointOfSales.embeddedIDs.Transaction_Item_ID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Transaction_Item {
    
    @EmbeddedId
    private Transaction_Item_ID transaction_Item_ID;

    @ManyToOne
    @MapsId("itemID")
    @JoinColumn(name="item")
    private Item item;

    
    @ManyToOne
    @MapsId("transactionID")
    @JoinColumn(name = "transaction")
    private Transaction transaction;

    private Long qty;

    private Long amount;

}
