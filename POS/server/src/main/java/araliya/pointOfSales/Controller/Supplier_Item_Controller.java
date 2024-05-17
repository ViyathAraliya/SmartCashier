package araliya.pointOfSales.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import araliya.pointOfSales.entity.Supplier;

import araliya.pointOfSales.service.SupplierService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class Supplier_Item_Controller {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("loadSupplier_Item/{id}")
    public ResponseEntity<List<Supplier>> loadSupplier_ItemsByItemID(@PathVariable Long id) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByItemID(id);
            return ResponseEntity.ok().body(suppliers);
        } catch (Exception e) {
          System.out.println(e.getMessage());
          return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
