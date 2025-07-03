package Escenas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Jugadores.Jugador;
import Jugadores.botFacil;
import Jugadores.botDificil;
import Gato_de_Gatos.Partida;
import Gato_de_Gatos.Cuadrantes;
import Gato_de_Gatos.Gato;
import java.io.FileInputStream;
import Escenas.MenuInicialController;

public class JuegoPrincipalController {
    private String nombreJugador1;
    private String nombreJugador2;

    private Partida partida;
    private Jugador jugador1;
    private Jugador jugador2;
    private Cuadrantes cuadrantes;

    private boolean turnoX = true; // true = X, false = O
    private int cuadranteActualX = -1;
    private int cuadranteActualY = -1;

    @FXML private GridPane tableroGrid;
    @FXML private Text textJugadorX;
    @FXML private Text textJugadorO;
    @FXML private Text textTurno;
    @FXML private Button surrenderButton;
    @FXML private Pane gifPane;

    private ImageView gifImageView;

    private final int SIZE = 9; // 9x9 tablero

    public void setNombresJugadores(String nombre1, String nombre2) {
        this.nombreJugador1 = nombre1;
        this.nombreJugador2 = nombre2;
        inicializarPartida();
        actualizarTextos();
    }

    private void inicializarPartida() {
        if ("BotFacil".equals(nombreJugador2)) {
            jugador1 = buscarJugadorExistente(nombreJugador1);
            if (jugador1 == null) jugador1 = new Jugador(nombreJugador1);
            jugador2 = new botFacil('O');
        } else if ("BotDificil".equals(nombreJugador2)) {
            jugador1 = buscarJugadorExistente(nombreJugador1);
            if (jugador1 == null) jugador1 = new Jugador(nombreJugador1);
            jugador2 = new botDificil('O');
        } else {
            jugador1 = buscarJugadorExistente(nombreJugador1);
            if (jugador1 == null) jugador1 = new Jugador(nombreJugador1);
            jugador2 = buscarJugadorExistente(nombreJugador2);
            if (jugador2 == null) jugador2 = new Jugador(nombreJugador2);
        }
        cuadrantes = new Cuadrantes();
        partida = Partida.getInstance();
        partida.Inicializar(jugador1, jugador2, cuadrantes);
    }

    @FXML
    private void initialize() {
        // Crea los 81 botones dinámicamente
        for (int fila = 0; fila < SIZE; fila++) {
            for (int col = 0; col < SIZE; col++) {
                Button btn = new Button();
                btn.setPrefSize(50, 50);
                btn.setStyle("-fx-font-size: 18px;");
                final int f = fila;
                final int c = col;
                btn.setOnAction(e -> manejarClick(f, c, btn));
                tableroGrid.add(btn, col, fila);
            }
        }

        // Inicializa textos y GIF
        actualizarTextos();
        surrenderButton.setOnAction(e -> handleSurrender());

        gifImageView = new ImageView();
        gifImageView.setFitWidth(gifPane.getPrefWidth());
        gifImageView.setFitHeight(gifPane.getPrefHeight());
        gifImageView.setPreserveRatio(true);
        gifPane.getChildren().clear();
        gifPane.getChildren().add(gifImageView);
        mostrarGif("./manion_bailando.gif"); // Cambia la ruta por la de tu GIF
    }

    private void actualizarTextos() {
        if (textJugadorX != null && jugador1 != null)
            textJugadorX.setText("Jugador X: " + jugador1.getNombre());
        if (textJugadorO != null && jugador2 != null)
            textJugadorO.setText("Jugador O: " + jugador2.getNombre());
        if (textTurno != null)
            textTurno.setText("Turno jugador " + (turnoX ? "X" : "O"));
    }

    private void manejarClick(int fila, int columna, Button btn) {
        int cuadFila = fila / 3;
        int cuadCol = columna / 3;
        int celdaFila = fila % 3;
        int celdaCol = columna % 3;

        // Solo permitir jugada en el cuadrante correcto (si aplica)
        if (cuadranteActualX != -1 && cuadranteActualY != -1) {
            if (cuadFila != cuadranteActualX || cuadCol != cuadranteActualY) {
                mostrarAlerta("Movimiento inválido", "Debes jugar en el cuadrante (" + (cuadranteActualX+1) + "," + (cuadranteActualY+1) + ")");
                return;
            }
        }

        // Verificar si la celda está libre
        Gato cuadrante = cuadrantes.getCuadrante(cuadFila, cuadCol);
        if (!cuadrante.verificarCasillaOcupada(celdaFila, celdaCol)) {
            return;
        }

        // Realizar jugada
        char simbolo = turnoX ? 'X' : 'O';
        cuadrante.marcarSeleccion(celdaFila, celdaCol, simbolo);
        btn.setText(String.valueOf(simbolo));
        btn.setDisable(true);

        // Bloquear cuadrante si corresponde
        cuadrantes.bloquearCuadrante(cuadFila, cuadCol);

        // Actualizar cuadrante actual para el próximo turno
        cuadranteActualX = celdaFila;
        cuadranteActualY = celdaCol;

        // Verificar victoria o empate
        int estado = partida.verificarVictoria();
        if (estado == 1) {
            mostrarAlerta("¡Victoria!", "¡Ganó el jugador " + (turnoX ? jugador1.getNombre() : jugador2.getNombre()) + "!");
            bloquearTablero();
            finalizarPartida();
            return;
        } else if (estado == 2) {
            mostrarAlerta("Empate", "¡La partida terminó en empate!");
            bloquearTablero();
            finalizarPartida();
            return;
        }

        // Cambiar turno
        turnoX = !turnoX;
        actualizarTextos();

        // Si el siguiente es bot, hacer jugada automática
        if (!turnoX && jugador2 instanceof botFacil) {
            jugadaBot((botFacil)jugador2);
        } else if (!turnoX && jugador2 instanceof botDificil) {
            jugadaBot((botDificil)jugador2);
        }
    }

    private void jugadaBot(Jugador bot) {
        Gato cuadrante = cuadrantes.getCuadrante(cuadranteActualX, cuadranteActualY);
        bot.hacerSeleccion(cuadrante);
        int fila = bot.getFila();
        int col = bot.getColumna();

        // Actualiza el botón correspondiente
        int globalFila = cuadranteActualX * 3 + fila;
        int globalCol = cuadranteActualY * 3 + col;
        for (javafx.scene.Node node : tableroGrid.getChildren()) {
            if (GridPane.getRowIndex(node) == globalFila && GridPane.getColumnIndex(node) == globalCol) {
                Button btn = (Button) node;
                btn.setText(String.valueOf(bot.getSimbolo()));
                btn.setDisable(true);
                break;
            }
        }

        cuadrantes.bloquearCuadrante(cuadranteActualX, cuadranteActualY);

        // Actualizar cuadrante actual para el próximo turno
        cuadranteActualX = fila;
        cuadranteActualY = col;

        // Verificar victoria o empate
        int estado = partida.verificarVictoria();
        if (estado == 1) {
            mostrarAlerta("¡Victoria!", "¡Ganó el bot!");
            bloquearTablero();
            finalizarPartida();
            return;
        } else if (estado == 2) {
            mostrarAlerta("Empate", "¡La partida terminó en empate!");
            bloquearTablero();
            finalizarPartida();
            return;
        }

        // Cambiar turno de vuelta al jugador
        turnoX = !turnoX;
        actualizarTextos();
    }

    private void handleSurrender() {
        String perdedor = turnoX ? jugador1.getNombre() : jugador2.getNombre();
        String ganador = turnoX ? jugador2.getNombre() : jugador1.getNombre();
        mostrarAlerta("Surrender", "¡" + perdedor + " se ha rendido!\nGanador: " + ganador);
        bloquearTablero();
        finalizarPartida();
    }

    private void bloquearTablero() {
        for (javafx.scene.Node node : tableroGrid.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
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

    private void mostrarGif(String ruta) {
        try {
            Image gif = new Image(new FileInputStream(ruta));
            gifImageView.setImage(gif);
        } catch (Exception e) {
            // Si falla, deja el placeholder
        }
    }

    private Jugador buscarJugadorExistente(String nombre) {
        for (Jugador j : MenuInicialController.jugadores) {
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                return j;
            }
        }
        return null;
    }
    
    private void finalizarPartida() {
        int estado = partida.verificarVictoria(); // 1 = victoria, 2 = empate
        
        if (estado == 1) {
            // Victoria: turnoX indica quién fue el último en jugar
            if (turnoX) {
                jugador1.aumentarPartidasGanadas();
                jugador2.aumentarPartidasPerdidas();
                partida.setJugadorGanador(jugador1); // <--- NUEVO
            } else {
                jugador2.aumentarPartidasGanadas();
                jugador1.aumentarPartidasPerdidas();
                partida.setJugadorGanador(jugador2); // <--- NUEVO
            }
        } else if (estado == 2) {
            jugador1.aumentarPartidasEmpatadas();
            jugador2.aumentarPartidasEmpatadas();
            partida.setJugadorGanador(null); // <--- Empate, sin ganador
        }
        jugador1.aumentarPartidasJugadas();
        jugador2.aumentarPartidasJugadas();
    
        if (!MenuInicialController.jugadores.contains(jugador1)) MenuInicialController.jugadores.add(jugador1);
        if (!MenuInicialController.jugadores.contains(jugador2)) MenuInicialController.jugadores.add(jugador2);
        MenuInicialController.partidas.add(partida.crearCopia());
    
        // Serializar aquí para guardar los datos inmediatamente
        Serializador.Serializar serializar = new Serializador.Serializar();
        serializar.serializarJugadores(MenuInicialController.jugadores);
        serializar.serializarPartidas(MenuInicialController.partidas);
    
        // Volver al menú inicial
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu_inicial.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tableroGrid.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}