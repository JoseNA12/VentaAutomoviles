package controlador.administrador;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modelo.Empleado;
import modelo.PedidoVehiculo;

import java.io.IOException;

public class PedidoListViewCell extends JFXListCell<PedidoVehiculo> {

    @FXML GridPane gp_pedidos_cliente;
    @FXML Label lb_nombre_vehiculo;
    @FXML Label lb_precio_total;
    @FXML Label lb_nombre_cliente;
    @FXML Label lb_telefono_cliente;
    @FXML Label lb_correo_cliente;
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(PedidoVehiculo pedidoVehiculo, boolean empty) {
        super.updateItem(pedidoVehiculo, empty);
        if(empty || pedidoVehiculo == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../../vista/administrador/PedidoListCell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            lb_nombre_vehiculo.setText(pedidoVehiculo.getVehiculo().getNombre_carro());
            lb_precio_total.setText(String.valueOf(pedidoVehiculo.getPrecioTotal()));
            lb_nombre_cliente.setText(pedidoVehiculo.getUsuario().getNombre() + " " + pedidoVehiculo.getUsuario().getApellidos());
            lb_telefono_cliente.setText(pedidoVehiculo.getUsuario().getTelefono());
            lb_correo_cliente.setText(pedidoVehiculo.getUsuario().getCorreo());

            setText(null);
            setGraphic(gp_pedidos_cliente);
        }
    }
}
