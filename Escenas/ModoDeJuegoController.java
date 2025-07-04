package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

// Controlador para la selección del modo de juego
public class ModoDeJuegoController {
    // Botón para modo Jugador vs Jugador
    @FXML private Button jugadorVsJugadorButton;
    // Botón para modo Jugador vs Bot Fácil
    @FXML private Button jugadorVsBotFacilButton;
    // Botón para modo Jugador vs Bot Difícil
    @FXML private Button jugadorVsBotDificilButton;

    // Inicializa el controlador y asigna los eventos a los botones
    @FXML
    private void initialize() {
        jugadorVsJugadorButton.setOnAction(e -> cambiarEscena("JugadorvsJugador.fxml"));
        jugadorVsBotFacilButton.setOnAction(e -> cambiarEscena("JugadorVsBotFacil.fxml"));
        jugadorVsBotDificilButton.setOnAction(e -> cambiarEscena("JugadorVsBotDificil.fxml"));
    }

    // Cambia la escena según el modo de juego seleccionado
    private void cambiarEscena(String fxml) {
        try {
            Stage stage = (Stage) jugadorVsJugadorButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace(); // Muestra errores en consola si ocurre algún problema
        }
    }
}