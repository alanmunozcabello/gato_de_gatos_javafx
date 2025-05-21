public class Partida{
    private static Partida instance;
    private int movimientosJ1;
    private int movimientosJ2;
    private Cuadrantes cuadrantes;
    private Gato cuadranteActualDeJuego;
    private Jugador jugadorX;
    private Jugador jugadorO;
    private ArrayList<Observador> observadores;
}