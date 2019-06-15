package controlador.cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modelo.Sucursal;

import java.io.IOException;

public class SucursalListViewCell extends JFXListCell<Sucursal> {

    @FXML GridPane gp_sucursales;

    @FXML Label lb_nombre;
    @FXML Label lb_ubicacion;

    @FXML JFXButton btn_consultar;

    private FXMLLoader mLLoader;


    @Override
    protected void updateItem(Sucursal sucursal, boolean empty) {
        super.updateItem(sucursal, empty);

        if(empty || sucursal == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../../vista/cliente/SucursalListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            lb_nombre.setText(sucursal.getNombreSucursal());
            lb_ubicacion.setText(sucursal.getNombrePais());

            setText(null);
            setGraphic(gp_sucursales);
        }
    }
}
