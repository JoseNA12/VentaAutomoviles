package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo.ExtraVehiculo;
import modelo.GroupDBConnection;
import modelo.Vehiculo;

import java.io.IOException;

public class C_ConsultarVehiculo {

    @FXML Label lb_nombre_carro;
    @FXML Label lb_marca;
    @FXML Label lb_modelo;
    @FXML Label lb_anio;
    @FXML Label lb_num_pasajeros;
    @FXML Label lb_tipo;
    @FXML Label lb_motor;
    @FXML Label lb_asientos;
    @FXML Label lb_puertas;
    @FXML Label lb_gasolina;
    @FXML Label lb_aceleracion;
    @FXML Label lb_vel_maxima;
    @FXML Label lb_precio;
    @FXML Label lb_precio_total;
    @FXML Label lb_total_extras;

    @FXML JFXListView lv_extras;
    @FXML JFXListView lv_extras_seleccionadas;

    private ObservableList<ExtraVehiculo> extrasObservableList;
    private ObservableList<ExtraVehiculo> extras_seleccionadasObservableList;

    @FXML JFXButton btn_atras;
    @FXML JFXButton btn_comprar;
    @FXML JFXButton btn_agregar_extra;
    @FXML JFXButton btn_quitar_extra;

    @FXML JFXComboBox cb_metodo_pago;

    private Vehiculo vehiculo_seleccionado = null;

    private double montoTotal = 0.0;
    private double montoTotalExtras = 0.0;


    public void initialize() throws Exception {
        initComponentes();
        init_listView_extras();
        init_cb_metodo_pago();
    }

    private void initComponentes() throws Exception {
        btn_comprar.setOnAction(this::handle_btn_comprar);
        btn_agregar_extra.setOnAction(this::handle_btn_agregar_extra);
        btn_quitar_extra.setOnAction(this::handle_btn_quitar_extra);
        btn_atras.setOnAction(this::handle_btn_atras);

        vehiculo_seleccionado = (Vehiculo) FXRouter.getData(); // la pantalla previa a esta envia el objeto Vehiculo
        lb_nombre_carro.setText(vehiculo_seleccionado.getNombre_carro());
        lb_marca.setText(vehiculo_seleccionado.getMarca());
        lb_modelo.setText(vehiculo_seleccionado.getModelo());
        lb_anio.setText(vehiculo_seleccionado.getAnio());
        lb_num_pasajeros.setText(vehiculo_seleccionado.getNum_pasajeros());
        lb_tipo.setText(vehiculo_seleccionado.getTipo());
        lb_motor.setText(vehiculo_seleccionado.getMotor());
        lb_asientos.setText(vehiculo_seleccionado.getAsientos());
        lb_puertas.setText(vehiculo_seleccionado.getPuertas());
        lb_gasolina.setText(vehiculo_seleccionado.getGasolina());
        lb_aceleracion.setText(vehiculo_seleccionado.getAceleracion());
        lb_vel_maxima.setText(vehiculo_seleccionado.getVel_maxima());
        lb_precio.setText(vehiculo_seleccionado.getPrecio());

        montoTotal = Double.parseDouble(vehiculo_seleccionado.getPrecio());
        lb_precio_total.setText(String.valueOf(montoTotal));
        lb_total_extras.setText("0.0");
    }

    private void init_listView_extras() {
        extrasObservableList = FXCollections.observableArrayList();
        extras_seleccionadasObservableList = FXCollections.observableArrayList();

        // ---------------------------------------------------------------
        // HACER LA CONSULTA A LAS BB's
        // ->>>> vehiculo_seleccionado.getID();

        /*extrasObservableList.addAll(
                new ExtraVehiculo("Extra 1", "600.000"),
                new ExtraVehiculo("Extra 2", "210.000"),
                new ExtraVehiculo("Extra 3", "10.000"),
                new ExtraVehiculo("Extra 4", "50.000")
        );*/
        extrasObservableList = GroupDBConnection.getDBInstance().getCarAccessories(vehiculo_seleccionado.getID());
        // ---------------------------------------------------------------

        lv_extras.setItems(extrasObservableList);

        lv_extras.setCellFactory(extrasListView -> new ExtraVehiculoListViewCell());
        lv_extras_seleccionadas.setCellFactory(extrasListView -> new ExtraVehiculoListViewCell());
    }

    private void init_cb_metodo_pago() {
        // ---------------------------------------------------------------
        // HACER LA CONSULTA A LAS BB's
        // meter en un for la inserciones de los tipos, tal vez ?

        cb_metodo_pago.getItems().add("A");
    }

    private void handle_btn_comprar(ActionEvent event) {

    }

    private void handle_btn_agregar_extra(ActionEvent event) {
        ExtraVehiculo seleccion = (ExtraVehiculo) lv_extras.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            extrasObservableList.remove(seleccion); // elimino el item de la lista observable
            lv_extras.setItems(extrasObservableList); // actualizao la lista

            extras_seleccionadasObservableList.add(seleccion);  // añado el item a la lista de seleccionados
            lv_extras_seleccionadas.setItems(extras_seleccionadasObservableList); // actualizo la lista

            // actualizar montos totales y sus labels
            montoTotal += Double.parseDouble(seleccion.getPrecio());
            montoTotalExtras += Double.parseDouble(seleccion.getPrecio());
            lb_precio_total.setText(String.valueOf(montoTotal));
            lb_total_extras.setText(String.valueOf(montoTotalExtras));
        }
    }

    private void handle_btn_quitar_extra(ActionEvent event) {
        ExtraVehiculo seleccion = (ExtraVehiculo) lv_extras_seleccionadas.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            extras_seleccionadasObservableList.remove(seleccion);
            lv_extras_seleccionadas.setItems(extras_seleccionadasObservableList);

            extrasObservableList.add(seleccion);
            lv_extras.setItems(extrasObservableList);

            montoTotal -= Double.parseDouble(seleccion.getPrecio());
            montoTotalExtras -= Double.parseDouble(seleccion.getPrecio());
            lb_precio_total.setText(String.valueOf(montoTotal));
            lb_total_extras.setText(String.valueOf(montoTotalExtras));
        }
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("Catalogo_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
