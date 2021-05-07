package com.openclassrooms.PayMyBuddy.services;

import com.openclassrooms.PayMyBuddy.dao.UserDAO;
import com.openclassrooms.PayMyBuddy.entity.User;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private User user = new User();
    private UserDAO userDao = new UserDAO();

    public String getUserNameOrEmail(int userid){
        String name = "undefined";
        user = userDao.getUserById(userid);
        if(user.getName() != null){
            name = user.getName();
        }else {
            name = user.getEmail();
        }
        return name;
    }
}
