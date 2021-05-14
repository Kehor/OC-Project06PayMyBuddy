package com.openclassrooms.PayMyBuddy.services;

import com.openclassrooms.PayMyBuddy.dto.FriendsDTO;
import com.openclassrooms.PayMyBuddy.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendsService {
    private UserService userService = new UserService();
    private TransactionsService transactionsService =new TransactionsService();

    public List<FriendsDTO> getFriendsList(int userId, List<Integer> friendsId){
        List<FriendsDTO> friendsDTO = new ArrayList<>();
        for (int id: friendsId) {
            friendsDTO.add(new FriendsDTO( id, userService.getUserNameOrEmail(id),transactionsService.lastTransactionWithFriend(userId, id)));
        }
        return friendsDTO;
    }
}
