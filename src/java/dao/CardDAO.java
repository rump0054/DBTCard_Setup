package dao;

import entities.Card;
import entities.Target;
import java.sql.*;
import java.util.ArrayList;
import utils.DateUtils;

public class CardDAO {
    public static Card getCard(String username, String datekey)
    {
        ArrayList<Target> targets = new ArrayList<Target>();
 
        long weekStart = DateUtils.getWeekStart(datekey);
        int dayKey = DateUtils.getDayKey(datekey);
        
        Card card = new Card();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String q = "SELECT * FROM card WHERE username=? AND weekStart=?";

        try {
            ps = connection.prepareStatement(q);
            ps.setString(1, username);
            ps.setDate(2, new java.sql.Date(weekStart));

            rs = ps.executeQuery();

            if (rs != null && rs.next()) {
               card.setCardID(rs.getLong("cardID"));
               card.setUsername(rs.getString("username"));
               card.setWeekStart(weekStart);
            }
            
            if(card != null)
            {
                q = "SELECT `card_targets`.*,`targets`.* FROM card_targets "
                        + "LEFT JOIN `targets` ON `card_targets`.`targetID` "
                        + "= `targets`.`targetID` WHERE `card_targets`.`cardID` = ?";
 
                ps = connection.prepareStatement(q);
                ps.setLong(1, card.getCardID());
                
                rs = ps.executeQuery();

                while(rs.next()) {
                    Target target = new Target();
                    target.setCardID(rs.getLong("cardID"));
                    target.setTargetID(rs.getLong("targetID"));
                    target.setTarget(rs.getString("target"));
                    target.setDescription(rs.getString("description"));
                    target.setCategory(rs.getString("category"));
                    target.setRangeMax(rs.getString("range"));
                    target.setDayKey(dayKey);
                    
                    targets.add(target);
                }
                
                for(Target t : targets)
                {
                    q = "SELECT * FROM card_targets_values "
                            + "WHERE cardID = " + t.getCardID() + " "
                            + "AND targetID = " + t.getTargetID() + " "
                            + "AND dayKey = " + t.getDayKey();
                    
                    ps = connection.prepareStatement(q);
                    rs = ps.executeQuery(q);
                    
                    if(rs.next())
                    {
                        t.setValue(rs.getString("value"));
                    }
                }
            }

            rs.close();
            ps.close();;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }
        
        card.setTargets(targets);
        card.setDaykey(dayKey);
                
        return card;
    }
    
    public static boolean needCard(String username, String datekey) {
        boolean needCard = true;
        long weekStart = DateUtils.getWeekStart(datekey);

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String q = "SELECT * FROM card WHERE username=? AND weekStart=?";

        try {

            ps = connection.prepareStatement(q);
            ps.setString(1, username);
            ps.setDate(2, new java.sql.Date(weekStart));

            rs = ps.executeQuery();

            if (rs != null && rs.next()) {
                needCard = false;
            }

            rs.close();
            ps.close();;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }

        return needCard;
    }

    public static Card setupCard(Card card) {
        ArrayList<Target> statusTargets = new ArrayList<Target>();
        ArrayList<Target> targets = new ArrayList<Target>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String q = "SELECT * FROM `targets` WHERE `username` = ? ORDER BY "
                + "`target`";

        try {
            ps = connection.prepareStatement(q);
            ps.setString(1, card.getUsername());

            rs = ps.executeQuery();

            while (rs.next()) {
                Target t = new Target();
                t.setTargetID(rs.getLong("targetID"));
                t.setCategory(rs.getString("category"));
                t.setTarget(rs.getString("target"));
                t.setDescription(rs.getString("description"));
                t.setRangeMax(rs.getString("range"));
                t.setActive(false);

                targets.add(t);
            }

            long weekStart = DateUtils.getLastSunday(card.getWeekStart());
            q = "SELECT `card_targets`.`targetID` FROM card "
                    + "LEFT JOIN `card_targets` ON `card`.`cardID`=`card_targets`.`cardID` "
                    + "WHERE `card`.`username` = ? AND `card`.`weekStart` = ?";

            ps = connection.prepareStatement(q);
            ps.setString(1, card.getUsername());
            ps.setDate(2, new java.sql.Date(weekStart));
            
            rs = ps.executeQuery();

            while (rs.next()) {
                long targetID = rs.getLong("targetID");
                for (Target t : targets) {
                    long current = t.getTargetID();
                    if (current == targetID) {
                        t.setActive(true);
                    }
                    
                    statusTargets.add(t);
                }
            }

            rs.close();
            ps.close();;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }

        card.setTargets(targets);

        return card;
    }

    public static Target insertTarget(Target target) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String q = "INSERT INTO targets (`username`,`target`,`description`,"
                + "`range`, `category`) VALUES (?,?,?,?,?)";

        try {

            ps = connection.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, target.getUsername());
            ps.setString(2, target.getTarget());
            ps.setString(3, target.getDescription());
            ps.setString(4, target.getRangeMax());
            ps.setString(5, target.getCategory());

            int rows = ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next() && rs != null) {
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

    public static void insertCard(Card card)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String q = "INSERT INTO card(`username`, `weekStart`) VALUES (?,?)";

        try {
            
            ps = connection.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, card.getUsername());
            ps.setDate(2, new java.sql.Date(card.getWeekStart()));
            
            int rows = ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                card.setCardID(rs.getLong(1));
            }
            
            for(Target t : card.getTargets())
            {
                q = "INSERT INTO `card_targets` VALUES (?,?)";
                ps = connection.prepareStatement(q);
                ps.setLong(1, card.getCardID());
                ps.setLong(2, t.getTargetID());
                
                rows = ps.executeUpdate();
            }

            rs.close();
            ps.close();;
        } catch (SQLException e) {
           e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }
    }  

    public static void deleteDayValues(long cardID, int daykey) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String q = "DELETE FROM card_targets_values WHERE cardID = ? AND daykey = ?";

        try {

            ps = connection.prepareStatement(q);
            ps.setLong(1, cardID);
            ps.setInt(2, daykey);

            int rows = ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }
    }
    
    public static void insertDayValues(Target target)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String q = "INSERT INTO card_targets_values (`cardID`,`targetID`,`dayKey`,`value`) "
                 + "VALUES (?,?,?,?)";

        try {
            
            ps = connection.prepareStatement(q);
            ps.setLong(1, target.getCardID());
            ps.setLong(2, target.getTargetID());
            ps.setInt(3, target.getDayKey());
            ps.setString(4, target.getValue());
            
            int rows = ps.executeUpdate();

            ps.close();;
        } catch (SQLException e) {
           e.printStackTrace();

        } finally {
            pool.freeConnection(connection);

        }       
    }
}
