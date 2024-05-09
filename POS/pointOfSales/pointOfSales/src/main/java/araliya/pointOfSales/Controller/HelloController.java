package araliya.pointOfSales.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin(origins = "*")
public class HelloController {

    @GetMapping("/hello")
    public String getMethodName() {
        return  "hollo";
    }
    
    
}
