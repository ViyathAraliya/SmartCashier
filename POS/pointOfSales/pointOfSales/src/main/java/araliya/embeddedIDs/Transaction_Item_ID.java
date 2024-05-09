package araliya.embeddedIDs;

import java.io.Serializable;

import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.entity.Transaction;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class Transaction_Item_ID implements Serializable {

  
    @ManyToOne
    @JoinColumn(name="transaction")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name="item")
    private Item item;

    
    
}
