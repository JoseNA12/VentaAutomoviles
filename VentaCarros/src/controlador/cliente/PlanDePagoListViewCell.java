package controlador.cliente;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modelo.PlanDePago;
import modelo.TipoUsuario;

import java.io.IOException;

public class PlanDePagoListViewCell extends JFXListCell<PlanDePago> {

    @FXML Label lb_prima;
    @FXML Label lb_pago_por_mes;
    @FXML Label lb_plazo;
    @FXML Label lb_interes;
    @FXML Label lb_monto_total;

    @FXML GridPane gp_planes_de_pago;

    private FXMLLoader mLLoader;

    private TipoUsuario bandera;


    public PlanDePagoListViewCell() {
        this.bandera = TipoUsuario.CLIENTE;
    }

    public PlanDePagoListViewCell(TipoUsuario bandera) {
        this.bandera = bandera;
    }

    @Override
    protected void updateItem(PlanDePago planDePago, boolean empty) {
        super.updateItem(planDePago, empty);

        if(empty || planDePago == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../../vista/cliente/PlanDePagoListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bandera == TipoUsuario.ADMINISTRADOR) {
                lb_prima.setText(String.valueOf(planDePago.getPorcentaje_prima()*100)+"%");
                lb_pago_por_mes.setText("[Según la compra]");
                lb_plazo.setText(String.valueOf(planDePago.getPlazo())+" años");
                lb_monto_total.setText("[Según la compra]");
                lb_interes.setText(String.valueOf(planDePago.getInteres()*100)+"%");
            }
            else {
                lb_prima.setText(String.valueOf(planDePago.getPorcentaje_prima()*100)+"%");
                lb_pago_por_mes.setText(String.valueOf(planDePago.getPago_por_mes())+" dólares");
                lb_plazo.setText(String.valueOf(planDePago.getPlazo())+" años");
                lb_interes.setText(String.valueOf(planDePago.getInteres()*100)+"%");
                lb_monto_total.setText(String.valueOf(planDePago.getTotal_a_pagar())+" dólares");
            }

            setText(null);
            setGraphic(gp_planes_de_pago);
        }
    }
}
