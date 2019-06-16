package controlador.administrador;

import com.github.fxrouter.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import modelo.Empleado;
import modelo.GroupDBConnection;
import modelo.Sucursal;

import java.io.IOException;

import static modelo.TipoUsuario.FACTURADOR;

public class C_GestionarEmpleados {

    @FXML private ListView<Empleado> ListView_Empleados;
    @FXML private ComboBox<Sucursal> cbx_Sucursal;
    @FXML private Button btn_InsertEmpleado;
    @FXML private Button btn_DeleteEmpleado;
    @FXML private Button btn_UpdateEmpleado;
    @FXML private Button btn_Atras;

    private ObservableList<Empleado> empleadosObservableList;
    private ObservableList<Sucursal> sucursalesObservablelist;

    public void initialize(){
        initComponentes();
        init_ListView_Empleados();
        cbx_Sucursal.valueProperty().addListener(new ChangeListener<Sucursal>(){
            @Override
            public void changed(ObservableValue<? extends Sucursal> observable, Sucursal oldValue, Sucursal newValue) {
                filtrarEmpleadosXSucursal();
            }
        });
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
        btn_InsertEmpleado.setOnAction(this::handle_btn_InsertEmpleado);
        btn_DeleteEmpleado.setOnAction(this::handle_btn_DeleteEmpleado);
        btn_UpdateEmpleado.setOnAction(this::handle_btn_UpdateEmpleado);
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
        }else{
            System.out.println("nada seleccionado");
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
        else{
            //MENSAJE DE QUE SELECCIONE EMPLEADO
        }
    }
    private void handle_btn_Atras(ActionEvent event) {
        try{
            FXRouter.goTo("Menu_administrador");
        }catch (IOException e) {
            e.printStackTrace();
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