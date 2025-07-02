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

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Juego_principal.fxml"));
            Parent root = loader.load();

            // Suponiendo que tienes un controlador para Juego_principal.fxml:
            JuegoPrincipalController controller = loader.getController();
            controller.setNombresJugadores(nombre, "BotFacil");

            Stage stage = (Stage) iniciarPartidaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}