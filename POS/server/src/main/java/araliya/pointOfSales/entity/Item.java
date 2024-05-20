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

    @Column(name="Item")
    private String name;

    @Column(name="unit")
    private String unit;

    @Column(name="unit_price")
    private Long unitPrice;

    @ManyToOne
    @JoinColumn(name="catogryID")
    private ItemCategory category;

    @Transient
    @OneToOne
    @JoinColumn(name = "stock")
    private Stock stock;

 @Transient
    @OneToMany(mappedBy = "supplier_item",targetEntity = Supplier_Item.class)
    private List<Supplier_Item> supplier_Items;

    @Transient
    @OneToMany(mappedBy = "transaction_item", targetEntity = Transaction_Item.class)
    private List<Transaction_Item> transaction_items;

    
}
