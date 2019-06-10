package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class C_Menu {

    @FXML JFXButton btn_catalogo;
    @FXML JFXButton btn_sucursales;
    @FXML JFXButton btn_salir;

    public void initialize() throws Exception {
        initComponentes();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_catalogo.setOnAction(this::handle_btn_catalogo);
        btn_sucursales.setOnAction(this::handle_btn_sucursales);
        btn_salir.setOnAction(this::handle_btn_salir);
    }

    private void handle_btn_catalogo(ActionEvent event) {
        try {
            FXRouter.goTo("Catalogo_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_sucursales(ActionEvent event) {
        try {
            FXRouter.goTo("Sucursal_cliente");
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
