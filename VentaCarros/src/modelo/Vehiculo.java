package modelo;

import java.io.FileInputStream;

public class Vehiculo {

    private int ID;
    private String nombre_carro;
    private Marca marca;
    private String modelo;
    private String anio;
    private String num_pasajeros;
    private TipoVehiculo tipoVehiculo;
    private String motor;
    private String puertas;
    private TipoCombustible tipoCombustible;
    private String aceleracion;
    private String vel_maxima;
    private String precio;
    private String cantidad_en_fabrica;
    private int idFabrica;
    private String fechaProduccion;

    private FileInputStream imagen;
    private int file_length; // para la imagen
    private byte[] bytes_imagen;


    public Vehiculo(int ID, Marca marca, String modelo, String anio, String num_pasajeros, TipoVehiculo tipoVehiculo, String motor, String puertas, TipoCombustible tipoCombustible, String aceleracion, String vel_maxima, String precio, String cantidad_en_fabrica, int idFabrica, String fechaProduccion) {
        this.ID = ID;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.num_pasajeros = num_pasajeros;
        this.tipoVehiculo = tipoVehiculo;
        this.motor = motor;
        this.puertas = puertas;
        this.tipoCombustible = tipoCombustible;
        this.aceleracion = aceleracion;
        this.vel_maxima = vel_maxima;
        this.precio = precio;
        this.cantidad_en_fabrica = cantidad_en_fabrica;
        this.idFabrica = idFabrica;
        this.fechaProduccion = fechaProduccion;
    }

    public Vehiculo(int ID, Marca marca, String modelo, String anio, String num_pasajeros,
                    TipoVehiculo tipo, String motor, String puertas, TipoCombustible combustible,
                    String aceleracion, String vel_maxima, String precio) {
        this.ID = ID;
        this.nombre_carro = marca.getNombre() + " " + modelo;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.num_pasajeros = num_pasajeros;
        this.tipoVehiculo = tipo;
        this.motor = motor;
        this.puertas = puertas;
        this.tipoCombustible = combustible;
        this.aceleracion = aceleracion;
        this.vel_maxima = vel_maxima;
        this.precio = precio;
    }

    public Vehiculo(int ID, Marca marca, String modelo, String anio, String num_pasajeros,
                    TipoVehiculo tipo, String motor, String puertas, TipoCombustible combustible,
                    String aceleracion, String vel_maxima, String precio, String cantidad_en_fabrica) {
        this.ID = ID;
        this.nombre_carro = marca.getNombre() + " " + modelo;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.num_pasajeros = num_pasajeros;
        this.tipoVehiculo = tipo;
        this.motor = motor;
        this.puertas = puertas;
        this.tipoCombustible = combustible;
        this.aceleracion = aceleracion;
        this.vel_maxima = vel_maxima;
        this.precio = precio;
        this.cantidad_en_fabrica = cantidad_en_fabrica;
    }

    public Vehiculo() { // dejar para hacer pruebas
        this.nombre_carro = "marca" + " " + "modelo";
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre_carro() {
        return nombre_carro;
    }

    public void setNombre_carro(String nombre_carro) {
        this.nombre_carro = nombre_carro;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
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

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getPuertas() {
        return puertas;
    }

    public void setPuertas(String puertas) {
        this.puertas = puertas;
    }

    public TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(TipoCombustible tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
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

    public int getIdFabrica() {
        return idFabrica;
    }

    public void setIdFabrica(int idFabrica) {
        this.idFabrica = idFabrica;
    }

    public String getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(String fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public FileInputStream getImagen() {
        return imagen;
    }

    public void setImagen(FileInputStream imagen, int file_length) {
        this.imagen = imagen;
        this.file_length = file_length;
    }

    public int getFile_length() {
        return file_length;
    }

    public byte[] getBytes_imagen() {
        return bytes_imagen;
    }

    public void setBytes_imagen(byte[] bytes_imagen) {
        this.bytes_imagen = bytes_imagen;
    }
}
