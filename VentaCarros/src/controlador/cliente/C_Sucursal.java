package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.GroupDBConnection;
import modelo.Sucursal;

import java.io.IOException;

public class C_Sucursal {

    @FXML JFXListView listView_sucursales;
    private ObservableList<Sucursal> sucursalesObservableList;

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
        sucursalesObservableList = GroupDBConnection.getDBInstance().getSucursales();
        listView_sucursales.setItems(sucursalesObservableList);
        listView_sucursales.setCellFactory(studentListView -> new SucursalListViewCell());
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("Menu_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
