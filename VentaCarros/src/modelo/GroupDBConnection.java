package modelo;

import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//Agrupa las conexiones a la DB. Actua como una interfaz de estas.
public class GroupDBConnection {
    private static GroupDBConnection DBInstance;
    private static FactoryDB_Connection FactoryDBInstance;
    private static HumanResourcesDB_Connection HSDBInstance;
    private static BranchOfficeDB_Connection BOfficeDBInstance;

    private GroupDBConnection() {
        FactoryDBInstance = FactoryDB_Connection.getFactoryDBInstance();
        HSDBInstance = HumanResourcesDB_Connection.getHSDBInstance();
        BOfficeDBInstance = BranchOfficeDB_Connection.getHSDBInstance();
    }

    public static GroupDBConnection getDBInstance(){
        if (DBInstance == null){
            DBInstance = new GroupDBConnection();
        }
        return DBInstance;
    }

    public Usuario loginDB(String user, String password){ return HSDBInstance.login(user, password);}

    public boolean signIn(String name, String lastname, LocalDate birthDate, String idCard, String phone, String zip_code, String email, String password){ return HSDBInstance.signIn(name, lastname,birthDate.format(DateTimeFormatter.ISO_DATE), idCard, phone, Integer.parseInt(zip_code), email, password);}

    public ObservableList<ExtraVehiculo> getCarAccessories(int idCar){ return FactoryDBInstance.getCarAccessories(idCar);}

    public ObservableList<Vehiculo> SelectAutosXSucursal(int idSucursal){ return BOfficeDBInstance.SelectAutosXSucursal(idSucursal);}

    public ObservableList<MetodoPago> getPaymentMethods(){return BOfficeDBInstance.getPaymentMethods();}

    public ObservableList<PlanDePago> getCreditPlan(VehiculoComprado vehiculoComprado){return BOfficeDBInstance.getCreditPlan(vehiculoComprado);}

    public ObservableList<Marca>  getCarBrands() {return FactoryDBInstance.getCarBrands();}

    public ObservableList<TipoVehiculo>  getCarType() {return FactoryDBInstance.getCarType();}

    public ObservableList<TipoCombustible>  getFuelType() {return FactoryDBInstance.getFuelType();}

    public ObservableList<Fabrica> getFactory() {return FactoryDBInstance.getFactory();}

    public void comprarPorCredito(VehiculoComprado vehiculoComprado, int idSucursal, PlanDePago planDePago){
        int idCompra = BOfficeDBInstance.generarCompra(vehiculoComprado.getUsuario(), vehiculoComprado.getMetodoPago(), idSucursal, (int)vehiculoComprado.getPrecioTotal(), planDePago.getPrima(), 1);
        int idCarroVendido = BOfficeDBInstance.generarCarroVendido(vehiculoComprado, idSucursal);
        BOfficeDBInstance.agregarProductoACompra(idCarroVendido, vehiculoComprado.getPrecioTotal(), idCompra);
        BOfficeDBInstance.generarCredito(idCompra, planDePago);
    }

    public void comprarVehiculo(VehiculoComprado vehiculoComprado, int idSucursal){
        int idCompra = BOfficeDBInstance.generarCompra(vehiculoComprado.getUsuario(), vehiculoComprado.getMetodoPago(), idSucursal, (int)vehiculoComprado.getPrecioTotal(), (int)vehiculoComprado.getPrecioTotal(), 2);
        int idCarroVendido = BOfficeDBInstance.generarCarroVendido(vehiculoComprado, idSucursal);
        BOfficeDBInstance.agregarProductoACompra(idCarroVendido, vehiculoComprado.getPrecioTotal(), idCompra);
    }

    public ObservableList<Sucursal> getSucursales(){return BOfficeDBInstance.getSucursales();}

    public ObservableList<Abono> SelectAbonoXUsuario(Usuario usuario){return BOfficeDBInstance.SelectAbonoXUsuario(usuario);}

    public ObservableList<PlanDePago> SelectPlanActual(int credit_id) {return BOfficeDBInstance.SelectPlanActual(credit_id);}

    public ObservableList<MetodoPago> SelectMetodosDePago(){return BOfficeDBInstance.SelectMetodosDePago();}

    public void InsertAbono(int credit_id, float payment, int paymentMethod_id){BOfficeDBInstance.InsertAbono(credit_id,payment,paymentMethod_id);}

    public int crearNuevoVehiculo(Vehiculo vehiculo, Fabrica fabrica, ObservableList<ExtraVehiculo> extras){
        int idVehiculo = FactoryDBInstance.insertVehiculo(vehiculo, fabrica.getID());
        if (idVehiculo != 0){
            for(ExtraVehiculo extraVehiculo: extras)
                FactoryDBInstance.agregarExtra(extraVehiculo, idVehiculo);
        }
        return idVehiculo;
    }

    public int updateVehiculo(Vehiculo vehiculo, Fabrica fabrica, ObservableList<ExtraVehiculo> extras){
        int idVehiculo = FactoryDBInstance.updateVehiculo(vehiculo);
        if (idVehiculo != 0){
            for(ExtraVehiculo extraVehiculo: extras)
                FactoryDBInstance.agregarExtra(extraVehiculo, idVehiculo);
        }
        return idVehiculo;
    }

    public ObservableList<PlanDePago> getPlanesDePago() {return BOfficeDBInstance.getPlanesDePagos();}

    public void cambiarTazaInteres(float tazaInteres, int idPlan){BOfficeDBInstance.cambiarTazaInteres(tazaInteres, idPlan);}

    public ObservableList<Empleado> SelectEmpleados(){return HSDBInstance.SelectEmpleados();}

    public int InsertEmpleado(Empleado empleado){return HSDBInstance.InsertEmpleado(empleado);}

    public void enviarCarroASucursal(Vehiculo vehiculo, Sucursal sucursal, int cantidadVehiculos){
        FactoryDBInstance.generarOrdenEnvio(vehiculo.getID(), sucursal.getIdSucursal(), cantidadVehiculos, vehiculo.getIdFabrica());
        BOfficeDBInstance.agregarCarroEnSucursal(vehiculo.getID(), sucursal.getIdSucursal(), cantidadVehiculos);
    }

    public ObservableList<Vehiculo> getCarrosDeFabrica(){ return FactoryDBInstance.getCarrosDeFabricas();}

    public ObservableList<PedidoVehiculo> getPedidoVehiculos(){ return FactoryDBInstance.getPedidoVehiculos();}

    public void DeleteEmpleado(Empleado empleado){HSDBInstance.DeleteEmpleado(empleado);}

    public void UpdateEmpleado(Empleado empleado){HSDBInstance.UpdateEmpleado(empleado);}

    public void InsertNuevoPlan(PlanDePago plan){BOfficeDBInstance.InsertNuevoPlan(plan);}

    //public void enviarVehiculo(PedidoVehiculo pedidoVehiculo){BOfficeDBInstance.enviarVehiculoPedido(pedid);}

}