package modelo;

public class Abono {

    private String fecha;
    private String metodoPago;
    private String monto;

    public Abono(String fecha, String metodoPago, String monto) {
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
