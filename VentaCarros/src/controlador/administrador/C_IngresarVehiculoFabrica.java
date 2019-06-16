package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import controlador.cliente.ExtraVehiculoListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.*;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class C_IngresarVehiculoFabrica {

    @FXML Label lb_titulo_ventana;

    @FXML ImageView iv_imagen_vehiculo;

    @FXML JFXComboBox<Fabrica> cb_fabrica;
    @FXML JFXComboBox<Marca> cb_marca;
    @FXML JFXComboBox<TipoVehiculo> cb_tipo;
    @FXML JFXComboBox<TipoCombustible> cb_gasolina;

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
    @FXML JFXTextField tf_cantidad_vehiculos;

    @FXML JFXButton btn_atras;
    @FXML JFXButton btn_subir_imagen;
    @FXML JFXButton btn_registrar;
    @FXML JFXButton btn_aniadir_extra; // extras
    @FXML JFXButton btn_remover_extra; // extras

    @FXML JFXListView listView_extras; // extras
    private ObservableList<ExtraVehiculo> extrasVehiculoObservableList;

    final FileChooser fileChooser = new FileChooser();

    private Vehiculo vehiculoSeleccionado = null; // solo en caso de modificar vehiculo


    public void initialize() throws Exception {
        initComponentes();
        init_listView_extras();
        init_cb_fabrica();
        init_cb_marca();
        init_cb_tipo();
        init_cb_gasolina();

        // la pantalla previa a esta envia el objeto Vehiculo (VehiculosFabrica), en caso de modificar eso si
        // caso contrario es null y se selecionó ingresar nuevo vehiculo
        vehiculoSeleccionado = (Vehiculo) FXRouter.getData();

        if (vehiculoSeleccionado != null) { // se va a modificar un vehiculo
            initComponentes_ventanaModificar();
        }
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_subir_imagen.setOnAction(this::handle_btn_subir_imagen);
        btn_registrar.setOnAction(this::handle_btn_registrar);
        btn_aniadir_extra.setOnAction(this::handle_btn_aniadir_extra);
        btn_remover_extra.setOnAction(this::handle_btn_remover_extra);
        btn_atras.setOnAction(this::handle_btn_atras);

    }

    /**
     *  Cuando se va a modificar hay que inicializar con valores los componentes de la UI
     *  segun el vehiculo seleccionado
     */
    private void initComponentes_ventanaModificar() {
        lb_titulo_ventana.setText("Edición: " + vehiculoSeleccionado.getNombre_carro());
        tf_modelo.setText(vehiculoSeleccionado.getModelo());
        tf_anio.setText(vehiculoSeleccionado.getAnio());
        tf_num_pasajeros.setText(vehiculoSeleccionado.getNum_pasajeros());
        tf_motor.setText(vehiculoSeleccionado.getMotor());
        tf_asientos.setText(vehiculoSeleccionado.getNum_pasajeros());
        tf_puertas.setText(vehiculoSeleccionado.getPuertas());
        tf_aceleracion.setText(vehiculoSeleccionado.getAceleracion());
        tf_vel_maxima.setText(vehiculoSeleccionado.getVel_maxima());
        tf_precio.setText(vehiculoSeleccionado.getPrecio());
        tf_cantidad_vehiculos.setText(vehiculoSeleccionado.getCantidad_en_fabrica());

        // obtener el valor del vehiculo, y buscar dentro de los valores del comboBox el item respectivo
        // para hacer el set del objeto
        ObservableList<Marca> items_m = cb_marca.getItems();
        for(Marca item : items_m) {
            if (item.getNombre().equals(vehiculoSeleccionado.getMarca())) {
                cb_marca.getSelectionModel().select(item);
            }
        }

        ObservableList<TipoVehiculo> items_t = cb_tipo.getItems();
        for(TipoVehiculo item : items_t) {
            if (item.getTipo().equals(vehiculoSeleccionado.getMarca())) {
                cb_tipo.getSelectionModel().select(item);
            }
        }

        ObservableList<TipoCombustible> items_g = cb_gasolina.getItems();
        for(TipoCombustible item : items_g) {
            if (item.getTipo().equals(vehiculoSeleccionado.getMarca())) {
                cb_gasolina.getSelectionModel().select(item);
            }
        }

        // faltan los extras
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
        ArrayList<Fabrica> fabricas = GroupDBConnection.getDBInstance().getFactory();
        for (int i=0;i<fabricas.size();i++){
            cb_fabrica.getItems().add(fabricas.get(i));
        }
    }

    private void init_cb_marca() {
        ArrayList<Marca> marcas = GroupDBConnection.getDBInstance().getCarBrands();
        for (int i=0;i<marcas.size();i++){
            cb_marca.getItems().add(marcas.get(i));
        }
    }

    private void init_cb_tipo() {
        ArrayList<TipoVehiculo> tipos = GroupDBConnection.getDBInstance().getCarType();
        for (int i=0;i<tipos.size();i++){
            cb_tipo.getItems().add(tipos.get(i));
        }
    }

    private void init_cb_gasolina() {
        ArrayList<TipoCombustible> tipos = GroupDBConnection.getDBInstance().getFuelType();
        for (int i=0;i<tipos.size();i++){
            cb_gasolina.getItems().add(tipos.get(i));
        }
    }

    private void init_listView_extras() {
        extrasVehiculoObservableList = FXCollections.observableArrayList();
        listView_extras.setItems(extrasVehiculoObservableList);
        listView_extras.setCellFactory(studentListView -> new ExtraVehiculoListViewCell());
    }

    private void handle_btn_subir_imagen(ActionEvent event) {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog((Stage)((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            iv_imagen_vehiculo.setImage(new Image(file.toURI().toString()));
        }
    }

    private void handle_btn_registrar(ActionEvent event) {
        if (vehiculoSeleccionado != null) { // pantalla de modificar, actualizar los datos

        }
        else { // ingresar un nuevo vehiculo
            vehiculoSeleccionado = new Vehiculo(0, cb_marca.getSelectionModel().getSelectedItem(), tf_modelo.getText(), tf_anio.getText(),
                    tf_num_pasajeros.getText(), cb_tipo.getSelectionModel().getSelectedItem(), tf_motor.getText(), tf_puertas.getText(),
                    cb_gasolina.getSelectionModel().getSelectedItem(), tf_aceleracion.getText(), tf_vel_maxima.getText(), tf_precio.getText());
            if (GroupDBConnection.getDBInstance().crearNuevoVehiculo(vehiculoSeleccionado, cb_fabrica.getSelectionModel().getSelectedItem()) == 0){

            }else{

            }
        }

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

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("VehiculosFabricas_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
