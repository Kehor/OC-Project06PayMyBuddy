package com.openclassrooms.PayMyBuddy.dao;

import com.openclassrooms.PayMyBuddy.config.DBConstants;
import com.openclassrooms.PayMyBuddy.config.DataBaseConfig;
import com.openclassrooms.PayMyBuddy.entity.Friends;
import com.openclassrooms.PayMyBuddy.services.FriendsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FriendsDAO {

    private static final Logger logger = LogManager.getLogger("TicketDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();
    private FriendsService friendsService = new FriendsService();

    public boolean addFriends(int userId, int friendId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.ADD_FRIENDS);
            //USER_ID, FRIEND_ID
            ps.setInt(1, userId);
            ps.setInt(2, friendId);
            ps.execute();
        } catch (Exception ex) {
            logger.error("Error insert Friends", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);
            return false;
        }
    }

    public Friends getFriends(int userId){
        Connection con = null;
        Friends friends = null;
        List<Integer> friendsId = new ArrayList<>();
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_FRIENDS_LIST);
            ps.setString(1, String.valueOf(userId));
            //USER_ID, FRIEND_ID
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                friendsId.add(rs.getInt(1));
            }

            friends = new Friends(userId,friendsId,friendsService.getFriendsName(friendsId));
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error fetching Friends",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            return friends;
        }
    }
}
