package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import modelo.Empleado;
import modelo.GroupDBConnection;
import modelo.Sucursal;

import java.io.IOException;

import static modelo.Alerts.errorDialog;
import static modelo.Alerts.informationDialog;
import static modelo.TipoUsuario.FACTURADOR;

public class C_GestionarEmpleados {

    @FXML private JFXListView<Empleado> ListView_Empleados;
    @FXML private JFXComboBox<Sucursal> cbx_Sucursal;
    @FXML private JFXButton btn_InsertEmpleado;
    @FXML private JFXButton btn_DeleteEmpleado;
    @FXML private JFXButton btn_UpdateEmpleado;
    @FXML private JFXButton btn_Atras;
    @FXML private JFXButton btn_horarios;

    private ObservableList<Empleado> empleadosObservableList;
    private ObservableList<Sucursal> sucursalesObservablelist;

    @FXML Label lb_titulo_ventana;

    private Boolean esCliente = false;


    public void initialize(){
        initComponentes();
        init_ListView_Empleados();
        cbx_Sucursal.valueProperty().addListener(new ChangeListener<Sucursal>(){
            @Override
            public void changed(ObservableValue<? extends Sucursal> observable, Sucursal oldValue, Sucursal newValue) {
                filtrarEmpleadosXSucursal();
            }
        });

        if (esCliente) {
            btn_InsertEmpleado.setVisible(false);
            btn_DeleteEmpleado.setVisible(false);
            btn_UpdateEmpleado.setVisible(false);
            lb_titulo_ventana.setText("Sucursales y empleados registrados");
        }
    }

    private void init_cbx_Sucursal() {
        sucursalesObservablelist = FXCollections.observableArrayList();
        sucursalesObservablelist = GroupDBConnection.getDBInstance().getSucursales();
        cbx_Sucursal.setItems(sucursalesObservablelist);
    }

    private void init_ListView_Empleados() {
        empleadosObservableList = FXCollections.observableArrayList();

        empleadosObservableList = GroupDBConnection.getDBInstance().SelectEmpleados();
        ListView_Empleados.setItems(empleadosObservableList);
        ListView_Empleados.setCellFactory(empleadosListView -> new EmpleadoListViewCell());
    }

    private void initComponentes(){
        esCliente = (Boolean) FXRouter.getData();
        btn_InsertEmpleado.setOnAction(this::handle_btn_InsertEmpleado);
        btn_DeleteEmpleado.setOnAction(this::handle_btn_DeleteEmpleado);
        btn_UpdateEmpleado.setOnAction(this::handle_btn_UpdateEmpleado);
        btn_horarios.setOnAction(this::handle_btn_horarios);
        btn_Atras.setOnAction(this::handle_btn_Atras);
        init_cbx_Sucursal();
    }

    private void handle_btn_InsertEmpleado(ActionEvent event){
        try{
            FXRouter.goTo("Empleados_Agregar");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handle_btn_DeleteEmpleado(ActionEvent event){
        if(!ListView_Empleados.getSelectionModel().isEmpty()){
            Empleado empSeleccionado = ListView_Empleados.getSelectionModel().getSelectedItem();
            GroupDBConnection.getDBInstance().DeleteEmpleado(empSeleccionado);
            init_ListView_Empleados();
        } else {
            errorDialog("Error", "Empleado no selecionado", "Debe seleccionar un empleado para continuar");
        }
    }
    private void handle_btn_UpdateEmpleado(ActionEvent event){
        if(!ListView_Empleados.getSelectionModel().isEmpty()) {
            try {
                FXRouter.goTo("Empleados_Actualizar",ListView_Empleados.getSelectionModel().getSelectedItem());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            errorDialog("Error", "Empleado no selecionado", "Debe seleccionar un empleado para continuar");
        }
    }

    private void handle_btn_Atras(ActionEvent event) {
        try {
            if (esCliente) {
                FXRouter.goTo("Menu_cliente");
            } else {
                FXRouter.goTo("Menu_administrador");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_horarios(ActionEvent event) {
        if (cbx_Sucursal.getValue() != null) {
            informationDialog("Horarios", cbx_Sucursal.getValue().getNombreSucursal(),
                    "De Lunes a Domingo \n\n" +
                    "Hora de apertura: " + cbx_Sucursal.getValue().getHoraApertura() + "\n\n" +
                            "Hora de cierre: " + cbx_Sucursal.getValue().getHoraCierre());
        }
        else {
            errorDialog("Error", "Seleccione una sucursal",
                    "Para mostrar los horarios debe seleccionar una sucursal!");
        }
    }

    private void filtrarEmpleadosXSucursal(){
        ObservableList<Empleado> EmpleadosXSucursal = FXCollections.observableArrayList();
        int idSucursalSeleccionada = cbx_Sucursal.getSelectionModel().getSelectedItem().getIdSucursal();
        for(Empleado emp : empleadosObservableList){
            if(emp.getIdSucursal() == idSucursalSeleccionada){
                EmpleadosXSucursal.add(emp);
            }
        }
        ListView_Empleados.setItems(EmpleadosXSucursal);
        ListView_Empleados.setCellFactory(empleadosListView -> new EmpleadoListViewCell());
    }


}