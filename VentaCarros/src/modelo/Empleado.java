package modelo;

public class Empleado extends Usuario{
    private String idPuesto;
    private String nombrePuesto;
    private int idSucursal;
    private int idEmpleado;


    public Empleado(int idUsuario, String nombre, String apellidos, String fechaNacimiento,
                    String cedula, String telefono, String correo, int zipCode, TipoUsuario tipoUsuario,
                    String idPuesto, String nombrePuesto, int idSucursal) {
        super(idUsuario, nombre, apellidos, fechaNacimiento, cedula, telefono, correo, zipCode, tipoUsuario);
        this.idPuesto = idPuesto;
        this.nombrePuesto = nombrePuesto;
        this.idSucursal = idSucursal;
    }

    public Empleado(int idEmpleado,int idUsuario, String nombre, String apellidos, String fechaNacimiento,
                    String cedula, String telefono, String correo, int zipCode, TipoUsuario tipoUsuario,
                    String idPuesto, String nombrePuesto, int idSucursal) {
        super(idUsuario, nombre, apellidos, fechaNacimiento, cedula, telefono, correo, zipCode, tipoUsuario);
        this.idPuesto = idPuesto;
        this.nombrePuesto = nombrePuesto;
        this.idSucursal = idSucursal;
        this.idEmpleado = idEmpleado;
    }

    public Empleado( int IdSucursal, String nombre, String apellidos, String telefono, String correo,
                    String contrasenia, String position_id, String fecha, String cedula, TipoUsuario tipoUsuario){
        super(nombre,apellidos,cedula,telefono,correo,tipoUsuario,fecha, contrasenia);
        idSucursal = IdSucursal;
        idPuesto = position_id;
    }


    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdEmpleado() {return idEmpleado;}

    public void setIdEmpleado(int idEmpleado) {this.idEmpleado = idEmpleado;}
}
