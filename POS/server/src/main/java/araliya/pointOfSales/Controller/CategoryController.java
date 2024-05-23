package araliya.pointOfSales.Controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import araliya.pointOfSales.dtos.CategoryDto;
import araliya.pointOfSales.embeddedIDs.Supplier_Item_ID;
import araliya.pointOfSales.entity.ItemCategory;
import araliya.pointOfSales.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/saveCategory") // save new item
    public ResponseEntity<String> postMethodName(@RequestBody CategoryDto categoryDto) {
        try {System.out.println(777);
            ItemCategory itemCategory=new ItemCategory();
            itemCategory.setCategoryID(categoryDto.getCategoryID());
            itemCategory.setName(categoryDto.getName());

            String msg = categoryService.saveCategory(itemCategory);
            return ResponseEntity.ok(msg);
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed
            // to save item");

        } catch (Exception e) {
            System.out.println(999);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stackTrace);
        }

    }

    @GetMapping("/loadCategories")
    public ResponseEntity<List<ItemCategory>> getMethodName() {
        try {
            List<ItemCategory> categories = categoryService.loadCategories();
            return ResponseEntity.ok().body(categories);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //@DeleteMapping("deleteCategory")

    /*  @DeleteMapping("supplierDoesntProvideThisItem")
    public ResponseEntity<Void> deleteSupplier_Items(@RequestBody Supplier_Item_ID supplier_Item_ID) {
        try {*/

    

}