package com.openclassrooms.PayMyBuddy.services;

import com.openclassrooms.PayMyBuddy.dao.BankDAO;
import com.openclassrooms.PayMyBuddy.dao.TransactionsDAO;
import com.openclassrooms.PayMyBuddy.entity.Bank;
import com.openclassrooms.PayMyBuddy.entity.Transactions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TransactionsService {
    private TransactionsDAO transactionsDAO = new TransactionsDAO();
    private BankDAO bankDAO = new BankDAO();

    public void makeTransaction(int userId, int sendTo,String description, float amount){
        Transactions transactions = new Transactions(0,userId,null,sendTo,null,description,amount,new Date());
        transactionsDAO.addTransaction(transactions);
        /*
        if(sendTo == 0){
            Bank bank = new Bank(0,userId,description,amount, new Date());
            bankDAO.addBankTransaction(bank);
        }
        */
    }

    public List<Transactions> getSendTransaction(List<Transactions> transactions){
        List<Transactions> transactionsList = new ArrayList<>();

        return transactionsList;
    }

    public List<Transactions> getReceiveTransaction(List<Transactions> transactions){
        List<Transactions> transactionsList = new ArrayList<>();

        return transactionsList;
    }

    public Date lastTransactionWithFriend (int userId, int friendId){
        List<Transactions> transactionsList = new ArrayList<>();
        Date lastTransaction = null;
        transactionsList = transactionsDAO.getTransaction(userId,friendId);
        for (Transactions transactions: transactionsList){
           if (lastTransaction == null || lastTransaction.getTime() < transactions.getCreatedAt().getTime()) lastTransaction = transactions.getCreatedAt();
        }
        return lastTransaction;
    }
}
