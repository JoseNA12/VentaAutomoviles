package modelo;

import java.sql.*;

public class HumanResourcesDB_Connection extends DB_Connection{
    private static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // Path Josué
    private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\HR_INSTANCE:51171;database=HumanResourcesDB;user=sa;password=123";
    // Path Jose
    //private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\HR_INSTANCE:51171;database=HumanResourcesDB;user=sa;password=123";
    private static HumanResourcesDB_Connection DBInstance;

    public static HumanResourcesDB_Connection getHSDBInstance(){
        if (DBInstance == null){
            DBInstance = new HumanResourcesDB_Connection();
        }
        return DBInstance;
    }

    public void prueba(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("SELECT customer_id FROM Customer");
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                String name = rs.getString("customer_id");
                System.out.println(name);
            }
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
    }

}
