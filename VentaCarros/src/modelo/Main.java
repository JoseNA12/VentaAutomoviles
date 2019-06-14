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


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXRouter.bind(this, primaryStage, "Jx3-L Autos", 1280, 700);
        FXRouter.when("InicioSesion", "../vista/InicioSesion.fxml");

        // Cliente
        FXRouter.when("Menu_cliente", "../vista/cliente/Menu.fxml");
        FXRouter.when("CrearCuenta_cliente", "../vista/cliente/CrearCuenta.fxml");
        FXRouter.when("Catalogo_cliente", "../vista/cliente/Catalogo.fxml");
        FXRouter.when("Sucursal_cliente", "../vista/cliente/Sucursal.fxml");
        FXRouter.when("ConsultarVehiculo_cliente", "../vista/cliente/ConsultarVehiculo.fxml");
        FXRouter.when("SolicitarCredito_cliente", "../vista/cliente/SolicitarCredito.fxml");
        FXRouter.when("Abonos_cliente", "../vista/cliente/Abono.fxml");

        // Facturador
        FXRouter.when("Menu_facturador", "../vista/facturador/Menu.fxml");

        // Administrador
        FXRouter.when("Menu_administrador", "../vista/administrador/Menu.fxml");

        // -> Pantalla inicial
        FXRouter.startFrom("InicioSesion");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
