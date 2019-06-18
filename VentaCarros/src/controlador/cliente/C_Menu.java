package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo.BranchOfficeDB_Connection;
import modelo.GroupDBConnection;
import modelo.Sucursal;
import static controlador.C_InicioSesion.idSucursalActual;

import javax.swing.*;
import java.io.IOException;

import static controlador.C_InicioSesion.usuarioActual;

public class C_Menu {

    @FXML JFXButton btn_catalogo;
    @FXML JFXButton btn_abonos;
    @FXML JFXButton btn_sucursales;
    @FXML JFXButton btn_salir;
    @FXML JFXButton btn_atencion;
    @FXML JFXButton btn_mi_pedido;

    @FXML Label lb_usuario_actual;
    @FXML Label lb_msg_atencion;

    @FXML JFXComboBox cmb_sucursal_actual;

    private Boolean label_atencion = false;

/*
* public void getSucursal(int idSucursal){
        switch (idSucursal){
            case 1:
                DEFAULT_URL = "jdbc:sqlserver://localhost\BOFFICE_INSTANCE:50449;database=BranchOfficeDB;user=sa;password=123";
                break;
            case 2:
                DEFAULT_URL = "jdbc:sqlserver://localhost\BOFFICE_INSTANCE2:57352;database=BranchOfficeDB;user=sa;password=123";
                break;
            case 3:
                DEFAULT_URL = "jdbc:sqlserver://localhost\BOFFICE_INSTANCE3:57348;database=BranchOfficeDB;user=sa;password=123";
                break;
        }
    }*/
    public void initialize() throws Exception {
        initComponentes();
        //getSucursales
        cmb_sucursal_actual.setItems(GroupDBConnection.getDBInstance().getSucursales());
        cmb_sucursal_actual.getSelectionModel().selectFirst();
        cmb_sucursal_actual.valueProperty().addListener(new ChangeListener<Sucursal>(){
            @Override
            public void changed(ObservableValue<? extends Sucursal> observable, Sucursal oldValue, Sucursal newValue) {
                //idSucursalActual = newValue.getIdSucursal();
                BranchOfficeDB_Connection.getSucursal(idSucursalActual);
            }
        });
        //System.out.println(idSucursalActual);
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_catalogo.setOnAction(this::handle_btn_catalogo);
        btn_abonos.setOnAction(this::handle_btn_abonos);
        btn_sucursales.setOnAction(this::handle_btn_sucursales);
        btn_salir.setOnAction(this::handle_btn_salir);
        btn_atencion.setOnAction(this::handle_btn_atencion);
        btn_mi_pedido.setOnAction(this::handle_btn_mi_pedido);

        lb_usuario_actual.setText(usuarioActual.getNombre() + " " + usuarioActual.getApellidos());
        lb_msg_atencion.setVisible(label_atencion);
    }

    private void init_cmb_sucursal_actual() {

    }

    private void handle_btn_catalogo(ActionEvent event) {
        try {
            FXRouter.goTo("Catalogo_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_abonos(ActionEvent event) {
        try {
            FXRouter.goTo("Abonos_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_sucursales(ActionEvent event) {
        try {
            FXRouter.goTo("Empleados_administrador", true); // true: soy cliente
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_atencion(ActionEvent event) {
        label_atencion = !label_atencion;
        lb_msg_atencion.setVisible(label_atencion);
    }

    private void handle_btn_mi_pedido(ActionEvent event) {
        try {
            FXRouter.goTo("PedidoActual_cliente");
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
