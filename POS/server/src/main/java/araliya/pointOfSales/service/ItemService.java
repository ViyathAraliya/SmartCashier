package araliya.pointOfSales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.dtos.ItemDto;
import araliya.pointOfSales.entity.Item;


@Service
public interface ItemService {
    String saveItem(ItemDto itemDto) throws Exception;
    List<Item> loadItems() throws Exception;
    String updateItem(Item item) throws Exception;
}
