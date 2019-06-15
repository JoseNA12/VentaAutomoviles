package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import controlador.cliente.VehiculoListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.GroupDBConnection;
import modelo.Vehiculo;

import java.io.IOException;

import static controlador.C_InicioSesion.usuarioActual;

public class C_VehiculosFabricas {

    @FXML JFXButton btn_nuevo_ingreso;
    @FXML JFXButton bnt_editar_caracteristicas;
    @FXML JFXButton btn_atras;

    @FXML JFXComboBox cb_filtrar_por_sucursal;

    @FXML JFXListView listView_catalogo;
    private ObservableList<Vehiculo> catalogoObservableList;

    private Vehiculo vehiculoSeleccionado = null;


    public void initialize() throws Exception {
        initComponentes();
        init_listView();
        init_cb_filtrar_por_sucursal();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_atras.setOnAction(this::handle_btn_atras);
        btn_nuevo_ingreso.setOnAction(this::handle_btn_nuevo_ingreso);
        bnt_editar_caracteristicas.setOnAction(this::handle_bnt_editar_caracteristicas);
    }

    private void init_cb_filtrar_por_sucursal() {
        // -------------------- query a la BD

        //cb_filtrar_por_sucursal.setItems(GroupDBConnection.getDBInstance().getPaymentMethods());
        //cb_filtrar_por_sucursal.getSelectionModel().selectFirst(); //select the first element

        //-----------------------------------

        cb_filtrar_por_sucursal.getItems().add("A");
    }

    private void init_listView() {
        //TODO: CAMBIAR IDSUCURSAL POR VARIABLE
        catalogoObservableList = FXCollections.observableArrayList();
        catalogoObservableList = GroupDBConnection.getDBInstance().SelectAutosXSucursal(1);
        listView_catalogo.setItems(catalogoObservableList);
        listView_catalogo.setCellFactory(studentListView -> new VehiculoListViewCell(true));

        // listener del listview de los planes
        listView_catalogo.getSelectionModel().selectedItemProperty().addListener((ov, old_val, new_val) -> {
            vehiculoSeleccionado = (Vehiculo) listView_catalogo.getSelectionModel().getSelectedItem();
        });
    }

    private void handle_btn_nuevo_ingreso(ActionEvent event) {
        try {
            FXRouter.goTo("IngresarVehiculo_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_bnt_editar_caracteristicas(ActionEvent event) {
        if (vehiculoSeleccionado != null) {

        }
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("Menu_administrador");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
