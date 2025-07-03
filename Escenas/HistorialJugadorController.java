package Escenas;

import Jugadores.Jugador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HistorialJugadorController {
    @FXML private TextField nombreJugadorTextField;
    @FXML private ListView<String> listViewEstadisticas;
    @FXML private Button atrasButton; 

    private ArrayList<Jugador> jugadores;
    
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @FXML
    private void initialize() {
        nombreJugadorTextField.setOnKeyPressed(this::buscarJugador);
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

    private void buscarJugador(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String nombre = nombreJugadorTextField.getText();
            listViewEstadisticas.getItems().clear();
            if (jugadores != null) {
                for (Jugador j : jugadores) {
                    if (j.getNombre().equalsIgnoreCase(nombre)) {
                        listViewEstadisticas.getItems().add(j.toString());
                        return;
                    }
                }
                listViewEstadisticas.getItems().add("Jugador no encontrado.");
            }
        }
    }

    private void cambiarEscena(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            if (fxml.equals("Historial_Jugador.fxml")) {
                HistorialJugadorController controller = loader.getController();
                controller.setJugadores(jugadores);
            }
            Stage stage = (Stage) nombreJugadorTextField.getScene().getWindow(); // <-- CORREGIDO
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}