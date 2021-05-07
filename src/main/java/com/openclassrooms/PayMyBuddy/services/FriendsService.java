package com.openclassrooms.PayMyBuddy.services;

import com.openclassrooms.PayMyBuddy.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsService {
    private UserService userService = new UserService();

    public List<String> getFriendsName(List<Integer> friendsId){
        List<String> name = new ArrayList<>();
        for (int id: friendsId) {
            name.add(userService.getUserNameOrEmail(id));
        }
        return name;
    }
}
