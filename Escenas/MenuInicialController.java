package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import Serializador.DeSerializar;
import Jugadores.Jugador;
import Gato_de_Gatos.Partida;
import java.util.ArrayList;

public class MenuInicialController {
    @FXML private Button jugarButton;
    @FXML private Button historialJugadorButton;
    @FXML private Button historialPartidasButton;
    @FXML private Button salirButton;

    private ArrayList<Jugador> jugadores;
    private ArrayList<Partida> partidas;

    @FXML
    private void initialize() {
        // Deserializa los datos al iniciar el menú
        DeSerializar deSerializar = new DeSerializar();
        jugadores = deSerializar.deserializarJugadores();
        partidas = deSerializar.deserializarPartidas();

        jugarButton.setOnAction(e -> cambiarEscena("ModoDeJuego.fxml"));
        historialJugadorButton.setOnAction(e -> abrirHistorialJugador());
        historialPartidasButton.setOnAction(e -> abrirHistorialPartidas());
        salirButton.setOnAction(e -> ((Stage)salirButton.getScene().getWindow()).close());
    }

    private void abrirHistorialJugador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Historial_Jugador.fxml"));
            Parent root = loader.load();
            HistorialJugadorController controller = loader.getController();
            controller.setJugadores(jugadores);
            Stage stage = (Stage) historialJugadorButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void abrirHistorialPartidas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Historial_partidas.fxml"));
            Parent root = loader.load();
            HistorialPartidasController controller = loader.getController();
            controller.setPartidas(partidas);
            Stage stage = (Stage) historialPartidasButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
