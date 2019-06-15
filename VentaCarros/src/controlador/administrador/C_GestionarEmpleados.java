package controlador.administrador;

import com.github.fxrouter.FXRouter;
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
    }

    private void init_cbx_Sucursal() {
        sucursalesObservablelist = FXCollections.observableArrayList();
        sucursalesObservablelist.addAll(new Sucursal(1,"Autos Jx3-L Cieneguita", "Costa Rica",1,"08:00","17:00"),
                new Sucursal(2,"Autos Jx3-L Río de Janeiro", "Brasil",3,"08:00","18:00"),
                new Sucursal(3,"Autos Jx3-L Detroit", "Estados Unidos",2,"07:30","16:00"));
        cbx_Sucursal.setItems(sucursalesObservablelist);
    }

    private void init_ListView_Empleados() {
        empleadosObservableList = FXCollections.observableArrayList();

        empleadosObservableList = GroupDBConnection.getDBInstance().SelectEmpleados();
        /*Empleado emp1 = new Empleado(1,"Jon","Calvo","Mañana","305060175","87139703",
                "jon@correo.com",30105,FACTURADOR,"2","Facturador",1);
        empleadosObservableList.add(emp1);*/
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
        /*try{
            FXRouter.goTo("Menu_administrador");
        }catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    private void handle_btn_UpdateEmpleado(ActionEvent event){
        try{
            FXRouter.goTo("Empleados_Actualizar");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handle_btn_Atras(ActionEvent event) {
        try{
            FXRouter.goTo("Menu_administrador");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}