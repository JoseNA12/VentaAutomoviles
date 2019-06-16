package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import controlador.cliente.VehiculoListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import modelo.Fabrica;
import modelo.GroupDBConnection;
import modelo.TipoUsuario;
import modelo.Vehiculo;

import java.io.IOException;
import java.util.ArrayList;

public class C_VehiculosFabricas {

    @FXML JFXButton btn_nuevo_ingreso;
    @FXML JFXButton btn_editar_caracteristicas;
    @FXML JFXButton btn_ingresar_a_sucursales;
    @FXML JFXButton btn_atras;

    @FXML JFXComboBox cb_filtrar_por_fabrica;

    @FXML JFXListView listView_catalogo;
    private ObservableList<Vehiculo> catalogoObservableList;

    private Vehiculo vehiculoSeleccionado = null;


    public void initialize() throws Exception {
        initComponentes();
        init_listView();
        init_cb_filtrar_por_fabrica();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_atras.setOnAction(this::handle_btn_atras);
        btn_nuevo_ingreso.setOnAction(this::handle_btn_nuevo_ingreso);
        btn_editar_caracteristicas.setOnAction(this::handle_btn_editar_caracteristicas);
        btn_ingresar_a_sucursales.setOnAction(this::handle_btn_ingresar_a_sucursales);
    }

    private void init_cb_filtrar_por_fabrica() {
        cb_filtrar_por_fabrica.setItems(GroupDBConnection.getDBInstance().getFactory());
        // listener del combo box, cuando se selecciona un elemento hacer un trigger con una accion
        cb_filtrar_por_fabrica.setOnAction(this::handle_cb_filtrar_por_fabrica);
    }

    private void init_listView() {
        catalogoObservableList = GroupDBConnection.getDBInstance().getCarrosDeFabrica();
        listView_catalogo.setItems(catalogoObservableList);
        listView_catalogo.setCellFactory(studentListView -> new VehiculoListViewCell(TipoUsuario.ADMINISTRADOR));

        // listener del listview de los planes
        listView_catalogo.getSelectionModel().selectedItemProperty().addListener((ov, old_val, new_val) -> {
            vehiculoSeleccionado = (Vehiculo) listView_catalogo.getSelectionModel().getSelectedItem();
        });
    }

    private void handle_btn_nuevo_ingreso(ActionEvent event) {
        try {
            FXRouter.goTo("IngresarVehiculoFabrica_administrador", null); // null -> no voy a modificar vehiculo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_editar_caracteristicas(ActionEvent event) {
        if (vehiculoSeleccionado != null) {
            try {
                FXRouter.goTo("IngresarVehiculoFabrica_administrador", vehiculoSeleccionado);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handle_btn_ingresar_a_sucursales(ActionEvent event) {
        if (vehiculoSeleccionado != null) {
            try {
                FXRouter.goTo("IngresarVehiculoSucursal_administrador", vehiculoSeleccionado);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handle_cb_filtrar_por_fabrica(Event event) {
        System.out.println(cb_filtrar_por_fabrica.getValue());
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("Menu_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
