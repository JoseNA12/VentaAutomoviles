package modelo;

public class PlanDePago {

    private float porcentaje_prima;
    private int prima;
    private float pago_por_mes;
    private float plazo;
    private float interes;
    private float total_a_pagar;
    private String nombrePlan;
    private int planID;

    public PlanDePago(int planID, String nombrePlan, float porcentaje_prima, float plazo, float interes, int total_a_pagar) {
        this.planID = planID;
        this.nombrePlan = nombrePlan;
        this.porcentaje_prima = porcentaje_prima;
        this.plazo = plazo;
        this.interes = interes;
        this.total_a_pagar = total_a_pagar;
        calcularPrima();
        calcularCuotaMensual();
    }

    public PlanDePago(int planID, String nombrePlan, float porcentaje_prima, float plazo, float interes) {
        this.porcentaje_prima = porcentaje_prima;
        this.plazo = plazo;
        this.interes = interes;
        this.nombrePlan = nombrePlan;
        this.planID = planID;
    }
    public PlanDePago(int credit_id, float saldo, float mesesRestantes, float interes) {
        this.planID = credit_id;
        this.total_a_pagar = saldo;
        this.plazo = mesesRestantes/12;
        this.interes = interes;
        calcularCuotaMensual();
    }
    public PlanDePago(float porcentaje_prima, float interes, float plazo){
        this.porcentaje_prima = porcentaje_prima;
        this.interes = interes;
        this.plazo = plazo;
    }

    private void calcularCuotaMensual(){
        this.pago_por_mes = (float) (total_a_pagar * ((interes * Math.pow((1 + interes), (plazo * 12))) / (Math.pow((1 + interes), (plazo * 12)) - 1)));
    }

    private void calcularPrima(){
        this.prima = (int) (total_a_pagar * porcentaje_prima);
        this.total_a_pagar = total_a_pagar - prima;
    }

    public float getPorcentaje_prima() {
        return porcentaje_prima;
    }

    public void setPorcentaje_prima(float porcentaje_prima) {
        this.porcentaje_prima = porcentaje_prima;
    }

    public float getPago_por_mes() {
        return pago_por_mes;
    }

    public void setPago_por_mes(float pago_por_mes) {
        this.pago_por_mes = pago_por_mes;
    }

    public float getPlazo() {
        return plazo;
    }

    public void setPlazo(float plazo) {
        this.plazo = plazo;
    }

    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }

    public float getTotal_a_pagar() {
        return total_a_pagar;
    }

    public void setTotal_a_pagar(float total_a_pagar) {
        this.total_a_pagar = total_a_pagar;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public int getPrima() {
        return prima;
    }

    public void setPrima(int prima) {
        this.prima = prima;
    }
}
