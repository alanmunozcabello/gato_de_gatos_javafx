import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        ArrayList<Jugador> jugadores;
        ArrayList<Partida> partidas;
        DeSerializar deSerializar = new DeSerializar();
        jugadores = deSerializar.deserializarJugadores();
        partidas = deSerializar.deserializarPartidas();
        while(true){
            System.out.println("----Menú de opciones----");
            System.out.println("[1] Jugar");
            System.out.println("[2] Ver historial de un jugador");
            System.out.println("[3] Ver historial de partidas");
            System.out.println("[4] Salir");
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
                            boolean banderaA = false;
                            for(Jugador jugador : jugadores){
                                if(nombreA.equals(jugador.getNombre())){
                                    jugadorA = jugador;
                                    banderaA = true;
                                    break;
                                }
                            }
                            if(!banderaA){
                                jugadorA = new Jugador(nombreA);
                            }
                            boolean banderaFacil = false;
                            botFacil botFacil = null;
                            for(Jugador bot : jugadores){
                                if(bot instanceof botFacil){
                                    botFacil = (botFacil) bot;
                                    banderaFacil = true;
                                    break;
                                }
                            }
                            if(!banderaFacil){
                                botFacil = new botFacil('X');
                            }
                            partida.Inicializar(jugadorA, botFacil, cuadrantes);
                            partida.setJugadores(jugadorA, botFacil);
                            partida.agregarObservador(consola);
                            partida.comenzarPartida();
                            if(!banderaA){
                                jugadores.add(jugadorA);
                            }
                            if(!banderaFacil){
                                jugadores.add(botFacil);
                            }
                            partidas.add(partida.crearCopia());
                            break;
                        case 2:
                            System.out.print("Ingrese su nombre: ");
                            String nombreB = scanner.nextLine();
                            Jugador jugadorB = null;
                            boolean banderaB = false;
                            for(Jugador jugador : jugadores){
                                if(nombreB.equals(jugador.getNombre())){
                                    jugadorB = jugador;
                                    banderaB = true;
                                    break;
                                }
                            }
                            if(!banderaB){
                                jugadorB = new Jugador(nombreB);
                            }
                            boolean banderaDificil = false;
                            botDificil botDificil = null;
                            for(Jugador bot : jugadores){
                                if(bot instanceof botDificil){
                                    botDificil = (botDificil) bot;
                                    banderaDificil = true;
                                    break;
                                }
                            }
                            if(!banderaDificil){
                                botDificil = new botDificil('X');
                            }
                            partida.Inicializar(jugadorB, botDificil, cuadrantes);
                            partida.setJugadores(jugadorB, botDificil);
                            partida.agregarObservador(consola);
                            partida.comenzarPartida();
                            if(!banderaB){
                                jugadores.add(jugadorB);
                            }
                            if(!banderaDificil){
                                jugadores.add(botDificil);
                            }
                            partidas.add(partida.crearCopia());
                            break;
                        case 3:
                            System.out.print("Jugador 1 ingrese su nombre: ");
                            String nombreJ1 = scanner.nextLine();
                            Jugador jugador1 = null;
                            boolean banderaJ1 = false;
                            for(Jugador jugador : jugadores){
                                if(nombreJ1.equals(jugador.getNombre())){
                                    jugador1 = jugador;
                                    banderaJ1 = true;
                                    break;
                                }
                            }
                            if(!banderaJ1){
                                jugador1 = new Jugador(nombreJ1);
                            }
                            System.out.print("Jugador 2 ingrese su nombre: ");
                            String nombreJ2 = scanner.nextLine();
                            Jugador jugador2 = null;
                            boolean banderaJ2 = false;
                            for(Jugador jugador : jugadores){
                                if(nombreJ2.equals(jugador.getNombre())){
                                    jugador2 = jugador;
                                    banderaJ2 = true;
                                    break;
                                }
                            }
                            if(!banderaJ2){
                                jugador2 = new Jugador(nombreJ2);
                            }
                            partida.Inicializar(jugador1, jugador2, cuadrantes);
                            partida.setJugadores(jugador1, jugador2);
                            partida.agregarObservador(consola);
                            partida.comenzarPartida();
                            if(!banderaJ1){
                                jugadores.add(jugador1);
                            }
                            if(!banderaJ2){
                                jugadores.add(jugador2);
                            }
                            partidas.add(partida.crearCopia());
                            break;
                        default:
                            System.out.println("Opción inválida, intentelo de nuevo");
                            break;
                    }
                    break;
                case 2:
                    boolean bandera = false;
                    String nombreJugadorBuscado;
                    System.out.print("Ingrese el nombre del jugador a buscar: ");
                    nombreJugadorBuscado = scanner.nextLine();
                    for(Jugador jugador_j : jugadores){
                        if(nombreJugadorBuscado.equals(jugador_j.getNombre())){
                            System.out.println(jugador_j.toString());
                            bandera = true;
                        }
                    }
                    if(!bandera){
                        System.out.println("Jugador no encontrado");
                    }
                    break;
                case 3:
                    for(Partida partida_p : partidas){
                        System.out.println(partida_p.toString());
                    }
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    Serializar serializar = new Serializar();
                    serializar.serializarJugadores(jugadores);
                    serializar.serializarPartidas(partidas);
                    return;
                default:
                    System.out.println("Opción inválida, intentelo de nuevo");
                    break;
            }
        }
    }
}