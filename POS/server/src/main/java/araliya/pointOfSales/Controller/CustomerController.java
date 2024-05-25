package araliya.pointOfSales.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import araliya.pointOfSales.entity.Customer;
import araliya.pointOfSales.service.CustomerService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/loadCustomers")
    public ResponseEntity<List<Customer>> loadCustomers() {
       try{
            List<Customer> customers=customerService.loadCustomers();
                return ResponseEntity.ok().body(customers);

       }catch(Exception e){
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    
    
}
