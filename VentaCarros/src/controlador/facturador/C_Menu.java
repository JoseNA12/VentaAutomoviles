package controlador.facturador;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import modelo.Usuario;

import java.io.IOException;

import static controlador.C_InicioSesion.usuarioActual;

public class C_Menu {

    @FXML JFXButton btn_catalogo;
    @FXML JFXButton btn_abono;
    @FXML JFXButton btn_salir;

    @FXML Label lb_usuario_actual;

    @FXML StackPane sp_dialogs;


    public void initialize() throws Exception {
        initComponentes();
    }

    // Inicializar las referecias de los handlers de los componentes de la UI
    private void initComponentes() throws Exception {
        btn_catalogo.setOnAction(this::handle_btn_catalogo);
        btn_abono.setOnAction(this::handle_btn_abono);
        btn_salir.setOnAction(this::handle_btn_salir);

        lb_usuario_actual.setText(usuarioActual.getNombre() + " " + usuarioActual.getApellidos());
    }

    private void handle_btn_catalogo(ActionEvent event) {
       try {
            FXRouter.goTo("Catalogo_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle_btn_abono(ActionEvent event) {
        solicitarCedula("Atención", "Ingrese el número de cédula\n\n\n");
    }

    private void handle_btn_salir(ActionEvent event) {
        try {
            FXRouter.goTo("InicioSesion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solicitarCedula(String encabezado, String cuerpo) {
        JFXDialogLayout content= new JFXDialogLayout();
        JFXTextField tf_cedula = new JFXTextField();
        content.setHeading(new Text(encabezado));
        content.setBody(new Text(cuerpo), tf_cedula);
        JFXDialog dialog =new JFXDialog(sp_dialogs, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btn_ingresar = new JFXButton("Ingresar");
        btn_ingresar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try {
                    // validar la cedula
                    // obtener el usuario de la cedula y meter dentro del objeto Usuario
                    // ------------- Query

                    Usuario usuario = new Usuario(12, "", "", "", "", "", "", 1, null);


                    FXRouter.goTo("Abonos_cliente", usuario);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        JFXButton btn_cancelar = new JFXButton("Cancelar");
        btn_cancelar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                dialog.close();
            }
        });

        content.setActions(btn_ingresar, btn_cancelar);
        dialog.show();
    }
}
