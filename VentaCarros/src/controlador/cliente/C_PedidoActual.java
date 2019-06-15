package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class C_PedidoActual {

    @FXML Pane gp_info_pedido;

    @FXML Label lb_info;
    @FXML Label lb_nombre_vehiculo;
    @FXML Label lb_monto_total;
    @FXML Label lb_nombre_cliente;
    @FXML Label lb_correo_cliente;
    @FXML Label lb_telefono_cliente;

    private String msg_1 = "Usted no ha realizado ningún pedido de un vehículo!";
    private String msg_2 = "Su pedido esta en proceso de chequeo!";

    @FXML JFXButton btn_atras;


    public void initialize() throws Exception {
        btn_atras.setOnAction(this::handle_btn_atras);


        // tener un switch con varios opciones

        int respuesta_de_la_base = 0;

        switch (respuesta_de_la_base){
            case 0: // pedido realizado y aprobado, esperando hasta la fecha
                gp_info_pedido.setVisible(true);
                lb_nombre_vehiculo.setText("as");
                lb_monto_total.setText("as");
                lb_nombre_cliente.setText("as");
                lb_correo_cliente.setText("as");
                lb_telefono_cliente.setText("as");
                break;
            case 1: // pedido realizado pero no aprobado por el admin
                lb_info.setVisible(true);
                lb_info.setText(msg_2);
                break;
            case 2: // no tiene ningun pedido, no tiene del todo o ya paso la fecha del pedido previo (ya tiene el carro)
                lb_info.setVisible(true);
                lb_info.setText(msg_1);
                break;

        }
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("Menu_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
