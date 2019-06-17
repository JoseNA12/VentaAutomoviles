package modelo;

import java.util.ArrayList;
import java.util.List;

public class PedidoVehiculo {

    private int idPedido;
    private Vehiculo vehiculoPedido;
    private Usuario usuarioSolicitante;
    private Fabrica fabrica;
    private int idSucursal;
    private String fechaPedido;
    private String fechaEntrega;
    private String detalles;


    public PedidoVehiculo(int idPedido, Vehiculo vehiculoPedido, Usuario usuarioSolicitante, Fabrica fabrica ,int idSucursal,
                          String fechaPedido, String fechaEntrega, String detalles) {
        this.idPedido = idPedido;
        this.idSucursal = idSucursal;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.detalles = detalles;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Vehiculo getVehiculoPedido() {
        return vehiculoPedido;
    }

    public void setVehiculoPedido(Vehiculo vehiculoPedido) {
        this.vehiculoPedido = vehiculoPedido;
    }

    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
