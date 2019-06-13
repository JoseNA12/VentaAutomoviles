package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class FactoryDB_Connection extends DB_Connection{

    private static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // Path Josué
    private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\FACTORYINSTANCE:51024;database=FactoryDB;user=sa;password=123";
    // Path Jose
    // private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\FACTORYINSTANCE:51024;database=FactoryDB;user=sa;password=123";
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

    public ObservableList<ExtraVehiculo> getCarAccessories(int idCar){
        ObservableList<ExtraVehiculo> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CarXAccessorySelect](?)}");
            callableStatement.setInt(1, idCar);
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                //String id = rs.getString("carXAccessory_id");
                int extraID = rs.getInt("accessorie_id");
                float price = rs.getFloat("price");
                String name = rs.getString("name");
                ReturnList.add(new ExtraVehiculo(extraID, name, String.valueOf(price)));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return ReturnList;
    }


}
