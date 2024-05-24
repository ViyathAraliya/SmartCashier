package araliya.pointOfSales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.Stock;
import araliya.pointOfSales.repository.StockRepository;
import araliya.pointOfSales.service.StockService;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> loadStocks() throws Exception {
        return stockRepository.findAll();
    }

    @Override
    public Stock findStockByItemID() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findStockByItemID'");
    }

    @Override
    public Stock updateStock(Stock stock) throws Exception {
        if (stock.getStockID() != null) {
            return stockRepository.save(stock);
        } else {
            throw new Exception("stockID is null");
        }

    }
}