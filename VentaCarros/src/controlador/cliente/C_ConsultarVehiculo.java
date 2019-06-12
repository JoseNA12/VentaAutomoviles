package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo.ExtraVehiculo;
import modelo.Sucursal;
import modelo.SucursalListViewCell;
import modelo.Vehiculo;

import java.io.IOException;

public class C_ConsultarVehiculo {

    @FXML Label lb_nombre_carro;
    @FXML Label lb_marca;
    @FXML Label lb_modelo;
    @FXML Label lb_anio;
    @FXML Label lb_num_pasajeros;
    @FXML Label lb_tipo;
    @FXML Label lb_motor;
    @FXML Label lb_asientos;
    @FXML Label lb_puertas;
    @FXML Label lb_gasolina;
    @FXML Label lb_aceleracion;
    @FXML Label lb_vel_maxima;
    @FXML Label lb_precio;
    @FXML Label lb_precio_total;
    @FXML Label lb_total_extras;

    @FXML JFXListView lv_extras;
    @FXML JFXListView lv_extras_seleccionadas;

    private ObservableList<ExtraVehiculo> sucursalesObservableList;

    @FXML JFXButton btn_atras;
    @FXML JFXButton btn_comprar;


    public void initialize() throws Exception {
        initComponentes();
        //init_listView_extras();
    }

    private void initComponentes() throws Exception {
        btn_atras.setOnAction(this::handle_btn_atras);

        Vehiculo vehiculo_seleccionado = (Vehiculo) FXRouter.getData(); // la pantalla previa a esta envia el objeto Vehiculo
        lb_nombre_carro.setText(vehiculo_seleccionado.getNombre_carro());
        lb_marca.setText(vehiculo_seleccionado.getMarca());
        lb_modelo.setText(vehiculo_seleccionado.getModelo());
        lb_anio.setText(vehiculo_seleccionado.getAnio());
        lb_num_pasajeros.setText(vehiculo_seleccionado.getNum_pasajeros());
        lb_tipo.setText(vehiculo_seleccionado.getTipo());
        lb_motor.setText(vehiculo_seleccionado.getMotor());
        lb_asientos.setText(vehiculo_seleccionado.getAsientos());
        lb_puertas.setText(vehiculo_seleccionado.getPuertas());
        lb_gasolina.setText(vehiculo_seleccionado.getGasolina());
        lb_aceleracion.setText(vehiculo_seleccionado.getAceleracion());
        lb_vel_maxima.setText(vehiculo_seleccionado.getVel_maxima());
        lb_precio.setText(vehiculo_seleccionado.getPrecio());

    }

   /* private void init_listView_extras() {
        //listView_catalogo.getStyleClass().add("mylistview");

        //listView_sucursales.getItems().add(new Label("Item"));
        sucursalesObservableList = FXCollections.observableArrayList();

        // ---------------------------------------------------------------
        // HACER LA CONSULTA A LAS BB's

        sucursalesObservableList.addAll(
                new ExtraVehiculo("Josue se la come", " y entera")
        );
        // ---------------------------------------------------------------

        lv_extras.setItems(sucursalesObservableList);
        lv_extras.setCellFactory(studentListView -> new SucursalListViewCell());
    }*/

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("Catalogo_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
