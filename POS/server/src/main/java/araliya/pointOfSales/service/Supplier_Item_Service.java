
package araliya.pointOfSales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.Supplier_Item;

@Service
public interface Supplier_Item_Service {

    List<Supplier_Item> loadSupplier_Items(Long itemID) throws Exception;
    
} 