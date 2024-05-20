package araliya.pointOfSales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import araliya.pointOfSales.embeddedIDs.Supplier_Item_ID;
import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.entity.Supplier;
import araliya.pointOfSales.entity.Supplier_Item;
import araliya.pointOfSales.repository.ItemRepository;
import araliya.pointOfSales.repository.SupplieRepository;
import araliya.pointOfSales.repository.Supplier_Item_Repository;
import araliya.pointOfSales.service.Supplier_Item_Service;

@Service
public class Supplier_Item_ServiceImpl implements Supplier_Item_Service {
       @Autowired
    private PlatformTransactionManager transactionManager;


    @Autowired
    private Supplier_Item_Repository supplier_Item_Repository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SupplieRepository supplieRepository;

    @Override
    public List<Supplier_Item> loadSupplier_Items(Long itemID) throws Exception {

        Item item = itemRepository.findById(itemID).orElse(null);
        if (item == null) {
            throw new Exception("couldnt retrieve item from repository.");
        }

        return supplier_Item_Repository.findAllByItem(item);
    }

    @Override
    public void deleteSupplier_Items(Supplier_Item_ID supplier_Item_ID) throws Exception {
        Supplier_Item supplier_item = supplier_Item_Repository.findById(supplier_Item_ID).orElse(null);
        try {
            if (supplier_item == null) {
                System.out.println("reached here");
                throw new Exception("couldnt retriev supplier from repository");
            }
            supplier_Item_Repository.delete(supplier_item);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Supplier_Item saveSupplier_Item(Supplier_Item supplier_Item) throws Exception {
         TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        System.out.println(888);
        try {
            Supplier_Item_ID supplier_Item_ID = supplier_Item.getSupplier_Item_ID();
            Long itemID = supplier_Item_ID.getItemID();
            Long supplierID = supplier_Item_ID.getSupplierID();
//System.out.println("ITEM ID: "+itemID+" Supplier ID : "+supplierID);
            if (supplierID == null) {
                
                throw new Exception("supplier id is null");
            }
            Item item = itemRepository.findById(itemID).orElse(null);
            //System.out.println("retrieved item : "+item.getItemID());
            if (item == null) {
                throw new Exception("item is null");
            }

            Supplier supplier = supplieRepository.findById(supplierID).orElse(null);
            supplier_Item.setItem(item);
            supplier_Item.setSupplier(supplier);
            if (supplier == null) {
                throw new Exception("supplier is null");
            }

          //  System.out.println("id: "+(supplier_Item_ID==null)+"item :"+(item==null)+"supplier"+(supplier==null));
            Supplier_Item supplier_Item2 = supplier_Item_Repository.save(supplier_Item);
            if (supplier_Item2 == null) {
                System.out.println("couldn't save");
                return supplier_Item2;
            }
           
                    transactionManager.commit(status);
                    System.out.println("saved successfully");
            return supplier_Item2;
        } catch (Exception e) {
            System.out.println(e);
            transactionManager.rollback(status);
            throw new Exception(e.getMessage());
        }
        
    }
}