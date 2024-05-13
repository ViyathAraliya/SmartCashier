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

            // validating dto
            String itemName = itemDto.getName();
            String unit = itemDto.getUnit();
            ItemCategory itemCategory = itemDto.getCategory();
            Stock stock = itemDto.getStock();
            Supplier supplier = itemDto.getSupplier();
            Long qty = itemDto.getQty();

            Object[] arr = { itemName, unit, itemCategory, stock, supplier, qty };
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    throw  new Exception ("there is a null value in the dto. Please check the dto" );
                }
            }

            Long categoryID = itemCategory.getCatagoryID();
            String categoryName = itemCategory.getDescription();

            Boolean categoryIdIsNull = categoryID == null;
            Boolean categoryNameIsNull = categoryName == null;
            System.out.println("at id" + categoryID);

            // validating and saving category
            boolean itemExistsByID = false;
            if (categoryIdIsNull == false) {
                itemExistsByID=itemCatagoryRepository.existsById(categoryID);
            }
            boolean itemExistsByName = false;
            if (categoryNameIsNull == false) {
                itemExistsByName=itemCatagoryRepository.existsByDescription(categoryName);
            }

            ItemCategory itemCategoryFoundByDescription = null;
            if (categoryNameIsNull == false) {
                itemCategoryFoundByDescription=itemCatagoryRepository.findByDescription(categoryName);
            }
            ItemCategory itemCategoryFoundByID = null;
            if (categoryIdIsNull == false) {
                itemCategoryFoundByID=itemCatagoryRepository.findById(categoryID).orElse(null);
            }
            boolean itemCategoryFoundByIDisNull = itemCategoryFoundByID == null;

            if (categoryIdIsNull) {
                if (categoryNameIsNull) {
                    throw new Exception("both the categoryID and the categoryName are null");
                }
                if (categoryNameIsNull == false) {
                    if (itemExistsByName == false) {
                        throw new Exception("no such category by provided name");
                    }
                    itemCategory = itemCategoryFoundByDescription;
                    categoryID = itemCategoryFoundByID.getCatagoryID();
                }
            } else if (categoryNameIsNull) {
                if (itemExistsByID) {
                    itemCategory = itemCategoryFoundByID;
                    if (itemCategoryFoundByIDisNull) {
                        throw new Exception( "error in retriving item. t=retrivesnull by repo");
                    }
                    categoryName = itemCategory.getDescription();
                } else {
                    throw new Exception( "no such category by provided id");
                }
            } else if (itemExistsByID == false) {
                throw new Exception("no such category by provided id");
            } else if (itemExistsByName == false) {
                throw new Exception("no such category by provided name");
            } else {
                itemCategory = itemCategoryFoundByID;
                if (itemCategory == null) {
                    throw new Exception("error in retriving categoru(repo returns null)");
                }
                if (itemCategory.getCatagoryID()
                        .equals(itemCategoryFoundByDescription.getCatagoryID()) == false) {
                    throw new Exception( "category ID doesnt match the category name");
                }
            }

            // validating and saving transient entitie
            // validating and saving item
            Item item = new Item();

            item.setName(itemName);
            boolean itemAlreadyExists = itemRepository.existsByName(itemName);
            if (itemAlreadyExists) {
                throw new Exception("this item already exists");
            }
            item.setCategory(itemCategory);
            item.setUnit(unit);
            item = itemRepository.save(item);
            // validating and saving entities which contains transient entities
            // validating and saving stock
            stock = itemDto.getStock();
            Long stockID = stock.getStockID();
            if (stockID != null) {
                throw new Exception("a newly added item cannot already have a  stockID. Send the item without a stock ID the  buisness logic will assign a stockID and other attributes of stock");
            }
            if (stock.getItem() != null) {
                throw new Exception("dont send the item with stock, the buisness logic will assign the item to stock");
            }
            stock.setItem(item);
            if (stock.getUnit() != null) {
                throw new Exception("dont send the unit with stock. The business logic will assign the unit from item");
            }
            stock.setUnit(item.getUnit());
            stock = stockRepository.save(stock);// no need to validate other attributes of stock because they are
                                                // nullable

            // validating and saving Supplier_Item
            Long supplierID = supplier.getSupplierID();
            boolean supplierIdIsNull = supplierID == null;
            boolean supplierExists;
            if (supplierIdIsNull) {
                boolean nameExists = supplierRepository.existsByName(itemName);
                if (nameExists) {
                    throw new Exception("a supplier is already registered with this name. Check from supplier service if the existing supplier is the right one and send supplier again with that id. Else register this supplier under a different name");
                }
                if (supplier.getEmail() == null || supplier.getAddress() == null || supplier.getContactNo() == null) {
                    throw new Exception( "lacking essential supplier details.");
                }

                supplier = supplierRepository.save(supplier);

            } else {
                supplierExists = supplierRepository.existsById(supplierID);
                if (supplierExists == false) {
                    throw new Exception("A supplier with the given id does not exist");
                }
            }

            Supplier_Item_ID supplier_Item_ID = new Supplier_Item_ID(item.getItemID(), supplier.getSupplierID());
            Supplier_Item supplier_Item = new Supplier_Item(supplier_Item_ID, item, supplier);
            supplier_Item_Repository.save(supplier_Item);// this line error

            transactionManager.commit(status);
            return "item saved succesfully";

        } catch (Exception ex) {
            transactionManager.rollback(status);
            throw ex;
        }
        finally{
            if(!status.isCompleted()){
                transactionManager.rollback(status);
            }
        }
    }

}