package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

import static controlador.C_InicioSesion.usuarioActual;

public class C_Menu {

    @FXML JFXButton btn_vehiculos;
    @FXML JFXButton btn_ventas;
    @FXML JFXButton btn_salir;

    @FXML Label lb_usuario_actual;


    public void initialize() throws Exception {
        initComponentes();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_vehiculos.setOnAction(this::handle_btn_vehiculos);
        btn_ventas.setOnAction(this::handle_btn_ventas);
        btn_salir.setOnAction(this::handle_btn_salir);

        lb_usuario_actual.setText(usuarioActual.getNombre() + " " + usuarioActual.getApellidos());
    }

    private void handle_btn_vehiculos(ActionEvent event) {
        try {
            FXRouter.goTo("Vehiculos_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_ventas(ActionEvent event) {
        try {
            FXRouter.goTo("Ventas_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_salir(ActionEvent event) {
        try {
            FXRouter.goTo("InicioSesion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
