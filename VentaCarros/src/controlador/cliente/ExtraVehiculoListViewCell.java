package controlador.cliente;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modelo.ExtraVehiculo;

import java.io.IOException;

public class ExtraVehiculoListViewCell extends JFXListCell<ExtraVehiculo> {

    @FXML GridPane gp_extras;

    @FXML Label lb_extra_nombre;
    @FXML Label lb_extra_precio;

    private FXMLLoader mLLoader;


    @Override
    protected void updateItem(ExtraVehiculo extra, boolean empty) {
        super.updateItem(extra, empty);

        if(empty || extra == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../../vista/cliente/ExtraVehiculoListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            lb_extra_nombre.setText(extra.getNombre());
            lb_extra_precio.setText(extra.getPrecio());

            setText(null);
            setGraphic(gp_extras);
        }
    }
}
