package modelo;

import javafx.scene.control.Alert;

public class Alerts {

    public static void informationDialog(String titulo, String encabezado, String contenido){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);

        alert.showAndWait();
    }

    public static void errorDialog(String titulo, String encabezado, String contenido){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);

        alert.showAndWait();
    }
}
