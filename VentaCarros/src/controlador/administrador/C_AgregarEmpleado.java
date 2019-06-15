package controlador.administrador;

import com.github.fxrouter.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.*;

import java.io.IOException;
import java.time.LocalDate;

public class C_AgregarEmpleado {

    @FXML private ComboBox<Sucursal> cbx_sucursal;
    @FXML private TextField txt_nombre;
    @FXML private TextField txt_apellidos;
    @FXML private TextField txt_telefono;
    @FXML private TextField txt_correo;
    @FXML private DatePicker date_fechaIngreso;
    @FXML private ComboBox<PuestoTrabajo> cbx_position;
    @FXML private ComboBox<String> cbx_tipoUsuario;
    @FXML private TextField txt_cedula;
    @FXML private TextField txt_contrania;
    @FXML private Button btn_agregar;
    @FXML private Button btn_atras;

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
        sucursalesObservablelist.addAll(new Sucursal(1,"Autos Jx3-L Cieneguita", "Costa Rica",1,"08:00","17:00"),
                new Sucursal(2,"Autos Jx3-L Río de Janeiro", "Brasil",3,"08:00","18:00"),
                new Sucursal(3,"Autos Jx3-L Detroit", "Estados Unidos",2,"07:30","16:00"));
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
                System.out.println("Éxito");
                try{
                    FXRouter.goTo("Empleados_administrador");
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Error");
            }

        }else{
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