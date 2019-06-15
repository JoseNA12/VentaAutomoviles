package controlador.administrador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import controlador.cliente.ExtraVehiculoListViewCell;
import controlador.cliente.VehiculoListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.*;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class C_IngresarVehiculoFabrica {

    @FXML ImageView iv_imagen_vehiculo;

    @FXML JFXComboBox cb_fabrica;
    @FXML JFXComboBox cb_marca;
    @FXML JFXComboBox cb_tipo;
    @FXML JFXComboBox cb_gasolina;

    @FXML JFXTextField tf_modelo;
    @FXML JFXTextField tf_anio;
    @FXML JFXTextField tf_num_pasajeros;
    @FXML JFXTextField tf_motor;
    @FXML JFXTextField tf_asientos;
    @FXML JFXTextField tf_puertas;
    @FXML JFXTextField tf_aceleracion;
    @FXML JFXTextField tf_vel_maxima;
    @FXML JFXTextField tf_precio;
    @FXML JFXTextField tf_nombre_extra; // extras
    @FXML JFXTextField tf_precio_extra; // extras

    @FXML JFXButton btn_subir_imagen;
    @FXML JFXButton btn_registrar;
    @FXML JFXButton btn_aniadir_extra; // extras
    @FXML JFXButton  btn_remover_extra; // extras

    @FXML JFXListView listView_extras; // extras
    private ObservableList<ExtraVehiculo> extrasVehiculoObservableList;

    final FileChooser fileChooser = new FileChooser();


    public void initialize() throws Exception {
        initComponentes();
        init_listView_extras();
        init_cb_fabrica();
        init_cb_marca();
        init_cb_tipo();
        init_cb_gasolina();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_subir_imagen.setOnAction(this::handle_bnt_subir_imagen);
        btn_registrar.setOnAction(this::handle_btn_registrar);
        btn_aniadir_extra.setOnAction(this::handle_btn_aniadir_extra);
        btn_remover_extra.setOnAction(this::handle_btn_remover_extra);

        btn_subir_imagen.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        configureFileChooser(fileChooser);
                        File file = fileChooser.showOpenDialog((Stage)((Node) e.getSource()).getScene().getWindow());
                        if (file != null) {
                            iv_imagen_vehiculo.setImage(new Image(file.toURI().toString()));

                        }
                    }
                });
    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Selecionar imagen del vehículo");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
               new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    private void init_cb_fabrica() {
        // -------------------- query a la BD

        //cb_filtrar_por_sucursal.setItems(GroupDBConnection.getDBInstance().getPaymentMethods());
        //cb_filtrar_por_sucursal.getSelectionModel().selectFirst(); //select the first element

        //-----------------------------------

        ArrayList<Fabrica> fabricas = GroupDBConnection.getDBInstance().getFactory();
        for (int i=0;i<fabricas.size();i++){
            cb_fabrica.getItems().add(fabricas.get(i).getNombre());
        }
    }

    private void init_cb_marca() {
        // -------------------- query a la BD
        // objeto: Marca

        //cb_filtrar_por_sucursal.setItems(GroupDBConnection.getDBInstance().getPaymentMethods());
        //cb_filtrar_por_sucursal.getSelectionModel().selectFirst(); //select the first element

        //-----------------------------------
        ArrayList<Marca> marcas = GroupDBConnection.getDBInstance().getCarBrands();
        for (int i=0;i<marcas.size();i++){
            cb_marca.getItems().add(marcas.get(i).getNombre());
        }


    }

    private void init_cb_tipo() {
        // -------------------- query a la BD
        // objeto: TipoVehiculo

        //cb_filtrar_por_sucursal.setItems(GroupDBConnection.getDBInstance().getPaymentMethods());
        //cb_filtrar_por_sucursal.getSelectionModel().selectFirst(); //select the first element

        //-----------------------------------
        ArrayList<TipoVehiculo> tipos = GroupDBConnection.getDBInstance().getCarType();
        for (int i=0;i<tipos.size();i++){
            cb_tipo.getItems().add(tipos.get(i).getTipo());
        }
    }

    private void init_cb_gasolina() {
        // -------------------- query a la BD
        // objeto: TipoGasolina

        //cb_filtrar_por_sucursal.setItems(GroupDBConnection.getDBInstance().getPaymentMethods());
        //cb_filtrar_por_sucursal.getSelectionModel().selectFirst(); //select the first element

        //-----------------------------------

        ArrayList<TipoGasolina> tipos = GroupDBConnection.getDBInstance().getFuelType();
        for (int i=0;i<tipos.size();i++){
            cb_gasolina.getItems().add(tipos.get(i).getTipo());
        }
    }

    private void init_listView_extras() {
        extrasVehiculoObservableList = FXCollections.observableArrayList();

        listView_extras.setItems(extrasVehiculoObservableList);
        listView_extras.setCellFactory(studentListView -> new ExtraVehiculoListViewCell());
    }

    private void handle_bnt_subir_imagen(ActionEvent event) {

    }

    private void handle_btn_registrar(ActionEvent event) {

    }

    private void handle_btn_aniadir_extra(ActionEvent event) {
        if (!tf_nombre_extra.getText().trim().equals("") && !tf_precio_extra.getText().trim().equals("")) {
            extrasVehiculoObservableList.add(           // añado el item a la lista de seleccionados
                    new ExtraVehiculo(0, tf_nombre_extra.getText(), tf_precio_extra.getText()));
            listView_extras.setItems(extrasVehiculoObservableList); // actualizo la lista
        }
    }

    private void handle_btn_remover_extra(ActionEvent event) {
        ExtraVehiculo seleccion = (ExtraVehiculo) listView_extras.getSelectionModel().getSelectedItem();

        if (seleccion != null) {
            extrasVehiculoObservableList.remove(seleccion); // elimino el item de la lista observable
            listView_extras.setItems(extrasVehiculoObservableList); // actualizao la lista
        }
    }
}
