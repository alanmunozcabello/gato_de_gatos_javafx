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

// Controlador para el menú inicial del juego
public class MenuInicialController {
    // Botón para iniciar el juego
    @FXML private Button jugarButton;
    // Botón para ver el historial de un jugador
    @FXML private Button historialJugadorButton;
    // Botón para ver el historial de partidas
    @FXML private Button historialPartidasButton;
    // Botón para salir de la aplicación
    @FXML private Button salirButton;

    // Lista global de jugadores (se comparte entre escenas)
    public static ArrayList<Jugador> jugadores;
    // Lista global de partidas (se comparte entre escenas)
    public static ArrayList<Partida> partidas;

    // Inicializa el menú, cargando datos y asignando eventos a los botones
    @FXML
    private void initialize() {
        // Deserializa jugadores y partidas solo si aún no están cargados
        if (jugadores == null) {
            DeSerializar deSerializar = new DeSerializar();
            jugadores = deSerializar.deserializarJugadores();
        }
        if (partidas == null) {
            DeSerializar deSerializar = new DeSerializar();
            partidas = deSerializar.deserializarPartidas();
        }

        // Asigna acciones a los botones del menú
        jugarButton.setOnAction(e -> cambiarEscena("ModoDeJuego.fxml"));
        historialJugadorButton.setOnAction(e -> abrirHistorialJugador());
        historialPartidasButton.setOnAction(e -> abrirHistorialPartidas());
        salirButton.setOnAction(e -> ((Stage)salirButton.getScene().getWindow()).close());
    }

    // Abre la ventana de historial de jugadores y le pasa la lista de jugadores
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

    // Abre la ventana de historial de partidas y le pasa la lista de partidas
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

    // Cambia la escena principal a la que se indique por parámetro
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