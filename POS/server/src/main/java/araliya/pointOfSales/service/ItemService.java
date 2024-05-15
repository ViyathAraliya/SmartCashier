package araliya.pointOfSales.service;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.dtos.ItemDto;


@Service
public interface ItemService {
    String saveItem(ItemDto itemDto) throws Exception;
}
