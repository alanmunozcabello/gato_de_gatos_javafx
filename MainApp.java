import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Serializador.Serializar;
import Escenas.MenuInicialController;
import Escenas.*;

// Clase principal de la aplicación JavaFX
public class MainApp extends Application {
    // Método principal de inicio de la aplicación
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga la escena inicial del menú principal desde el archivo FXML
        System.out.println(getClass().getResource("/Escenas/Menu_inicial.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/Escenas/Menu_inicial.fxml"));
        System.out.println(getClass().getResource("/Escenas/Menu_inicial.fxml"));
        primaryStage.setTitle("Ultimate Tic-Tac-Toe");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        // Al cerrar la ventana, serializa los datos de jugadores y partidas para guardarlos
        primaryStage.setOnCloseRequest(event -> {
            Serializar serializar = new Serializar();
            serializar.serializarJugadores(MenuInicialController.jugadores);
            serializar.serializarPartidas(MenuInicialController.partidas);
        });
    }

    // Método main para lanzar la aplicación
    public static void main(String[] args) {
        launch(args);
    }
}