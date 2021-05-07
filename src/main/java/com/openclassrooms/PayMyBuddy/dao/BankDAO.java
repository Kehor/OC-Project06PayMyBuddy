package com.openclassrooms.PayMyBuddy.dao;

import com.openclassrooms.PayMyBuddy.config.DBConstants;
import com.openclassrooms.PayMyBuddy.config.DataBaseConfig;
import com.openclassrooms.PayMyBuddy.entity.Bank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

public class BankDAO {

    private static final Logger logger = LogManager.getLogger("TicketDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public boolean addBankTransaction(Bank bank) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.SAVE_BANK_TRANSACTIONS);
            //ID, USER_ID, DESCRIPTION, AMOUNT, CREATED_AT
            ps.setInt(1, bank.getUserId());
            ps.setString(2, bank.getDescription());
            ps.setFloat(3, bank.getAmount());
            ps.execute();
        } catch (Exception ex) {
            logger.error("Error insert Bank", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);
            return false;
        }
    }

    public Bank getBankTransaction(int userId) {
        Connection con = null;
        Bank bank = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_BANK_TRANSACTIONS);
            ps.setString(1, String.valueOf(userId));
            //ID, USER_ID, DESCRIPTION, AMOUNT, CREATED_AT
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bank = new Bank();
                bank.setId(rs.getInt(1));
                bank.setUserId(rs.getInt(2));
                bank.setDescription(rs.getString(3));
                bank.setAmount(rs.getFloat(4));
                bank.setCreatedAt(rs.getTimestamp(5));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        } catch (Exception ex) {
            logger.error("Error fetching Bank", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            return bank;
        }
    }
}
