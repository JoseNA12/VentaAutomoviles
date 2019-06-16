package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BranchOfficeDB_Connection extends DB_Connection{
    private static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\BOFFICE_INSTANCE:50449;database=BranchOfficeDB;user=sa;password=123";
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

    public ObservableList<Abono> SelectAbonoXUsuario(Usuario usuario){
        ObservableList<Abono> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
                Abono abonoAux = new Abono(fecha, metodoPago, monto, proximoPago,idPlan);
                ReturnList.add(abonoAux);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return ReturnList;
    }

    public ObservableList<PlanDePago> SelectPlanActual(int credit_id){
        ObservableList<PlanDePago> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
                PlanDePago PlanAux = new PlanDePago(PlanId, NombrePlan, porcentajePrima, plazo, interes, totalAPagar);
                ReturnList.add(PlanAux);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return ReturnList;
    }

    public ObservableList<PlanDePago> getPlanesDePagos(){
        ObservableList<PlanDePago> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CreditPlanSelect]}");
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                int PlanId = rs.getInt("creditPlan_id");
                String NombrePlan = rs.getString("planName");
                float porcentajePrima = rs.getFloat("prima");
                float plazo = rs.getFloat("anualTerm");
                float interes = rs.getFloat("interest");
                PlanDePago PlanAux = new PlanDePago(PlanId, NombrePlan, porcentajePrima, plazo, interes);
                ReturnList.add(PlanAux);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
        return ReturnList;
    }

    public ObservableList<MetodoPago> SelectMetodosDePago(){
        ObservableList<MetodoPago> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_PaymentMethodSelect]}");
            callableStatement.executeQuery();
            rs = callableStatement.getResultSet();
            while (rs.next()) {
                int MetodoPagoId = rs.getInt("paymentMethod_id");
                String NombreMetodoPago = rs.getString("name");
                MetodoPago PlanAux = new MetodoPago(MetodoPagoId, NombreMetodoPago);
                ReturnList.add(PlanAux);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return ReturnList;
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
                int id = rs.getInt("car_id");
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
                Vehiculo CarroAux = new Vehiculo(id, new Marca(idMarca, nombreMarca), modelo, annio, num_pasajeros,
                        new TipoVehiculo(idTipo, nombreTipo), motor, puertas,new TipoCombustible(idFuel, nombreCombustible), aceleracion, vel_maxima, precio);
                ReturnList.add(CarroAux);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return ReturnList;
    }

    public ObservableList<MetodoPago> getPaymentMethods(){
        ObservableList<MetodoPago> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
            closeJDBCResources(connection, ps, rs);
        }
        return ReturnList;
    }

    public ObservableList<PlanDePago> getCreditPlan(PedidoVehiculo pedidoVehiculo){
        ObservableList<PlanDePago> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CallableStatement callableStatement;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
            closeJDBCResources(connection, ps, rs);
        }
        return ReturnList;
    }

    public int generarCompra(Usuario usuario, MetodoPago metodoPago, int idSucursal, int cantidadTotal, int pago, int estadoOrden){
        Connection connection = null;
        ResultSet rs = null;
        int result = 0;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return result;
    }

    public int agregarProductoACompra(int idCarroVendido, float costeTotal, int ordenCompra){
        Connection connection = null;
        ResultSet rs = null;
        int result = 0;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
        }
        return result;
    }

    public int generarCarroVendido(PedidoVehiculo pedidoVehiculo){
        Connection connection = null;
        ResultSet rs = null;
        int result = 0;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("{call dbo.[usp_CarSoldInsert](?)}");
            ps.setInt(1, pedidoVehiculo.getVehiculo().getID());
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                result = rs.getInt("car_sold_id");
                System.out.println(pedidoVehiculo.getExtrasVehiculo());
               // if(pedidoVehiculo.getExtrasVehiculo() != null){
                    for(ExtraVehiculo extra: pedidoVehiculo.getExtrasVehiculo())
                        agregarAccesoriosCarroVendido(result, extra);
                //}
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return result;
    }

    public int agregarAccesoriosCarroVendido(int idCarroVendido, ExtraVehiculo extraVehiculo){
        Connection connection = null;
        ResultSet rs = null;
        int result = 0;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
        }
        return result;
    }

    public int generarCredito(int idCompra, PlanDePago planDePago){
        Connection connection = null;
        ResultSet rs = null;
        int result = 0;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("{call dbo.[usp_CreditGivenInsert](?,?,?,?)}");
            ps.setInt(1, idCompra);
            ps.setInt(2, planDePago.getPlanID());
            ps.setFloat(3, planDePago.getTotal_a_pagar());
            ps.setFloat(4, planDePago.getPago_por_mes());
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                result = rs.getInt("credit_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
        return result;
    }

    public ObservableList<Sucursal> getSucursales() {
        ObservableList<Sucursal> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
        }
            return ReturnList;
    }

    public void InsertAbono(int credit_id, float payment, int paymentMethod_id){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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

    public void agregarCarroEnSucursal(int idVehiculo, int idSucursal, int cantidadVehiculos){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_Car-StockInsert](?,?,?)}");
            callableStatement.setInt(1, idVehiculo);
            callableStatement.setInt(2, idSucursal);
            callableStatement.setInt(3, cantidadVehiculos);
            callableStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, callableStatement, rs);
        }
    }

    public void InsertNuevoPlan(PlanDePago plan){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement callableStatement = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            callableStatement = connection.prepareCall("{call [dbo].[usp_CreditPlanInsert](?,?,?)}");
            callableStatement.setFloat(1, plan.getPrima());
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
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
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
}
