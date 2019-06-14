package controlador.cliente;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modelo.Abono;
import modelo.ExtraVehiculo;

import java.io.IOException;

public class AbonoListViewCell extends JFXListCell<Abono> {

    @FXML GridPane gp_abono;

    @FXML Label lb_tipo_pago;
    @FXML Label lb_monto_pagado;
    @FXML Label lb_fecha_pago;

    private FXMLLoader mLLoader;


    @Override
    protected void updateItem(Abono abono, boolean empty) {
        super.updateItem(abono, empty);

        if(empty || abono == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../../vista/cliente/AbonoListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            lb_tipo_pago.setText(abono.getMetodoPago());
            lb_monto_pagado.setText(abono.getMonto());
            lb_fecha_pago.setText(abono.getFecha());

            setText(null);
            setGraphic(gp_abono);
        }
    }
}
