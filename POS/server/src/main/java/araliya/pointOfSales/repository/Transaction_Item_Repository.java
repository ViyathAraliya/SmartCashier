package araliya.pointOfSales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import araliya.pointOfSales.embeddedIDs.Transaction_Item_ID;
import araliya.pointOfSales.entity.Transaction_Item;

@Repository
public interface Transaction_Item_Repository extends JpaRepository<Transaction_Item, Transaction_Item_ID> {
    
    @Query("SELECT t FROM Transaction_Item t WHERE t.transaction.transactionID = :transactionID")
    List<Transaction_Item> findAllByTransactionId(@Param("transactionID") Long transactionID);
    
}