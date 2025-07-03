package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ModoDeJuegoController {
    @FXML private Button jugadorVsJugadorButton;
    @FXML private Button jugadorVsBotFacilButton;
    @FXML private Button jugadorVsBotDificilButton;

    @FXML
    private void initialize() {
        jugadorVsJugadorButton.setOnAction(e -> cambiarEscena("JugadorvsJugador.fxml"));
        jugadorVsBotFacilButton.setOnAction(e -> cambiarEscena("JugadorVsBotFacil.fxml"));
        jugadorVsBotDificilButton.setOnAction(e -> cambiarEscena("JugadorVsBotDificil.fxml"));
    }

    private void cambiarEscena(String fxml) {
        try {
            Stage stage = (Stage) jugadorVsJugadorButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}