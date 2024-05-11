package araliya.pointOfSales.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stockID")
    private Long stockID;

    @OneToOne
    @JoinColumn(name="itemID")
    private Item item;

    @Column(name="unit")
    private String unit;

    @Column(name="qty_on_hand", nullable = true)
    private Long qty_on_hand;

    @Column(name="qty_already_ordered_by_customers", nullable = true)
    private Long ordered_by_customers;

    @Column(name="qty_available_for_sale", nullable = true)
    private Long available_for_sale;

    /*@Column(name="qty_to_be_recieved")
    private Long to_be_recieved;

    @Column(name="recieving_date")
    private Date recieveing_date;*/

   


    
}
