package controlador.administrador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import modelo.Empleado;
import modelo.Sucursal;

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
        sucursalesObservablelist.addAll(new Sucursal("Autos Jx3-L Cieneguita", 1),
                new Sucursal("Autos Jx3-L Río de Janeiro", 2),
                new Sucursal("Autos Jx3-L Detroit", 3));
        cbx_Sucursal.setItems(sucursalesObservablelist);
    }

    private void init_ListView_Empleados() {
        empleadosObservableList = FXCollections.observableArrayList();
        Empleado emp1 = new Empleado(1,"Jon","Calvo","Mañana","305060175","87139703",
                "jon@correo.com",30105,FACTURADOR,"2","Facturador",1);
        empleadosObservableList.add(emp1);
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

    }
    private void handle_btn_DeleteEmpleado(ActionEvent event){

    }
    private void handle_btn_UpdateEmpleado(ActionEvent event){

    }
    private void handle_btn_Atras(ActionEvent event){

    }


}