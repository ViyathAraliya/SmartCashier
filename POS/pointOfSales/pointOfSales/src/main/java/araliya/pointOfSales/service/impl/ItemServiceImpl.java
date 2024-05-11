package araliya.pointOfSales.service.impl;

import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //"If an item exists, then a stock exists; otherwise, there is no stock."
        String itemName=itemDto.getName();
        if(itemRepository.findByName(itemName)!=null){
        
            return "this item already exist";
        }

        Item item = new Item();
       

        Stock stock = itemDto.getStock();
        
       
      // item.setItemID(itemDto.getItemID());
       item.setName(itemDto.getName());
       item.setUnit(itemDto.getUnit());
       item.setCategory(itemDto.getCategory());
      
       item.setStock(stock);
       //stock.setItem(item);
       
       System.out.println(0);
       ItemCategory itemCategory = itemDto.getCategory();
       String categoryName=itemCategory.getDescription();
       
       Long categoryID = itemCategory.getCatagoryID();
        if (categoryID == null) {//new catogry
            itemCategory = itemCatagoryRepository.save(itemCategory);
            categoryID = itemCategory.getCatagoryID();
            item.setCategory(itemCategory);
        }
        else{
            if(itemCatagoryRepository.existsById(categoryID)){
                
               if(categoryName.equals(itemCatagoryRepository.findById(categoryID).orElse(null).getDescription())==false){
                    return "categoryID does not match with the category name(description).send the category without id if you want to register a new category";
               }
            }
        }

        item=itemRepository.save(item);
        stock.setItem(item);
        Stock savedStock = stockRepository.save(stock);
        System.out.println(1);
    
        if (savedStock == null) {// adding new stock entity to stocks table
            return "error in saving stock ";
        }
        
        item.setStock(savedStock);
        System.out.println(3);
        
        System.out.println(4);
        if (itemCatagoryRepository.existsById(categoryID) == false) {//no cat by id
            return "a catogory with provided ID does not exist";
        }

        item = itemRepository.save(item);
      
        if (item == null) {
            return "error in saving item";
        }

        Supplier supplier = itemDto.getSupplier();
        Long supplierID = supplier.getSupplierID();

        if (supplierID == null) {//new supplier
            supplier = supplierRepository.save(supplier);
            supplierID=supplier.getSupplierID();
        }

        System.out.println(6);
        if (!supplierRepository.existsById(supplierID)) {
            return "a supplier with provided id does not exist";
        }
        System.out.println(7);
        Supplier_Item_ID supplier_Item_ID = new Supplier_Item_ID(item, supplier);
        System.out.println(8);
        Supplier_Item supplier_Item = new Supplier_Item(supplier_Item_ID);
        System.out.println(9);
        if (supplier_Item_Repository.existsById(supplier_Item_ID) == false) {// new item_supplier
            System.out.println(10);
            if (supplier_Item_Repository.save(supplier_Item) == null) {
                System.out.println(11);
                return "error in saving supplier_item";
            }
            System.out.println(12);
        }
        System.out.println(13);
        return "item saved succesfully";

    }
}