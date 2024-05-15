package araliya.pointOfSales.service;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.ItemCategory;

@Service
public interface CategoryService {
    String saveCategory(ItemCategory itemCategory)throws Exception;
    
}
