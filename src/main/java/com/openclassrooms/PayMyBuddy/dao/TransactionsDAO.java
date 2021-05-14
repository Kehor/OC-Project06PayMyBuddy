package com.openclassrooms.PayMyBuddy.dao;

import com.openclassrooms.PayMyBuddy.config.DBConstants;
import com.openclassrooms.PayMyBuddy.config.DataBaseConfig;
import com.openclassrooms.PayMyBuddy.entity.Transactions;
import com.openclassrooms.PayMyBuddy.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionsDAO {

    private static final Logger logger = LogManager.getLogger("TicketDAO");
    private UserService userService = new UserService();

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public boolean addTransaction(Transactions transactions) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.SAVE_TRANSACTIONS);
            //ID, SENDER_ID, RECEIVER_ID, DESCRIPTION, AMOUNT, CREATED_AT
            ps.setInt(1, transactions.getSenderId());
            ps.setInt(2, transactions.getReceiverId());
            ps.setString(3, transactions.getDescription());
            ps.setFloat(4, transactions.getAmount());
            ps.execute();
        } catch (Exception ex) {
            logger.error("Error insert transaction", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);
            return false;
        }
    }

    /**
     *
     *  Get All transaction of User
     *
     */
    public List<Transactions> getTransaction(int userId) {

        return getTransaction(userId, userId);
    }

    /**
     *
     *  Get Only transaction between two Users
     *
     */
    public List<Transactions> getTransaction(int userId, int friendId) {
        Connection con = null;
        Transactions transaction = null;
        List<Transactions> transactions = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            if(userId == friendId){
                ps = con.prepareStatement(DBConstants.GET_TRANSACTIONS);
            }else {
                ps = con.prepareStatement(DBConstants.GET_TRANSACTIONSWithUser);
            }
            ps.setString(1, String.valueOf(userId));
            ps.setString(2, String.valueOf(friendId));
            //ID, SENDER_ID, RECEIVER_ID, DESCRIPTION, AMOUNT, CREATED_AT
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                //if (rs.next()) {
                    transaction = new Transactions();
                    transaction.setId(rs.getInt(1));
                    transaction.setSenderId(rs.getInt(2));
                    transaction.setReceiverId(rs.getInt(3));
                    transaction.setDescription(rs.getString(4));
                    transaction.setAmount(rs.getFloat(5));
                    transaction.setCreatedAt(rs.getTimestamp(6));
                    transaction.setSenderName(userService.getUserNameOrEmail(rs.getInt(2)));
                    transaction.setReceiverName(userService.getUserNameOrEmail(rs.getInt(3)));
                    transactions.add(transaction);

                //}
            }

            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        } catch (Exception ex) {
            logger.error("Error fetching transaction", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            return transactions;
        }
    }
}
