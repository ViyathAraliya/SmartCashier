package araliya.pointOfSales.entity;



import java.util.List;

import araliya.embeddedIDs.Item_Supplier_ID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supplierID")
    private Long supplierID;

    @ManyToOne
    @JoinColumn(name="ItemID")
    private Item itemID;

    @Column(name="supplier_name")
    private Integer name;

    @Column(name="contactNo")
    private String contactNo;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Transient
    @OneToMany(mappedBy = "Item_supplier",targetEntity = Item_Supplier.class)
    private List<Item_Supplier> item_Suppliers;
    


    
}
