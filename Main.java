import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    //
    public static void main(String[] args) {
        ArrayList<Jugador> jugadores;
        ArrayList<Partida> partidas;
        boolean bandera;
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
                    Partida partida = Partida.getInstance();
                    Cuadrantes cuadrantes=new Cuadrantes();
                    Observador consola=new VistaEnConsola();
                    partida.agregarObservador(consola);
                    System.out.println("----Elegir Modo----");
                    System.out.println("{1} Jugador vs Bot fácil");
                    System.out.println("{2} Jugador vs Bot difícil");
                    System.out.println("{3} Jugador vs Jugador");
                    System.out.print("Opción: ");
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese su nombre: ");
                            String nombreA = scanner.nextLine();
                            Jugador jugadorA = null;
                            bandera = false;
                            for(Jugador jugador : jugadores){
                                if(nombreA.equals(jugador.getNombre())){
                                    jugadorA = jugador;
                                    bandera = true;
                                    break;
                                }
                            }
                            if(!bandera){
                                jugadorA = new Jugador(nombreA);
                            }
                            bandera = false;
                            botFacil botFacil = null;
                            for(Jugador bot : jugadores){
                                if(bot instanceof botFacil){
                                    botFacil = (botFacil) bot;
                                    bandera = true;
                                    break;
                                }
                            }
                            if(!bandera){
                                botFacil = new botFacil('X');
                            }
                            partida.Inicializar(jugadorA, botFacil, cuadrantes);
                            partida.setJugadores(jugadorA, botFacil);
                            partida.agregarObservador(consola);
                            partida.comenzarPartida();
                            System.out.println(partida.toString()); 
                            break;
                        case 2:
                            System.out.print("Ingrese su nombre: ");
                            String nombreB = scanner.nextLine();
                            Jugador jugadorB = null;
                            bandera = false;
                            for(Jugador jugador : jugadores){
                                if(nombreB.equals(jugador.getNombre())){
                                    jugadorB = jugador;
                                    bandera = true;
                                    break;
                                }
                            }
                            if(!bandera){
                                jugadorB = new Jugador(nombreB);
                            }
                            bandera = false;
                            botDificil botDificil = null;
                            for(Jugador bot : jugadores){
                                if(bot instanceof botDificil){
                                    botDificil = (botDificil) bot;
                                    bandera = true;
                                    break;
                                }
                            }
                            if(!bandera){
                                botDificil = new botDificil('X');
                            }
                            partida.Inicializar(jugadorB, botDificil, cuadrantes);
                            partida.setJugadores(jugadorB, botDificil);
                            partida.agregarObservador(consola);
                            partida.comenzarPartida();
                            System.out.println(partida.toString()); 
                            break;
                        case 3:
                            System.out.print("Jugador 1 ingrese su nombre: ");
                            String nombreJ1 = scanner.nextLine();
                            Jugador jugador1 = null;
                            bandera = false;
                            for(Jugador jugador : jugadores){
                                if(nombreJ1.equals(jugador.getNombre())){
                                    jugador1 = jugador;
                                    bandera = true;
                                    break;
                                }
                            }
                            if(!bandera){
                                jugador1 = new Jugador(nombreJ1);
                            }
                            System.out.print("Jugador 2 ingrese su nombre: ");
                            String nombreJ2 = scanner.nextLine();
                            Jugador jugador2 = null;
                            bandera = false;
                            for(Jugador jugador : jugadores){
                                if(nombreJ2.equals(jugador.getNombre())){
                                    jugador2 = jugador;
                                    bandera = true;
                                    break;
                                }
                            }
                            if(!bandera){
                                jugador2 = new Jugador(nombreJ2);
                            }
                            partida.Inicializar(jugador1, jugador2, cuadrantes);
                            partida.setJugadores(jugador1, jugador2);
                            partida.agregarObservador(consola);
                            partida.comenzarPartida();
                            System.out.println(partida.toString()); 
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
                    System.out.println("Opción inválida, intentelo de nuevo");
                    break;
            }
        }
    }
}