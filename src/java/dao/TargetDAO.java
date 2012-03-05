package dao;

import entities.Card;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TargetDAO 
{
    /*public static void updateTarget(Card target)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String q = "UPDATE targets SET `target` = ?, `description` = ?, "
                +  "`range` = ?, `category` = ? WHERE `targetID` = ?";

        try {
            
            ps = connection.prepareStatement(q);
            ps.setString(1, target.getTarget());
            ps.setString(2, target.getDescription());
            ps.setString(3, target.getRange());
            ps.setString(4, target.getCategory());
            ps.setLong(5, target.getTargetID());
            
            int rows = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
           e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }
    }    
        
    public static Card insertTarget(Card target)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String q = "INSERT INTO targets (`username`,`target`,`description`,"
                +  "`range`, `category`) VALUES (?,?,?,?,?)";

        try {
            
            ps = connection.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, target.getUsername());
            ps.setString(2, target.getTarget());
            ps.setString(3, target.getDescription());
            ps.setString(4, target.getRange());
            ps.setString(5, target.getCategory());
            
            int rows = ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            
            if (rs.next() && rs != null)
            {
                target.setTargetID(rs.getLong("targetID"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
           e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }
        
        return target;
    }    

    public static ArrayList<Card> getTargetsByUsername(String username)
    {
        ArrayList<Card> targets = new ArrayList<Card>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String q = "SELECT * FROM targets WHERE username = ?";

        try {
            
            ps = connection.prepareStatement(q);
            ps.setString(1, username);
            
            rs = ps.executeQuery();

            while(rs.next())
            {
                Card t = new Card();
                t.setTargetID(rs.getLong("targetID"));
                t.setUsername(rs.getString("username"));
                t.setTarget(rs.getString("target"));
                t.setDescription(rs.getString("description"));
                t.setRange(rs.getString("range"));
                t.setCategory(rs.getString("category"));
                
                targets.add(t);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
           e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }
        
        return targets;
    }
    
    public static Card getTargetByID(long targetID)
    {
        Card target = new Card();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String q = "SELECT * FROM targets WHERE targetID = ?";

        try {
            
            ps = connection.prepareStatement(q);
            ps.setLong(1, targetID);
            
            rs = ps.executeQuery();

            if (rs.next())
            {
                target.setTargetID(rs.getLong("targetID"));
                target.setUsername(rs.getString("username"));
                target.setTarget(rs.getString("target"));
                target.setDescription(rs.getString("description"));
                target.setRange(rs.getString("range"));
                target.setCategory(rs.getString("category"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
           e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }
        
        return target;
    }   */
}
