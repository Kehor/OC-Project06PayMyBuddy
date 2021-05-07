package com.openclassrooms.PayMyBuddy.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseConfig {

    private static final Logger logger = LogManager.getLogger("DataBaseConfig");

    /**
     *
     * @return
     *      DB connection
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        Properties properties = new Properties();
            FileInputStream in = new FileInputStream("src/main/resources/database.properties");
            properties.load(in);
            in.close();
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            logger.info("Create DB connection");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,username,password);
    }

    /**
     *
     * @param con
     *      DB connection
     */
    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
                logger.info("Closing DB connection");
            } catch (SQLException e) {
                logger.error("Error while closing connection",e);
            }
        }
    }

    /**
     *
     * @param ps
     *      DB Prepared Statement
     */
    public void closePreparedStatement(PreparedStatement ps) {
        if(ps!=null){
            try {
                ps.close();
                logger.info("Closing Prepared Statement");
            } catch (SQLException e) {
                logger.error("Error while closing prepared statement",e);
            }
        }
    }

    /**
     *
     * @param rs
     *      DB Result Set
     */
    public void closeResultSet(ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
                logger.info("Closing Result Set");
            } catch (SQLException e) {
                logger.error("Error while closing result set",e);
            }
        }
    }
}
