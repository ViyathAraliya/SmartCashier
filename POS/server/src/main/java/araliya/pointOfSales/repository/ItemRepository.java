package araliya.pointOfSales.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import araliya.pointOfSales.dtos.UpdateItemDto;
import araliya.pointOfSales.entity.Item;


public interface ItemRepository extends  JpaRepository<Item,Long>{
    Item findByName(String name);
    boolean existsByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Item i SET i.name = :#{#updateItemDto.name}, i.unit = :#{#updateItemDto.unit}, " +
            "i.unitPrice = :#{#updateItemDto.unitPrice}, i.category.id = :#{#updateItemDto.categoryID} " +
            "WHERE i.itemID = :#{#updateItemDto.itemID}")
    int updateItem(UpdateItemDto updateItemDto);
   
    
}
