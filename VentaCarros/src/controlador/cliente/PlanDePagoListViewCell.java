package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXListCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modelo.PlanDePago;
import modelo.Vehiculo;

import java.io.IOException;

public class PlanDePagoListViewCell extends JFXListCell<PlanDePago> {

    @FXML Label lb_prima;
    @FXML Label lb_pago_por_mes;
    @FXML Label lb_plazo;
    @FXML Label lb_interes;
    @FXML Label lb_monto_total;

    @FXML GridPane gp_planes_de_pago;

    private FXMLLoader mLLoader;


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

            lb_prima.setText(planDePago.getPorcentaje_prima());
            lb_pago_por_mes.setText(planDePago.getPago_por_mes());
            lb_plazo.setText(planDePago.getPlazo());
            lb_interes.setText(planDePago.getInteres());
            lb_monto_total.setText(planDePago.getTotal_a_pagar());

            setText(null);
            setGraphic(gp_planes_de_pago);
        }
    }
}
