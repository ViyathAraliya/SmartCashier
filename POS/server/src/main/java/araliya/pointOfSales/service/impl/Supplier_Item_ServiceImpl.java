package araliya.pointOfSales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.entity.Supplier_Item;
import araliya.pointOfSales.repository.ItemRepository;
import araliya.pointOfSales.repository.Supplier_Item_Repository;
import araliya.pointOfSales.service.Supplier_Item_Service;

@Service
public class Supplier_Item_ServiceImpl implements Supplier_Item_Service {

    @Autowired
    private Supplier_Item_Repository supplier_Item_Repository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Supplier_Item> loadSupplier_Items(Long itemID) throws Exception {

        Item item = itemRepository.findById(itemID).orElse(null);
        if (item == null) {
            throw new Exception("couldnt retrieve item from repository.");
        }

        return supplier_Item_Repository.findAllByItem(item);
    }

}