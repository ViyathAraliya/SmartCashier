package araliya.pointOfSales.entity;

import java.util.List;

import araliya.embeddedIDs.Transaction_Item_ID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Transaction {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long transactionID;

@JoinColumn(name="customerID")
private Long customerID;


private Long totalAmount;

  @Transient
    @OneToMany(mappedBy = "transaction_itemID", targetEntity = Transaction_Item_ID.class)
    private List<Transaction_Item_ID> transaction_itemIDs;



}
