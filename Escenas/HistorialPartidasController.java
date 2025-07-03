package Escenas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

import Gato_de_Gatos.Partida;

public class HistorialPartidasController {
    @FXML private ListView<String> listViewPartidas;

    public void setPartidas(ArrayList<Partida> partidas) {
        listViewPartidas.getItems().clear();
        for (Partida p : partidas) {
            listViewPartidas.getItems().add(p.toString());
        }
    }

    private void cambiarEscena(String fxml) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        if (fxml.equals("Historial_partidas.fxml")) {
            HistorialPartidasController controller = loader.getController();
            controller.setPartidas(partidas);
        }
        Stage stage = (Stage) jugarButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
}