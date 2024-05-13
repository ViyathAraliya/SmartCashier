package araliya.pointOfSales.entity;

import java.util.Date;
import java.util.List;

import araliya.pointOfSales.embeddedIDs.Transaction_Item_ID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionID;

  @ManyToOne
  @JoinColumn(name = "customerID")
  private Customer customer;

  private Date dateTime;

  private Long totalAmount;

  @Transient
  @OneToMany(mappedBy = "transaction_item", targetEntity = Transaction_Item.class)
  private List<Transaction_Item> transaction_items;

}
