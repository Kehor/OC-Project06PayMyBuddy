package com.openclassrooms.PayMyBuddy.services;

import com.openclassrooms.PayMyBuddy.entity.Role;
import com.openclassrooms.PayMyBuddy.entity.User;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private User user = new User();


    @Transactional(readOnly = true)
    public User findOneByEmail(String email) {
        User user = null;
        try {
            user = userRepository.findOneByEmail(email);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    public String getUserNameOrEmail(Long userId){
        String name = "";
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            this.user = userOptional.get();
            if(user.getName() != null){
                name = user.getName();
            }else if(user.getEmail() != null){
                name = user.getEmail();
            }
        }

        return name;
    }

    public User saveUser(String name, String email, String password, String iban){
        User user = new User();
        user = userRepository.findOneByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        if(user != null && (user.getName() != name || user.getPassword() != encodedPassword || user.getIban() != iban) ){
            if(!name.isEmpty())user.setName(name);
            if(!password.isEmpty())user.setPassword(encodedPassword);
            if(!iban.isEmpty())user.setIban(iban);
            userRepository.updateUser(user.getId(), user.getName(), user.getPassword(), user.getIban());
        }else if(user == null){
            Role role = new Role(2,"ROLE_USER");
            user = new User(null, name, email, encodedPassword, new Date(), iban, 0, role);
            userRepository.save(user);
        }
        return user;
    }
}
