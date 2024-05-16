package araliya.pointOfSales.Controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import araliya.pointOfSales.dtos.ItemDto;
import araliya.pointOfSales.entity.Item;
import araliya.pointOfSales.service.ItemService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/saveItem") // save new item
    public ResponseEntity<String> postMethodName(@RequestBody ItemDto itemDto) {
        try {
            String msg = itemService.saveItem(itemDto);
            return ResponseEntity.ok(msg);
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed
            // to save item");

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stackTrace);
        }

    }

    @GetMapping("/loadItems")
    public ResponseEntity<List<Item>> getItems() {
        try {
            List<Item> items = itemService.loadItems();
            return ResponseEntity.ok().body(items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateItem/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable String id, @RequestBody ItemDto ItemDto) {
       try{
            String msg=itemService.updateItem(ItemDto);
            return ResponseEntity.ok().body(msg);
       }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

}
