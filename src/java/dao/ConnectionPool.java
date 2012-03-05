package dao;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;

public class ConnectionPool
{
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    private ConnectionPool()
    {
        try
        {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup(
                    "java:/comp/env/jdbc/diaryCardDB");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public static ConnectionPool getInstance()
    {
        if (pool == null)
            pool = new ConnectionPool();

        return pool;
    }

    public Connection getConnection()
    {
        try
        {
            return dataSource.getConnection();
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            return null;
        }
    }

    public void freeConnection(Connection c)
    {
        try
        {
            c.close();
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
    }
}