package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import modelo.GroupDBConnection;

import java.io.IOException;
import java.time.LocalDate;

import static modelo.Alerts.errorDialog;
import static modelo.Alerts.informationDialog;

public class C_CrearCuenta {

    @FXML StackPane st_dialogs;
    @FXML JFXButton btn_proceder;
    @FXML JFXButton btn_regresar;
    @FXML JFXTextField tf_nombre;
    @FXML JFXTextField tf_apellidos;
    @FXML JFXDatePicker tf_fecha_nacimiento;
    @FXML JFXTextField tf_cedula;
    @FXML JFXTextField tf_telefono;
    @FXML JFXTextField tf_zip_code;
    @FXML JFXTextField tf_correo;
    @FXML JFXPasswordField tf_contrasenia;

    public void initialize() throws Exception {
        initComponentes();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_proceder.setOnAction(this::handle_btn_proceder);
        btn_regresar.setOnAction(this::handle_btn_regresar);
    }

    private void handle_btn_proceder(ActionEvent event) {
        if (!tf_nombre.getText().trim().equals("") && !tf_apellidos.getText().trim().equals("") &&
            tf_fecha_nacimiento.getValue() != null  && !tf_cedula.getText().trim().equals("") &&
            !tf_telefono.getText().trim().equals("") && !tf_zip_code.getText().trim().equals("") &&
            !tf_correo.getText().trim().equals("") && !tf_contrasenia.getText().trim().equals("")) {


            if(GroupDBConnection.getDBInstance().signIn(tf_nombre.getText(), tf_apellidos.getText(), tf_fecha_nacimiento.getValue(),
                    tf_cedula.getText(), tf_telefono.getText(), tf_zip_code.getText(), tf_correo.getText(), tf_contrasenia.getText())) {
                informationDialog("Atención", "Usuario registrado con éxito", "Usuario registrado correctamente");
            }else{
                errorDialog("Error", "Correo electrónico en uso", "El correo electrónico indicado ya se encuentra registrado en el sistema");
            }


            //System.out.println(localDate);
        }
        else {
            errorDialog("Error", "Datos incompletos", "Por favor, todos los campos son requeridos para el registro!");
        }
    }

    private void handle_btn_regresar(ActionEvent event) {
        try {
            FXRouter.goTo("InicioSesion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
