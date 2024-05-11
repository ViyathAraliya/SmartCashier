package araliya.pointOfSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import araliya.pointOfSales.entity.ItemCategory;

@Repository
public interface ItemCatagoryRepository extends JpaRepository<ItemCategory,Long>{
    
}
