package controlador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import modelo.*;


import java.io.IOException;

import static modelo.Alerts.errorDialog;

public class C_InicioSesion {

    @FXML AnchorPane ap_pane;

    @FXML StackPane st_dialogs;

    @FXML JFXButton btn_ingresar;
    @FXML JFXButton btn_crear_cuenta;

    @FXML JFXTextField tf_correo;
    @FXML JFXPasswordField tf_contrasenia;

    public static Usuario usuarioActual;
    public static int idSucursalActual = 1;
    //public static TipoUsuario tipoUsuarioActual;


    public void initialize() throws Exception {
        initComponentes();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_ingresar.setOnAction(this::handle_btn_ingresar);
        btn_crear_cuenta.setOnAction(this::handle_btn_crear_cuenta);
        //GroupDBConnection.getDBInstance().prueba1();
        //GroupDBConnection.getDBInstance().prueba2();
        //GroupDBConnection.getDBInstance().prueba3();
        //BranchOfficeDB_Connection.getHSDBInstance().updateCuota(666,1);
    }

    private void handle_btn_ingresar(ActionEvent event) {
        /*try{
            FXRouter.goTo("Menu_cliente");
        }catch(IOException e) {
            e.printStackTrace();
        }*/
        if (!tf_correo.getText().trim().equals("") && !tf_contrasenia.getText().trim().equals("")) {
            try {
                GroupDBConnection.getDBInstance().loginDB(tf_correo.getText(), tf_contrasenia.getText());
                usuarioActual = GroupDBConnection.getDBInstance().loginDB(tf_correo.getText(), tf_contrasenia.getText());
                if(usuarioActual == null)
                    errorDialog("Error", "La cuenta no existe", "Por favor, verifique las credenciales ingresadas!");
                else{
                    switch (usuarioActual.getTipoUsuario()) {
                        case ADMINISTRADOR:
                            FXRouter.goTo("Menu_administrador");
                            break;
                        case FACTURADOR:
                            FXRouter.goTo("Menu_facturador");
                            break;
                        case CLIENTE:
                            FXRouter.goTo("Menu_cliente");
                            break;
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            errorDialog("Error", "Datos incompletos", "Por favor, ingrese un correo electrónico y una contraseña!");
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
