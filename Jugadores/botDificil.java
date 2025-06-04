public class botDificil extends Jugador{
    public botDificil(char simbolo){
        super(simbolo);
        this.setNombre("botDificil");
    }

    @Override
    public Gato seleccionarCuadranteDeJuego(Cuadrantes cuadrantes) {
        int x=0;
        int y=0;
        boolean bandera=false;
        while(!bandera){
            x = (int) (Math.random() * 3);
            y = (int) (Math.random() * 3);
            if(cuadrantes.getCuadrante(x, y).getEstado().equals("libre")){ //si las coordenadas son validas
                bandera=true;
            }
        }
        Gato gato = cuadrantes.getCuadrante(x,y);
        this.setFila(x);
        this.setColumna(y);
        return gato;
    }

    @Override
    public boolean hacerSeleccion(Gato gato) { //se recibe el cuadarnte de juego actual para hacer la seleccion
        // Obtener el estado actual del tablero
        try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e);
            }
        char[][] tablero = gato.getGato();

        int[] mejorJugada = obtenerMejorMovimiento(tablero, this.getSimbolo());

        int fila = mejorJugada[0];
        int columna = mejorJugada[1];

        gato.marcarSeleccion(fila, columna, this.getSimbolo());
        this.setFila(fila);
        this.setColumna(columna);
        return false;
    }

    // Minimax adaptado para el cuadrante actual de juego
    private int[] obtenerMejorMovimiento(char[][] tablero, char miSimbolo) {
        char oponenteSimbolo=' ';
        if (miSimbolo == 'X') {
            oponenteSimbolo = 'O';
        } else {
            oponenteSimbolo = 'X';
        }
        int mejorValor = Integer.MIN_VALUE;
        int[] mejorJugada = {-1, -1};

        for(int fila = 0; fila < 3; fila++){
            for(int columna = 0; columna < 3; columna++){
                if(tablero[fila][columna] == ' '){
                    tablero[fila][columna] = miSimbolo;
                    int valor = minimax(tablero, false, miSimbolo, oponenteSimbolo);
                    tablero[fila][columna] = ' ';
                    if (valor > mejorValor){
                        mejorValor = valor;
                        mejorJugada[0] = fila;
                        mejorJugada[1] = columna;
                    }
                }
            }
        }
        return mejorJugada;
    }

    private int minimax(char[][] tablero, boolean esMaximizando, char miSimbolo, char oponenteSimbolo) {
        if(hayGanador(tablero, miSimbolo)){ //si el bot gana +10
            return 10;
        }
        if(hayGanador(tablero, oponenteSimbolo)){//si el bot pierde -10
            return -10;
        }
        if(tableroLleno(tablero)){ //si no 0
            return 0;
        }

        if(esMaximizando){ //esMaximizando significa que es el turno del bot
            int mejor = Integer.MIN_VALUE;
            for (int fila = 0; fila < 3; fila++){
                for (int col = 0; col < 3; col++){
                    if (tablero[fila][col] == ' '){
                        tablero[fila][col] = miSimbolo;
                        int valor = minimax(tablero, false, miSimbolo, oponenteSimbolo);
                        tablero[fila][col] = ' ';
                        mejor = Math.max(mejor, valor);//Se saca el int mayor entre ambos
                    }
                }
            }
            return mejor;
        }else{ //turno del oponente
            int peor = Integer.MAX_VALUE;
            for(int fila = 0; fila < 3; fila++){
                for(int col = 0; col < 3; col++){
                    if(tablero[fila][col] == ' '){
                        tablero[fila][col] = oponenteSimbolo;
                        int valor = minimax(tablero, true, miSimbolo, oponenteSimbolo);
                        tablero[fila][col] = ' ';
                        peor = Math.min(peor, valor);
                    }
                }
            }
            return peor;
        }
    }

    private boolean tableroLleno(char[][] tablero){
        for(int fila = 0; fila < 3; fila++){
            for(int col = 0; col < 3; col++){
                if(tablero[fila][col] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hayGanador(char[][] tablero, char simbolo) {
        for(int i = 0; i < 3; i++) {
            if(tablero[i][0] == simbolo && tablero[i][1] == simbolo && tablero[i][2] == simbolo){
                return true;
            }
            if(tablero[0][i] == simbolo && tablero[1][i] == simbolo && tablero[2][i] == simbolo){
                return true;
            }
        }
        if(tablero[0][0] == simbolo && tablero[1][1] == simbolo && tablero[2][2] == simbolo){
            return true;
        }
        if(tablero[0][2] == simbolo && tablero[1][1] == simbolo && tablero[2][0] == simbolo){
            return true;
        }
        return false;
    }
}