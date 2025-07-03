import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Serializador.Serializar;
import Escenas.MenuInicialController;
import Escenas.*;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(getClass().getResource("/Escenas/Menu_inicial.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/Escenas/Menu_inicial.fxml"));
        System.out.println(getClass().getResource("/Escenas/Menu_inicial.fxml"));
        primaryStage.setTitle("Ultimate Tic-Tac-Toe");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            Serializar serializar = new Serializar();
            serializar.serializarJugadores(MenuInicialController.jugadores);
            serializar.serializarPartidas(MenuInicialController.partidas);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}