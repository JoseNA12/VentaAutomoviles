package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.*;
import controlador.cliente.VehiculoListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.*;

import java.io.IOException;

import static modelo.Alerts.informationDialog;

public class C_IngresarVehiculoSucursal {

    @FXML JFXListView listView_vehiculo_seleccionado;
    private ObservableList<Vehiculo> vehiculo_ObservableList;

    @FXML JFXComboBox<Sucursal> cb_sucursal;

    @FXML JFXTextField tf_cantidad_vehiculos;

    @FXML JFXButton btn_atras;
    @FXML JFXButton btn_enviar;

    private Vehiculo vehiculo_seleccionado = null;


    public void initialize() throws Exception {
        initComponentes();
        init_cb_sucursal();
        init_listView_vehiculo_seleccionado();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_atras.setOnAction(this::handle_btn_atras);
        btn_enviar.setOnAction(this::handle_btn_enviar);
        vehiculo_seleccionado = (Vehiculo) FXRouter.getData(); // la pantalla previa a esta envia el objeto Vehiculo
    }

    private void init_cb_sucursal() {
        cb_sucursal.setItems(GroupDBConnection.getDBInstance().getSucursales());
        cb_sucursal.getSelectionModel().selectFirst(); //select the first element
    }

    private void init_listView_vehiculo_seleccionado() {
        vehiculo_ObservableList = FXCollections.observableArrayList();
        if (vehiculo_seleccionado != null)
            vehiculo_ObservableList.add(vehiculo_seleccionado);
        listView_vehiculo_seleccionado.setItems(vehiculo_ObservableList);
        listView_vehiculo_seleccionado.setCellFactory(miLista -> new VehiculoListViewCell(TipoUsuario.ADMINISTRADOR));
    }

    private void handle_btn_enviar(ActionEvent event) {
        if(GroupDBConnection.getDBInstance().enviarCarroASucursal(vehiculo_seleccionado,
                cb_sucursal.getSelectionModel().getSelectedItem(), Integer.parseInt(tf_cantidad_vehiculos.getText()))==0) {
            Alerts.errorDialog("Error", "Error en el envío", "Ha ocurrido un error inesperado, inténtelo de nuevo!");
        }else {
            Alerts.informationDialog("Atención", "Vehículo enviado", "Se ha enviado el vehículo a la sucursal" + cb_sucursal.getSelectionModel().getSelectedItem().getNombreSucursal());
            handle_btn_atras(null);
        }
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("VehiculosFabricas_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
