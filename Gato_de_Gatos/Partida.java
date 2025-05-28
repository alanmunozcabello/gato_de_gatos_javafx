import java.util.ArrayList;
public class Partida{
    private static Partida instance;
    private int movimientosJ1;
    private int movimientosJ2;
    private Cuadrantes cuadrantes;
    private Gato cuadranteActualDeJuego;
    private Jugador jugadorX;
    private Jugador jugadorO;
    private ArrayList<Observador> observadores;

    private Partida(){
    }

    static Partida getInstance(){
        if(instance == null){
            instance = new Partida();
        }
        return instance;
    }

    public void Inicializar(Jugador jugador1, Jugador jugador2, Cuadrantes cuadrantes){
        this.jugadorX = jugador1;
        this.jugadorO = jugador2;
        this.cuadrantes = cuadrantes;
        this.movimientosJ1 = 0;
        this.movimientosJ2 = 0;
        // this.cuadranteActualDeJuego = cuadrantes.getCuadrante(0);
        this.observadores = new ArrayList<>();
    }

    public void setJugadores(Jugador j1, Jugador j2){
        this.jugadorX = j1;
        this.jugadorO = j2;
    }

    public void comenzarPartida(){ // iniciar la partida

    }

    public void terminarPartida(){ // darle cierre a la partida la partida

    }

    //metodos para aumentar los movimientos de los jugadores
    public void aumentarMovimientosJ1(){
        this.movimientosJ1++;
    }
    public void aumentarMovimientosJ2(){
        this.movimientosJ2++;
    }

    public void setCuadranteActualDeJuego(int x, int y){
        this.cuadranteActualDeJuego = this.cuadrantes.getCuadrante(x, y);
    }

    //public Gato seleccionarCuadranteDeJuego(){ //metodo para elegir el cuadrante de juego
        //logica para elegir cuadrante de juego
    //}-----------------------------------------------------

    //public boolean verificarVictoria(){ //verifica si uno de los jugadores ganó
        //logica para verificar victoria
    //}------------------------------------------------

    public void bloquearCuadrante(){ //bloquea el cuadrante de juego
        //logica para bloquear el cuadrante
    }

    public void turnoJugadorX(int x, int y){ //turno del jugador x
        //logica para los turnos
    }
    public void turnoJugadorO(int x, int y){ //turno del jugador o
        //logica para los turnos
    }

    public void agregarObservador(Observador observador){
        this.observadores.add(observador);
    }

    public void notificarObservadores(){
        for(Observador observador : this.observadores){
            observador.actualizar(cuadrantes);
        }
    }
}