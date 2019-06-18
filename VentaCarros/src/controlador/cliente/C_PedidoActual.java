package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import modelo.GroupDBConnection;
import modelo.PedidoVehiculo;
import modelo.Usuario;

import java.io.IOException;

import static controlador.C_InicioSesion.usuarioActual;

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
        ObservableList<PedidoVehiculo> MiPedido = GroupDBConnection.getDBInstance().SelectMiPedido(usuarioActual.getIdUsuario());
        if(!MiPedido.isEmpty()) {
            PedidoVehiculo pedido = MiPedido.get(0);
           // System.out.println(pedido.getVehiculoPedido().getModelo());
            switch (pedido.getDetalles()) { //Enviado
                case "Enviado": // pedido realizado y aprobado, esperando hasta la fecha
                    System.out.println(pedido.getVehiculoPedido().getModelo());
                    gp_info_pedido.setVisible(true);
                    lb_nombre_vehiculo.setText(pedido.getVehiculoPedido().getMarca() + " " + pedido.getVehiculoPedido().getModelo());
                    lb_monto_total.setText(pedido.getVehiculoPedido().getPrecio());
                    lb_nombre_cliente.setText(pedido.getUsuarioSolicitante().getNombre() + " " + pedido.getUsuarioSolicitante().getApellidos());
                    lb_correo_cliente.setText(pedido.getUsuarioSolicitante().getCorreo());
                    lb_telefono_cliente.setText(pedido.getUsuarioSolicitante().getTelefono());
                    break;
                case "Pendiente": // pedido realizado pero no aprobado por el admin Pendiente
                    System.out.println(pedido.getVehiculoPedido().getModelo());
                    lb_info.setVisible(true);
                    lb_info.setText(msg_2);
                    break;
                case "Entregado": // no tiene ningun pedido, no tiene del todo o ya paso la fecha del pedido previo (ya tiene el carro) Entregado
                    System.out.println(pedido.getVehiculoPedido().getModelo());
                    lb_info.setVisible(true);
                    lb_info.setText(msg_1);
                    break;

            }
        } else {
            lb_info.setVisible(true);
            lb_info.setText(msg_1);
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
