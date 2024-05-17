package araliya.pointOfSales.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import araliya.pointOfSales.entity.Supplier_Item;
import araliya.pointOfSales.service.Supplier_Item_Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class Supplier_Item_Controller {
    @Autowired
    private Supplier_Item_Service supplier_Item_Service;

    @GetMapping("loadSupplier_Item/{id}")
    public ResponseEntity<List<Supplier_Item>> loadSupplier_ItemsByItemID(@PathVariable Long id) {
        try {
            List<Supplier_Item> supplier_Items = supplier_Item_Service.loadSupplier_Items(id);
            return ResponseEntity.ok().body(supplier_Items);
        } catch (Exception e) {
          System.out.println(e.getMessage());
          return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
