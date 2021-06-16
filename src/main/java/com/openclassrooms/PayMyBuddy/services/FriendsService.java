package com.openclassrooms.PayMyBuddy.services;

import com.openclassrooms.PayMyBuddy.dto.FriendsDTO;
import com.openclassrooms.PayMyBuddy.entity.Friends;
import com.openclassrooms.PayMyBuddy.entity.Transactions;
import com.openclassrooms.PayMyBuddy.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsService {
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionsService transactionsService;

    public List<FriendsDTO> getFriendsList(Long userid,List<Friends> friends){
        List<FriendsDTO> friendsDTO = new ArrayList<>();
        Long id = Long.valueOf(0);
        for (Friends friend: friends) {
            if(userid == friend.getUserId()){
                id = friend.getFriendId();
            }else {
                id = friend.getUserId();
            }
            String nameFriend = userService.getUserNameOrEmail(id);
            Transactions transaction = transactionsRepository.findFirstBySenderIdOrReceiverIdOrderByCreatedAtDesc(friend.getUserId(), friend.getUserId());
            if(transaction != null){
                friendsDTO.add(new FriendsDTO(id, nameFriend, transaction.getCreatedAt()));
            }else {
                friendsDTO.add(new FriendsDTO(id, nameFriend, null));
            }
        }
        return friendsDTO;
    }
}
