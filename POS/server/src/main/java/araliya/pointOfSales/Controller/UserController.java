package araliya.pointOfSales.Controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import araliya.pointOfSales.entity.User;
import araliya.pointOfSales.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.createUser(user);
            if (savedUser == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error in saving User");
            }
            return ResponseEntity.ok("user saved succesfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stackTrace);
        }

    }

    @GetMapping("/isUserEmpty")
    public ResponseEntity<Boolean> getMethodName() {
        try {
            Boolean b = userService.userEmpty();
            return ResponseEntity.ok().body(b);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}