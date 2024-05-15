package araliya.pointOfSales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import araliya.pointOfSales.entity.User;
import araliya.pointOfSales.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user=userRepository.findUserByName(userName).orElse(null);

        if(user==null){
            throw new UsernameNotFoundException("User is not found with username: "+userName);
        }else{
            return org.springframework.security.core.userdetails.User.builder()
            .username(user.getName())
            .password(user.getPassword())
            .build();
        }
        
    }
    
}
