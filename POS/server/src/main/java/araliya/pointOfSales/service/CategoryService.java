package araliya.pointOfSales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.ItemCategory;

@Service
public interface CategoryService {
    String saveCategory(ItemCategory itemCategory)throws Exception;
    List<ItemCategory> loadCategories() throws Exception;
    
}
