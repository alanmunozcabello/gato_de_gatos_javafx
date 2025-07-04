package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

// Controlador para la ventana de tirada de dados
public class VentanaDadosController {
    // Botón para lanzar los dados
    @FXML private Button lanzarDadosButton;
    // ListView para mostrar los resultados de los lanzamientos
    @FXML private ListView<String> listViewResultados;

    // Variables para el control del turno y resultados de los dados
    private int turno = 1; // 1 = jugador 1, 2 = jugador 2
    private int dadoJ1 = 0;
    private int dadoJ2 = 0;
    private String nombreJ1 = "Jugador 1";
    private String nombreJ2 = "Jugador 2";
    // Callback opcional para notificar al controlador principal
    private Runnable onDecidirQuienComienza;

    // Permite establecer los nombres de los jugadores que lanzan los dados
    public void setNombres(String nombre1, String nombre2) {
        this.nombreJ1 = nombre1;
        this.nombreJ2 = nombre2;
    }

    // Permite establecer un callback para notificar cuando se decide quién comienza
    public void setOnDecidirQuienComienza(Runnable callback) {
        this.onDecidirQuienComienza = callback;
    }

    // Inicializa la ventana y configura el botón de lanzar dados
    @FXML
    private void initialize() {
        lanzarDadosButton.setOnAction(e -> lanzarDados());
        listViewResultados.getItems().add("Presiona el botón para que " + nombreJ1 + " lance el dado.");
    }

    // Lógica para lanzar los dados y decidir quién comienza la partida
    private void lanzarDados() {
        int resultado = (int) (Math.random() * 12 + 1);
        if (turno == 1) {
            dadoJ1 = resultado;
            listViewResultados.getItems().add(nombreJ1 + " lanzó: " + dadoJ1);
            turno = 2;
            lanzarDadosButton.setText("Lanzar dado: " + nombreJ2);
        } else {
            dadoJ2 = resultado;
            listViewResultados.getItems().add(nombreJ2 + " lanzó: " + dadoJ2);

            // Decidir quién comienza según los resultados
            if (dadoJ1 > dadoJ2) {
                listViewResultados.getItems().add("¡" + nombreJ1 + " comienza la partida!");
                mostrarAlerta("Resultado", "¡" + nombreJ1 + " comienza la partida!");
                cerrarVentana();
            } else if (dadoJ2 > dadoJ1) {
                listViewResultados.getItems().add("¡" + nombreJ2 + " comienza la partida!");
                mostrarAlerta("Resultado", "¡" + nombreJ2 + " comienza la partida!");
                cerrarVentana();
            } else {
                // Si hay empate, se reinicia el proceso
                listViewResultados.getItems().add("Empate, se lanzan los dados de nuevo.");
                dadoJ1 = 0;
                dadoJ2 = 0;
                turno = 1;
                lanzarDadosButton.setText("Lanzar dado: " + nombreJ1);
            }
        }
    }

    // Muestra una alerta informativa con el resultado
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Cierra la ventana y ejecuta el callback si está definido
    private void cerrarVentana() {
        if (onDecidirQuienComienza != null) {
            onDecidirQuienComienza.run();
        }
        Stage stage = (Stage) lanzarDadosButton.getScene().getWindow();
        stage.close();
    }

    // Devuelve quién comienza: 1 para el primer jugador, 2 para el segundo, 0 si hubo empate
    public int getQuienComienza() {
        if (dadoJ1 > dadoJ2) return 1;
        if (dadoJ2 > dadoJ1) return 2;
        return 0; // empate
    }
}