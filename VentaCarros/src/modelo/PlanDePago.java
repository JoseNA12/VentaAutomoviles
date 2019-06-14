package modelo;

public class PlanDePago {

    private String porcentaje_prima;
    private String pago_por_mes;
    private String plazo;
    private String interes;
    private String total_a_pagar;

    public PlanDePago(String porcentaje_prima, String pago_por_mes, String plazo, String interes, String total_a_pagar) {
        this.porcentaje_prima = porcentaje_prima;
        this.pago_por_mes = pago_por_mes;
        this.plazo = plazo;
        this.interes = interes;
        this.total_a_pagar = total_a_pagar;
    }

    public String getPorcentaje_prima() {
        return porcentaje_prima;
    }

    public void setPorcentaje_prima(String porcentaje_prima) {
        this.porcentaje_prima = porcentaje_prima;
    }

    public String getPago_por_mes() {
        return pago_por_mes;
    }

    public void setPago_por_mes(String pago_por_mes) {
        this.pago_por_mes = pago_por_mes;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    public String getTotal_a_pagar() {
        return total_a_pagar;
    }

    public void setTotal_a_pagar(String total_a_pagar) {
        this.total_a_pagar = total_a_pagar;
    }
}
