package araliya.pointOfSales.embeddedIDs;

import java.io.Serializable;

import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.entity.Transaction;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@Embeddable
@AllArgsConstructor
public class Transaction_Item_ID implements Serializable {

  
    @ManyToOne
    @JoinColumn(name="transaction")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name="item")
    private Item item;

    
    
}
