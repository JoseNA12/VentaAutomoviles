package modelo;

import java.util.ArrayList;

public class VehiculoComprado {
    private Vehiculo vehiculo;
    private ArrayList<ExtraVehiculo> extrasVehiculo;
    private Usuario usuario;
    private float precioTotal;
    private MetodoPago metodoPago;

    public VehiculoComprado(Vehiculo vehiculo, ArrayList<ExtraVehiculo> extrasVehiculo, Usuario usuario, float precioTotal, MetodoPago metodoPago) {
        this.vehiculo = vehiculo;
        this.extrasVehiculo = extrasVehiculo;
        this.usuario = usuario;
        this.precioTotal = precioTotal;
        this.metodoPago = metodoPago;
    }

    public VehiculoComprado(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        extrasVehiculo = new ArrayList<ExtraVehiculo>();
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ArrayList<ExtraVehiculo> getExtrasVehiculo() {
        return extrasVehiculo;
    }

    public void setExtrasVehiculo(ArrayList<ExtraVehiculo> extrasVehiculo) {
        this.extrasVehiculo = extrasVehiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void addExtra(ExtraVehiculo extraVehiculo) {
        this.precioTotal += Float.parseFloat(extraVehiculo.getPrecio());
        System.out.println(extraVehiculo.getPrecio());
        this.extrasVehiculo.add(extraVehiculo);
    }
}
