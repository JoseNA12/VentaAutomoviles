package modelo;

public class TipoVehiculo {

    private int ID;
    private String tipo;
    private String detalles;

    public TipoVehiculo(int ID, String tipo, String detalles) {
        this.ID = ID;
        this.tipo = tipo;
        this.detalles = detalles;
    }

    public TipoVehiculo(int ID, String tipo) {
        this.ID = ID;
        this.tipo = tipo;
    }
    public TipoVehiculo(String tipo){
        this.tipo = tipo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
