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
import javafx.scene.layout.HBox;
import modelo.TipoUsuario;
import modelo.Vehiculo;

import java.io.IOException;


public class VehiculoListViewCell extends JFXListCell<Vehiculo> {

    @FXML ImageView imgv_imagen;

    @FXML Label lb_marca;
    @FXML Label lb_modelo;
    @FXML Label lb_anio;
    @FXML Label lb_num_pasajeros;
    @FXML Label lb_precio;

    @FXML Label lb_cantidad_en_fabrica; // solo cuando estoy en fabrica se pone visible

    @FXML JFXButton btn_consultar;

    @FXML GridPane gp_catalogo;

    @FXML HBox hbox_cantidad;

    private FXMLLoader mLLoader;

    private TipoUsuario bandera;


    public VehiculoListViewCell(TipoUsuario bandera) {
        this.bandera = bandera;
    }

    public VehiculoListViewCell() {
        this.bandera = TipoUsuario.CLIENTE;
    }

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

            switch (bandera) {
                case ADMINISTRADOR:
                    btn_consultar.setVisible(false);
                    hbox_cantidad.setVisible(true);
                    lb_cantidad_en_fabrica.setText(String.valueOf(vehiculo.getCantidad_en_fabrica()));
                    break;

                default:
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
                    break;
            }

            lb_marca.setText(vehiculo.getMarca().getNombre());
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
