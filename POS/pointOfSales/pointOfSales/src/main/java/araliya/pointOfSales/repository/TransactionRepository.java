package araliya.pointOfSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import araliya.pointOfSales.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    
}