package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo.Abono;
import modelo.PlanDePago;
import modelo.Usuario;

import java.io.IOException;


import static controlador.C_InicioSesion.usuarioActual;

public class C_Abono {

    @FXML JFXListView listView_abonos;
    @FXML JFXListView listView_plan_de_pago;

    private ObservableList<PlanDePago> plan_seleccionadoObservableList;
    private ObservableList<Abono> abonosObservableList;

    @FXML JFXComboBox cb_metodo_pago;

    @FXML JFXButton btn_realizar_abono;
    @FXML JFXButton btn_atras;
    @FXML JFXTextField tf_monto_a_pagar;

    @FXML Label lb_fecha_pago;
    @FXML Label lb_nombre_cliente;

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

        init_listView_plan_selecccionado(usuarioActual_);
        init_listView_abonos(usuarioActual_);
        lb_nombre_cliente.setText(usuarioActual_.getNombre() + " " + usuarioActual_.getApellidos());

    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_realizar_abono.setOnAction(this::handle_btn_realizar_abono);
        btn_atras.setOnAction(this::handle_btn_atras);
    }

    private void init_cb_metodo_pago() {
        // ---------------------------------------------------------------
        // HACER LA CONSULTA A LAS BB's
        // meter en un for la inserciones de los tipos, tal vez ?

        cb_metodo_pago.getItems().add("A");
    }

    private void init_listView_plan_selecccionado(Usuario usuarioActual) {
        plan_seleccionadoObservableList = FXCollections.observableArrayList();

        // ---------------------------------------------------------------
        // HACER LA CONSULTA A LAS BD's
        // Obtener el plan seleccionado por el usuario para pagar el carro actual
        // -> planDePago = ...
        // -> plan_seleccionadoObservableList.add(planDePago);

        plan_seleccionadoObservableList.add(
                new PlanDePago("Porcentaje 1", "600.000", "3 años", "8%",
                        "xxxxxxxxxxx")
        );
        // ---------------------------------------------------------------

        listView_plan_de_pago.setItems(plan_seleccionadoObservableList);
        listView_plan_de_pago.setCellFactory(planesListView -> new PlanDePagoListViewCell());
    }

    private void init_listView_abonos(Usuario usuarioActual) {
        abonosObservableList = FXCollections.observableArrayList();

        // ---------------------------------------------------------------
        // HACER LA CONSULTA A LAS BD's
        // Obtener los bonos realizados por el usuario

        abonosObservableList.add(
                new Abono("Porcentaje 1", "600.000", "3 años")
        );
        // ---------------------------------------------------------------

        listView_abonos.setItems(abonosObservableList);
        listView_abonos.setCellFactory(planesListView -> new AbonoListViewCell());
    }

    private void handle_btn_realizar_abono(ActionEvent event) {

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
