package modelo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Vehiculo {

    private String ID;
    private String nombre_carro;
    private String marca;
    private int idMarca;
    private Marca marcaTipo;
    private String modelo;
    private String anio;
    private String num_pasajeros;
    private String tipo;
    private int idTipo;
    private TipoVehiculo tipoVehiculo;
    private String motor;
    private String asientos;
    private String puertas;
    private String gasolina;
    private int idTipoGasolina;
    private TipoGasolina tipoGasolina;
    private String aceleracion;
    private String vel_maxima;
    private String precio;
    private String cantidad_en_fabrica;
    // falta la foto


    public Vehiculo(Marca marca, String modelo, String anio, String num_pasajeros, TipoVehiculo tipoVehiculo, String motor, String asientos, String puertas, TipoGasolina tipoGasolina, String aceleracion, String vel_maxima, String precio, String cantidad_en_fabrica) {
        this.marcaTipo = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.num_pasajeros = num_pasajeros;
        this.tipoVehiculo = tipoVehiculo;
        this.motor = motor;
        this.asientos = asientos;
        this.puertas = puertas;
        this.tipoGasolina = tipoGasolina;
        this.aceleracion = aceleracion;
        this.vel_maxima = vel_maxima;
        this.precio = precio;
        this.cantidad_en_fabrica = cantidad_en_fabrica;
    }

    public Vehiculo(String ID, String marca, String modelo, String anio, String num_pasajeros,
                    String tipo, String motor, String asientos, String puertas, String gasolina,
                    String aceleracion, String vel_maxima, String precio) {
        this.ID = ID;
        this.nombre_carro = marca + " " + modelo;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.num_pasajeros = num_pasajeros;
        this.tipo = tipo;
        this.motor = motor;
        this.asientos = asientos;
        this.puertas = puertas;
        this.gasolina = gasolina;
        this.aceleracion = aceleracion;
        this.vel_maxima = vel_maxima;
        this.precio = precio;
    }

    public Vehiculo(String ID, String marca, String modelo, String anio, String num_pasajeros,
                    String tipo, String motor, String asientos, String puertas, String gasolina,
                    String aceleracion, String vel_maxima, String precio, String cantidad_en_fabrica) {
        this.ID = ID;
        this.nombre_carro = marca + " " + modelo;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.num_pasajeros = num_pasajeros;
        this.tipo = tipo;
        this.motor = motor;
        this.asientos = asientos;
        this.puertas = puertas;
        this.gasolina = gasolina;
        this.aceleracion = aceleracion;
        this.vel_maxima = vel_maxima;
        this.precio = precio;
        this.cantidad_en_fabrica = cantidad_en_fabrica;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre_carro() {
        return nombre_carro;
    }

    public void setNombre_carro(String nombre_carro) {
        this.nombre_carro = nombre_carro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getNum_pasajeros() {
        return num_pasajeros;
    }

    public void setNum_pasajeros(String num_pasajeros) {
        this.num_pasajeros = num_pasajeros;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public String getPuertas() {
        return puertas;
    }

    public void setPuertas(String puertas) {
        this.puertas = puertas;
    }

    public String getGasolina() {
        return gasolina;
    }

    public void setGasolina(String gasolina) {
        this.gasolina = gasolina;
    }

    public String getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(String aceleracion) {
        this.aceleracion = aceleracion;
    }

    public String getVel_maxima() {
        return vel_maxima;
    }

    public void setVel_maxima(String vel_maxima) {
        this.vel_maxima = vel_maxima;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad_en_fabrica() {
        return cantidad_en_fabrica;
    }

    public void setCantidad_en_fabrica(String cantidad_en_fabrica) {
        this.cantidad_en_fabrica = cantidad_en_fabrica;
    }

    public Marca getMarcaTipo() {
        return marcaTipo;
    }

    public void setMarcaTipo(Marca marcaTipo) {
        this.marcaTipo = marcaTipo;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public TipoGasolina getTipoGasolina() {
        return tipoGasolina;
    }

    public void setTipoGasolina(TipoGasolina tipoGasolina) {
        this.tipoGasolina = tipoGasolina;
    }
}
