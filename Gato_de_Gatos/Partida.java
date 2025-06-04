import java.util.ArrayList;
import java.io.Serializable;
import java.lang.Thread;
public class Partida implements Serializable{
    private static Partida instance;
    private boolean partidaEnCurso;
    private int movimientosJX;
    private int movimientosJO;
    private Cuadrantes cuadrantes;
    private Gato cuadranteActualDeJuego;
    private Jugador jugadorX;
    private Jugador jugadorO;
    private Jugador jugadorGanador;
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
        this.movimientosJX = 0;
        this.movimientosJO = 0;
        this.jugadorGanador = null;
        // this.cuadranteActualDeJuego = cuadrantes.getCuadrante(0);
        this.observadores = new ArrayList<>();
    }

    public void setJugadores(Jugador j1, Jugador j2){
        this.jugadorX = j1;
        this.jugadorO = j2;
    }

    public void comenzarPartida(){ // iniciar la partida
        //PREVIAMENTE A JUGAR------------------------------------------------------
        //ver quien es X y quien es O
        partidaEnCurso = true;
        Dados dados=new Dados();
        int dadoJ1=0;
        int dadoJ2=0;
        System.out.println("Para determinar quien será la X y quien será O... ¡se lanzarán dados!\n");
        while(dadoJ1==dadoJ2){
            //lanza el jugador1
            System.out.println("¡Tira el jugador 1!\n");
            dadoJ1=dados.tirarDados();
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println("El jugador 1 consigue ¡"+dadoJ1+"!");
            //lanza el jugador2
            System.out.println("¡Tira el jugador 2!\n");
            dadoJ2=dados.tirarDados();
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println("El jugador 2 consigue ¡"+dadoJ2+"!");
            if(dadoJ1==dadoJ2){
                System.out.println("¡Empate... se hará otra ronda de dados!");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
        if(dadoJ1>dadoJ2){
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println("¡El jugador 1 será X!");
            //se quedan tal cual
            jugadorX.setSimbolo('X');
            jugadorO.setSimbolo('O');
        }
        if(dadoJ1<dadoJ2){
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println("¡El jugador 2 será X!");
            Jugador tempJ1=jugadorX; //se le da al jugador 2 la X
            jugadorX=jugadorO;
            jugadorO=tempJ1;
            jugadorX.setSimbolo('X');
            jugadorO.setSimbolo('O');
        }
        ////--------------------------------------------------------------------------
        //Dar comienzo a los turnos
        notificarObservadores();
        turnoJugadorX(-1, -1);
    }

    public Partida crearCopia(){
        Partida copia = new Partida();
        copia.jugadorX = this.jugadorX;
        copia.jugadorO = this.jugadorO;
        copia.jugadorGanador = this.jugadorGanador;
        copia.movimientosJX = this.movimientosJX;
        copia.movimientosJO = this.movimientosJO;
        return copia;
    }

    public void terminarPartida(){ // darle cierre a la partida la partida
        System.out.println("partida terminada\n");
        partidaEnCurso = false;
    }

    //metodos para aumentar los movimientos de los jugadores
    public void aumentarMovimientosJX(){
        this.movimientosJX++;
    }
    public void aumentarMovimientosJO(){
        this.movimientosJO++;
    }

    public void setCuadranteActualDeJuego(int x, int y){
        this.cuadranteActualDeJuego = this.cuadrantes.getCuadrante(x, y);
    }
    
    

    public int verificarVictoria(){ //verifica si uno de los jugadores ganó (a nivel cuadrantes!)
        int cuadrantesLibres = 0;
        // Chequear X, O y horizontales
        for(int i = 0; i < 3; i++){
            if(cuadrantes.getCuadrante(i, 0).getEstado().equals("X") && 
                cuadrantes.getCuadrante(i, 1).getEstado().equals("X") && 
                cuadrantes.getCuadrante(i, 2).getEstado().equals("X")){
                return 1;
            }
            if(cuadrantes.getCuadrante(i, 0).getEstado().equals("O") && 
                cuadrantes.getCuadrante(i, 1).getEstado().equals("O") && 
                cuadrantes.getCuadrante(i, 2).getEstado().equals("O")){
                return 1;
            }
        }
        // Chequear X y O verticales
        for(int j = 0; j < 3; j++){
            if(cuadrantes.getCuadrante(0, j).getEstado().equals("X") && 
                cuadrantes.getCuadrante(1, j).getEstado().equals("X") && 
                cuadrantes.getCuadrante(2, j).getEstado().equals("X")){
                return 1;
            }
            if(cuadrantes.getCuadrante(0, j).getEstado().equals("O") && 
                cuadrantes.getCuadrante(1, j).getEstado().equals("O") && 
                cuadrantes.getCuadrante(2, j).getEstado().equals("O")){
                return 1;
            }
        }
        // Chequear X y O diagonales
        if(cuadrantes.getCuadrante(0, 0).getEstado().equals("X") && 
            cuadrantes.getCuadrante(1, 1).getEstado().equals("X") && 
            cuadrantes.getCuadrante(2, 2).getEstado().equals("X")){
            return 1;
        }
        if(cuadrantes.getCuadrante(0, 0).getEstado().equals("O") && 
            cuadrantes.getCuadrante(1, 1).getEstado().equals("O") && 
            cuadrantes.getCuadrante(2, 2).getEstado().equals("O")){
            return 1;
        }
        if(cuadrantes.getCuadrante(0, 2).getEstado().equals("X") && 
            cuadrantes.getCuadrante(1, 1).getEstado().equals("X") && 
            cuadrantes.getCuadrante(2, 0).getEstado().equals("X")){
            return 1;
        }
        if(cuadrantes.getCuadrante(0, 2).getEstado().equals("O") && 
            cuadrantes.getCuadrante(1, 1).getEstado().equals("O") && 
            cuadrantes.getCuadrante(2, 0).getEstado().equals("O")){
            return 1;
        }

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(cuadrantes.getCuadrante(i, j).getEstado().equals("libre")){
                    cuadrantesLibres++;
                }
            }
        }

        if(cuadrantesLibres == 0){
            return 2;
        }
        return 3;
    }

    public void turnoJugadorX(int x, int y){ //turno del jugador X
        if(partidaEnCurso){
            System.out.println("TURNO X--------------------------------");
            if(!cuadrantes.hayCuadrantesLibres()){
                jugadorX.aumentarPartidasEmpatadas();
                jugadorO.aumentarPartidasEmpatadas();
                jugadorX.aumentarPartidasJugadas();
                jugadorO.aumentarPartidasJugadas();
                System.out.println("Empate");
                terminarPartida();
                return;
            }
            if(x==-1 || y==-1){ //unicamente al inicio de la partida
                cuadranteActualDeJuego=jugadorX.seleccionarCuadranteDeJuego(cuadrantes);
                jugadorX.setSurrender(jugadorX.hacerSeleccion(cuadranteActualDeJuego));//turno del jugador X
                if(jugadorX.getSurrender()){
                    jugadorGanador = jugadorO;
                    jugadorX.aumentarPartidasPerdidas();
                    jugadorO.aumentarPartidasGanadas();
                    jugadorX.aumentarPartidasJugadas();
                    jugadorO.aumentarPartidasJugadas();
                    terminarPartida();
                    return;
                }
                x=jugadorX.getFila();
                y=jugadorX.getColumna();
                notificarObservadores();
                aumentarMovimientosJX();
                System.out.println("Tu cuadrante de juego: " + (x+1) + "," + (y+1));
                turnoJugadorO(x, y);//turno del jugador O
                return;
            }

            if(cuadrantes.getCuadrante(x, y).getEstado().equals("libre")){ //se lleva a cabo el turno
                cuadranteActualDeJuego=cuadrantes.getCuadrante(x, y);
                jugadorX.setSurrender(jugadorX.hacerSeleccion(cuadranteActualDeJuego));
                if(jugadorX.getSurrender()){
                    jugadorGanador = jugadorO;
                    jugadorX.aumentarPartidasPerdidas();
                    jugadorO.aumentarPartidasGanadas();
                    jugadorX.aumentarPartidasJugadas();
                    jugadorO.aumentarPartidasJugadas();
                    terminarPartida();
                    return;
                }
                cuadrantes.bloquearCuadrante(x, y);
                x=jugadorX.getFila();
                y=jugadorX.getColumna();
                notificarObservadores();
                aumentarMovimientosJX();
                System.out.println("Tu cuadrante de juego: " + (x+1) + "," + (y+1));
                if(verificarVictoria()==1){ //verifica si alguien ganó o no quedan cuadrantes
                    jugadorGanador = jugadorX;
                    jugadorO.aumentarPartidasPerdidas();
                    jugadorX.aumentarPartidasGanadas();
                    jugadorX.aumentarPartidasJugadas();
                    jugadorO.aumentarPartidasJugadas();
                    terminarPartida();
                    return;
                }else{
                    turnoJugadorO(x, y); //le toca al siguiente en el cuadrante x , y
                }
            }else{
                System.out.println("El siguiente cuadrante está bloqueado");
                cuadranteActualDeJuego=jugadorX.seleccionarCuadranteDeJuego(cuadrantes);
                x=jugadorX.getFila(); //para el cuadrante de juego actual
                y=jugadorX.getColumna();
                jugadorX.setSurrender(jugadorX.hacerSeleccion(cuadranteActualDeJuego));
                if(jugadorX.getSurrender()){
                    jugadorGanador = jugadorO;
                    jugadorX.aumentarPartidasPerdidas();
                    jugadorO.aumentarPartidasGanadas();
                    jugadorX.aumentarPartidasJugadas();
                    jugadorO.aumentarPartidasJugadas();
                    terminarPartida();
                    return;
                }
                cuadrantes.bloquearCuadrante(x, y);
                x=jugadorX.getFila(); //para el proximo cuadrante de juego
                y=jugadorX.getColumna();
                notificarObservadores();
                aumentarMovimientosJX();
                System.out.println("Tu cuadrante de juego: " + (x+1) + "," + (y+1));
                if(verificarVictoria()==1){ //verifica si alguien ganó o no quedan cuadrantes
                    jugadorGanador = jugadorX;
                    jugadorO.aumentarPartidasPerdidas();
                    jugadorX.aumentarPartidasGanadas();
                    jugadorX.aumentarPartidasJugadas();
                    jugadorO.aumentarPartidasJugadas();
                    terminarPartida();
                    return;
                }else{
                    turnoJugadorO(x, y); //le toca al siguiente en el cuadrante x , y
                }
            }
        }else{
            return;
        }
    }

    public void turnoJugadorO(int x, int y){ //turno del jugador O
        if(partidaEnCurso){
            System.out.println("TURNO O--------------------------------");
            if(!cuadrantes.hayCuadrantesLibres()){
                jugadorX.aumentarPartidasEmpatadas();
                jugadorO.aumentarPartidasEmpatadas();
                jugadorX.aumentarPartidasJugadas();
                jugadorO.aumentarPartidasJugadas();
                System.out.println("Empate");
                terminarPartida();
                return;
            }
            if(cuadrantes.getCuadrante(x, y).getEstado().equals("libre")){ //se lleva a cabo el turno
                cuadranteActualDeJuego=cuadrantes.getCuadrante(x, y);
                jugadorO.setSurrender(jugadorO.hacerSeleccion(cuadranteActualDeJuego));
                if(jugadorO.getSurrender()){
                    jugadorO.aumentarPartidasPerdidas();
                    jugadorX.aumentarPartidasGanadas();
                    jugadorX.aumentarPartidasJugadas();
                    jugadorO.aumentarPartidasJugadas();
                    jugadorGanador = jugadorX;
                    terminarPartida();
                    return;
                }
                cuadrantes.bloquearCuadrante(x, y);
                x=jugadorO.getFila();
                y=jugadorO.getColumna();
                notificarObservadores();
                aumentarMovimientosJO();
                System.out.println("Tu cuadrante de juego: " + (x+1) + "," + (y+1));
                if(verificarVictoria()==1){ //verifica si alguien ganó o no quedan cuadrantes
                    jugadorGanador = jugadorO;
                    jugadorX.aumentarPartidasPerdidas();
                    jugadorO.aumentarPartidasGanadas();
                    jugadorX.aumentarPartidasJugadas();
                    jugadorO.aumentarPartidasJugadas();
                    terminarPartida();
                    return;
                }else{
                    turnoJugadorX(x, y); //le toca al siguiente en el cuadrante x , y
                }
            }else{
                System.out.println("El siguiente cuadrante está bloqueado\n");
                cuadranteActualDeJuego=jugadorO.seleccionarCuadranteDeJuego(cuadrantes);
                x=jugadorO.getFila(); //para el cuadrante de juego actual
                y=jugadorO.getColumna();
                jugadorO.setSurrender(jugadorO.hacerSeleccion(cuadranteActualDeJuego));
                if(jugadorO.getSurrender()){
                    jugadorO.aumentarPartidasPerdidas();
                    jugadorX.aumentarPartidasGanadas();
                    jugadorX.aumentarPartidasJugadas();
                    jugadorO.aumentarPartidasJugadas();
                    jugadorGanador = jugadorX;
                    terminarPartida();
                    return;
                }
                cuadrantes.bloquearCuadrante(x, y);
                x=jugadorO.getFila();
                y=jugadorO.getColumna();
                notificarObservadores();
                aumentarMovimientosJO();
                System.out.println("Tu cuadrante de juego: " + (x+1) + "," + (y+1));
                if(verificarVictoria()==1){ //verifica si alguien ganó o no quedan cuadrantes
                    jugadorGanador = jugadorO;
                    jugadorX.aumentarPartidasPerdidas();
                    jugadorO.aumentarPartidasGanadas();
                    jugadorX.aumentarPartidasJugadas();
                    jugadorO.aumentarPartidasJugadas();
                    terminarPartida();
                    return;
                }else{
                    turnoJugadorX(x, y); //le toca al siguiente en el cuadrante x , y
                }
            }
    }else{
        return;
    }
}

    public void agregarObservador(Observador observador){
        if(this.observadores==null){
            this.observadores=new ArrayList<>();
        }
        this.observadores.add(observador);
    }

    public void notificarObservadores(){
        for(Observador observador : this.observadores){
            observador.actualizar(cuadrantes);
        }
    }
    @Override
    public String toString() {
        if(jugadorGanador==null){
            return "\n[Jugador X: " + jugadorX.getNombre() + "\nJugador O: " + jugadorO.getNombre() + "\nMovimientos Jugador X: " 
        + movimientosJX + "\nMovimientos Jugador O: " + movimientosJO + "\nJugador victorioso: Ninguno ]";
        }else{
            return "\n[Jugador X: " + jugadorX.getNombre() + "\nJugador O: " + jugadorO.getNombre() + "\nMovimientos Jugador X: " 
        + movimientosJX + "\nMovimientos Jugador O: " + movimientosJO + "\nJugador victorioso: " + jugadorGanador.getNombre() + " ]";
        }
    }
}