package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.*;

import java.io.IOException;
import java.security.acl.Group;

public class C_PedidosClientes {

    @FXML JFXButton btn_atras;
    @FXML JFXButton btn_enviar_a_produccion;

    @FXML JFXListView listView_pedidos_pendientes;
    private ObservableList<PedidoVehiculo> pedidos_pendientes_ObservableList;

    @FXML JFXListView listView_pedidos_atendidos;
    private ObservableList<PedidoVehiculo> pedidos_atendidos_ObservableList;

    @FXML JFXDatePicker dt_fecha_de_entrega;


    private PedidoVehiculo pedidoVehiculo = null;


    public void initialize() throws Exception {
        initComponentes();
        init_listView_pedidos_pendientes();
        init_listView_pedidos_atendidos();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_atras.setOnAction(this::handle_btn_atras);
        btn_enviar_a_produccion.setOnAction(this::handle_btn_enviar_a_produccion);
    }

    private void init_listView_pedidos_pendientes() {

        pedidos_pendientes_ObservableList = FXCollections.observableArrayList();

        // TODO: PENDIENTES

        listView_pedidos_pendientes.setItems(pedidos_pendientes_ObservableList);
        listView_pedidos_pendientes.setItems(GroupDBConnection.getDBInstance().getPedidoVehiculos());
        listView_pedidos_pendientes.setCellFactory(miLista -> new PedidoListViewCell());
    }

    private void init_listView_pedidos_atendidos() {
        pedidos_atendidos_ObservableList = FXCollections.observableArrayList();

        // TODO: ATENDIDOS

        listView_pedidos_atendidos.setItems(pedidos_atendidos_ObservableList);
        listView_pedidos_atendidos.setItems(GroupDBConnection.getDBInstance().getPedidoVehiculos());
        listView_pedidos_atendidos.setCellFactory(miLista -> new PedidoListViewCell());
    }

    private void handle_btn_enviar_a_produccion(ActionEvent event) {
        pedidoVehiculo = (PedidoVehiculo) listView_pedidos_pendientes.getSelectionModel().getSelectedItem();
        if (pedidoVehiculo != null && dt_fecha_de_entrega.getValue() != null) {
            pedidoVehiculo.setFechaEntrega(dt_fecha_de_entrega.getValue().toString());
            GroupDBConnection.getDBInstance().enviarPedidoVehiculo(pedidoVehiculo);
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
