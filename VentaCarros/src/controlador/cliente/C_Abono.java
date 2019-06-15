package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import modelo.*;

import java.io.IOException;


import static controlador.C_InicioSesion.usuarioActual;

public class C_Abono {

    @FXML JFXListView listView_abonos;
    @FXML JFXListView listView_plan_de_pago;

    private ObservableList<PlanDePago> plan_seleccionadoObservableList;
    private ObservableList<Abono> abonosObservableList;
    private ObservableList<MetodoPago> metodoPagoObservableList;

    @FXML JFXComboBox cb_metodo_pago;

    @FXML JFXButton btn_realizar_abono;
    @FXML JFXButton btn_atras;
    @FXML JFXTextField tf_monto_a_pagar;
    @FXML Label lb_fecha_pago;
    @FXML Label lb_nombre_cliente;

    private int IdCreditoActual;
    private PlanDePago planDePago;

    Usuario usuarioActual_ = null;


    public void initialize() throws Exception {
        initComponentes();
        init_cb_metodo_pago();

        switch (usuarioActual.getTipoUsuario()) {
            case CLIENTE:
                usuarioActual_ = usuarioActual;
                break;
            case FACTURADOR:
                usuarioActual_ = (Usuario) FXRouter.getData(); // C_Menu envia este parametro al cambiar de pantalla
                break;
            default:
                usuarioActual_ = new Usuario(); // evitar errores, solo para prevenir
        }
        init_listView_abonos(usuarioActual_);
        init_listView_plan_selecccionado(usuarioActual_);
        lb_nombre_cliente.setText(usuarioActual_.getNombre() + " " + usuarioActual_.getApellidos());

    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_realizar_abono.setOnAction(this::handle_btn_realizar_abono);
        btn_atras.setOnAction(this::handle_btn_atras);
    }

    private void init_cb_metodo_pago() {
        metodoPagoObservableList = FXCollections.observableArrayList();
        metodoPagoObservableList = GroupDBConnection.getDBInstance().SelectMetodosDePago();
        cb_metodo_pago.setItems(metodoPagoObservableList);
    }

    private void init_listView_plan_selecccionado(Usuario usuarioActual) {
        plan_seleccionadoObservableList = FXCollections.observableArrayList();

        plan_seleccionadoObservableList = GroupDBConnection.getDBInstance().SelectPlanActual(IdCreditoActual);
        if(!plan_seleccionadoObservableList.isEmpty()){
            planDePago = plan_seleccionadoObservableList.get(plan_seleccionadoObservableList.size()-1);
        }
        listView_plan_de_pago.setItems(plan_seleccionadoObservableList);
        listView_plan_de_pago.setCellFactory(planesListView -> new PlanDePagoListViewCell());
    }

    private void init_listView_abonos(Usuario usuarioActual) {
        abonosObservableList = FXCollections.observableArrayList();
        abonosObservableList = GroupDBConnection.getDBInstance().SelectAbonoXUsuario(usuarioActual);
        if(abonosObservableList.isEmpty()){
            lb_fecha_pago.setText("No posee deudas");
        }
        else {
            lb_fecha_pago.setText(abonosObservableList.get(abonosObservableList.size() - 1).getfechaProximoPago());
            IdCreditoActual = Integer.parseInt(abonosObservableList.get(abonosObservableList.size() - 1).getIdPlan());
        }
        listView_abonos.setItems(abonosObservableList);
        listView_abonos.setCellFactory(planesListView -> new AbonoListViewCell());
    }

    private void handle_btn_realizar_abono(ActionEvent event) {
        if (!tf_monto_a_pagar.getText().trim().equals("")){
            MetodoPago MetodoPagoAux = (MetodoPago) cb_metodo_pago.getSelectionModel().getSelectedItem();
            GroupDBConnection.getDBInstance().InsertAbono(IdCreditoActual,Float.parseFloat(tf_monto_a_pagar.getText()),MetodoPagoAux.getIdMethod());
        }
        else{

        }
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            switch (usuarioActual.getTipoUsuario()) {
                case CLIENTE:
                    FXRouter.goTo("Menu_cliente");
                    break;
                case FACTURADOR:
                    FXRouter.goTo("Menu_facturador");

                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
