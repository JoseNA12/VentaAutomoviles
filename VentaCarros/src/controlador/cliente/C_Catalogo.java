package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.BranchOfficeDB_Connection;
import modelo.GroupDBConnection;
import modelo.TipoUsuario;
import modelo.Vehiculo;

import java.io.IOException;


import static controlador.C_InicioSesion.usuarioActual;
import static controlador.C_InicioSesion.idSucursalActual;
public class C_Catalogo {

    @FXML JFXListView listView_catalogo;
    private ObservableList<Vehiculo> catalogoObservableList;

    @FXML JFXButton btn_atras;


    public void initialize() throws Exception {
        initComponentes();
        init_listView();
    }

    //Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_atras.setOnAction(this::handle_btn_atras);
    }

    private void init_listView() {
        //TODO: CAMBIAR IDSUCURSAL POR VARIABLE
        listView_catalogo.setItems(GroupDBConnection.getDBInstance().SelectAutosXSucursal(idSucursalActual));
        listView_catalogo.setCellFactory(studentListView -> new VehiculoListViewCell());
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            switch (usuarioActual.getTipoUsuario()) {
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
