package araliya.pointOfSales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import araliya.pointOfSales.dtos.ItemDto;
import araliya.pointOfSales.dtos.UpdateItemDto;
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
            Long unitPrice = itemDto.getUnitPrice();
            ItemCategory itemCategory = itemDto.getCategory();
            Stock stock = itemDto.getStock();
            Supplier supplier = itemDto.getSupplier();
            Long qty = itemDto.getQty();

            if (itemRepository.existsByName(itemName)) {
                throw new Exception("an item with this name alrwady exists");
            }

            if (itemName == null) {
                throw new Exception("itemName is null in the DTO. Please check the DTO.");
            }
            if (unit == null) {
                throw new Exception("unit is null in the DTO. Please check the DTO.");
            }
            if (unitPrice == null) {
                throw new Exception("unit price is null");
            }
            if (itemCategory == null) {
                throw new Exception("itemCategory is null in the DTO. Please check the DTO.");
            }
            if (stock == null) {
                throw new Exception("stock is null in the DTO. Please check the DTO.");
            }
            if (supplier == null) {
                throw new Exception("supplier is null in the DTO. Please check the DTO.");
            }
            if (qty == null) {
                throw new Exception("qty is null in the DTO. Please check the DTO.");
            }
            Long categoryID = itemCategory.getCategoryID();
            String categoryName = itemCategory.getName();

            Boolean categoryIdIsNull = categoryID == null;
            Boolean categoryNameIsNull = categoryName == null;
            System.out.println("at id" + categoryID);

            // validating and saving category
            boolean itemExistsByID = false;
            if (categoryIdIsNull == false) {
                itemExistsByID = itemCatagoryRepository.existsById(categoryID);
            }
            boolean itemExistsByName = false;
            if (categoryNameIsNull == false) {
                itemExistsByName = itemCatagoryRepository.existsByName(categoryName);
            }

            ItemCategory itemCategoryFoundByDescription = null;
            if (categoryNameIsNull == false) {
                itemCategoryFoundByDescription = itemCatagoryRepository.findByName(categoryName);
            }
            ItemCategory itemCategoryFoundByID = null;
            if (categoryIdIsNull == false) {
                itemCategoryFoundByID = itemCatagoryRepository.findById(categoryID).orElse(null);
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
                    categoryID = itemCategoryFoundByID.getCategoryID();
                }
            } else if (categoryNameIsNull) {
                if (itemExistsByID) {
                    itemCategory = itemCategoryFoundByID;
                    if (itemCategoryFoundByIDisNull) {
                        throw new Exception("error in retriving item. t=retrivesnull by repo");
                    }
                    categoryName = itemCategory.getName();
                } else {
                    throw new Exception("no such category by provided id");
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
                if (itemCategory.getCategoryID()
                        .equals(itemCategoryFoundByDescription.getCategoryID()) == false) {
                    throw new Exception("category ID doesnt match the category name");
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
            item.setUnitPrice(unitPrice);
            item = itemRepository.save(item);
            // validating and saving entities which contains transient entities
            // validating and saving stock
            stock = itemDto.getStock();
            Long stockID = stock.getStockID();
            Long qtyOnHand = stock.getQty_on_hand();

            if (stockID != null) {
                throw new Exception(
                        "a newly added item cannot already have a  stockID. Send the item without a stock ID the  buisness logic will assign a stockID and other attributes of stock");
            }
            if (stock.getItem() != null) {
                throw new Exception("dont send the item with stock, the buisness logic will assign the item to stock");
            }
            if (qtyOnHand != null) {
                throw new Exception(
                        "dont send the qty with stock when adding a new item. The buisness logic will determine qtyOnHand by itemDto");
            }
            stock.setItem(item);
            if (stock.getUnit() != null) {
                throw new Exception("dont send the unit with stock. The business logic will assign the unit from item");
            }
            stock.setUnit(item.getUnit());
            stock.setQty_on_hand(qty);
            stock = stockRepository.save(stock);// no need to validate other attributes of stock because they are
                                                // nullable

            // validating and saving Supplier_Item
            Long supplierID = supplier.getSupplierID();
            boolean supplierIdIsNull = supplierID == null;
            boolean supplierExists;
            if (supplierIdIsNull) {
                boolean nameExists = supplierRepository.existsByName(itemName);
                if (nameExists) {
                    throw new Exception(
                            "a supplier is already registered with this name. Check from supplier service if the existing supplier is the right one and send supplier again with that id. Else register this supplier under a different name");
                }
                if (supplier.getEmail() == null || supplier.getAddress() == null || supplier.getContactNo() == null) {
                    throw new Exception("lacking essential supplier details.");
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
        } finally {
            if (!status.isCompleted()) {
                transactionManager.rollback(status);
            }
        }
    }

    public List<Item> loadItems() throws Exception {
        return itemRepository.findAll();
    }

    public String updateItem(UpdateItemDto updateItemDto) throws Exception {

        if (itemRepository.updateItem(updateItemDto) == 0) {
            throw new Exception("couldnt save");
        }
        return "item update succesfully";

    }

}