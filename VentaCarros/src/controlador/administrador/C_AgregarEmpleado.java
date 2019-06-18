package controlador.administrador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.*;

import java.io.IOException;
import java.time.LocalDate;

import static modelo.Alerts.errorDialog;
import static modelo.Alerts.informationDialog;

public class C_AgregarEmpleado {

    @FXML private JFXComboBox<Sucursal> cbx_sucursal;
    @FXML private JFXTextField txt_nombre;
    @FXML private JFXTextField txt_apellidos;
    @FXML private JFXTextField txt_telefono;
    @FXML private JFXTextField txt_correo;
    @FXML private JFXDatePicker date_fechaIngreso;
    @FXML private JFXComboBox<PuestoTrabajo> cbx_position;
    @FXML private JFXComboBox<String> cbx_tipoUsuario;
    @FXML private JFXTextField txt_cedula;
    @FXML private JFXPasswordField txt_contrania;
    @FXML private JFXButton btn_agregar;
    @FXML private JFXButton btn_atras;

    private ObservableList<Sucursal> sucursalesObservablelist;
    private ObservableList<PuestoTrabajo> puestosObservableList;
    private ObservableList<String> tipoUsuarioObservableList;

    public void initialize(){
        initcomponents();
    }

    private void initcomponents() {
        btn_agregar.setOnAction(this::handle_btn_agregar);
        btn_atras.setOnAction(this::handle_btn_atras);
        date_fechaIngreso.setValue(LocalDate.now());
        init_sucursales();
        init_puestos();
        init_tipoUsuario();
    }

    private void init_sucursales() {
        sucursalesObservablelist = FXCollections.observableArrayList();
        sucursalesObservablelist = GroupDBConnection.getDBInstance().getSucursales();
        cbx_sucursal.setItems(sucursalesObservablelist);
    }
    private void init_puestos() {
        puestosObservableList = FXCollections.observableArrayList();
        puestosObservableList.addAll(new PuestoTrabajo(1,"Gerente", "Gerente de empresa"),
                new PuestoTrabajo(2,"Cajero", "Atender la caja de la sucursal"),
                new PuestoTrabajo(3,"Inspector", "Revisión de procesos"),
                new PuestoTrabajo(4,"Mecánico", "Técnico en mecánica automotriz"));
        cbx_position.setItems(puestosObservableList);
    }

    private void init_tipoUsuario() {
        tipoUsuarioObservableList = FXCollections.observableArrayList();
        tipoUsuarioObservableList.addAll("Administrador","Facturador");
        cbx_tipoUsuario.setItems(tipoUsuarioObservableList);
    }

    private void handle_btn_agregar(ActionEvent actionEvent) {
        if(!txt_nombre.getText().equals("") && !txt_apellidos.getText().equals("")&& !txt_telefono.getText().equals("")&& !txt_correo.getText().equals("")&& !txt_cedula.getText().equals("")&& !txt_contrania.getText().equals("")
            && !cbx_position.getSelectionModel().isEmpty() && !cbx_sucursal.getSelectionModel().isEmpty() && !cbx_tipoUsuario.getSelectionModel().isEmpty()){
            Empleado newEmployee = null;
            switch (cbx_tipoUsuario.getSelectionModel().getSelectedItem()){
                case "Administrador":
                    newEmployee = new Empleado(cbx_sucursal.getSelectionModel().getSelectedItem().getIdSucursal(),txt_nombre.getText(),txt_apellidos.getText(),
                            txt_telefono.getText(),txt_correo.getText(),txt_contrania.getText(),Integer.toString(cbx_position.getSelectionModel().getSelectedItem().getIdPuesto()),
                            date_fechaIngreso.getValue().toString(),txt_cedula.getText(), TipoUsuario.ADMINISTRADOR);
                    break;
                case "Facturador":
                    newEmployee = new Empleado(cbx_sucursal.getSelectionModel().getSelectedItem().getIdSucursal(),txt_nombre.getText(),txt_apellidos.getText(),
                            txt_telefono.getText(),txt_correo.getText(),txt_contrania.getText(),Integer.toString(cbx_position.getSelectionModel().getSelectedItem().getIdPuesto()),
                            date_fechaIngreso.getValue().toString(),txt_cedula.getText(), TipoUsuario.FACTURADOR);
                    break;
            }
            int result = GroupDBConnection.getDBInstance().InsertEmpleado(newEmployee);

            if(result == 0){
                //System.out.println("Éxito");
                try{
                    informationDialog("Atención", "Empleado registrado", "Se ha registrado el nuevo empleado");
                    FXRouter.goTo("Empleados_administrador");
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Error");
            }

        }else{
            errorDialog("Error","Campos vacios", "Por favor, complete todos los campos para continuar");
            //MOSTRAR MENSAJE DE ERROR POR CAMPOS VACIOS
            System.out.println("Llenar todos los campos");
        }
    }

    private void handle_btn_atras(ActionEvent actionEvent) {
        try{
            FXRouter.goTo("Empleados_administrador");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}