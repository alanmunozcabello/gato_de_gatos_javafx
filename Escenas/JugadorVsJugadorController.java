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
            // Lanzar ventana de dados
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

            // Cargar la escena principal y pasar los nombres y el turno inicial
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Escenas/Juego_principal.fxml"));
            Parent root = loader.load();

            JuegoPrincipalController controller = loader.getController();
            controller.setNombresJugadores(nombre1, nombre2, quienComienza);
            controller.setTurnoInicial(quienComienza);

            Stage stage = (Stage) iniciarPartidaButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}