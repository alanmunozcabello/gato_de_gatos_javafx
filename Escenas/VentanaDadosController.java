package Escenas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class VentanaDadosController {
    @FXML private Button lanzarDadosButton;
    @FXML private ListView<String> listViewResultados;

    @FXML
    private void initialize() {
        lanzarDadosButton.setOnAction(e -> lanzarDados());
    }

    private void lanzarDados() {
        int resultado = (int) (Math.random() * 12 + 1);
        listViewResultados.getItems().add("Resultado: " + resultado);
    }
}