package araliya.pointOfSales.entity;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categoryID")
    private Long catagoryID;

    @Column(name="name",unique = true)
    private String name;

    @Transient
    @OneToMany(mappedBy = "item",targetEntity = Item.class)
    private List<Item> items;
}
