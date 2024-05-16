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


import araliya.pointOfSales.entity.ItemCategory;
import araliya.pointOfSales.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    
@Autowired
private CategoryService categoryService;

    @PostMapping("/saveCategory")// save new item
    public ResponseEntity<String> postMethodName(@RequestBody ItemCategory itemCategory) {
       try{
            String msg=categoryService.saveCategory(itemCategory);
           return  ResponseEntity.ok(msg);
           // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to save item");
    
     }catch(Exception e){
         StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String stackTrace = sw.toString();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stackTrace);
        }
        
    }
}
