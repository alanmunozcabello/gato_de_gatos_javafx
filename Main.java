import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        ArrayList<Jugador> jugadores;
        ArrayList<Partida> partidas;
        Partida partida = Partida.getInstance();
        Cuadrantes cuadrantes=new Cuadrantes();
        Observador consola=new VistaEnConsola();
        partida.agregarObservador(consola);
        DeSerializar deSerializar = new DeSerializar();
        jugadores = deSerializar.deserializarJugadores();
        partidas = deSerializar.deserializarPartidas();
        while(true){
            System.out.println("----Menú de opciones----");
            System.out.println("[1] Jugar");
            System.out.println("[10] Salir");
            System.out.print("Opción: ");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("----Elegir Modo----");
                    System.out.println("{1} Jugador vs Bot fácil");
                    System.out.println("{2} Jugador vs Bot difícil");
                    System.out.println("{3} Jugador vs Jugador");
                    System.out.print("Opción: ");
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese su nombre:");
                            String nombreA = scanner.nextLine();
                            Jugador jugadorA = new Jugador(nombreA);
                            Jugador botFacil = new Jugador("BotFacil");
                            partida.Inicializar(jugadorA, botFacil, cuadrantes);
                            partida.agregarObservador(consola);
                            partida.comenzarPartida();
                            break;
                        case 2:
                            System.out.print("Ingrese su nombre:");
                            String nombreB = scanner.nextLine();
                            Jugador jugadorB = new Jugador(nombreB);
                            Jugador botDificil = new Jugador("BotDificil");
                            partida.Inicializar(jugadorB, botDificil, cuadrantes);
                            partida.agregarObservador(consola);
                            partida.comenzarPartida();
                            break;
                        case 3:
                            System.out.print("Jugador 1 ingrese su nombre:");
                            String nombreJ1 = scanner.nextLine();
                            Jugador jugador1 = new Jugador(nombreJ1);
                            System.out.print("Jugador 2 ingrese su nombre:");
                            String nombreJ2 = scanner.nextLine();
                            Jugador jugador2 = new Jugador(nombreJ2);
                            partida.Inicializar(jugador1, jugador2, cuadrantes);
                            partida.agregarObservador(consola);
                            partida.comenzarPartida();
                            break;
                        default:
                            System.out.println("Opción inválida, intentelo de nuevo");
                            break;
                    }
                    break;
                case 2:
                    break;
                case 10:
                    System.out.println("Saliendo...");
                    return;
                default:
                    break;
            }
        }
    }
}