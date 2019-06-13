package modelo;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BranchOfficeDB_Connection extends DB_Connection{
    private static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // Path Josué
    private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\BOFFICE_INSTANCE:50449;database=BranchOfficeDB;user=sa;password=123";
    // Path Jose ** Cambiar
    //private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\BOFFICE_INSTANCE:50449;database=BranchOfficeDB;user=sa;password=123";
    private static BranchOfficeDB_Connection DBInstance;

    public static BranchOfficeDB_Connection getHSDBInstance(){
        if (DBInstance == null){
            DBInstance = new BranchOfficeDB_Connection();
        }
        return DBInstance;
    }

    public void prueba(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("SELECT name FROM Country");
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

    public ObservableList<Vehiculo> SelectAutosXSucursal(int idSucursal){
        ObservableList<Vehiculo> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_Car-StockSelect](?)}");
            callableStatement.setInt(1, idSucursal);
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                String id = rs.getString("car_id");
                String marca = rs.getString("brand");
                String nombre = rs.getString("name");
                String modelo = rs.getString("model");
                String annio = rs.getString("year");
                String num_pasajeros = rs.getString("seats");
                String tipo = rs.getString("name");
                String motor = rs.getString("engine");
                String asientos = rs.getString("seats");
                String puertas = rs.getString("doors");
                String gasolina = rs.getString("fuel");
                String aceleracion = rs.getString("acceleration");
                String vel_maxima = rs.getString("maximum_speed");
                String precio = rs.getString("price");
                Vehiculo CarroAux = new Vehiculo(id, marca, modelo, annio, num_pasajeros, tipo, motor,
                        asientos, puertas, gasolina, aceleracion, vel_maxima, precio);
                ReturnList.add(CarroAux);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return ReturnList;
    }
}
