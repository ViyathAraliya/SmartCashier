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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@CrossOrigin(origins = "*")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/findSupplierByName/{name}")
    public ResponseEntity<Supplier> getMethodName(@PathVariable String name) {
        try{
            Supplier supplier=supplierService.findByName(name);
            if(supplier==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok().body(supplier);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addNewSupplier")
    public ResponseEntity<Supplier> postMethodName(@RequestBody Supplier supplier) {
        try{
        Supplier savedSupplier=supplierService.saveSupplier(supplier);
        if(savedSupplier==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().body(savedSupplier);
    }catch(Exception e){
        System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
}

@GetMapping("/loadSuppliers")
public ResponseEntity<List<Supplier>> getMethodName() throws Exception{
    try{
        List<Supplier> suppliers=supplierService.loadSuppliers();
        return ResponseEntity.ok().body(suppliers);
    }catch(Exception e){
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

}