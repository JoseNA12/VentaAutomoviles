package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

public class BranchOfficeDB_Connection extends DB_Connection{
    private static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DEFAULT_URLBO1 = "jdbc:sqlserver://localhost\\BOFFICE_INSTANCE:50449;database=BranchOfficeDB;user=sa;password=123";
    private static final String DEFAULT_URLBO2 = "jdbc:sqlserver://localhost\\BOFFICE_INSTANCE2:57352;database=BranchOfficeDB;user=sa;password=123";
    private static final String DEFAULT_URLBO3 = "jdbc:sqlserver://localhost\\BOFFICE_INSTANCE3:57348;database=BranchOfficeDB;user=sa;password=123";
    private static BranchOfficeDB_Connection DBInstance;

    public static BranchOfficeDB_Connection getHSDBInstance(){
        if (DBInstance == null){
            DBInstance = new BranchOfficeDB_Connection();
        }
        return DBInstance;
    }


    public ObservableList<Abono> SelectAbonoXUsuario(Usuario usuario){
        ObservableList<Abono> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CreditGiven-PaymentSelect](?)}");
            callableStatement.setInt(1, usuario.getIdUsuario());
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                String fecha = rs.getString("date");
                String metodoPago = rs.getString("name");
                String monto = rs.getString("payment");
                String proximoPago = rs.getString("nextPayment_date");
                String idPlan = rs.getString("credit_id");
                ReturnList.add(new Abono(fecha, metodoPago, monto, proximoPago,idPlan));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
            return ReturnList;
        }
    }

    public ObservableList<PlanDePago> SelectPlanActual(int credit_id){
        ObservableList<PlanDePago> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CreditGivenPlanInfo](?)}");
            callableStatement.setInt(1, credit_id);
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                int PlanId = rs.getInt("creditPlan_id");
                String NombrePlan = rs.getString("planName");
                float porcentajePrima = rs.getFloat("prima");
                float plazo = rs.getFloat("anualTerm");
                float interes = rs.getFloat("interest");
                int totalAPagar = rs.getInt("balance");
                ReturnList.add(new PlanDePago(PlanId, NombrePlan, porcentajePrima, plazo, interes, totalAPagar));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
            return ReturnList;
        }
    }

    public ObservableList<PlanDePago> getPlanesDePagos(){
        ObservableList<PlanDePago> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CreditPlanSelect]}");
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                int PlanId = rs.getInt("creditPlan_id");
                String NombrePlan = rs.getString("planName");
                float porcentajePrima = rs.getFloat("prima");
                float plazo = rs.getFloat("anualTerm");
                float interes = rs.getFloat("interest");
                ReturnList.add(new PlanDePago(PlanId, NombrePlan, porcentajePrima, plazo, interes));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
            return ReturnList;
        }
    }

    public ObservableList<MetodoPago> SelectMetodosDePago(){
        ObservableList<MetodoPago> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_PaymentMethodSelect]}");
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                int MetodoPagoId = rs.getInt("paymentMethod_id");
                String NombreMetodoPago = rs.getString("name");
                ReturnList.add(new MetodoPago(MetodoPagoId, NombreMetodoPago));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
            return ReturnList;
        }
    }

    public ObservableList<Vehiculo> SelectAutosXSucursal(int idSucursal) {
        ObservableList<Vehiculo> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_Car-StockSelect](?)}");
            callableStatement.setInt(1, idSucursal);
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                int idCarroEnFabrica = rs.getInt("car_id");
                int idMarca = rs.getInt("carBrand_id");
                String nombreMarca = rs.getString("brand");
                int idTipo = rs.getInt("carType_id");
                String nombreTipo = rs.getString("typeName");
                String modelo = rs.getString("model");
                String annio = rs.getString("year");
                String num_pasajeros = rs.getString("seats");
                String motor = rs.getString("engine");
                String puertas = rs.getString("doors");
                int idFuel = rs.getInt("fuelType_id");
                String nombreCombustible = rs.getString("fuel");
                String aceleracion = rs.getString("acceleration");
                String vel_maxima = rs.getString("maximum_speed");
                String precio = rs.getString("price");
                int cantidad = rs.getInt("quantity");

                Vehiculo miVehiculo = new Vehiculo(idCarroEnFabrica, new Marca(idMarca, nombreMarca), modelo, annio, num_pasajeros, new TipoVehiculo(idTipo, nombreTipo),
                        motor, puertas,new TipoCombustible(idFuel, nombreCombustible), aceleracion, vel_maxima, precio, cantidad);

                miVehiculo.setBytes_imagen(rs.getBytes("photo"));

                ReturnList.add(miVehiculo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
            return ReturnList;
        }
    }

    public ObservableList<MetodoPago> getPaymentMethods(){
        ObservableList<MetodoPago> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_PaymentMethodSelect]}");
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                int methodID = rs.getInt("paymentMethod_id");
                String name = rs.getString("name");
                ReturnList.add(new MetodoPago(methodID, name));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
            return ReturnList;
        }
    }

    public ObservableList<PlanDePago> getCreditPlan(VehiculoComprado pedidoVehiculo){
        ObservableList<PlanDePago> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CreditPlanSelect]}");
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                int creditPlan_id = rs.getInt("creditPlan_id");
                float prima = rs.getFloat("prima");
                float interest = rs.getFloat("interest");
                float anualTerm = rs.getFloat("anualTerm");
                String name = rs.getString("planName");
                ReturnList.add(new PlanDePago(creditPlan_id, name, prima, anualTerm, interest, (int)pedidoVehiculo.getPrecioTotal()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
            return ReturnList;
        }
    }

    public int generarCompra(Usuario usuario, MetodoPago metodoPago, int idSucursal, int cantidadTotal, int pago, int estadoOrden){
        int result = 0;
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            ps = connection.prepareCall("{call dbo.[usp_SalesOrderInsert](?, ?, ?, ?, ?, ?)}");
            ps.setInt(1, usuario.getIdUsuario());
            ps.setInt(2, metodoPago.getIdMethod());
            ps.setInt(3, idSucursal);
            ps.setInt(4, cantidadTotal);
            ps.setInt(5, pago);
            ps.setInt(6, estadoOrden);
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                result = rs.getInt("salesOrder_id");
                if(rs.getInt("isDiscount") == 1){
                    Alerts.informationDialog("Descuento","Se ha aplicado un descuento","Se ha aplicado un descuento del 10% a su compra");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
            return result;
        }
    }

    public int agregarProductoACompra(int idCarroVendido, float costeTotal, int ordenCompra){
        int result = 0;
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            ps = connection.prepareCall("{call dbo.[usp_SalesOrderDetailsInsert](?,?,?,?)}");
            ps.setInt(1, ordenCompra);
            ps.setInt(2, idCarroVendido);
            ps.setInt(3, 1);
            ps.setFloat(4, costeTotal);
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                result = rs.getInt("orderDetails_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
            return result;
        }
    }

    public int generarCarroVendido(VehiculoComprado vehiculoComprado, int idSucursal){
        int result = 0;
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            ps = connection.prepareCall("{call dbo.[usp_CarSoldInsert](?,?)}");
            ps.setInt(1, vehiculoComprado.getVehiculo().getID());
            ps.setInt(2, idSucursal);
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                result = rs.getInt("car_sold_id");
                    for(ExtraVehiculo extra: vehiculoComprado.getExtrasVehiculo())
                        agregarAccesoriosCarroVendido(result, extra);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
            return result;
        }
    }

    public int agregarAccesoriosCarroVendido(int idCarroVendido, ExtraVehiculo extraVehiculo){
        int result = 0;
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            ps = connection.prepareCall("{call dbo.[usp_CarSold-AccessoryInsert](?,?)}");
            ps.setInt(1, idCarroVendido);
            ps.setInt(2, extraVehiculo.getIdExtra());
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                result = rs.getInt("line");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
            return result;
        }
    }

    public int generarCredito(int idCompra, PlanDePago planDePago, int idMetodoPago){
        int result = 0;
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            ps = connection.prepareCall("{call dbo.[usp_CreditGivenInsert](?,?,?,?,?,?)}");
            ps.setInt(1, idCompra);
            ps.setInt(2, planDePago.getPlanID());
            ps.setFloat(3, planDePago.getTotal_a_pagar());
            ps.setFloat(4, planDePago.getPago_por_mes());
            ps.setFloat(5, planDePago.getPrima());
            ps.setFloat(6, idMetodoPago);
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                result = rs.getInt("credit_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
            return result;
        }
    }

    public ObservableList<Sucursal> getSucursales() {
        ObservableList<Sucursal> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            ps = connection.prepareCall("{call [dbo].[usp_BranchOfficeSelectAll]}");
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                int branchOffice_id = rs.getInt("branchOffice_id");
                String branchoffice_name = rs.getString("name");
                int country_id = rs.getInt("country_id");
                String countryName = rs.getString("countryName");
                String horaApertura = rs.getString("horaApertura");
                String horaCierre = rs.getString("horaCierre");
                ReturnList.add(new Sucursal(branchOffice_id, branchoffice_name, countryName, country_id, horaApertura, horaCierre));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
            return ReturnList;
        }
    }

    public void InsertAbono(int credit_id, float payment, int paymentMethod_id){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            ps = connection.prepareCall("{call dbo.[usp_CreditGiven-PaymentInsert](?,?,?)}");
            ps.setInt(1, credit_id);
            ps.setFloat(2, payment);
            ps.setInt(3, paymentMethod_id);
            ps.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
    }

    public void cambiarTazaInteres(float tazaInteres, int idPlanDeCredito){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            ps = connection.prepareCall("{call dbo.[usp_CreditPlanUpdate](?,?)}");
            ps.setInt(1, idPlanDeCredito);
            ps.setFloat(2, tazaInteres);
            ps.executeQuery();
            recalcularCuota(tazaInteres, idPlanDeCredito);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
    }

    public void recalcularCuota(float tazaInteres, int idPlanDeCredito){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        PlanDePago p;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            ps = connection.prepareCall("{call dbo.[usp_CreditGivenSelectByPlan](?)}");
            ps.setInt(1, idPlanDeCredito);
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                int credit_id = rs.getInt("credit_id");
                float saldo = rs.getFloat("balance");
                float mesesRestantes = rs.getFloat("remainingMonths");
                p = new PlanDePago(credit_id, saldo, mesesRestantes, tazaInteres);
                System.out.print(p.getPago_por_mes());
                updateCuota(p.getPago_por_mes(), credit_id);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
    }

    public void updateCuota(float cuotaMensual, int idCreditoOtorgado){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            ps = connection.prepareCall("{call dbo.[usp_CreditGivenUpdateMensualPayment](?,?)}");
            ps.setInt(1, idCreditoOtorgado);
            ps.setFloat(2, cuotaMensual);
            ps.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
    }

    public int agregarCarroEnSucursal(int idVehiculo, int idSucursal, int cantidadVehiculos, int idFabrica){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        int car_stock_id = 0;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_Car-StockInsert](?,?,?,?)}");
            callableStatement.setInt(1, idVehiculo);
            callableStatement.setInt(2, idSucursal);
            callableStatement.setInt(3, cantidadVehiculos);
            callableStatement.setInt(4, idFabrica);
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                car_stock_id = rs.getInt("car_stock_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
            return car_stock_id;
        }
    }

    public void InsertNuevoPlan(PlanDePago plan){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CreditPlanInsert](?,?,?)}");
            callableStatement.setFloat(1, plan.getPorcentaje_prima());
            callableStatement.setFloat(2, plan.getInteres());
            callableStatement.setFloat(3, plan.getPlazo());
            callableStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
    }


    public ObservableList<Pais> SelectPaises(){
        ObservableList<Pais> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CountrySelect]}");
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while(rs.next()){
                int country_id = rs.getInt("country_id");
                String name = rs.getString("name");
                Pais countryAux = new Pais(Integer.toString(country_id),name);

                if(!countryAux.getNombre().equals("")){
                    ReturnList.add(countryAux);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
        return ReturnList;
    }

    //public void enviarVehiculoPedido(String fechaEntrega, int idPedido){

    public ObservableList<Venta> SelectInfoVentas(int sucursal, int tipoCar, int pais, String fecha1, String fecha2, int metodoPago){
        ObservableList<Venta> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            switch (sucursal){
                case 1:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
                    callableStatement = connection.prepareCall("{call [dbo].[usp_SalesOrderSelect](?,?,?,?,?,?)}");
                    break;
                case 2:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO2);
                    callableStatement = connection.prepareCall("{call [dbo].[usp_SalesOrderSelect](?,?,?,?,?,?)}");
                    break;
                case 3:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO3);
                    callableStatement = connection.prepareCall("{call [dbo].[usp_SalesOrderSelect](?,?,?,?,?,?)}");
                    break;
                default:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
                    callableStatement = connection.prepareCall("{call [dbo].[usp_SalesOrderSelectAll](?,?,?,?,?,?)}");
                    break;
            }
            if(!(sucursal==-1)){
                callableStatement.setInt(1, sucursal);
            }
            else{
                callableStatement.setNull(1, Types.NULL);
            }
            if(!(tipoCar==-1)){
                callableStatement.setInt(2, tipoCar);
            }
            else{
                callableStatement.setNull(2, Types.NULL);
            }
            if(!(pais==-1)){
                callableStatement.setInt(3, pais);
            }
            else{
                callableStatement.setNull(3, Types.NULL);
            }
            if(!fecha1.equals("null")){
                callableStatement.setString(4, fecha1);
            }
            else{
                callableStatement.setNull(4, Types.DATE);
            }
            if(!fecha2.equals("null")){
                callableStatement.setString(5, fecha2);
            }
            else{
                callableStatement.setNull(5, Types.DATE);
            }
            if(!(metodoPago==-1)){
                callableStatement.setInt(6, metodoPago);
            }
            else{
                callableStatement.setNull(6, Types.NULL);
            }
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while(rs.next()){
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int anio = rs.getInt("anio");
                String pasajeros = rs.getString("pasajeros");
                float precio = rs.getFloat("precio");
                String tipo = rs.getString("tipo");
                String nombreCl = rs.getString("nombreCl");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefonoCl");
                String mPago = rs.getString("metodoPago");
                float precioTotal = rs.getFloat("precioTotal");
                String fecha = rs.getString("fecha");
                int estatus = rs.getInt("estatus");
                Venta ventaAux = new Venta(marca,modelo,Integer.toString(anio),pasajeros,Float.toString(precio),
                        tipo,nombreCl,apellidos,telefono, mPago,Float.toString(precioTotal),Integer.toString(estatus),
                        fecha);
                ReturnList.add(ventaAux);
            }
            /*connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
            callableStatement = connection.prepareCall("{call [dbo].[usp_OrderUpdate](?)}");
            callableStatement.setInt(1, idPedido);
            callableStatement.setNString(2, fechaEntrega);
            callableStatement.executeQuery();*/
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
        return ReturnList;
    }

    public ObservableList<Vehiculo> SelectVehiculosDemanda(int idSucursal){
        ObservableList<Vehiculo> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try{
            switch (idSucursal){
                case 1:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
                    callableStatement = connection.prepareCall("{call [dbo].[usp_SelectMasDemanda]}");
                    break;
                case 2:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO2);
                    callableStatement = connection.prepareCall("{call [dbo].[usp_SelectMasDemanda]}");
                    break;
                case 3:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO3);
                    callableStatement = connection.prepareCall("{call [dbo].[usp_SelectMasDemanda]}");
                    break;
                default:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
                    callableStatement = connection.prepareCall("{call [dbo].[usp_SelectMasDemandaAll]}");
                    break;
            }
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while(rs.next()){
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int pasajeros = rs.getInt("pasajeros");
                String anio = rs.getString("anio");
                int cantidad = rs.getInt("cantidad");
                float precio = rs.getFloat("precio");
                Vehiculo carroAux = new Vehiculo(marca,modelo,anio,pasajeros,cantidad,precio);
                ReturnList.add(carroAux);
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
        return ReturnList;
    }

    public ObservableList<Vehiculo> SelectVehiculosNoVendidos(int idSucursal){
        ObservableList<Vehiculo> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try{
            switch (idSucursal){
                case 1:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
                    callableStatement = connection.prepareCall("{call [dbo].[SelectVehiculosNoVendidos]}");
                    break;
                case 2:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO2);
                    callableStatement = connection.prepareCall("{call [dbo].[SelectVehiculosNoVendidos]}");
                    break;
                case 3:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO3);
                    callableStatement = connection.prepareCall("{call [dbo].[SelectVehiculosNoVendidos]}");
                    break;
                default:
                    connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URLBO1);
                    callableStatement = connection.prepareCall("{call [dbo].[usp_SelectVehiculosNoVendidosAll]}");
                    break;
            }
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while(rs.next()){
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int pasajeros = rs.getInt("pasajeros");
                String anio = rs.getString("anio");
                int cantidad = rs.getInt("cantidad");
                float precio = rs.getFloat("precio");
                Vehiculo carroAux = new Vehiculo(marca,modelo,anio,pasajeros,cantidad,precio);
                ReturnList.add(carroAux);
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
        return ReturnList;
    }


}
