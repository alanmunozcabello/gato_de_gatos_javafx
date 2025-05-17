public class BotDificil extends Jugador {

    public BotDificil(String nombre, char simbolo) {
        super(nombre, simbolo);
    }

    @Override
    protected Movimiento elegirMovimiento(Tablero tablero, int cuadrante) {
        char[][] subTablero = tablero.getSubTablero(cuadrante);
        int[] mejor = mejorMovimiento(subTablero, simbolo);
        System.out.println(nombre + " juega en (" + mejor[0] + ", " + mejor[1] + ") del cuadrante " + cuadrante);
        return new Movimiento(cuadrante, mejor[0], mejor[1]);
    }

    private int[] mejorMovimiento(char[][] tablero, char miSimbolo) {
        int mejorValor = Integer.MIN_VALUE;
        int[] mejorJugada = {-1, -1};
        char rivalSimbolo = (miSimbolo == 'X') ? 'O' : 'X';

        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                if (tablero[fila][col] == ' ') {
                    tablero[fila][col] = miSimbolo;
                    int valor = minimax(tablero, false, miSimbolo, rivalSimbolo);
                    tablero[fila][col] = ' ';
                    if (valor > mejorValor) {
                        mejorValor = valor;
                        mejorJugada[0] = fila;
                        mejorJugada[1] = col;
                    }
                }
            }
        }

        return mejorJugada;
    }

    private int minimax(char[][] tablero, boolean esMax, char miSimbolo, char rivalSimbolo) {
        if (hayGanador(tablero, miSimbolo)) return 10;
        if (hayGanador(tablero, rivalSimbolo)) return -10;
        if (tableroLleno(tablero)) return 0;

        if (esMax) {
            int mejor = Integer.MIN_VALUE;
            for (int fila = 0; fila < 3; fila++) {
                for (int col = 0; col < 3; col++) {
                    if (tablero[fila][col] == ' ') {
                        tablero[fila][col] = miSimbolo;
                        int valor = minimax(tablero, false, miSimbolo, rivalSimbolo);
                        tablero[fila][col] = ' ';
                        mejor = Math.max(mejor, valor);
                    }
                }
            }
            return mejor;
        } else {
            int peor = Integer.MAX_VALUE;
            for (int fila = 0; fila < 3; fila++) {
                for (int col = 0; col < 3; col++) {
                    if (tablero[fila][col] == ' ') {
                        tablero[fila][col] = rivalSimbolo;
                        int valor = minimax(tablero, true, miSimbolo, rivalSimbolo);
                        tablero[fila][col] = ' ';
                        peor = Math.min(peor, valor);
                    }
                }
            }
            return peor;
        }
    }

    private boolean tableroLleno(char[][] tablero) {
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                if (tablero[fila][col] == ' ') return false;
            }
        }
        return true;
    }

    private boolean hayGanador(char[][] tablero, char simbolo) {
        // filas y columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == simbolo && tablero[i][1] == simbolo && tablero[i][2] == simbolo) return true;
            if (tablero[0][i] == simbolo && tablero[1][i] == simbolo && tablero[2][i] == simbolo) return true;
        }
        // diagonales
        if (tablero[0][0] == simbolo && tablero[1][1] == simbolo && tablero[2][2] == simbolo) return true;
        if (tablero[0][2] == simbolo && tablero[1][1] == simbolo && tablero[2][0] == simbolo) return true;

        return false;
    }
}
