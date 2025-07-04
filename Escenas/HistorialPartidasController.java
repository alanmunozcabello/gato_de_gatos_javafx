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

// Controlador para la escena del historial de partidas
public class HistorialPartidasController {
    @FXML private ListView<String> listViewPartidas; // Lista para mostrar partidas
    @FXML private Button atrasButton; // Botón para volver al menú

    private ArrayList<Partida> partidas; // Lista de objetos Partida

    // Inicializa el controlador, asignando acción al botón de volver
    @FXML
    private void initialize() {
        if (atrasButton != null) {
            atrasButton.setOnAction(e -> volverAlMenu());
        }
    }

    // Cambia la escena al menú inicial
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

    // Recibe y muestra la lista de partidas en el ListView
    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas; // Guarda la referencia
        listViewPartidas.getItems().clear();
        for (Partida p : partidas) {
            listViewPartidas.getItems().add(p.toString());
        }
    }

    // Cambia la escena a la vista de historial de partidas
    private void cambiarEscena(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            if (fxml.equals("Historial_partidas.fxml")) {
                // Si la nueva escena es el historial, pasa la lista de partidas al controlador
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