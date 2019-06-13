package modelo;

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

    public void prueba(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL);
            ps = connection.prepareCall("SELECT customer_id FROM Customer");
            ps.execute();
            rs = ps.getResultSet();
            while (rs.next()) {
                String name = rs.getString("customer_id");
                System.out.println(name);
            }
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        } finally {
            closeJDBCResources(connection, ps, rs);
        }
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
                    int userID = rs.getInt("user_id");
                    String userType_id = rs.getString("userType_id");
                    String userTypeName = rs.getString("userTypeName");
                    if(userTypeName.equals("Cliente")){
                        result = new Usuario(userID, name, lastName, birthDate,identification_card, phone, email, zip_code, TipoUsuario.CLIENTE);
                    }else {
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

}
