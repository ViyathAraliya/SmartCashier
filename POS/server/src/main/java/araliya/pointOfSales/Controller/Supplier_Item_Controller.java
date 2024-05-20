package araliya.pointOfSales.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import araliya.pointOfSales.embeddedIDs.Supplier_Item_ID;
import araliya.pointOfSales.entity.Supplier;

import araliya.pointOfSales.service.SupplierService;
import araliya.pointOfSales.service.Supplier_Item_Service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class Supplier_Item_Controller {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private Supplier_Item_Service supplier_Item_Service;

    @GetMapping("loadSupplier_Item/{id}")
    public ResponseEntity<List<Supplier>> loadSupplier_ItemsByItemID(@PathVariable Long id) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByItemID(id);
            return ResponseEntity.ok().body(suppliers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("supplierDoesntProvideThisItem")
    public ResponseEntity<Void> deleteSupplier_Items(@RequestBody Supplier_Item_ID supplier_Item_ID) {
        try {
           
            supplier_Item_Service.deleteSupplier_Items(supplier_Item_ID);
            ;
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
