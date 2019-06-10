package modelo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class VehiculoListViewCell extends JFXListCell<Vehiculo> {

    @FXML ImageView imgv_imagen;

    @FXML Label lb_marca;
    @FXML Label lb_modelo;
    @FXML Label lb_motor;
    @FXML Label lb_anio;
    @FXML Label lb_num_pasajeros;
    @FXML Label lb_precio;

    @FXML JFXButton btn_consultar;

    @FXML GridPane gp_catalogo;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Vehiculo vehiculo, boolean empty) {
        super.updateItem(vehiculo, empty);

        if(empty || vehiculo == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../vista/cliente/VehiculoListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            lb_marca.setText(vehiculo.getMarca());
            lb_modelo.setText(vehiculo.getAnio());

            setText(null);
            setGraphic(gp_catalogo);
        }

    }
}
