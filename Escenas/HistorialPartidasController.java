package Escenas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

import Gato_de_Gatos.Partida;

public class HistorialPartidasController {
    @FXML private ListView<String> listViewPartidas;
    @FXML private Button atrasButton; 

    private ArrayList<Partida> partidas;

    @FXML
    private void initialize() {
        if (atrasButton != null) {
            atrasButton.setOnAction(e -> volverAlMenu());
        }
    }

    private void volverAlMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu_inicial.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) atrasButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas; // Guarda la referencia
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
                controller.setPartidas(partidas); // Ahora sí existe
            }
            Stage stage = (Stage) listViewPartidas.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}