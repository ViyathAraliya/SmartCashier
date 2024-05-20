package araliya.pointOfSales.entity;



import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    

    @Column(name="supplier_name",unique = true)
    private String name;

    @Column(name="contactNo")
    private String contactNo;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Transient
    @OneToMany(mappedBy = "Item_supplier",targetEntity = Supplier_Item.class)
    private List<Supplier_Item> item_Suppliers;
    


    
}
