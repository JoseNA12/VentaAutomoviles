package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo.SucursalListViewCell;
import modelo.Vehiculo;
import modelo.VehiculoListViewCell;

import java.io.IOException;

public class C_Catalogo {

    @FXML JFXListView listView_catalogo;
    private ObservableList<Vehiculo> catalogoObservableList;

    @FXML JFXButton btn_atras;


    public void initialize() throws Exception {
        initComponentes();
        init_listView();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_atras.setOnAction(this::handle_btn_atras);
    }

    private void init_listView() {
        //listView_catalogo.getStyleClass().add("mylistview");

        //listView_catalogo.getItems().add(new Label("Item"));
        catalogoObservableList = FXCollections.observableArrayList();

        // ---------------------------------------------------------------
        // HACER LA CONSULTA A LAS BB's

        catalogoObservableList.addAll(
                new Vehiculo("John Doe", "1997"),
                new Vehiculo("Jane Doe", "1997"),
                new Vehiculo("Donte Dunigan", "1998"),
                new Vehiculo("Gavin Genna", "1997"),
                new Vehiculo("Darin Dear", "1995"),
                new Vehiculo("Pura Petty", "1996"),
                new Vehiculo("Herma Hines", "2019")
        );
        // ---------------------------------------------------------------

        listView_catalogo.setItems(catalogoObservableList);
        listView_catalogo.setCellFactory(studentListView -> new VehiculoListViewCell());
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("Menu_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
