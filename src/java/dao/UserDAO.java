package dao;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static User getUser(String username)
    {
        User user = new User();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String q = "SELECT * FROM userpass WHERE username = ?";

        try {
            
            ps = connection.prepareStatement(q);
            ps.setString(1, username);
            
            rs = ps.executeQuery();

            if (rs != null && rs.next()) {
                user.setUsername(username);
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmailAddress(rs.getString("emailAddress"));
                user.setProviderName(rs.getString("providerName"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
           e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }

        return user;
    }
    
    public static List<User> getUsersByProvider(String username)
    {
        List<User> users = new ArrayList<User>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String q = "SELECT * FROM userpass WHERE providerName = ?";

        try {
            
            ps = connection.prepareStatement(q);
            ps.setString(1, username);
            
            rs = ps.executeQuery();

            if (rs != null && rs.next()) {
                User user = new User();
                
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmailAddress(rs.getString("emailAddress"));
                user.setProviderName(rs.getString("providerName"));
                
                users.add(user);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
           e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }
        
        return users;
    }
}
