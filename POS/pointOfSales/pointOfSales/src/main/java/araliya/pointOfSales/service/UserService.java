package araliya.pointOfSales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.User;

@Service
public interface UserService {

    List<User> getAllUsers();
    User getUserByID(Long id);
    User createUser(User user);
    User updateUser(Long id, User user) throws Exception;
    void deleteUser(Long id);

    
}
