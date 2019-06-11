package modelo;

import java.sql.*;

public class DBConnection{
    public static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String DEFAULT_URL = "jdbc:sqlserver://localhost:1433;database=FactoryDB";
    private static final String DEFAULT_USERNAME = "";
    private static final String DEFAULT_PASSWORD = "";
    /*public static final String FIND_ALL_CUSTOMERS_QUERY = "SELECT Fname, Address FROM Customers ";
    private static final String BY_FIRST_NAME = "WHERE FNAME = ? ";*/

    public static Connection getConnection(String driverClass, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClass);
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void prueba() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection connection = DBConnection.getConnection(DEFAULT_DRIVER_CLASS,DEFAULT_URL);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Statement stat = connection.createStatement();
            String sql = "Select name from Accessory";
            rs = stat.executeQuery(sql);
            rs.next();

            String name = rs.getString("name");
            System.out.println(name);

            /*ps = connection.prepareCall("select name,price from Accessory");
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                String name = rs.getString("name");
                String price = rs.getString("price");
                System.out.println(name+" "+price);
            }
            rs.close();
            connection.close();*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);
            DBConnection.close(ps);
        }
    }

}