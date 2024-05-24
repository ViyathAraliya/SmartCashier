package araliya.pointOfSales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.Stock;

@Service
public interface StockService {

    List<Stock> loadStocks() throws Exception;
    Stock findStockByItemID() throws Exception;
    Stock updateStock(Stock stock)throws Exception;
    
     
}