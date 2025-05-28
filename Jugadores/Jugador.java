import java.util.Scanner;

public class Jugador{
    String nombre;
    char simbolo;
    int partidasJugadas;
    int partidasGanadas;
    int partidasPerdidas; 
    int fila, columna;
    boolean gana;
    boolean surrender;

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

    public void hacerSeleccion(Gato gato){ //recibe el gato para hacer la seleccion
        int fila=0;
        int columna=0;
        Scanner scanner = new Scanner(System.in);
        boolean bandera=false; //siendo falsa es que la casilla no es valida
        while(!bandera){
            System.out.println("ingrese la casilla en la que quiere tirar: [Formato: fila columna]");
            fila = scanner.nextInt()-1;
            columna = scanner.nextInt()-1;
            //verifica si la casilla es ocupada
            bandera=gato.verificarCasillaOcupada(fila, columna);//si la casilla es valida se saldra del ciclo
        }
        gato.marcarSimbolo(fila, columna, simbolo); //marca el simbolo en la casilla valida
        this.fila = fila;
        this.columna = columna;
    }
    //para aumentar el numero de partidas jugadas -> se llama al inicio de cada partida
    public void aumentarPartidasJugadas(){
        this.partidasJugadas=this.partidasJugadas+1;
    }
    public void aumentarPartidasGanadas(){
        this.partidasGanadas=this.partidasGanadas+1;
    }
    public void aumentarPartidasPerdidas(){
        this.partidasPerdidas=this.partidasPerdidas+1;
    }
    //metodo para calcular el winrate
    public double calcularWinRate(){
        if(this.partidasJugadas==0){ //si no se usa este if no s epoddría calcular el winrate -> divición entre 0
            return 0;
        }
        return (double)this.partidasGanadas/this.partidasJugadas*100; //*100 para que sea un porcentaje
    }

    //para cambiar el simbolo
    public void setSimbolo(char simbolo){
        this.simbolo = simbolo;
    }
    //metodos en caso de ser necesarios
    public char getSimbolo(){
        return this.simbolo;
    }
    public int getFila(){
        return this.fila;
    }
    public int getColumna(){
        return this.columna;
    }
    public int getpartidasJugadas(){
        return this.partidasJugadas;
    }
    public int getPartidasGanadas(){
        return this.partidasGanadas;
    }
    public int getPartidasPerdidas(){
        return this.partidasPerdidas;
    }
    @Override
    public String toString(){
        return "Jugador: "+ this.nombre + "\nSimbolo: " + this.simbolo + "\nPartidas Jugadas: " + this.partidasJugadas + "\nPartidas Ganadas: " + this.partidasGanadas + 
                "\nPartidas Perdidas: " + this.partidasPerdidas + "WinRate: " + calcularWinRate() + "%";
    }
}