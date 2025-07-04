package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

// Controlador para la escena de "Jugador vs Bot Dificil"
public class JugadorVsBotDificilController {
    // Campo de texto para el nombre del jugador humano
    @FXML private TextField nombreJugadorField;
    // Botón para iniciar la partida
    @FXML private Button iniciarPartidaButton;

    // Inicializa el controlador y asigna el evento al botón
    @FXML
    private void initialize() {
        iniciarPartidaButton.setOnAction(e -> iniciarPartida());
    }

    // Lógica para iniciar la partida contra el bot difícil
    private void iniciarPartida() {
        String nombre = nombreJugadorField.getText(); // Obtiene el nombre del jugador
        String nombreBot = "BotDificil"; // Nombre fijo para el bot

        try {
            // Carga y muestra la ventana de dados para decidir quién comienza
            FXMLLoader dadosLoader = new FXMLLoader(getClass().getResource("/Escenas/Ventana_dados.fxml"));
            Parent dadosRoot = dadosLoader.load();
            VentanaDadosController dadosController = dadosLoader.getController();
            dadosController.setNombres(nombre, nombreBot);

            Stage dadosStage = new Stage();
            dadosStage.setTitle("Tirada de Dados");
            dadosStage.setScene(new Scene(dadosRoot));
            dadosStage.initOwner(iniciarPartidaButton.getScene().getWindow());
            dadosStage.showAndWait();

            // Obtiene el resultado de los dados: 1 = jugador, 2 = bot
            int quienComienza = dadosController.getQuienComienza();

            // Carga la escena principal del juego y pasa los nombres y el turno inicial
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Escenas/Juego_principal.fxml"));
            Parent root = loader.load();

            JuegoPrincipalController controller = loader.getController();
            controller.setNombresJugadores(nombre, nombreBot, quienComienza);
            controller.setTurnoInicial(quienComienza);

            // Cambia la escena actual a la del juego principal
            Stage stage = (Stage) iniciarPartidaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace(); // Muestra errores en consola si ocurre algún problema
        }
    }
}