package modelo;

public class Sucursal {

    private int idSucursal;
    private String nombreSucursal;
    private String nombrePais;
    private int idPais;
    private String horaApertura;
    private String horaCierre;

    public Sucursal(int idSucursal, String nombreSucursal, String nombrePais, int idPais, String horaApertura, String horaCierre) {
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.nombrePais = nombrePais;
        this.idPais = idPais;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }
}
