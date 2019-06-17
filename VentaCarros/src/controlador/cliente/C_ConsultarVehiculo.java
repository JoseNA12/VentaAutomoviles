package controlador.cliente;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import modelo.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static controlador.C_InicioSesion.usuarioActual;

public class C_ConsultarVehiculo {

    @FXML Label lb_nombre_carro;
    @FXML Label lb_marca;
    @FXML Label lb_modelo;
    @FXML Label lb_anio;
    @FXML Label lb_num_pasajeros;
    @FXML Label lb_tipo;
    @FXML Label lb_motor;
    @FXML Label lb_asientos;
    @FXML Label lb_puertas;
    @FXML Label lb_gasolina;
    @FXML Label lb_aceleracion;
    @FXML Label lb_vel_maxima;
    @FXML Label lb_precio;
    @FXML Label lb_precio_total;
    @FXML Label lb_total_extras;

    @FXML JFXListView lv_extras;
    @FXML JFXListView lv_extras_seleccionadas;

    private ObservableList<ExtraVehiculo> extrasObservableList;
    private ObservableList<ExtraVehiculo> extras_seleccionadasObservableList;

    @FXML JFXButton btn_atras;
    @FXML JFXButton btn_comprar;
    @FXML JFXButton btn_agregar_extra;
    @FXML JFXButton btn_quitar_extra;
    @FXML JFXButton btn_solicitar_credito;

    @FXML JFXComboBox<MetodoPago> cb_metodo_pago;

    @FXML StackPane sp_dialogs;

    private Vehiculo vehiculo_seleccionado = null;

    private double montoTotal = 0.0;
    private double montoTotalExtras = 0.0;


    public void initialize() throws Exception {
        initComponentes();
        init_listView_extras();
        init_cb_metodo_pago();
    }

    private void initComponentes() throws Exception {
        btn_comprar.setOnAction(this::handle_btn_comprar);
        btn_solicitar_credito.setOnAction(this::handle_btn_solicitar_credito);
        btn_agregar_extra.setOnAction(this::handle_btn_agregar_extra);
        btn_quitar_extra.setOnAction(this::handle_btn_quitar_extra);
        btn_atras.setOnAction(this::handle_btn_atras);
        vehiculo_seleccionado = (Vehiculo) FXRouter.getData(); // la pantalla previa a esta envia el objeto Vehiculo
        lb_nombre_carro.setText(vehiculo_seleccionado.getNombre_carro());
        lb_marca.setText(vehiculo_seleccionado.getMarca().getNombre());
        lb_modelo.setText(vehiculo_seleccionado.getModelo());
        lb_anio.setText(vehiculo_seleccionado.getAnio());
        lb_num_pasajeros.setText(vehiculo_seleccionado.getNum_pasajeros());
        lb_tipo.setText(vehiculo_seleccionado.getTipoVehiculo().getTipo());
        lb_motor.setText(vehiculo_seleccionado.getMotor());
        lb_asientos.setText(vehiculo_seleccionado.getNum_pasajeros());
        lb_puertas.setText(vehiculo_seleccionado.getPuertas());
        lb_gasolina.setText(vehiculo_seleccionado.getTipoCombustible().getTipo());
        lb_aceleracion.setText(vehiculo_seleccionado.getAceleracion());
        lb_vel_maxima.setText(vehiculo_seleccionado.getVel_maxima());
        lb_precio.setText(vehiculo_seleccionado.getPrecio());
        montoTotal = Double.parseDouble(vehiculo_seleccionado.getPrecio());
        lb_precio_total.setText(String.valueOf(montoTotal));
        lb_total_extras.setText("0.0");
    }

    private void init_listView_extras() {
        extrasObservableList = FXCollections.observableArrayList();
        extras_seleccionadasObservableList = FXCollections.observableArrayList();

        extrasObservableList = GroupDBConnection.getDBInstance().getCarAccessories(vehiculo_seleccionado.getID());

        lv_extras.setItems(extrasObservableList);
        lv_extras.setCellFactory(extrasListView -> new ExtraVehiculoListViewCell());
        lv_extras_seleccionadas.setCellFactory(extrasListView -> new ExtraVehiculoListViewCell());
    }

    private void init_cb_metodo_pago() {
        cb_metodo_pago.setItems(GroupDBConnection.getDBInstance().getPaymentMethods());
        cb_metodo_pago.getSelectionModel().selectFirst(); //select the first element
    }

    /**
     * Se encarga de construir el pedido del cliente, toma el vehiculo y los extras seleccionados
     * Se construye cuando se solicita credito o se hace la compra directamente
     * @return
     */
    private VehiculoComprado GetVehiculoComprado() {
        VehiculoComprado vehiculoComprado = new VehiculoComprado(vehiculo_seleccionado);
        ObservableList<ExtraVehiculo> extras_seleccionadas = lv_extras_seleccionadas.getItems();
        for (ExtraVehiculo extra: extras_seleccionadas)
            vehiculoComprado.addExtra(extra);
        return vehiculoComprado;
    }

    private void handle_btn_comprar(ActionEvent event) {
        switch (usuarioActual.getTipoUsuario()) {
            case FACTURADOR:
                // solicitarCedula hace la consulta a la bd y redirigue a la pantalla
                this.solicitarCedula_compra_directa("Atención", "Ingrese el número de cédula\n\n\n");
                break;
            case CLIENTE:
                // TODO: Cambiar ID Sucursal
                if (validarEdad()) {
                    GroupDBConnection.getDBInstance().comprarVehiculo(componerVehiculoCompra(usuarioActual), 1);
                }else {
                    Alerts.errorDialog("Cliente no autorizado", "Cliente no autorizado!","No cuenta con la edad suficiente para comprar un vehículo");
                    //FXRouter.goTo("Abonos_cliente");
                }
                break;
        }
    }

    private void handle_btn_solicitar_credito(ActionEvent event) {
        if (vehiculo_seleccionado != null) {
            try {
                switch (usuarioActual.getTipoUsuario()) {
                    case FACTURADOR:
                        // solicitarCedula hace la consulta a la bd y redirigue a la pantalla
                        solicitarCedula_credito("Atención", "Ingrese el número de cédula\n\n\n");
                        break;
                    case CLIENTE:
                        FXRouter.goTo("SolicitarCredito_cliente", componerVehiculoCompra(usuarioActual));
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private VehiculoComprado componerVehiculoCompra(Usuario usuario){
        VehiculoComprado vehiculoComprado = GetVehiculoComprado();
        vehiculoComprado.setUsuario(usuario);
        vehiculoComprado.setMetodoPago(cb_metodo_pago.getSelectionModel().getSelectedItem());
        return vehiculoComprado;
    }

    private void handle_btn_agregar_extra(ActionEvent event) {
        ExtraVehiculo seleccion = (ExtraVehiculo) lv_extras.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            extrasObservableList.remove(seleccion); // elimino el item de la lista observable
            lv_extras.setItems(extrasObservableList); // actualizao la lista

            extras_seleccionadasObservableList.add(seleccion);  // añado el item a la lista de seleccionados
            lv_extras_seleccionadas.setItems(extras_seleccionadasObservableList); // actualizo la lista

            // actualizar montos totales y sus labels
            montoTotal += Double.parseDouble(seleccion.getPrecio());
            montoTotalExtras += Double.parseDouble(seleccion.getPrecio());
            lb_precio_total.setText(String.valueOf(montoTotal));
            lb_total_extras.setText(String.valueOf(montoTotalExtras));
        }
    }

    private void handle_btn_quitar_extra(ActionEvent event) {
        ExtraVehiculo seleccion = (ExtraVehiculo) lv_extras_seleccionadas.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            extras_seleccionadasObservableList.remove(seleccion);
            lv_extras_seleccionadas.setItems(extras_seleccionadasObservableList);

            extrasObservableList.add(seleccion);
            lv_extras.setItems(extrasObservableList);

            montoTotal -= Double.parseDouble(seleccion.getPrecio());
            montoTotalExtras -= Double.parseDouble(seleccion.getPrecio());
            lb_precio_total.setText(String.valueOf(montoTotal));
            lb_total_extras.setText(String.valueOf(montoTotalExtras));
        }
    }

    private void handle_btn_atras(ActionEvent event) {
        try {
            FXRouter.goTo("Catalogo_cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solicitarCedula_compra_directa(String encabezado, String cuerpo) {
        JFXDialogLayout content= new JFXDialogLayout();
        JFXTextField tf_cedula = new JFXTextField();
        content.setHeading(new Text(encabezado));
        content.setBody(new Text(cuerpo), tf_cedula);
        JFXDialog dialog =new JFXDialog(sp_dialogs, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btn_ingresar = new JFXButton("Ingresar");
        btn_ingresar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                    // validar la cedula
                    // obtener el usuario de la cedula y meter dentro del objeto Usuario
                    GroupDBConnection.getDBInstance().comprarVehiculo(componerVehiculoCompra(usuarioActual),1);
                    //FXRouter.goTo("Abonos_cliente", usuario);

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

    private void solicitarCedula_credito(String encabezado, String cuerpo) {
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
                    int idUsuario = GroupDBConnection.getDBInstance().SelectIDCustomerByEmail(tf_cedula.getText());
                    Usuario usuario = new Usuario(idUsuario, "", "", "", "", "", "", 1, null);

                    // añadir al objeto PedidoVehiculo el tipo de pago
                    FXRouter.goTo("SolicitarCredito_cliente", componerVehiculoCompra(usuario));
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

    private boolean validarEdad(){
        Calendar fechaNacimiento = Calendar.getInstance();
        Calendar fechaActual = Calendar.getInstance();
        try{
            fechaNacimiento.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(usuarioActual.getFechaNacimiento()));
            fechaNacimiento.add(Calendar.YEAR, 18);
            if(fechaNacimiento.compareTo(fechaActual) > 0)
                return false;
        }catch (ParseException e){}
        return true;
    }
}
