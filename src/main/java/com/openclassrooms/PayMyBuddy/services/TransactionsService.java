package com.openclassrooms.PayMyBuddy.services;

import com.openclassrooms.PayMyBuddy.dto.TransactionsDTO;
import com.openclassrooms.PayMyBuddy.entity.User;
import com.openclassrooms.PayMyBuddy.repository.TransactionsRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import com.openclassrooms.PayMyBuddy.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public List<TransactionsDTO> getTransactionList (List<Transactions> transactions){
        List<TransactionsDTO> transactionsDTOList = new ArrayList<>();
        for (Transactions transaction : transactions) {
            String senderName = userService.getUserNameOrEmail(transaction.getSenderId());
            String receiverName = userService.getUserNameOrEmail(transaction.getReceiverId());
            TransactionsDTO transactionsDTO = new TransactionsDTO(transaction.getId(),senderName,receiverName,transaction.getDescription(),transaction.getAmount());
            transactionsDTOList.add(transactionsDTO);
        }
        return transactionsDTOList;
    }

    public boolean sendTransaction(Transactions transactions){
            Optional<User> senderOptional = userRepository.findById(transactions.getSenderId());
            Optional<User> receiverOptional = userRepository.findById(transactions.getReceiverId());
            if(senderOptional.isPresent() && receiverOptional.isPresent()){
                User sender = senderOptional.get();
                User receiver = receiverOptional.get();
                if(sender.getBalance() > transactions.getAmount()){
                    sender.setBalance(sender.getBalance()-transactions.getAmount());
                    transactions.setAmount(transactions.getAmount()-transactions.getAmount()*(float)0.05);
                    receiver.setBalance((receiver.getBalance()+(transactions.getAmount())));
                    transactionsRepository.save(transactions);
                }else{
                    return true;
                }
            }else {
                return true;
            }
        return false;
    }

}
