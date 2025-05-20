public class TestBot {
    public static void main(String[] args) {
        char[][] tablero = {
            {'X', 'O', ' '},
            {' ', 'O', ' '},
            {' ', ' ', ' '}
        };

        botDificil bot = new botDificil('X'); // el bot juega con 'X'
        int[] jugada = bot.obtenerMejorMovimiento(tablero);

        System.out.println("Bot juega en fila: " + jugada[0] + ", columna: " + jugada[1]);
    }
}