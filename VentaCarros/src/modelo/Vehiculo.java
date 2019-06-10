package modelo;

public class Vehiculo {

    private String modelo;
    private String marca;
    private String motor;
    private String precio;
    private String anio;
    private String cant_pasajeros;
    // falta la foto


    public Vehiculo(String modelo, String marca, String motor, String precio, String anio, String cant_pasajeros) {
        this.modelo = modelo;
        this.marca = marca;
        this.motor = motor;
        this.precio = precio;
        this.anio = anio;
        this.cant_pasajeros = cant_pasajeros;
    }

    public Vehiculo(String marca, String anio) {
        this.marca = marca;
        this.anio = anio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCant_pasajeros() {
        return cant_pasajeros;
    }

    public void setCant_pasajeros(String cant_pasajeros) {
        this.cant_pasajeros = cant_pasajeros;
    }
}
