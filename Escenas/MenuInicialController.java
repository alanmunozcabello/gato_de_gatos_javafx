package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MenuInicialController {
    @FXML private Button jugarButton;
    @FXML private Button historialJugadorButton;
    @FXML private Button historialPartidasButton;
    @FXML private Button salirButton;

    @FXML
    private void initialize() {
        jugarButton.setOnAction(e -> cambiarEscena("ModoDeJuego.fxml"));
        historialJugadorButton.setOnAction(e -> cambiarEscena("Historial Jugador.fxml"));
        historialPartidasButton.setOnAction(e -> cambiarEscena("Historial_partidas.fxml"));
        salirButton.setOnAction(e -> ((Stage)salirButton.getScene().getWindow()).close());
    }

    private void cambiarEscena(String fxml) {
        try {
            Stage stage = (Stage) jugarButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}