package modelo;

import java.sql.*;

public class FactoryDB_Connection extends DB_Connection{

    private static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\FACTORYINSTANCE:1433;database=FactoryDB;user=sa;password=123";
    private static FactoryDB_Connection DBInstance;

    public static FactoryDB_Connection getFactoryDBInstance(){
        if (DBInstance == null){
            DBInstance = new FactoryDB_Connection();
        }
        return DBInstance;
    }

    public void prueba(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("SELECT name FROM CarBrands");
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
    }

}
