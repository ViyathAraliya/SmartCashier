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
public class Customer {//since designing app for an buisneess such as supermarket,  only taking phone number to identify customer
    
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long customerID;

    @Column(name="phone_number",nullable = true)//if customer doesnt like to give phonenumber 
    private String phoneNumber;

    @Transient
    @OneToMany(mappedBy = "transaction",targetEntity=Transaction.class)
    private List<Transaction> transactions;
}
