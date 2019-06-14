package modelo;

public class MetodoPago {
    private int idMethod;
    private String name;

    public MetodoPago(int idMethod, String name) {
        this.idMethod = idMethod;
        this.name = name;
    }

    public int getIdMethod() {
        return idMethod;
    }

    public void setIdMethod(int idMethod) {
        this.idMethod = idMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
