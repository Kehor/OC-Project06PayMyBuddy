package com.openclassrooms.PayMyBuddy.dao;

import com.openclassrooms.PayMyBuddy.config.DBConstants;
import com.openclassrooms.PayMyBuddy.config.DataBaseConfig;
import com.openclassrooms.PayMyBuddy.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class UserDAO {

    private static final Logger logger = LogManager.getLogger("TicketDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public boolean saveUser(User user) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.SAVE_USER);
            //ID, NAME, EMAIL, PASS, CREATED_AT, IBAN, BALANCE
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getIban());
            ps.setFloat(5, user.getBalance());
            ps.execute();
        } catch (Exception ex) {
            logger.error("Error insert User", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);
            return false;
        }
    }

    public User getUserById(int id){
        Connection con = null;
        User user = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_USER_INFO);
            ps.setString(1, String.valueOf(id));
            //ID, NAME, EMAIL, PASS, CREATED_AT, IBAN, BALANCE
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setCreatedAt(rs.getTimestamp(5));
                user.setIban(rs.getString(6));
                user.setBalance(rs.getFloat(7));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error fetching User",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            return user;
        }
    }

    public User getUserByEmail(String email){
        Connection con = null;
        User user = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_USER_BY_EMAIL);
            ps.setString(1, String.valueOf(email));
            //ID, NAME, EMAIL, PASS, CREATED_AT, IBAN, BALANCE
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setCreatedAt(rs.getTimestamp(5));
                user.setIban(rs.getString(6));
                user.setBalance(rs.getFloat(7));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error fetching User",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            return user;
        }
    }
}
