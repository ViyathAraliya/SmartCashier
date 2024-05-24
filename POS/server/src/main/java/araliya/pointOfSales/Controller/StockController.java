package araliya.pointOfSales.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import araliya.pointOfSales.entity.Stock;
import araliya.pointOfSales.service.StockService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin("*")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/loadStocks")
    public ResponseEntity<List<Stock>> loadStocks() throws Exception {
        try {
            List<Stock> stocks = stockService.loadStocks();
            return ResponseEntity.ok().body(stocks);

        } catch (Exception e) {

            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateStock")
    public ResponseEntity<Stock> updateStock(@RequestBody Stock stock) {
        try {
            Stock updatedStock = stockService.updateStock(stock);
            if (updatedStock == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok().body(updatedStock);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
