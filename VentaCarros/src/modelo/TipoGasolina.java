package modelo;

public class TipoGasolina {

    private String ID;
    private String tipo;

    public TipoGasolina(String ID, String tipo) {
        this.ID = ID;
        this.tipo = tipo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
