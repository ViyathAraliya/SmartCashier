package araliya.pointOfSales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.ItemCategory;
import araliya.pointOfSales.repository.ItemCatagoryRepository;
import araliya.pointOfSales.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ItemCatagoryRepository itemCatagoryRepository;

    @Override
    public String saveCategory(ItemCategory itemCategory) throws Exception {
        if(itemCatagoryRepository.existsByName(itemCategory.getName())){
            throw new Exception("a category with the same name already exists");
        }
        itemCatagoryRepository.save(itemCategory);
        return "Item Category saved succesfully";
    }

    @Override
    public List<ItemCategory> loadCategories() throws Exception{
            return itemCatagoryRepository.findAll();
    }

}
