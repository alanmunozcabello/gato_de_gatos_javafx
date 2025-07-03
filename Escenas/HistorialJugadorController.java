package Escenas;

import Jugadores.Jugador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HistorialJugadorController {
    @FXML private TextField nombreJugadorTextField;
    @FXML private ListView<String> listViewEstadisticas;

    private ArrayList<Jugador> jugadores;
    
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @FXML
    private void initialize() {
        nombreJugadorTextField.setOnKeyPressed(this::buscarJugador);
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
        Stage stage = (Stage) jugarButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
}