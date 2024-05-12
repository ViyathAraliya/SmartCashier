package araliya.pointOfSales.dtos;

import java.util.List;

import araliya.pointOfSales.entity.Customer;
import araliya.pointOfSales.entity.Transaction_Item;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class TransactionDto {
    
    private Customer customer;
    
    
    
}
