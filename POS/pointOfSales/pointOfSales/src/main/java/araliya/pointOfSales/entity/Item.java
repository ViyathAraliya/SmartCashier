package araliya.pointOfSales.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

import lombok.Data;


@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="itemID")
    private Long itemID;

    @ManyToOne
    @JoinColumn(name="catogryID")
    private Catagory catagoryID;

    @OneToOne
    @JoinColumn(name = "stockID")
    private Stock stockID;

 @Transient
    @OneToMany(mappedBy = "Item_supplier",targetEntity = Item_Supplier.class)
    private List<Item_Supplier> item_Suppliers;

    @Transient
    @OneToMany(mappedBy = "transaction_item", targetEntity = Transaction_Item.class)
    private List<Transaction_Item> transaction_items;

    
}
