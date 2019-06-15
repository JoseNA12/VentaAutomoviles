package modelo;

public class Abono {
    private String idPlan;
    private String fecha;
    private String metodoPago;
    private String monto;
    private String fechaProximoPago;

    public Abono(String fecha, String metodoPago, String monto, String fechaProximoPago, String pIdPlan) {
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaProximoPago = fechaProximoPago;
        this.idPlan = pIdPlan;
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

    public String getfechaProximoPago(){return fechaProximoPago;}

    public void setfechaProximoPago(String pfechaProximoPago) {this.fechaProximoPago = pfechaProximoPago;}

    public String getIdPlan(){return idPlan;}

    public void setIdPlan(String pIdPlan){ this.idPlan = pIdPlan;}
}
