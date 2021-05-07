package com.openclassrooms.PayMyBuddy.services;

import com.openclassrooms.PayMyBuddy.dao.BankDAO;
import com.openclassrooms.PayMyBuddy.dao.TransactionsDAO;
import com.openclassrooms.PayMyBuddy.entity.Bank;
import com.openclassrooms.PayMyBuddy.entity.Transactions;
import org.springframework.stereotype.Service;

import java.util.Date;


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
}
