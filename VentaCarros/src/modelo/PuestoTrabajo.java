package modelo;

public class PuestoTrabajo {

    private String nombrePuesto;
    private int idPuesto;
    private String detallesPuesto;

    public PuestoTrabajo(int pIdPuesto, String nombrePuesto, String detalles) {
        this.idPuesto = pIdPuesto;
        this.nombrePuesto = nombrePuesto;
        this.detallesPuesto = detalles;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public String getDetallesPuesto() {
        return detallesPuesto;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public void setDetallesPuesto(String detallesPuesto) {
        this.detallesPuesto = detallesPuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    @Override
    public String toString(){
        return nombrePuesto;
    }
}
