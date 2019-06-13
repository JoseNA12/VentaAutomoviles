package modelo;

import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public void prueba1(){
        FactoryDBInstance.prueba();
    }

    public void prueba2(){
        HSDBInstance.prueba();
    }

    public void prueba3(){
        BOfficeDBInstance.prueba();
    }

    public Usuario loginDB(String user, String password){ return HSDBInstance.login(user, password);}

    public boolean signIn(String name, String lastname, LocalDate birthDate, String idCard, String phone, String zip_code, String email, String password){ return HSDBInstance.signIn(name, lastname,birthDate.format(DateTimeFormatter.ISO_DATE), idCard, phone, Integer.parseInt(zip_code), email, password);}

    public ObservableList<ExtraVehiculo> getCarAccessories(String idCar){ return FactoryDBInstance.getCarAccessories(Integer.parseInt(idCar));}

    public ObservableList<Vehiculo> SelectAutosXSucursal(int idSucursal){ return BOfficeDBInstance.SelectAutosXSucursal(idSucursal);}


}