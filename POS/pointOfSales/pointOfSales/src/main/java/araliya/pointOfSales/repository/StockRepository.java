package araliya.pointOfSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import araliya.pointOfSales.entity.Stock;

@Repository
public interface StockRepository  extends JpaRepository<Stock, Long>{
    
}
