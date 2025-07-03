package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class VentanaDadosController {
    @FXML private Button lanzarDadosButton;
    @FXML private ListView<String> listViewResultados;

    private int turno = 1; // 1 = jugador 1, 2 = jugador 2
    private int dadoJ1 = 0;
    private int dadoJ2 = 0;
    private String nombreJ1 = "Jugador 1";
    private String nombreJ2 = "Jugador 2";
    private Runnable onDecidirQuienComienza; // callback para notificar al controlador principal

    public void setNombres(String nombre1, String nombre2) {
        this.nombreJ1 = nombre1;
        this.nombreJ2 = nombre2;
    }

    public void setOnDecidirQuienComienza(Runnable callback) {
        this.onDecidirQuienComienza = callback;
    }

    @FXML
    private void initialize() {
        lanzarDadosButton.setOnAction(e -> lanzarDados());
        listViewResultados.getItems().add("Presiona el botón para que " + nombreJ1 + " lance el dado.");
    }

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

            // Decidir quién comienza
            if (dadoJ1 > dadoJ2) {
                listViewResultados.getItems().add("¡" + nombreJ1 + " comienza la partida!");
                mostrarAlerta("Resultado", "¡" + nombreJ1 + " comienza la partida!");
                cerrarVentana();
            } else if (dadoJ2 > dadoJ1) {
                listViewResultados.getItems().add("¡" + nombreJ2 + " comienza la partida!");
                mostrarAlerta("Resultado", "¡" + nombreJ2 + " comienza la partida!");
                cerrarVentana();
            } else {
                listViewResultados.getItems().add("Empate, se lanzan los dados de nuevo.");
                dadoJ1 = 0;
                dadoJ2 = 0;
                turno = 1;
                lanzarDadosButton.setText("Lanzar dado: " + nombreJ1);
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana() {
        // Llama al callback si existe (puedes usarlo para notificar al controlador principal)
        if (onDecidirQuienComienza != null) {
            onDecidirQuienComienza.run();
        }
        Stage stage = (Stage) lanzarDadosButton.getScene().getWindow();
        stage.close();
    }

    // Puedes agregar un método para obtener quién comienza si lo necesitas
    public int getQuienComienza() {
        if (dadoJ1 > dadoJ2) return 1;
        if (dadoJ2 > dadoJ1) return 2;
        return 0; // empate
    }
}