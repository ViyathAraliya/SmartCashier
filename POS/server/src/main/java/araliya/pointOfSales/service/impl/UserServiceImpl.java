package araliya.pointOfSales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.User;
import araliya.pointOfSales.repository.UserRepository;
import araliya.pointOfSales.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    @Override
    public User getUserByID(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) throws Exception{
        User existUser=userRepository.findById(id).orElse(null);

        if(existUser!=null){
            existUser.setName(user.getName());
            existUser.setEmail(user.getEmail());
            existUser.setPassword(user.getPassword());
             return userRepository.save(existUser);
        }
        else{throw new Exception("no user found by provided id");}
    }

    @Override
    public void deleteUser(Long id) {
        
    }

    @Override
    public boolean userEmpty() throws Exception{
        return userRepository.count()==0;
    }
    
}
