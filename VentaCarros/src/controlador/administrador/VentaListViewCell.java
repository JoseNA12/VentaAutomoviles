package controlador.administrador;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modelo.PedidoVehiculo;
import modelo.Venta;

import java.io.IOException;

public class VentaListViewCell extends JFXListCell<Venta> {

    @FXML GridPane gp_ventas;

    @FXML Label lb_vehiculo;
    @FXML Label lb_cliente;
    @FXML Label lb_tipo_pago;
    @FXML Label lb_monto;
    @FXML Label lb_fecha;
    @FXML Label lb_estado;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Venta venta, boolean empty) {
        super.updateItem(venta, empty);
        if(empty || venta == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../../vista/administrador/VentaListCell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            lb_vehiculo.setText(venta.getVehiculo().getNombre_carro());
            lb_cliente.setText(venta.getUsuario().getNombre() + " " + venta.getUsuario().getApellidos());
            lb_tipo_pago.setText(venta.getMetodoPago().getName());
            lb_monto.setText(venta.getMonto());
            lb_fecha.setText(venta.getFecha());
            lb_estado.setText(venta.getEstado());

            setText(null);
            setGraphic(gp_ventas);
        }
    }
}
