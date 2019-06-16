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

    @FXML JFXButton btn_ingresar_vehiculos_fabricas;
    @FXML JFXButton btn_ventas;
    @FXML JFXButton btn_pedidos_clientes;
    @FXML JFXButton btn_planes_de_credito;
    @FXML JFXButton btn_empleados;
    @FXML JFXButton btn_salir;

    @FXML Label lb_usuario_actual;


    public void initialize() throws Exception {
        initComponentes();
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_ingresar_vehiculos_fabricas.setOnAction(this::handle_btn_ingresar_vehiculos_fabricas);
        btn_ventas.setOnAction(this::handle_btn_ventas);
        btn_pedidos_clientes.setOnAction(this::handle_btn_pedidos_clientes);
        btn_salir.setOnAction(this::handle_btn_salir);
        btn_planes_de_credito.setOnAction(this::handle_btn_planes_de_credito);
        btn_empleados.setOnAction(this::handle_btn_empleados);

        lb_usuario_actual.setText(usuarioActual.getNombre() + " " + usuarioActual.getApellidos());
    }

    private void handle_btn_empleados(ActionEvent event) {
        try {
            FXRouter.goTo("Empleados_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_ingresar_vehiculos_fabricas(ActionEvent event) {
        try {
            FXRouter.goTo("VehiculosFabricas_administrador");
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

    private void handle_btn_pedidos_clientes(ActionEvent event) {
        try {
            FXRouter.goTo("PedidosClientes_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_planes_de_credito(ActionEvent event) {
        try {
            FXRouter.goTo("PlanesCredito_administrador");
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
