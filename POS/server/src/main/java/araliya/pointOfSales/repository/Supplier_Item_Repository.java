package araliya.pointOfSales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import araliya.pointOfSales.embeddedIDs.Supplier_Item_ID;
import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.entity.Supplier_Item;



@Repository
public interface Supplier_Item_Repository extends JpaRepository<Supplier_Item, Supplier_Item_ID>{

    List<Supplier_Item> findAllByItem(Item item); 
}