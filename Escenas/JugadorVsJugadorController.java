package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class JugadorVsJugadorController {
    @FXML private TextField nombreJugador1Field;
    @FXML private TextField nombreJugador2Field;
    @FXML private Button iniciarPartidaButton;

    @FXML
    private void initialize() {
        iniciarPartidaButton.setOnAction(e -> iniciarPartida());
    }

    private void iniciarPartida() {
        String nombre1 = nombreJugador1Field.getText();
        String nombre2 = nombreJugador2Field.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Juego_principal.fxml"));
            Parent root = loader.load();

            JuegoPrincipalController controller = loader.getController();
            controller.setNombresJugadores(nombre1, nombre2);

            Stage stage = (Stage) iniciarPartidaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}