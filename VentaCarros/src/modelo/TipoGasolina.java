package modelo;

public class TipoGasolina {

    private int ID;
    private String tipo;

    public TipoGasolina(int ID, String tipo) {
        this.ID = ID;
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

    @Override
    public String toString() {
        return tipo;
    }
}
