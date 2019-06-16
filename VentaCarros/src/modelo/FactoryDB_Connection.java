package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class FactoryDB_Connection extends DB_Connection{

    private static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\FACTORYINSTANCE:51024;database=FactoryDB;user=sa;password=123";
    // Path Leo
    //private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\FACTORY_1:51024;database=FactoryDB;user=sa;password=123";

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

    public ObservableList<Marca> getCarBrands(){
        ObservableList<Marca> marcas = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CarBrandsSelect]}");
            //callableStatement.setInt(1, Types.NULL);
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()){
                int id = rs.getInt("carBrand_id");
                String name = rs.getString("name");
                marcas.add(new Marca(id, name));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return marcas;
    }

    public ObservableList<TipoVehiculo> getCarType(){
        ObservableList<TipoVehiculo> tipos = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CarTypeSelect]}");
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()){
                int id = rs.getInt("carType_id");
                String name = rs.getString("name");
                tipos.add(new TipoVehiculo(id, name));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return tipos;
    }

    public ObservableList<TipoCombustible> getFuelType(){
        ObservableList<TipoCombustible> tipos = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_FuelTypeSelect]}");
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()){
                int id = rs.getInt("fuelType_id");
                String name = rs.getString("name");
                tipos.add(new TipoCombustible(id, name));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return tipos;
    }

    public ObservableList<Fabrica> getFactory(){
        ObservableList<Fabrica> fabricas = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_FactorySelect]}");
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()){
                int id = rs.getInt("factory_id");
                String name = rs.getString("name");
                fabricas.add(new Fabrica(id, name));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return fabricas;
    }

    public int insertVehiculo(Vehiculo vehiculo, int idFabrica){
        int result = 0;
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CarInsert](?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, vehiculo.getMarca().getID());
            callableStatement.setInt(2, vehiculo.getTipoVehiculo().getID());
            callableStatement.setNString(3, vehiculo.getModelo());
            callableStatement.setNString(4, vehiculo.getMotor());
            callableStatement.setInt(5, Integer.parseInt(vehiculo.getAnio()));
            callableStatement.setInt(6, Integer.parseInt(vehiculo.getNum_pasajeros()));
            callableStatement.setInt(7, Integer.parseInt(vehiculo.getPuertas()));
            callableStatement.setInt(8, vehiculo.getTipoCombustible().getID());
            callableStatement.setFloat(9, Float.parseFloat(vehiculo.getAceleracion()));
            callableStatement.setFloat(10, Float.parseFloat(vehiculo.getVel_maxima()));
            callableStatement.setInt(11, Integer.parseInt(vehiculo.getPrecio()));

            if (vehiculo.getImagen() != null) {
                callableStatement.setBinaryStream(12, (InputStream) vehiculo.getImagen(), vehiculo.getFile_length());
            }
            else  {
                callableStatement.setNull(12, Types.NULL);
            }

            callableStatement.setInt(13, idFabrica);
            callableStatement.setInt(14, Integer.parseInt(vehiculo.getCantidad_en_fabrica()));
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                result = rs.getInt("car_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
        return result;
    }

    public ObservableList<Vehiculo> getCarrosDeFabricas(){
        ObservableList<Vehiculo> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("{call [dbo].[usp_Factory-CarSelect]}");
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()){
                int idCarro = rs.getInt("car_id");
                int idFabrica = rs.getInt("factory_id");
                String cantidad = rs.getString("quantity");
                int idMarca = rs.getInt("carBrand_id");
                String nombreMarca = rs.getString("Brandname");
                int idTipo = rs.getInt("carType_id");
                String nombreTipo = rs.getString("typeName");
                String modelo = rs.getString("model");
                String motor = rs.getString("engine");
                String anio = rs.getString("year");
                String puertas = rs.getString("doors");
                int idCombustible = rs.getInt("fuelType_id");
                String nombreCombustible = rs.getString("fuelName");
                String aceleracion = rs.getString("acceleration");
                String velMaxima = rs.getString("maximum_speed");
                String precio = rs.getString("price");
                String asientos = rs.getString("seats");
                String fechaProduccion = rs.getString("production_date");
                //int id = rs.getInt("photo");
                ReturnList.add(new Vehiculo(idCarro, new Marca(idMarca, nombreMarca), modelo, anio, asientos, new TipoVehiculo(idTipo, nombreTipo),
                        motor, puertas, new TipoCombustible(idCombustible, nombreCombustible), aceleracion, velMaxima, precio, cantidad, idFabrica, fechaProduccion));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
            return ReturnList;
        }
    }

    public void generarOrdenEnvio(int idVehiculo, int idSucursal, int cantidadVehiculo, int idFabrica){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            System.out.print(idSucursal);
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_OrderInsert](?,?,?,?,?)}");
            callableStatement.setInt(1, idSucursal);
            callableStatement.setInt(2, idFabrica);
            callableStatement.setNull(3, Types.NULL);
            callableStatement.setInt(4, idVehiculo);
            callableStatement.setInt(5, 1);
            //callableStatement.setNull(6, Types.NULL);
            callableStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
    }

    public int updateVehiculo(Vehiculo vehiculo){
        int result = 0;
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            System.out.print(vehiculo.getID());
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CarUpdate](?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, vehiculo.getID());
            callableStatement.setInt(2, vehiculo.getMarca().getID());
            callableStatement.setInt(3, vehiculo.getTipoVehiculo().getID());
            callableStatement.setNString(4, vehiculo.getModelo());
            callableStatement.setNString(5, vehiculo.getMotor());
            callableStatement.setInt(6, Integer.parseInt(vehiculo.getAnio()));
            callableStatement.setInt(7, Integer.parseInt(vehiculo.getNum_pasajeros()));
            callableStatement.setInt(8, Integer.parseInt(vehiculo.getPuertas()));
            callableStatement.setInt(9, vehiculo.getTipoCombustible().getID());
            callableStatement.setFloat(10, Float.parseFloat(vehiculo.getAceleracion()));
            callableStatement.setFloat(11, Float.parseFloat(vehiculo.getVel_maxima()));
            callableStatement.setFloat(12, Float.parseFloat(vehiculo.getPrecio()));
            //callableStatement.setNull(12, Types.NULL);
            //callableStatement.setInt(14, Integer.parseInt(vehiculo.getCantidad_en_fabrica()));
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                result = rs.getInt("car_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
        return result;
    }

    public int agregarExtra(ExtraVehiculo extraVehiculo, int idVehiculo){
        int result = 0;
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_AccessoryInsert](?)}");
            callableStatement.setNString(1, extraVehiculo.getNombre());
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                result = rs.getInt("accessory_id");
                asociarExtraAVehiculo(result, Integer.parseInt(extraVehiculo.getPrecio()), idVehiculo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
            return result;
        }
    }

    public void asociarExtraAVehiculo(int extraID, int extraPrecio, int idVehiculo){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CarXAccessoryInsert](?,?,?)}");
            callableStatement.setInt(1, idVehiculo);
            callableStatement.setInt(2, extraID);
            callableStatement.setInt(3, extraPrecio);
            callableStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
    }

   /* public int updateExtra(ExtraVehiculo extraVehiculo, int idVehiculo){
        int result = 0;
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_AccessoryUpdate](?)}");
            callableStatement.setInt(1, extraVehiculo.getIdExtra());
            callableStatement.setNString(2, extraVehiculo.getNombre());
            callableStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
            return result;
        }
    }*/


}
