package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.GroupDBConnection;
import modelo.PlanDePago;

import java.io.IOException;

public class C_NuevoCredito {

    @FXML JFXButton btn_atras;
    @FXML JFXButton btn_registrar_credito;

    @FXML JFXTextField tf_porcentaje_prima;
    @FXML JFXTextField tf_interes;
    @FXML JFXTextField tf_plazo;


    private PlanDePago planDePago = null;


    public void initialize() throws Exception {
        initComponentes();

        planDePago = (PlanDePago) FXRouter.getData();

        if (planDePago != null) { // modificar plan
            tf_plazo.setVisible(false);
            tf_interes.setText(String.valueOf(planDePago.getInteres()));
            tf_porcentaje_prima.setVisible(false);
        }
    }

    private void initComponentes() throws Exception {
        btn_registrar_credito.setOnAction(this::handle_btn_registrar_credito);
        btn_atras.setOnAction(this::handle_btn_atras);
    }

    private void handle_btn_registrar_credito(ActionEvent event) {
        if (planDePago != null) {
            GroupDBConnection.getDBInstance().cambiarTazaInteres(Float.parseFloat(tf_interes.getText()), planDePago.getPlanID());
        }
        else { // crear plan de credito
            if (!tf_porcentaje_prima.getText().trim().equals("") && !tf_interes.getText().trim().equals("") &&
                    !tf_plazo.getText().trim().equals("")) {
                // --------------------------------------------------
                // query
                // --------------------------------------------------
            }
        }
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("PlanesCredito_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
