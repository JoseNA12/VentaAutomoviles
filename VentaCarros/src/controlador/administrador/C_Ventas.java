package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import controlador.cliente.VehiculoListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.*;

import java.io.IOException;
import java.util.ArrayList;

public class C_Ventas {

    @FXML JFXComboBox<Sucursal> cb_sucursal;
    @FXML JFXComboBox<Sucursal> cb_sucursal_2; // vehiculos
    @FXML JFXComboBox<TipoVehiculo> cb_tipo_vehiculo;
    @FXML JFXComboBox<Pais> cb_pais;
    @FXML JFXComboBox<MetodoPago> cb_tipo_pago;
    @FXML JFXComboBox cb_tipo_demanda; // vehiculos

    @FXML JFXDatePicker dt_fecha_inicial;
    @FXML JFXDatePicker dt_fecha_final;

    @FXML JFXButton btn_visualizar_datos;
    @FXML JFXButton btn_visualizar_datos_2; // vehiculos
    @FXML JFXButton btn_atras;
    @FXML JFXButton btn_limpiar_campos;

    @FXML JFXListView listView_ventas;
    private ObservableList<Venta> ventasObservableList;

    @FXML JFXListView listView_vehiculos_demanda;
    private ObservableList<Vehiculo> ventasDemandaObservableList;
    private ObservableList<Sucursal> sucursalesObservablelist;
    private ObservableList<Pais> paisesObservableList;
    private ObservableList<MetodoPago> metodoPagoObservableList;


    public void initialize() throws Exception {
        init_componentes();
        init_listView_ventas();
    }

    private void init_componentes() {
        btn_visualizar_datos.setOnAction(this::handle_btn_visualizar_datos);
        btn_visualizar_datos_2.setOnAction(this::handle_btn_visualizar_datos_2);
        btn_atras.setOnAction(this::handle_btn_atras);
        btn_limpiar_campos.setOnAction(this::handle_btn_limpiar_campos);
        init_cb_sucursal();
        init_cb_tipo_vehiculo();
        init_cb_pais();
        init_cb_tipo_pago();
        init_cb_tipo_demanda();
    }

    private void init_listView_ventas() {
        ventasObservableList = FXCollections.observableArrayList();
        listView_ventas.setItems(ventasObservableList);
        listView_ventas.setCellFactory(empleadosListView -> new VentaListViewCell());

        // ---------------------------

        ventasDemandaObservableList = FXCollections.observableArrayList();
        listView_vehiculos_demanda.setItems(ventasDemandaObservableList);
        listView_vehiculos_demanda.setCellFactory(vehiculosDemandaListView -> new VehiculoListViewCell(TipoUsuario.ADMINISTRADOR));
    }

    private void init_cb_sucursal() {
        // ------------------------- query a la BD's
        // cb_sucursal
        // hacer el set tambien a: cb_sucursal_2
        // --------------------------------------------------
        sucursalesObservablelist = FXCollections.observableArrayList();
        sucursalesObservablelist = GroupDBConnection.getDBInstance().getSucursales();
        cb_sucursal.setItems(sucursalesObservablelist);
        cb_sucursal_2.setItems(sucursalesObservablelist);

        //cb_sucursal.getSelectionModel().selectFirst();
        //cb_sucursal_2.getSelectionModel().selectFirst();
    }

    private void init_cb_tipo_vehiculo() {
        cb_tipo_vehiculo.setItems(GroupDBConnection.getDBInstance().getCarType());
        //cb_tipo_vehiculo.getSelectionModel().selectFirst();
    }

    private void init_cb_pais() {
        // ------------------------- query a la BD's
        // cb_pais
        // ---------------------------------------------------
        paisesObservableList = FXCollections.observableArrayList();
        paisesObservableList = GroupDBConnection.getDBInstance().SelectPaises();
        cb_pais.setItems(paisesObservableList);
        //cb_pais.getSelectionModel().selectFirst();
    }

    private void init_cb_tipo_pago() {
        // ------------------------- query a la BD's
        // cb_tipo_pago
        // ---------------------------------------------------
        metodoPagoObservableList = FXCollections.observableArrayList();
        metodoPagoObservableList = GroupDBConnection.getDBInstance().SelectMetodosDePago();
        cb_tipo_pago.setItems(metodoPagoObservableList);
        //cb_tipo_pago.getSelectionModel().selectFirst();
    }

    private void init_cb_tipo_demanda() {
        cb_tipo_demanda.getItems().add("Demanda en ventas");
        cb_tipo_demanda.getItems().add("Ninguna venta");
        cb_tipo_demanda.getSelectionModel().selectFirst();

    }

    private void handle_btn_visualizar_datos(ActionEvent event) {
        int sucursal=-1,tipoCarro=-1,pais=-1,tipoPago=-1;
        String fecha1="null",fecha2="null";
        if (cb_sucursal.getValue() != null) {
            sucursal = cb_sucursal.getValue().getIdSucursal();
        }

        if (cb_tipo_vehiculo.getValue() != null) {
            tipoCarro = cb_tipo_vehiculo.getValue().getID();
        }

        if (cb_pais.getValue() != null) {
            pais = Integer.parseInt(cb_pais.getValue().getID());
        }

        if (cb_tipo_pago.getValue() != null) {
            tipoPago = cb_tipo_pago.getValue().getIdMethod();
        }

        if (dt_fecha_inicial.getValue() != null) {
            fecha1 = dt_fecha_inicial.getValue().toString(); // en el query -> dt_fecha_inicial.getValue().toString();
        }

        if (dt_fecha_final.getValue() != null) {
            fecha2 = dt_fecha_final.getValue().toString(); // en el query -> dt_fecha_final.getValue().toString();
        }
        ventasObservableList.removeAll();   // limpiar la lista
        ventasObservableList = FXCollections.observableArrayList();
        ventasObservableList = GroupDBConnection.getDBInstance().SelectInfoVentas(sucursal,tipoCarro,pais,fecha1,fecha2,tipoPago);
        listView_ventas.setItems(ventasObservableList);
    }

    private void handle_btn_visualizar_datos_2(ActionEvent event) {
        int sucursal = -1;
        if (cb_sucursal_2.getValue() != null) {
            sucursal = cb_sucursal_2.getValue().getIdSucursal();
        }
        if(cb_tipo_demanda.getSelectionModel().getSelectedItem().equals("Demanda en ventas")){
            ventasDemandaObservableList.removeAll();
            ventasDemandaObservableList = FXCollections.observableArrayList();
            ventasDemandaObservableList = GroupDBConnection.getDBInstance().SelectVehiculosDemanda(sucursal);
            listView_vehiculos_demanda.setItems(ventasDemandaObservableList);
        }else{
            ventasDemandaObservableList.removeAll();
            ventasDemandaObservableList = FXCollections.observableArrayList();
            ventasDemandaObservableList = GroupDBConnection.getDBInstance().SelectVehiculosNoVendidos(sucursal);
            listView_vehiculos_demanda.setItems(ventasDemandaObservableList);
        }
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
