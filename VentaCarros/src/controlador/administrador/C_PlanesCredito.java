package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import controlador.cliente.PlanDePagoListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.*;

import java.io.IOException;


public class C_PlanesCredito {

    @FXML JFXButton btn_atras;
    @FXML JFXButton btn_nuevo_credito;
    @FXML JFXButton btn_editar_credito;
    @FXML JFXButton btn_eliminar_credito;

    @FXML JFXListView listView_planes_pago;
    private ObservableList<PlanDePago> planesObservableList;

    private PlanDePago planDePago = null;


    public void initialize() throws Exception {
        initComponentes();
        init_listView_planes();
    }

    private void initComponentes() throws Exception {
        btn_nuevo_credito.setOnAction(this::handle_btn_nuevo_credito);
        btn_editar_credito.setOnAction(this::handle_btn_editar_credito);
        btn_eliminar_credito.setOnAction(this::handle_btn_eliminar_credito);
        btn_atras.setOnAction(this::handle_btn_atras);
    }

    private void init_listView_planes() {
        listView_planes_pago.setItems(GroupDBConnection.getDBInstance().getPlanesDePago());
        listView_planes_pago.setCellFactory(planListView -> new PlanDePagoListViewCell(TipoUsuario.ADMINISTRADOR));
    }

    private void handle_btn_nuevo_credito(ActionEvent event) {
        try {
            FXRouter.goTo("NuevoCredito_administrador", null); // nulo para saber que hacer en dicha pantalla
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handle_btn_editar_credito(ActionEvent event) {
        planDePago = (PlanDePago) listView_planes_pago.getSelectionModel().getSelectedItem();
        if (planDePago != null) {
            try {
                FXRouter.goTo("NuevoCredito_administrador", planDePago);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handle_btn_eliminar_credito(ActionEvent event) {
        planDePago = (PlanDePago) listView_planes_pago.getSelectionModel().getSelectedItem();
        if (planDePago != null) {
            GroupDBConnection.getDBInstance().deleteCreditPlan(planDePago.getPlanID());
           /* planesObservableList.remove(planDePago);
            listView_planes_pago.setItems(planesObservableList);*/
            listView_planes_pago.getItems().remove(planDePago);
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
