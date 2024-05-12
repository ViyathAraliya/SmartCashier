package araliya.pointOfSales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import araliya.pointOfSales.dtos.ItemDto;
import araliya.pointOfSales.embeddedIDs.Supplier_Item_ID;
import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.entity.ItemCategory;
import araliya.pointOfSales.entity.Stock;
import araliya.pointOfSales.entity.Supplier;
import araliya.pointOfSales.entity.Supplier_Item;
import araliya.pointOfSales.repository.ItemCatagoryRepository;
import araliya.pointOfSales.repository.ItemRepository;
import araliya.pointOfSales.repository.StockRepository;
import araliya.pointOfSales.repository.SupplieRepository;
import araliya.pointOfSales.repository.Supplier_Item_Repository;
import araliya.pointOfSales.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SupplieRepository supplierRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemCatagoryRepository itemCatagoryRepository;

    @Autowired
    private Supplier_Item_Repository supplier_Item_Repository;

    public String saveItem(ItemDto itemDto) throws Exception {// for a new item,
        // "If an item exists, then a stock exists; otherwise, there is no stock."
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            ItemCategory itemCategory = itemDto.getCategory();
            
            Long categoryID = itemCategory.getCatagoryID();
            String categoryName = itemCategory.getDescription();
            
            Boolean categoryIdIsNull = categoryID == null;

            // validating dto
            String itemName = itemDto.getName();
            String unit = itemDto.getUnit();
            ItemCategory category = itemDto.getCategory();
            Stock stock = itemDto.getStock();
            Supplier supplier = itemDto.getSupplier();
            Long qty = itemDto.getQty();

            Object[] arr = { itemName, unit, category, stock, supplier, qty };
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    return "there is a null value in the dto. Please check the dto";
                }
            }

            // validating and saving category
            Boolean noSuchCategoryByName= noSuchCategoryByName = itemCatagoryRepository.existsByDescription(categoryName) ==false;
            
             
            if (noSuchCategoryByName) {
                return "no such category(by name). If you want to add this category, add it from category service ";
            }
            if (categoryIdIsNull) {
                categoryID = itemCatagoryRepository.findByDescription(categoryName).getCatagoryID();
                itemCategory.setCatagoryID(categoryID);
                
            } else {
                Boolean noSuchCategoryByID = itemCatagoryRepository.existsById(categoryID)==false;
                if (noSuchCategoryByID) {
                    return "no such category(by id). Send without an id if you want to create a new category";
                }
              ;
                Boolean categoryIdMatchesName = categoryID == itemCatagoryRepository.findByDescription(categoryName)
                        .getCatagoryID();
                      
                if (categoryIdMatchesName==false) {
                    return "categoryID does'nt match  category name";
                }
            }
      
            //validating and saving transient entitie
            //validating and saving item
            Item item = new Item();

            item.setName(itemName);
            Boolean itemAlreadyExists = itemRepository.existsByName(itemName);
            if (itemAlreadyExists) {
                return "this item already exists";
            }
            item.setCategory(category);
            item.setUnit(unit);
            item = itemRepository.save(item);
            //validating and saving entities which contains transient entities
            //validating and saving stock
            stock = itemDto.getStock();
            Long stockID = stock.getStockID();
            if (stockID != null) {
                return "a newly added item cannot already have a  stockID. Send the item without a stock ID the  buisness logic will assign a stockID and other attributes of stock";
            }
            if (stock.getItem() != null) {
                return "dont send the item with stock, the buisness logic will assign the item to stock";
            }
            stock.setItem(item);
            if (stock.getUnit() != null) {
                return "dont send the unit with stock. The business logic will assign the unit from item";
            }
            stock.setUnit(item.getUnit());
            stock = stockRepository.save(stock);// no need to validate other attributes of stock because they are nullable                                       
          
            //validating and saving Supplier_Item
            Long supplierID = supplier.getSupplierID();
            boolean supplierIdIsNull = supplierID == null;
            boolean supplierExists;
            if (supplierIdIsNull) {
                boolean nameExists = supplierRepository.existsByName(itemName);
                if (nameExists) {
                    return "a supplier is already registered with this name. Check from supplier service if the existing supplier is the right one and send supplier again with that id. Else register this supplier under a different name";
                }
                if (supplier.getEmail() == null || supplier.getAddress() == null || supplier.getContactNo() == null) {
                    return "lacking essential supplier details.";
                }
             
                supplier = supplierRepository.save(supplier);
           

            } else {
                supplierExists = supplierRepository.existsById(supplierID);
                if (supplierExists == false) {
                    return "A supplier with the given id does not exist";
                }
            }

            Supplier_Item_ID supplier_Item_ID = new Supplier_Item_ID(item.getItemID(), supplier.getSupplierID());
            Supplier_Item supplier_Item = new Supplier_Item(supplier_Item_ID,item,supplier); 
            supplier_Item_Repository.save(supplier_Item);//this line error
           

            transactionManager.commit(status);
            return "item saved succesfully";

        } catch (Exception ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }

}