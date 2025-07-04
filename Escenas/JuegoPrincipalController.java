package Escenas;

// Importaciones de JavaFX y clases del juego
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

/*
    Controlador principal de la vista de juego.
    Maneja la lógica de la partida, interacción con el tablero, turnos, bots y serialización.
 */
public class JuegoPrincipalController {
    // Nombres de los jugadores (X y O)
    private String nombreJugador1;
    private String nombreJugador2;

    // Instancias de la partida, jugadores y cuadrantes
    private Partida partida;
    private Jugador jugador1;
    private Jugador jugador2;
    private Cuadrantes cuadrantes;

    // Control de turno y cuadrante actual
    private boolean turnoX = true; // true = X, false = O
    private int cuadranteActualX = -1;
    private int cuadranteActualY = -1;

    // Elementos de la interfaz gráfica
    @FXML private GridPane tableroGrid;
    @FXML private Text textJugadorX;
    @FXML private Text textJugadorO;
    @FXML private Text textTurno;
    @FXML private Button surrenderButton;
    @FXML private Pane gifPane;

    private ImageView gifImageView;

    private final int SIZE = 9; // Tamaño del tablero (9x9)

    /*
        Recibe los nombres de los jugadores y quién comienza (1 o 2).
        Inicializa la partida y, si el bot es X, realiza el primer turno automáticamente.
    */
    public void setNombresJugadores(String nombre1, String nombre2, int quienComienza) {
        if (quienComienza == 1) {
            this.nombreJugador1 = nombre1; // X
            this.nombreJugador2 = nombre2; // O
        } else {
            this.nombreJugador1 = nombre2; // X
            this.nombreJugador2 = nombre1; // O
        }
        inicializarPartida();
        actualizarTextos();

        // Si el bot es X, realiza el primer turno automáticamente
        if (jugador1 instanceof botFacil || jugador1 instanceof botDificil) {
            turnoInicialBot();
        }
    }

    /*
        Busca un jugador existente por nombre o lo crea si no existe.
    */
    private Jugador obtenerJugador(String nombre) {
        Jugador existente = buscarJugadorExistente(nombre);
        if (existente != null) return existente;
        Jugador nuevo = new Jugador(nombre);
        MenuInicialController.jugadores.add(nuevo);
        return nuevo;
    }

    /**
        Establece el turno inicial según quién comienza (1 = jugador1/X, 2 = jugador2/O).
    */
    public void setTurnoInicial(int quienComienza) {
        this.turnoX = (quienComienza == 1);
    }

    /*
        Inicializa la partida, asignando los jugadores y cuadrantes.
        Si el modo es contra bot, asegura que el bot esté en la lista global.
    */
    private void inicializarPartida() {
        if ("BotFacil".equals(nombreJugador2)) {
            jugador1 = obtenerJugador(nombreJugador1);
            jugador2 = null;
            for (Jugador j : MenuInicialController.jugadores) {
                if (j instanceof botFacil) {
                    jugador2 = j;
                    break;
                }
            }
            if (jugador2 == null) {
                jugador2 = new botFacil('O');
                MenuInicialController.jugadores.add(jugador2);
            }
        } else if ("BotDificil".equals(nombreJugador2)) {
            jugador1 = obtenerJugador(nombreJugador1);
            jugador2 = null;
            for (Jugador j : MenuInicialController.jugadores) {
                if (j instanceof botDificil) {
                    jugador2 = j;
                    break;
                }
            }
            if (jugador2 == null) {
                jugador2 = new botDificil('O');
                MenuInicialController.jugadores.add(jugador2);
            }
        } else {
            jugador1 = obtenerJugador(nombreJugador1);
            jugador2 = obtenerJugador(nombreJugador2);
        }
        cuadrantes = new Cuadrantes();
        partida = Partida.getInstance();
        partida.Inicializar(jugador1, jugador2, cuadrantes);
    }

    /*
        Inicializa la interfaz gráfica: crea los botones del tablero, configura textos y GIF.
    */
    @FXML
    private void initialize() {
        // Crea los 81 botones del tablero y les asigna su evento de click
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

    /*
        Actualiza los textos de la interfaz con los nombres y el turno actual.
    */
    private void actualizarTextos() {
        if (textJugadorX != null && jugador1 != null)
            textJugadorX.setText("Jugador X: " + jugador1.getNombre());
        if (textJugadorO != null && jugador2 != null)
            textJugadorO.setText("Jugador O: " + jugador2.getNombre());
        if (textTurno != null)
            textTurno.setText("Turno jugador " + (turnoX ? "X" : "O"));
    }

    /*
        Maneja el click de un botón del tablero (jugada de un jugador humano).
        Valida el cuadrante, marca la casilla, actualiza movimientos y verifica victoria/empate.
        Si el siguiente turno es de un bot, lo ejecuta automáticamente.
    */
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

        // Verifica si la celda está libre
        Gato cuadrante = cuadrantes.getCuadrante(cuadFila, cuadCol);
        if (!cuadrante.verificarCasillaOcupada(celdaFila, celdaCol)) {
            return;
        }

        // Realiza la jugada
        char simbolo = turnoX ? 'X' : 'O';
        cuadrante.marcarSeleccion(celdaFila, celdaCol, simbolo);
        btn.setText(String.valueOf(simbolo));
        btn.setDisable(true);

        // Incrementa movimientos del jugador correspondiente
        if (simbolo == 'X') {
            partida.aumentarMovimientosJX();
        } else {
            partida.aumentarMovimientosJO();
        }

        // Bloquea el cuadrante si corresponde
        cuadrantes.bloquearCuadrante(cuadFila, cuadCol);

        // Actualiza el cuadrante actual para el próximo turno
        cuadranteActualX = celdaFila;
        cuadranteActualY = celdaCol;

        // Verifica victoria o empate
        int estado = partida.verificarVictoria();
        if (estado == 1) {
            mostrarAlerta("¡Victoria!", "¡Ganó el jugador " + (turnoX ? jugador1.getNombre() : jugador2.getNombre()) + "!");
            bloquearTablero();
            finalizarPartida(false);
            return;
        } else if (estado == 2) {
            mostrarAlerta("Empate", "¡La partida terminó en empate!");
            bloquearTablero();
            finalizarPartida(false);
            return;
        }

        // Cambia el turno
        turnoX = !turnoX;
        actualizarTextos();

        // Si el siguiente turno es de un bot, ejecuta su jugada automáticamente
        if (!turnoX && jugador2 instanceof botFacil) {
            jugadaBot((botFacil)jugador2);
        } else if (!turnoX && jugador2 instanceof botDificil) {
            jugadaBot((botDificil)jugador2);
        }
    }

    /*
        Realiza la jugada automática del bot en el cuadrante actual.
        Marca la casilla, actualiza movimientos, verifica victoria/empate y cambia el turno.
    */
    private void jugadaBot(Jugador bot) {
        Gato cuadrante = cuadrantes.getCuadrante(cuadranteActualX, cuadranteActualY);
        bot.hacerSeleccion(cuadrante);
        int fila = bot.getFila();
        int col = bot.getColumna();

        // Actualiza el botón correspondiente en la interfaz
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

        // Incrementa movimientos del bot
        if (bot.getSimbolo() == 'X') {
            partida.aumentarMovimientosJX();
        } else {
            partida.aumentarMovimientosJO();
        }

        cuadrantes.bloquearCuadrante(cuadranteActualX, cuadranteActualY);

        // Actualiza el cuadrante actual para el próximo turno
        cuadranteActualX = fila;
        cuadranteActualY = col;

        // Verifica victoria o empate
        int estado = partida.verificarVictoria();
        if (estado == 1) {
            mostrarAlerta("¡Victoria!", "¡Ganó el bot!");
            bloquearTablero();
            finalizarPartida(false);
            return;
        } else if (estado == 2) {
            mostrarAlerta("Empate", "¡La partida terminó en empate!");
            bloquearTablero();
            finalizarPartida(false);
            return;
        }

        // Cambia el turno de vuelta al jugador humano
        turnoX = !turnoX;
        actualizarTextos();
    }

    /*
        Realiza el primer turno automático del bot cuando le toca iniciar la partida.
        Elige un cuadrante y una casilla aleatoria, marca la jugada y actualiza el estado.
     */
    private void turnoInicialBot() {
        // El bot elige un cuadrante aleatorio para iniciar
        int cuadX = (int) (Math.random() * 3);
        int cuadY = (int) (Math.random() * 3);
        Gato cuadrante = cuadrantes.getCuadrante(cuadX, cuadY);

        // El bot elige una casilla dentro del cuadrante
        jugador1.seleccionarCuadranteDeJuego(cuadrantes); // actualiza fila y columna del bot
        jugador1.hacerSeleccion(cuadrante);
        int fila = jugador1.getFila();
        int col = jugador1.getColumna();

        // Actualiza el botón correspondiente en la interfaz
        int globalFila = cuadX * 3 + fila;
        int globalCol = cuadY * 3 + col;
        for (javafx.scene.Node node : tableroGrid.getChildren()) {
            if (GridPane.getRowIndex(node) == globalFila && GridPane.getColumnIndex(node) == globalCol) {
                Button btn = (Button) node;
                btn.setText(String.valueOf(jugador1.getSimbolo()));
                btn.setDisable(true);
                break;
            }
        }

        // Incrementa movimientos del bot
        partida.aumentarMovimientosJX();

        cuadrantes.bloquearCuadrante(cuadX, cuadY);

        // Actualiza el cuadrante actual para el próximo turno
        cuadranteActualX = fila;
        cuadranteActualY = col;

        // Verifica victoria o empate
        int estado = partida.verificarVictoria();
        if (estado == 1) {
            mostrarAlerta("¡Victoria!", "¡Ganó el bot!");
            bloquearTablero();
            finalizarPartida(false);
            return;
        } else if (estado == 2) {
            mostrarAlerta("Empate", "¡La partida terminó en empate!");
            bloquearTablero();
            finalizarPartida(false);
            return;
        }

        // Cambia el turno de vuelta al jugador humano
        turnoX = false;
        actualizarTextos();
    }


    //Maneja la acción de rendirse. Actualiza estadísticas y termina la partida.
    private void handleSurrender() {
        String perdedor = turnoX ? jugador1.getNombre() : jugador2.getNombre();
        String ganador = turnoX ? jugador2.getNombre() : jugador1.getNombre();
        mostrarAlerta("Surrender", "¡" + perdedor + " se ha rendido!\nGanador: " + ganador);
        bloquearTablero();
        finalizarPartida(true);
    }

    //Bloquea todos los botones del tablero (deshabilita el tablero).
    private void bloquearTablero() {
        for (javafx.scene.Node node : tableroGrid.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
        }
    }

    //Muestra una alerta informativa en pantalla.
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Muestra un GIF en el panel correspondiente.
    private void mostrarGif(String ruta) {
        try {
            Image gif = new Image(new FileInputStream(ruta));
            gifImageView.setImage(gif);
        } catch (Exception e) {
            // Si falla, deja el placeholder
        }
    }

    // Busca un jugador existente por nombre en la lista global.
    private Jugador buscarJugadorExistente(String nombre) {
        for (Jugador j : MenuInicialController.jugadores) {
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                return j;
            }
        }
        return null;
    }

    // Finaliza la partida, actualizando estadísticas y serializando datos.
    // Si fue surrender, asigna ganador/perdedor según el turno.
    // Si fue victoria/empate normal, consulta el estado de la partida.
    private void finalizarPartida(boolean fueSurrender) {
        if (fueSurrender) {
            // El que NO es turnoX es el ganador
            if (turnoX) {
                jugador2.aumentarPartidasGanadas();
                jugador1.aumentarPartidasPerdidas();
                partida.setJugadorGanador(jugador2);
            } else {
                jugador1.aumentarPartidasGanadas();
                jugador2.aumentarPartidasPerdidas();
                partida.setJugadorGanador(jugador1);
            }
        } else {
            int estado = partida.verificarVictoria(); // 1 = victoria, 2 = empate
            if (estado == 1) {
                if (turnoX) {
                    jugador1.aumentarPartidasGanadas();
                    jugador2.aumentarPartidasPerdidas();
                    partida.setJugadorGanador(jugador1);
                } else {
                    jugador2.aumentarPartidasGanadas();
                    jugador1.aumentarPartidasPerdidas();
                    partida.setJugadorGanador(jugador2);
                }
            } else if (estado == 2) {
                jugador1.aumentarPartidasEmpatadas();
                jugador2.aumentarPartidasEmpatadas();
                partida.setJugadorGanador(null);
            }
        }
        jugador1.aumentarPartidasJugadas();
        jugador2.aumentarPartidasJugadas();

        // Guarda la partida en el historial
        MenuInicialController.partidas.add(partida.crearCopia());

        // Serializa jugadores y partidas para persistencia
        Serializador.Serializar serializar = new Serializador.Serializar();
        serializar.serializarJugadores(MenuInicialController.jugadores);
        serializar.serializarPartidas(MenuInicialController.partidas);

        // Vuelve al menú inicial
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu_inicial.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tableroGrid.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Finaliza la partida por defecto (no surrender).
    private void finalizarPartida() {
        finalizarPartida(false);
    }
}