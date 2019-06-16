package modelo;

public class Venta {

    private Vehiculo vehiculo;
    private MetodoPago metodoPago;
    private Usuario usuario;
    private String monto;
    private String estado;
    private String fecha;

    public Venta(Vehiculo vehiculo, MetodoPago metodoPago, Usuario usuario, String monto, String estado, String fecha) {
        this.vehiculo = vehiculo;
        this.metodoPago = metodoPago;
        this.usuario = usuario;
        this.monto = monto;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
