package modelo;

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




}