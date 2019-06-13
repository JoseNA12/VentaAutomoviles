package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.BranchOfficeDB_Connection;
import modelo.Vehiculo;

import java.io.IOException;

import static controlador.C_InicioSesion.tipoUsuarioActual;

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

        catalogoObservableList = FXCollections.observableArrayList();

        catalogoObservableList = BranchOfficeDB_Connection.getHSDBInstance().SelectAutosXSucursal(1);
        // ---------------------------------------------------------------
        // HACER LA CONSULTA A LAS BD's

        /*catalogoObservableList.addAll(
                new Vehiculo("1", "Nissan", "370z", "2012", "2", "deportivo",
                        "v8", "cuero", "2", "regular", "100", "290",
                        "9500000.00"),
                new Vehiculo("2", "Lamborghini", "Aventador Roadster. S", "2014", "2",
                        "deportivo", "v9", "tela y cuero", "2", "regular", "120",
                        "370", "270814559.98")
        );*/
        // ---------------------------------------------------------------

        listView_catalogo.setItems(catalogoObservableList);
        listView_catalogo.setCellFactory(studentListView -> new VehiculoListViewCell());
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            switch (tipoUsuarioActual) {
                case ADMINISTRADOR:

                    break;
                case FACTURADOR:
                    FXRouter.goTo("Menu_facturador");
                    break;
                case CLIENTE:
                    FXRouter.goTo("Menu_cliente");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
