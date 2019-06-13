package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import modelo.Vehiculo;

import java.io.IOException;

public class VehiculoListViewCell extends JFXListCell<Vehiculo> {

    @FXML ImageView imgv_imagen;

    @FXML Label lb_marca;
    @FXML Label lb_modelo;
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
                mLLoader = new FXMLLoader(getClass().getResource("../../vista/cliente/VehiculoListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            btn_consultar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXRouter.goTo("ConsultarVehiculo_cliente", vehiculo); // le paso el objeto vehiculo
                    } catch (IOException e) {   // .. entonces al momento de cargar la pantalla sepa cuales datos mostrar
                        e.printStackTrace();
                    }
                }
            });
            lb_marca.setText(vehiculo.getMarca());
            lb_modelo.setText(vehiculo.getModelo());
            lb_anio.setText(vehiculo.getAnio());
            lb_num_pasajeros.setText(vehiculo.getNum_pasajeros());
            lb_precio.setText(vehiculo.getPrecio());

            setText(null);
            setGraphic(gp_catalogo);
        }
    }
}

//Parent root;
/*try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/cliente/ConsultarVehiculo.fxml"));
    Parent root = loader.load();
    Label a = (Label)loader.getNamespace().get("lb_marca_2");
    System.out.println(a.getText());

    Stage stage = new Stage();
    stage.setTitle("El buga");
    stage.setScene(new Scene(root));
    stage.show();

}
catch (IOException e) {
    e.printStackTrace();
}*/
