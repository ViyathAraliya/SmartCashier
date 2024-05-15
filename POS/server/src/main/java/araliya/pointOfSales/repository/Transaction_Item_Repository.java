package araliya.pointOfSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import araliya.pointOfSales.embeddedIDs.Transaction_Item_ID;
import araliya.pointOfSales.entity.Transaction_Item;

@Repository
public interface  Transaction_Item_Repository extends JpaRepository<Transaction_Item, Transaction_Item_ID> {
    
}
