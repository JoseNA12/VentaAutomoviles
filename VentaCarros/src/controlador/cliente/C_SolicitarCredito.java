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
import modelo.*;

import java.io.IOException;
import java.util.Calendar;

import static controlador.C_InicioSesion.usuarioActual;
import static controlador.C_InicioSesion.idSucursalActual;
public class C_SolicitarCredito {

    @FXML JFXListView listView_planes;
    @FXML JFXListView listView_planSeleccionado;

    private ObservableList<PlanDePago> planesObservableList;
    private ObservableList<PlanDePago> plan_seleccionadoObservableList;

    @FXML JFXButton btn_comprar;
    @FXML JFXButton btn_atras;

    @FXML Label lb_nombre_vehiculo;


    private VehiculoComprado vehiculoComprado = null;


    public void initialize() throws Exception {
        initComponentes();
        init_listView_planes();
    }

    private void initComponentes() throws Exception {
        vehiculoComprado = (VehiculoComprado) FXRouter.getData();

        lb_nombre_vehiculo.setText(vehiculoComprado.getVehiculo().getNombre_carro());
        btn_comprar.setOnAction(this::handle_btn_comprar);
        btn_atras.setOnAction(this::handle_btn_atras);
    }

    private void init_listView_planes() {
        planesObservableList = FXCollections.observableArrayList();
        plan_seleccionadoObservableList = FXCollections.observableArrayList();

        plan_seleccionadoObservableList = GroupDBConnection.getDBInstance().getCreditPlan(vehiculoComprado);
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
        GroupDBConnection.getDBInstance().comprarPorCredito(vehiculoComprado, idSucursalActual, (PlanDePago) listView_planes.getSelectionModel().getSelectedItem());
        Alerts.informationDialog("Crédito brindado!","Crédito dado","Se le ha brindado el crédito en la compra");
        try {
            SendEmail.sendEmail(vehiculoComprado.getUsuario().getCorreo(),
                    SendEmail.getCuerpo(vehiculoComprado.getUsuario(), String.valueOf(vehiculoComprado.getPrecioTotal()),
                            vehiculoComprado.getMetodoPago().getName(), Calendar.getInstance().toString()));

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

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("ConsultarVehiculo_cliente", vehiculoComprado.getVehiculo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
