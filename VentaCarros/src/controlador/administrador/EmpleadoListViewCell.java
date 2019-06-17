package controlador.administrador;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import modelo.Empleado;

import java.io.IOException;

public class EmpleadoListViewCell extends JFXListCell<Empleado> {
    @FXML GridPane gp_planes_de_pago;
    @FXML Label lb_nombre;
    @FXML Label lb_telefono;
    @FXML Label lb_cedula;
    @FXML Label lb_correo;
    @FXML Label lb_puesto;
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Empleado empleado, boolean empty) {
        super.updateItem(empleado, empty);
        if(empty || empleado == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../../vista/administrador/EmpleadoListCell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            lb_nombre.setText(empleado.getNombre() + " " + empleado.getApellidos());
            lb_telefono.setText(empleado.getTelefono());
            lb_cedula.setText(empleado.getCedula());
            lb_correo.setText(empleado.getCorreo());
            lb_puesto.setText(empleado.getNombrePuesto());
            setText(null);
            setGraphic(gp_planes_de_pago);
        }
    }
}