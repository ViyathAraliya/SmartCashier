
package araliya.pointOfSales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.embeddedIDs.Supplier_Item_ID;
import araliya.pointOfSales.entity.Supplier_Item;

@Service
public interface Supplier_Item_Service {

    List<Supplier_Item> loadSupplier_Items(Long itemID) throws Exception;
    void deleteSupplier_Items(Supplier_Item_ID supplier_Item_ID) throws Exception;
    Supplier_Item saveSupplier_Item(Supplier_Item supplier_Item) throws Exception;
    
} 