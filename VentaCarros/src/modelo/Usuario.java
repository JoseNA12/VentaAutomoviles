package modelo;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String cedula;
    private String telefono;
    private String correo;
    private int zipCode;
    private TipoUsuario tipoUsuario;
    private String password;


    public Usuario(int idUsuario, String nombre, String apellidos, String telefono, String correo){
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;

    }
    public Usuario(int idUsuario, String nombre, String apellidos, String fechaNacimiento, String cedula, String telefono,
                   String correo, int zipCode, TipoUsuario tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.zipCode = zipCode;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String nombre, String apellidos, String cedula, String telefono, String correo,
                   TipoUsuario tipoUsuario, String fechaEntrada, String password){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaEntrada;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
    }

    public Usuario(String nombre, String apellidos, String telefono){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public Usuario(){ }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacemiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getPassword() { return password;}

    public void setPassword(String password) { this.password = password;}
}
