package araliya.pointOfSales.Controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import araliya.pointOfSales.dtos.TransactionDto;
import araliya.pointOfSales.service.TransactionService;

@RestController
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

     @PostMapping("/saveTransaction")// save new item
    public ResponseEntity<String> postMethodName(@RequestBody TransactionDto transactionDto) {
       try{
            String msg=transactionService.saveTransaction(transactionDto);
           return  ResponseEntity.ok(msg);
         
    
     }catch(Exception e){
         StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String stackTrace = sw.toString();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stackTrace);
        }
        
    }
    
}
