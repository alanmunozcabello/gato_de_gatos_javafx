import java.util.ArrayList;
import java.util.Scanner;
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
        //PREVIAMENTE A JUGAR------------------------------------------------------
        //ver quien es X y quien es O
        Dados dados=new Dados();
        int dadoJ1=0;
        int dadoJ2=0;
        System.out.println("Para determinar quien será la X y quien será O... ¡se lanzarán dados!\n");
        while(dadoJ1==dadoJ2){
            //lanza el jugador1
            System.out.println("¡Tira el jugador 1!\n");
            dadoJ1=dados.tirarDados();
            System.out.println("El jugador 1 consigue ¡"+dadoJ1+"!");
            //lanza el jugador2
            System.out.println("¡Tira el jugador 2!\n");
            dadoJ2=dados.tirarDados();
            System.out.println("El jugador 2 consigue ¡"+dadoJ2+"!");
            if(dadoJ1==dadoJ2){
                System.out.println("¡Empate... se hará otra ronda de dados!");
            }
        }
        if(dadoJ1>dadoJ2){
            System.out.println("¡El jugador 1 será X!");
            //se quedan tal cual
            jugadorX.setSimbolo('X');
            jugadorO.setSimbolo('O');
        }
        if(dadoJ1<dadoJ2){
            System.out.println("¡El jugador 2 será X!");
            Jugador tempJ1=jugadorX; //se le da al jugador 2 la X
            jugadorX=jugadorO;
            jugadorO=tempJ1;
            jugadorX.setSimbolo('X');
            jugadorO.setSimbolo('O');
        }
        //--------------------------------------------------------------------------
        //Dar comienzo a los turnos
        turnoJugadorX(-1, -1);
    }

    public void terminarPartida(){ // darle cierre a la partida la partida
        System.out.println("partida terminada\n");
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
    
    //Le pide al jugador las coordenadas del cuadrante que va a seleccionar
    public Gato seleccionarCuadranteDeJuego(){ 
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Elija el cuadrante!");
        System.out.println("Fila de cuadrante a jugar: ");
        int x = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Columna de cuadrante a jugar: ");
        int y = scanner.nextInt();
        scanner.nextLine();
        Gato gato = cuadrantes.getCuadrante(x,y);
        scanner.close();
        return gato;
    }

    //public boolean verificarVictoria(){ //verifica si uno de los jugadores ganó
        //logica para verificar victoria
    //}------------------------------------------------

    public void bloquearCuadrante(){ //bloquea el cuadrante de juego
        //logica para bloquear el cuadrante
    }

    public void turnoJugadorX(int x, int y){ //turno del jugador X
        System.out.println("TURNO X--------------------------------");
        if(!cuadrantes.hayCuadrantesLibres()){
            terminarPartida();
        }

        if(x==-1 || y==-1){ //unicamente al inicio de la partida
            Scanner scanner = new Scanner(System.in);
            boolean bandera=false; //siendo falsa es que la casilla no es valida
            while(!bandera){
                System.out.println("ingrese el cuadrante en el que quiere tirar [Formato: fila columna una a una]: \n");
                x = scanner.nextInt()-1;
                y = scanner.nextInt()-1;
                
                if((x>=0 && x<=2 && y>=0 && y<=2) && cuadrantes.getCuadrante(x, y).getEstado().equals("libre")){ //si las coordenadas son validas
                    bandera=true;
                }
                if(!bandera){
                    System.out.println("la casilla está ocupada, intente de nuevo\n");
                }
            }
            cuadranteActualDeJuego=cuadrantes.getCuadrante(x, y);

            jugadorX.hacerSeleccion(cuadranteActualDeJuego);
                x=jugadorX.getFila();
                y=jugadorX.getColumna();
                notificarObservadores();
                turnoJugadorO(x, y); //le toca al siguiente en el cuadrante x , y
        }

        if(cuadrantes.getCuadrante(x, y).getEstado().equals("libre")){ //se lleva a cabo el turno
            cuadranteActualDeJuego=cuadrantes.getCuadrante(x, y);
            jugadorX.hacerSeleccion(cuadranteActualDeJuego);
            x=jugadorX.getFila();
            y=jugadorX.getColumna();
            notificarObservadores();
            turnoJugadorO(x, y); //le toca al siguiente en el cuadrante x , y
        }
    }

    public void turnoJugadorO(int x, int y){ //turno del jugador O
        System.out.println("TURNO O--------------------------------");
        if(!cuadrantes.hayCuadrantesLibres()){
            terminarPartida();
        }

        if(cuadrantes.getCuadrante(x, y).getEstado().equals("libre")){ //se lleva a cabo el turno
            cuadranteActualDeJuego=cuadrantes.getCuadrante(x, y);
            jugadorO.hacerSeleccion(cuadranteActualDeJuego);
            x=jugadorO.getFila();
            y=jugadorO.getColumna();
            System.out.println("1");
            notificarObservadores();
            System.out.println("2");
            turnoJugadorX(x, y); //le toca al siguiente en el cuadrante x , y
        }
    }

    public void agregarObservador(Observador observador){
        if(this.observadores==null){
            this.observadores=new ArrayList<>();
        }
        this.observadores.add(observador);
        System.out.println("observador añadido");
    }

    public void notificarObservadores(){
        System.err.println("notificando");
        for(Observador observador : this.observadores){
            observador.actualizar(cuadrantes); System.err.println("notificados");
        }
        System.out.println("+-+-+-+-+-+-+");
    }
}