package araliya.pointOfSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import araliya.pointOfSales.entity.Supplier;

@Repository
public interface SupplieRepository extends JpaRepository<Supplier,Long>{

     boolean existsByName(String name);
     Supplier findByName(String name);
} 
