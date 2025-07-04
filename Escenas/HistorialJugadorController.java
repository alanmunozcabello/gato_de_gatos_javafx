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
// Controlador para la escena de historial de jugador en JavaFX

public class HistorialJugadorController {
    @FXML private TextField nombreJugadorTextField; // Campo de texto para ingresar el nombre del jugador
    @FXML private ListView<String> listViewEstadisticas; // Lista para mostrar estadísticas del jugador
    @FXML private Button atrasButton; // Botón para volver al menú anterior

    private ArrayList<Jugador> jugadores; // Lista de jugadores disponibles
    
    // Permite inyectar la lista de jugadores desde otra escena/controlador
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @FXML
    private void initialize() {
        // Asigna el evento de búsqueda al presionar una tecla en el campo de texto
        nombreJugadorTextField.setOnKeyPressed(this::buscarJugador);
        // Asigna la acción de volver al menú al botón "Atrás"
        if (atrasButton != null) {
            atrasButton.setOnAction(e -> volverAlMenu());
        }
    }
    
    // Cambia la escena actual al menú inicial
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

    // Busca un jugador por nombre al presionar ENTER y muestra sus estadísticas
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
                } //si no se retorna es porque no se encontró al jugador
                listViewEstadisticas.getItems().add("Jugador no encontrado.");
            }
        }
    }

    // Cambia la escena a la especificada por el archivo FXML y transfiere la lista de jugadores si corresponde
    private void cambiarEscena(String fxml) {
        try {
            // Crea un cargador de FXML para cargar la nueva interfaz gráfica desde el archivo especificado
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            // Carga el archivo FXML y obtiene el nodo raíz de la nueva escena
            Parent root = loader.load();
        
            // Si el archivo FXML es "Historial_Jugador.fxml", transfiere la lista de jugadores al nuevo controlador
            if (fxml.equals("Historial_Jugador.fxml")) {
                // Obtiene el controlador asociado al FXML cargado
                HistorialJugadorController controller = loader.getController();
                // Pasa la lista de jugadores al nuevo controlador
                controller.setJugadores(jugadores);
            }
        
            // Obtiene la ventana (Stage) actual a partir de un campo de texto de la interfaz
            Stage stage = (Stage) nombreJugadorTextField.getScene().getWindow();
            // Cambia la escena de la ventana por la nueva escena cargada
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            // Si ocurre algún error, imprime la traza para depuración
            ex.printStackTrace();
        }
    }
}