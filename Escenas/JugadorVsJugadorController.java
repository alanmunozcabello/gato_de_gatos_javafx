package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

// Controlador para la escena de "Jugador vs Jugador"
public class JugadorVsJugadorController {
    // Campo de texto para el nombre del primer jugador
    @FXML private TextField nombreJugador1Field;
    // Campo de texto para el nombre del segundo jugador
    @FXML private TextField nombreJugador2Field;
    // Botón para iniciar la partida
    @FXML private Button iniciarPartidaButton;

    // Inicializa el controlador y asigna el evento al botón
    @FXML
    private void initialize() {
        iniciarPartidaButton.setOnAction(e -> iniciarPartida());
    }

    // Lógica para iniciar la partida entre dos jugadores humanos
    private void iniciarPartida() {
        String nombre1 = nombreJugador1Field.getText(); // Obtiene el nombre del primer jugador
        String nombre2 = nombreJugador2Field.getText(); // Obtiene el nombre del segundo jugador

        try {
            // Lanza la ventana de dados para decidir quién comienza
            FXMLLoader dadosLoader = new FXMLLoader(getClass().getResource("/Escenas/Ventana_dados.fxml"));
            Parent dadosRoot = dadosLoader.load();
            VentanaDadosController dadosController = dadosLoader.getController();
            dadosController.setNombres(nombre1, nombre2);

            Stage dadosStage = new Stage();
            dadosStage.setTitle("Tirada de Dados");
            dadosStage.setScene(new Scene(dadosRoot));
            dadosStage.initOwner(iniciarPartidaButton.getScene().getWindow());
            dadosStage.showAndWait();

            int quienComienza = dadosController.getQuienComienza(); // 1 = nombre1, 2 = nombre2

            // Carga la escena principal y pasa los nombres y el turno inicial
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Escenas/Juego_principal.fxml"));
            Parent root = loader.load();

            JuegoPrincipalController controller = loader.getController();
            controller.setNombresJugadores(nombre1, nombre2, quienComienza);
            controller.setTurnoInicial(quienComienza);

            // Cambia la escena actual a la del juego principal
            Stage stage = (Stage) iniciarPartidaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace(); // Muestra errores en consola si ocurre algún problema
        }
    }
}