package controlador.facturador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static controlador.C_InicioSesion.usuarioActual;

public class C_Transaccion {

    @FXML JFXButton btn_atras;


    public void initialize() throws Exception {
        initComponentes();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_atras.setOnAction(this::handle_btn_atras);
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("Menu_facturador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
