package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo.GroupDBConnection;
import modelo.PedidoVehiculo;
import modelo.PlanDePago;
import modelo.Vehiculo;

import java.io.IOException;
import static controlador.C_InicioSesion.usuarioActual;

public class C_SolicitarCredito {

    @FXML JFXListView listView_planes;
    @FXML JFXListView listView_planSeleccionado;

    private ObservableList<PlanDePago> planesObservableList;
    private ObservableList<PlanDePago> plan_seleccionadoObservableList;

    @FXML JFXButton btn_comprar;
    @FXML JFXButton btn_atras;

    @FXML Label lb_nombre_vehiculo;


    private PedidoVehiculo pedidoVehiculo = null;


    public void initialize() throws Exception {
        initComponentes();
        init_listView_planes();
    }

    private void initComponentes() throws Exception {
        pedidoVehiculo = (PedidoVehiculo) FXRouter.getData();

        lb_nombre_vehiculo.setText(pedidoVehiculo.getVehiculo().getNombre_carro());
        btn_comprar.setOnAction(this::handle_btn_comprar);
        btn_atras.setOnAction(this::handle_btn_atras);
    }

    private void init_listView_planes() {
        planesObservableList = FXCollections.observableArrayList();
        plan_seleccionadoObservableList = FXCollections.observableArrayList();

        plan_seleccionadoObservableList = GroupDBConnection.getDBInstance().getCreditPlan(pedidoVehiculo);
        planesObservableList.addAll(plan_seleccionadoObservableList);
        // ---------------------------------------------------------------

        listView_planes.setItems(planesObservableList);

        listView_planes.setCellFactory(planesListView -> new PlanDePagoListViewCell());
        listView_planSeleccionado.setCellFactory(planesListView -> new PlanDePagoListViewCell());

        // listener del listview de los planes
        listView_planes.getSelectionModel().selectedItemProperty().addListener((ov, old_val, new_val) -> {
            PlanDePago selectedItem = (PlanDePago) listView_planes.getSelectionModel().getSelectedItem();

            plan_seleccionadoObservableList = FXCollections.observableArrayList();
            plan_seleccionadoObservableList.add(selectedItem);
            listView_planSeleccionado.setItems(plan_seleccionadoObservableList);
        });
    }

    private void handle_btn_comprar(ActionEvent event) {
        GroupDBConnection.getDBInstance().comprarPorCredito(pedidoVehiculo, 1, (PlanDePago) listView_planes.getSelectionModel().getSelectedItem());
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("ConsultarVehiculo_cliente", pedidoVehiculo.getVehiculo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
