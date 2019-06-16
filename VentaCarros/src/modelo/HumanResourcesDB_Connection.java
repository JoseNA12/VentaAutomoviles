package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class HumanResourcesDB_Connection extends DB_Connection{
    private static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // Path Josué
    private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\HR_INSTANCE:51171;database=HumanResourcesDB;user=sa;password=123";
    // Path Jose
    //private static final String DEFAULT_URL = "jdbc:sqlserver://localhost\\HR_INSTANCE:51171;database=HumanResourcesDB;user=sa;password=123";
    private static HumanResourcesDB_Connection DBInstance;

    public static HumanResourcesDB_Connection getHSDBInstance(){
        if (DBInstance == null){
            DBInstance = new HumanResourcesDB_Connection();
        }
        return DBInstance;
    }

    public Usuario login(String user, String password){
        Connection connection = null;
        CallableStatement  ps = null;
        ResultSet rs = null;
        Usuario result = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("{call dbo.usp_LoginUser(?, ?)}");
            ps.setNString(1, user);
            ps.setNString(2, password);
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                if (rs.getInt("exit_status") == 0) {
                    String name = rs.getString("name");
                    String lastName = rs.getString("lastName");
                    String birthDate = rs.getString("birthDate");
                    String identification_card = rs.getString("identification_card");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    int zip_code = rs.getInt("zip_code");
//                    int userID = rs.getInt("user_id");
                    String userType_id = rs.getString("userType_id");
                    String userTypeName = rs.getString("userTypeName");
                    if(userTypeName.equals("Cliente")){
                        int userID = rs.getInt("customer_id");
                        result = new Usuario(userID, name, lastName, birthDate,identification_card, phone, email, zip_code, TipoUsuario.CLIENTE);
                    }else {
                        int userID = rs.getInt("employee_id");
                        int office_id = rs.getInt("office_id");
                        String position_id = rs.getString("position_id");
                        String positionName = rs.getString("positionName");
                        TipoUsuario tipo = null;
                        if(userTypeName.equals("Administrador")){ tipo = TipoUsuario.ADMINISTRADOR;}
                        else if(userTypeName.equals("Facturador")) { tipo = TipoUsuario.FACTURADOR;}
                        result = new Empleado(userID, name, lastName, birthDate,identification_card, phone, email, zip_code, tipo, position_id, positionName, office_id);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
            return result;
        }
    }

    /*

        @name nvarchar(50),
	@lastname nvarchar(50),
	@birthDate date,
    @phone nvarchar(50),
    @email nvarchar(50),
    @zip_code int,
	@password nvarchar(50),
	@identificationCard nvarchar(50)

     */
    public boolean signIn(String name, String lastname, String birthDate, String idCard, String phone, int zip_code, String email, String password){
        Connection connection = null;
        CallableStatement  ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("{call dbo.usp_CustomerInsert(?,?,?,?,?,?,?,?)}");
            ps.setNString(1, name);
            ps.setNString(2, lastname);
            ps.setNString(3, birthDate);
            ps.setNString(4, phone);
            ps.setNString(5, email);
            ps.setInt(6, zip_code);
            ps.setNString(7, password);
            ps.setNString(8, idCard);
            ps.executeQuery();
            rs = ps.getResultSet();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
            return result;
        }
    }

    public ObservableList<Empleado> SelectEmpleados(){
        ObservableList<Empleado> ReturnList = FXCollections.observableArrayList();
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try{
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("{call [dbo].[usp_EmployeeSelect]}");
            //ps.setNString(1, "");
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                int employee_id = rs.getInt("employee_id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                int position_id = rs.getInt("position_id");
                String position = rs.getString("position");
                int office_id = rs.getInt("office_id");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String entryDate = rs.getString("entryDate");
                int user_id = rs.getInt("user_id");
                String identification_card = rs.getString("identification_card");
                String birthDate = rs.getString("birthDate");
                String zip_code = rs.getString("zip_code");
                String userType = rs.getString("userType");
                Empleado EmpAux;
                switch (userType){
                    case "Administrador":
                        EmpAux = new Empleado(employee_id,user_id,name,lastname,birthDate,identification_card,
                                phone,email,Integer.parseInt(zip_code),TipoUsuario.ADMINISTRADOR,Integer.toString(position_id),position,office_id);
                        ReturnList.add(EmpAux);
                        break;
                    case "Facturador":
                        EmpAux = new Empleado(employee_id,user_id,name,lastname,birthDate,identification_card,
                                phone,email,Integer.parseInt(zip_code),TipoUsuario.FACTURADOR,Integer.toString(position_id),position,office_id);
                        ReturnList.add(EmpAux);
                        break;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }

        return ReturnList;
    }

    public int InsertEmpleado(Empleado empleado){
        int ReturnInt = 1;//
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try{
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("{call [dbo].[usp_EmployeeInsert](?,?,?,?,?,?,?,?,?,?)}");
            ps.setNString(1, empleado.getNombre());
            ps.setNString(2, empleado.getApellidos());
            ps.setInt(3, Integer.parseInt(empleado.getIdPuesto()));
            ps.setInt(4, empleado.getIdSucursal());
            ps.setNString(5, empleado.getCorreo());
            ps.setInt(6, Integer.parseInt(empleado.getTelefono()));
            ps.setNString(7, empleado.getFechaNacimiento());
            ps.setNString(8, empleado.getPassword());
            switch(empleado.getTipoUsuario()){
                case ADMINISTRADOR:
                    ps.setInt(9, 2);
                    break;
                case FACTURADOR:
                    ps.setInt(9, 3);
            }
            ps.setNString(10, empleado.getCedula());
            ps.executeQuery();
            rs = ps.getResultSet();
            while (rs.next()) {
                int result = rs.getInt("exit_status");
                ReturnInt = result;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }

        return ReturnInt;
    }

    public void DeleteEmpleado (Empleado empleado){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try{
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("{call [dbo].[usp_EmployeeDelete](?)}");
            ps.setNString(1, empleado.getCorreo());
            ps.executeQuery();
            rs = ps.getResultSet();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
    }

    public void UpdateEmpleado(Empleado empleado){
        Connection connection = null;
        ResultSet rs = null;
        CallableStatement ps = null;
        try{
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("{call [dbo].[usp_EmployeeUpdate](?,?,?,?,?,?,?)}");
            ps.setInt(1, empleado.getIdEmpleado());
            ps.setNString(2, empleado.getNombre());
            ps.setNString(3, empleado.getApellidos());
            ps.setInt(4, Integer.parseInt(empleado.getIdPuesto()));
            ps.setInt(5, empleado.getIdSucursal());
            ps.setNString(6, empleado.getCorreo());
            ps.setInt(7, Integer.parseInt(empleado.getTelefono()));
            ps.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
    }

}
