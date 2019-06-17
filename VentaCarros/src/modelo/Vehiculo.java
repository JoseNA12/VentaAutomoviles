package modelo;

import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class Vehiculo {

    private int idCarroEnFabrica;
    private int idCarroEnInventario;
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
    private int cantidad_en_fabrica;
    private int idFabrica;
    private String fechaProduccion;


    private FileInputStream fis; // para la imagen
    private int file_length;
    private byte[] bytes_imagen;
    private ImageView imagen;



    public Vehiculo(int idCarroEnFabrica, Marca marca, String modelo){
        this.idCarroEnFabrica = idCarroEnFabrica;
        this.nombre_carro = marca.getNombre() + " " + modelo;
        this.marca = marca;
        this.modelo = modelo;
    }


    public Vehiculo(int idCarroEnFabrica, Marca marca, String modelo, String anio, String num_pasajeros, TipoVehiculo tipoVehiculo, String motor, String puertas,
                    TipoCombustible tipoCombustible, String aceleracion, String vel_maxima, String precio, int cantidad_en_fabrica, int idFabrica,
                    String fechaProduccion) {
        this.idCarroEnFabrica = idCarroEnFabrica;
        this.nombre_carro = marca.getNombre() + " " + modelo;
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

    public Vehiculo(int idCarroEnFabrica, Marca marca, String modelo, String anio, String num_pasajeros,
                    TipoVehiculo tipo, String motor, String puertas, TipoCombustible combustible,
                    String aceleracion, String vel_maxima, String precio) {
        this.idCarroEnFabrica = idCarroEnFabrica;
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

    public Vehiculo(int idCarroEnFabrica, Marca marca, String modelo, String anio, String num_pasajeros,
                    TipoVehiculo tipo, String motor, String puertas, TipoCombustible combustible,
                    String aceleracion, String vel_maxima, String precio, int cantidad_en_fabrica) {
        this.idCarroEnFabrica = idCarroEnFabrica;
        this.idCarroEnInventario = idCarroEnInventario;
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

    public Vehiculo(String marca, String modelo, String anio, String num_pasajeros,
                    String tipo,String precio){
        this.marca = new Marca(marca);
        this.modelo = modelo;
        this.anio = anio;
        this.num_pasajeros = num_pasajeros;
        this.tipoVehiculo = new TipoVehiculo(tipo);
        this.precio = precio;
    }

    public Vehiculo(String marca, String modelo, String anio, int pasajeros, int cantidad, float precio){
        this.marca = new Marca(marca);
        this.modelo = modelo;
        this.anio = anio;
        this.num_pasajeros = Integer.toString(pasajeros);
        this.cantidad_en_fabrica = cantidad;
        this.precio = Float.toString(precio);
    }

    public Vehiculo(String marca,String modelo,String precio){
        this.marca = new Marca(marca);
        this.modelo = modelo;
        this.precio = precio;
    }

    public Vehiculo() { // dejar para hacer pruebas
        this.nombre_carro = "marca" + " " + "modelo";
    }

    public int getID() {
        return this.idCarroEnFabrica;
    }

    public void setID(int idCarroEnFabrica) {
        this.idCarroEnFabrica = idCarroEnFabrica;
    }

    public int getIdCarroEnInventario() {
        return idCarroEnInventario;
    }

    public void setIdCarroEnInventario(int idCarroEnInventario) {
        this.idCarroEnInventario = idCarroEnInventario;
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

    public int getCantidad_en_fabrica() {
        return cantidad_en_fabrica;
    }

    public void setCantidad_en_fabrica(int cantidad_en_fabrica) {
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

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis, int file_length) {
        this.fis = fis;
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

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

}
