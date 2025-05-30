public class TestBot {
    public static void main(String[] args) {
        // // Tablero de ejemplo: 'X' es el bot, 'O' es el rival
        // char[][] tablero = {
        //     {' ', ' ', ' '},
        //     {' ', ' ', ' '},
        //     {' ', ' ', ' '}
        // };

        // // Instanciar el bot con el símbolo 'X'
        // botDificil bot = new botDificil('X');

        // // Obtener la mejor jugada para el bot
        // int[] jugada = bot.obtenerMejorMovimiento(tablero, 'X');

        // // Mostrar la jugada sugerida por el bot
        // System.out.println("Bot juega en fila: " + jugada[0] + ", columna: " + jugada[1]);

        Jugador jugador1=new Jugador("Pepito");
        Jugador jugador2=new Jugador("Juanito");
        Cuadrantes cuadrantes=new Cuadrantes();
        Observador consola=new VistaEnConsola();

        Partida partida=Partida.getInstance();
        partida.Inicializar(jugador1, jugador2, cuadrantes);
        partida.agregarObservador(consola);
        partida.comenzarPartida();
    }
}