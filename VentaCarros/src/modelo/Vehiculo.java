package modelo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Vehiculo {

    private String nombre_carro;
    private String marca;
    private String modelo;
    private String anio;
    private String num_pasajeros;
    private String tipo;
    private String motor;
    private String asientos;
    private String puertas;
    private String gasolina;
    private String aceleracion;
    private String vel_maxima;
    private String precio;
    // falta la foto


    public Vehiculo(String marca, String modelo, String anio, String num_pasajeros,
                    String tipo, String motor, String asientos, String puertas, String gasolina,
                    String aceleracion, String vel_maxima, String precio) {
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

    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
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
}
