package controlador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import modelo.Usuario;

import java.io.IOException;

public class C_InicioSesion {

    @FXML AnchorPane ap_pane;

    @FXML StackPane st_dialogs;

    @FXML JFXButton btn_ingresar;
    @FXML JFXButton btn_crear_cuenta;

    @FXML JFXTextField tf_correo;
    @FXML JFXPasswordField tf_contrasenia;

    public static Usuario usuarioActual;


    public void initialize() throws Exception {
        initComponentes();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_ingresar.setOnAction(this::handle_btn_ingresar);
        btn_crear_cuenta.setOnAction(this::handle_btn_crear_cuenta);
    }

    private void handle_btn_ingresar(ActionEvent event) {
        if (!tf_correo.getText().trim().equals("") && !tf_contrasenia.getText().trim().equals("")) {
            try {
                // ---------------- query de inicio de sesion
                // if else ...
                usuarioActual = new Usuario("nombre", "apellidos", "fechaNacimiento",
                        "cedula", "telefono", "correo");

                FXRouter.goTo("Menu_cliente");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            mostrarMensaje("Datos incompletos", "Por favor, ingrese un correo electrónico y una contraseña!");
        }
    }

    private void handle_btn_crear_cuenta(ActionEvent event) {
        try {
            FXRouter.goTo("CrearCuenta_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String encabezado, String cuerpo) {
        JFXDialogLayout content= new JFXDialogLayout();
        content.setHeading(new Text(encabezado));
        content.setBody(new Text(cuerpo));
        JFXDialog dialog =new JFXDialog(st_dialogs, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button=new JFXButton("Cerrar");
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                dialog.close();
            }
        });
        content.setActions(button);
        dialog.show();
    }
}
