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

    @FXML
    private void initialize() {
        jugadorVsJugadorButton.setOnAction(e -> cambiarEscena("Jugador vs Jugador.fxml"));
        jugadorVsBotFacilButton.setOnAction(e -> cambiarEscena("Jugador vs BotFacil.fxml"));
        // Si agregas el botón para Bot Difícil, agrégalo aquí también
        // botDificilButton.setOnAction(e -> cambiarEscena("Jugador vs BotDificil.fxml"));
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