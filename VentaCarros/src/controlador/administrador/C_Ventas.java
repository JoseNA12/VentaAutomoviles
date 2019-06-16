package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.*;

import java.io.IOException;
import java.util.ArrayList;

public class C_Ventas {

    @FXML JFXComboBox<Sucursal> cb_sucursal;
    @FXML JFXComboBox<TipoVehiculo> cb_tipo_vehiculo;
    @FXML JFXComboBox<Pais> cb_pais;
    @FXML JFXComboBox<MetodoPago> cb_tipo_pago;

    @FXML JFXDatePicker dt_fecha_inicial;
    @FXML JFXDatePicker dt_fecha_final;

    @FXML JFXButton btn_visualizar_datos;
    @FXML JFXButton btn_atras;
    @FXML JFXButton btn_vehiculos_en_demanda;
    @FXML JFXButton btn_limpiar_campos;

    @FXML JFXListView listView_ventas;
    private ObservableList<Venta> ventasObservableList;


    public void initialize() throws Exception {
        init_componentes();
        init_listView_ventas();
    }

    private void init_componentes() {
        btn_visualizar_datos.setOnAction(this::handle_btn_visualizar_datos);
        btn_atras.setOnAction(this::handle_btn_atras);
        btn_vehiculos_en_demanda.setOnAction(this::handle_btn_vehiculos_en_demanda);
        btn_limpiar_campos.setOnAction(this::handle_btn_limpiar_campos);

        init_cb_sucursal();
        init_cb_tipo_vehiculo();
        init_cb_pais();
        init_cb_tipo_pago();
    }

    private void init_listView_ventas() {
        ventasObservableList = FXCollections.observableArrayList();

        // ------------------------- query a la BD's
        //ventasObservableList = GroupDBConnection.getDBInstance().SelectEmpleados();

        ventasObservableList.add(new Venta(new Vehiculo(), new MetodoPago(12, "Tarjetaso"),
                new Usuario("nombre", "apellidos", "", "", "", TipoUsuario.CLIENTE, "", ""),
                "monto", "estado", "fecha"));
        // -----------------------------------------

        listView_ventas.setItems(ventasObservableList);
        listView_ventas.setCellFactory(empleadosListView -> new VentaListViewCell());
    }

    private void init_cb_sucursal() {
        // ------------------------- query a la BD's
        // cb_sucursal
        // ---------------------------------------------------
    }

    private void init_cb_tipo_vehiculo() {
        ArrayList<TipoVehiculo> tipos = GroupDBConnection.getDBInstance().getCarType();
        for (int i=0;i<tipos.size();i++){
            cb_tipo_vehiculo.getItems().add(tipos.get(i));
        }
    }

    private void init_cb_pais() {
        // ------------------------- query a la BD's
        // cb_pais
        // ---------------------------------------------------
    }

    private void init_cb_tipo_pago() {
        // ------------------------- query a la BD's
        // cb_tipo_pago
        // ---------------------------------------------------
    }

    private void handle_btn_visualizar_datos(ActionEvent event) {
        if (cb_sucursal.getValue() != null) {
            cb_sucursal.getValue().getIdSucursal();
        }

        if (cb_tipo_vehiculo.getValue() != null) {
            cb_tipo_vehiculo.getValue().getID();
        }

        if (cb_pais.getValue() != null) {
            cb_pais.getValue().getID();
        }

        if (cb_pais.getValue() != null) {
            cb_pais.getValue().getID();
        }

        if (dt_fecha_inicial.getValue() != null) {
            dt_fecha_inicial.getValue(); // en el query -> dt_fecha_inicial.getValue().toString();
        }

        if (dt_fecha_final.getValue() != null) {
            dt_fecha_final.getValue(); // en el query -> dt_fecha_final.getValue().toString();
        }
        ventasObservableList.removeAll();   // limpiar la lista
        listView_ventas.setItems(ventasObservableList);

        // ------------------------- query a la BD's

        // ---------------------------------------------------
    }

    private void handle_btn_vehiculos_en_demanda(ActionEvent event) {

    }

    private void handle_btn_limpiar_campos(ActionEvent event) {
        dt_fecha_inicial.setValue(null);
        dt_fecha_final.setValue(null);
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("Menu_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
