package modelo;

public class ExtraVehiculo {
    private int idExtra;
    private String nombre;
    private String precio;

    public ExtraVehiculo(int idExtra, String nombre, String precio) {
        this.idExtra = idExtra;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getIdExtra() {
        return idExtra;
    }

    public void setIdExtra(int idExtra) {
        this.idExtra = idExtra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
