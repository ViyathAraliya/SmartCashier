package araliya.pointOfSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import araliya.pointOfSales.entity.Item;

public interface ItemRepository extends  JpaRepository<Item,Long>{
    Item findByName(String name);
}
