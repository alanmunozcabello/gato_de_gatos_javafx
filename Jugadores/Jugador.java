package Jugadores;

import Gato_de_Gatos.Cuadrantes;
import Gato_de_Gatos.Gato;

import java.util.Scanner;
import java.io.Serializable;

public class Jugador implements Serializable{
    private String nombre;
    private char simbolo;
    private int partidasJugadas;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int partidasEmpatadas;
    private int fila, columna;
    private boolean gana;
    private boolean surrender;

    //constructor con todos los atributos
    public Jugador(String nombre, char simbolo, int partidasJugadas, int partidasGanadas, int partidasPerdidas){
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.partidasJugadas = partidasJugadas;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
    }
    public Jugador(String nombre){
        this.nombre=nombre;
    }
    public Jugador(char simbolo){
        this.simbolo=simbolo;
    }
    //constructor sin simbolo
    public Jugador(String nombre, int partidasJugadas, int partidasGanadas, int partidasPerdidas){ 
        this.nombre = nombre;
        this.partidasJugadas = partidasJugadas;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
    }

    //Le pide al jugador las coordenadas del cuadrante que va a seleccionar
    public Gato seleccionarCuadranteDeJuego(Cuadrantes cuadrantes){ 
        Scanner scanner = new Scanner(System.in);
        int x=0;
        int y=0;
        boolean bandera=false; //siendo falsa es que la casilla no es valida
        while(!bandera){
            System.out.println("¡Elija el cuadrante!");
            System.out.println("Fila de cuadrante a jugar: ");
            x = scanner.nextInt()-1; //se espera que el usuario ingrese coordenadas del 1 en adelante
            scanner.nextLine();
            System.out.println("Columna de cuadrante a jugar: ");
            y = scanner.nextInt()-1;
            scanner.nextLine();
            
            if((x>=0 && x<=2 && y>=0 && y<=2) && cuadrantes.getCuadrante(x, y).getEstado().equals("libre")){ //si las coordenadas son validas
                bandera=true;
            }
            if(!bandera){
                System.out.println("Cuadrante bloqueado, intente de nuevo\n");
            }
        }
        Gato gato = cuadrantes.getCuadrante(x,y);
        this.fila = x;
        this.columna = y;
        return gato;
    }

    public boolean hacerSeleccion(Gato gato){ //recibe el gato para hacer la seleccion
        int fila=0;
        int columna=0;
        Scanner scanner = new Scanner(System.in);
        boolean bandera=false; //siendo falsa es que la casilla no es valida
        String lineaSig;
        System.out.print("Presione cualquier tecla para continuar (escriba surrender si desea rendirse): ");
        lineaSig = scanner.nextLine();
        if(lineaSig.equals("surrender")){
            return true;
        }
        while(!bandera){
            System.out.print("Ingrese la fila donde desea marcar: ");
            fila = scanner.nextInt() -1;
            scanner.nextLine();//consumir el salto de linea
            System.out.print("Ingrese la columna donde desea marcar: ");
            columna = scanner.nextInt() -1;
            scanner.nextLine();
            //verifica si la casilla es ocupada
            if(fila>=0 && fila<=2 && columna>=0 && columna<=2){
                bandera=gato.verificarCasillaOcupada(fila, columna);//si la casilla es valida se saldra del ciclo
            }
            if(!bandera){
                System.out.println("la casilla está ocupada o ingresó coordenadas fuera de limite, intente de nuevo\n");
            }
        }
        gato.marcarSeleccion(fila, columna, simbolo); //marca el simbolo en la casilla valida
        this.fila = fila;
        this.columna = columna;
        return false;
    }
    //para aumentar el numero de partidas jugadas -> se llama al inicio de cada partida
    public void aumentarPartidasJugadas(){
        this.partidasJugadas=this.partidasJugadas+1;
    }
    public void aumentarPartidasGanadas(){ //-> se llama al final de la partida si gana
        this.partidasGanadas=this.partidasGanadas+1;
    }
    public void aumentarPartidasPerdidas(){ //-> se llama al final de la partida si pierde
        this.partidasPerdidas=this.partidasPerdidas+1;
    }
    public void aumentarPartidasEmpatadas(){
        this.partidasEmpatadas=this.partidasEmpatadas+1;
    }
    //metodo para calcular el winrate
    public double calcularWinRate(){
        if(this.partidasJugadas==0){ //si no se usa este if no s epoddría calcular el winrate -> divición entre 0
            return 0;
        }
        double x = this.partidasGanadas;
        double y = this.partidasJugadas; 
        double winRate = x/y*100;//*100 para que sea un porcentaje
        return winRate;
    }

    //para cambiar el simbolo
    public void setSimbolo(char simbolo){
        this.simbolo = simbolo;
    }
    //metodos en caso de ser necesarios
    public char getSimbolo(){
        return this.simbolo;
    }
    public void setFila(int fila) {
        this.fila = fila;
    }
    public int getFila(){
        return this.fila;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
    public int getColumna(){
        return this.columna;
    }
    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }
    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }
    public int getPartidasGanadas(){
        return this.partidasGanadas;
    }
    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }
    public int getPartidasPerdidas(){
        return this.partidasPerdidas;
    }
    public void setPartidasEmpatadas(int partidasEmpatadas) {
        this.partidasEmpatadas = partidasEmpatadas;
    }
    public int getPartidasEmpatadas() {
        return partidasEmpatadas;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setGana(boolean gana) {
        this.gana = gana;
    }
    public boolean getGana(){
        return gana;
    }
    public void setSurrender(boolean surrender) {
        this.surrender = surrender;
    }
    public boolean getSurrender(){
        return surrender;
    }
    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jugador jugador = (Jugador) obj;
        return nombre != null && nombre.equalsIgnoreCase(jugador.nombre);
    }

@Override
public int hashCode() {
    return nombre != null ? nombre.toLowerCase().hashCode() : 0;
}
    
    @Override
    public String toString(){
        return "Jugador: "+ nombre + "\nPartidas Jugadas: " + partidasJugadas + "\nPartidas Ganadas: " + partidasGanadas + 
                "\nPartidas Perdidas: " + partidasPerdidas + "\nPartidas empatadas: " + partidasEmpatadas + "\nWinRate: " + calcularWinRate() + "%";
    }
}