package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class JugadorVsBotFacilController {
    @FXML private TextField nombreJugadorField;
    @FXML private Button iniciarPartidaButton;

    @FXML
    private void initialize() {
        iniciarPartidaButton.setOnAction(e -> iniciarPartida());
    }

    private void iniciarPartida() {
        String nombre = nombreJugadorField.getText();
        String nombreBot = "BotFacil";

        try {
            // Lanzar ventana de dados
            FXMLLoader dadosLoader = new FXMLLoader(getClass().getResource("/Escenas/Ventana_dados.fxml"));
            Parent dadosRoot = dadosLoader.load();
            VentanaDadosController dadosController = dadosLoader.getController();
            dadosController.setNombres(nombre, nombreBot);

            Stage dadosStage = new Stage();
            dadosStage.setTitle("Tirada de Dados");
            dadosStage.setScene(new Scene(dadosRoot));
            dadosStage.initOwner(iniciarPartidaButton.getScene().getWindow());
            dadosStage.showAndWait();

            int quienComienza = dadosController.getQuienComienza(); // 1 = jugador, 2 = bot

            // Cargar la escena principal y pasar los nombres y el turno inicial
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Escenas/Juego_principal.fxml"));
            Parent root = loader.load();

            JuegoPrincipalController controller = loader.getController();
            controller.setNombresJugadores(nombre, nombreBot, quienComienza);
            controller.setTurnoInicial(quienComienza);

            Stage stage = (Stage) iniciarPartidaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}