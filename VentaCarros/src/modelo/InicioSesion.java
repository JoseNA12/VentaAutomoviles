package modelo;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;


public class InicioSesion extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXRouter.bind(this, primaryStage, "Jx3-L Autos", 1280, 700);
        FXRouter.when("InicioSesion", "../vista/InicioSesion.fxml");
        FXRouter.when("Menu_cliente", "../vista/cliente/Menu.fxml");
        FXRouter.when("CrearCuenta_cliente", "../vista/cliente/CrearCuenta.fxml");
        FXRouter.when("Catalogo_cliente", "../vista/cliente/Catalogo.fxml");
        FXRouter.when("Sucursal_cliente", "../vista/cliente/Sucursal.fxml");
        FXRouter.when("ConsultarVehiculo_cliente", "../vista/cliente/ConsultarVehiculo.fxml");

        FXRouter.startFrom("InicioSesion");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
