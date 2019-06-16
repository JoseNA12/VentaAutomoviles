package controlador.administrador;

import com.github.fxrouter.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.Empleado;
import modelo.GroupDBConnection;
import modelo.Sucursal;
import modelo.TipoUsuario;

import java.io.IOException;

public class C_ActualizarEmpleado {

    @FXML private ComboBox<Sucursal> cbx_sucursal;
    @FXML private TextField txt_nombre;
    @FXML private TextField txt_apellidos;
    @FXML private TextField txt_telefono;
    @FXML private ComboBox<String> cbx_userType;
    @FXML private Button btn_actualizar;
    @FXML private Button btn_atras;

    private ObservableList<Sucursal> sucursalObservableList;
    private ObservableList<String> tipoUsuarioObservableList;
    private Empleado empleado;

    public void initialize(){
        this.empleado = (Empleado)FXRouter.getData();
        initcomponents();
    }
    private void actualizarEmpleado(){
        if(!cbx_sucursal.getSelectionModel().isEmpty()&& !cbx_userType.getSelectionModel().isEmpty()
            &&!txt_nombre.getText().equals("")&&!txt_apellidos.getText().equals("")&&!txt_telefono.getText().equals("")){
            this.empleado.setNombre(txt_nombre.getText());
            this.empleado.setApellidos(txt_apellidos.getText());
            this.empleado.setTelefono(txt_telefono.getText());
            this.empleado.setIdSucursal(cbx_sucursal.getSelectionModel().getSelectedItem().getIdSucursal());
            switch(cbx_userType.getSelectionModel().getSelectedItem()){
                case "Administrador":
                    this.empleado.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
                    break;
                case "Facturador":
                    this.empleado.setTipoUsuario(TipoUsuario.FACTURADOR);
                    break;
            }
        }else{
            System.out.println("No actualiza");
        }
    }

    private void initcomponents() {
        btn_actualizar.setOnAction(this::handle_btn_actualizar);
        btn_atras.setOnAction(this::handle_btn_atras);
        txt_nombre.setText(empleado.getNombre());
        txt_apellidos.setText(empleado.getApellidos());
        txt_telefono.setText(empleado.getTelefono());
        initcbx_tipoUsuario();
        initcbx_sucursales();
        switch (empleado.getTipoUsuario()){
            case ADMINISTRADOR:
                cbx_userType.setValue("Administrador");
                break;
            case FACTURADOR:
                cbx_userType.setValue("Facturador");
                break;
        }
    }

    private void initcbx_sucursales() {
        sucursalObservableList = FXCollections.observableArrayList();
        sucursalObservableList = GroupDBConnection.getDBInstance().getSucursales();
        cbx_sucursal.setItems(sucursalObservableList);
        for(Sucursal sucursal : sucursalObservableList){
            if(sucursal.getIdSucursal()==empleado.getIdSucursal()){
                cbx_sucursal.setValue(sucursal);
            }
        }
    }

    private void initcbx_tipoUsuario() {
        tipoUsuarioObservableList = FXCollections.observableArrayList();
        tipoUsuarioObservableList.addAll("Administrador","Facturador");
        cbx_userType.setItems(tipoUsuarioObservableList);
    }

    private void handle_btn_actualizar(ActionEvent actionEvent) {
        actualizarEmpleado();
        GroupDBConnection.getDBInstance().UpdateEmpleado(this.empleado);
        try{
            FXRouter.goTo("Empleados_administrador");
        }catch (IOException e) {
            e.printStackTrace();
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
