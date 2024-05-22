package araliya.pointOfSales.repository;

import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.entity.ItemCategory;

public interface ItemRepository extends  JpaRepository<Item,Long>{
    Item findByName(String name);
    boolean existsByName(String name);
   
    
}
