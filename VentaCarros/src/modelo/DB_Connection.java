package modelo;

import java.sql.*;
// Clase padre de todas las conexiones. Evita la repetición de código
public class DB_Connection {

    protected static Connection getConnection(String driver, String url) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        return DriverManager.getConnection(url);
    }

    protected static void closeJDBCResources(Connection conn, PreparedStatement ps, ResultSet rs){
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (ps != null) ps.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
}
