package modelo;

import java.util.ArrayList;
import java.util.List;

public class PedidoVehiculo {

    private Vehiculo vehiculo;
    private ArrayList<ExtraVehiculo> extrasVehiculo;
    private Usuario usuario;

    public PedidoVehiculo(Vehiculo vehiculo, ArrayList<ExtraVehiculo> extrasVehiculo, Usuario usuario) {
        this.vehiculo = vehiculo;
        this.extrasVehiculo = extrasVehiculo;
        this.usuario = usuario;
    }

    public PedidoVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.extrasVehiculo = new ArrayList<>();
        this.usuario = usuario;
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

    public void addExtra(ExtraVehiculo extraVehiculo) {
        this.extrasVehiculo.add(extraVehiculo);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
