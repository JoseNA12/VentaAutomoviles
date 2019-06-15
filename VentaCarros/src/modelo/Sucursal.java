package modelo;

public class Sucursal {

    private int IdSucursal;
    private String nombre;
    private String ubicacion;

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }

    public Sucursal(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public Sucursal(String nombre, int Id) {
        this.nombre = nombre;
        this.IdSucursal = Id;
    }

    public Sucursal(String nombre, String ubicacion, int Id) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.IdSucursal = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        IdSucursal = idSucursal;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
