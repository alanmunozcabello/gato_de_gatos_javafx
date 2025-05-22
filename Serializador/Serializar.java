import java.io.*;
import java.util.ArrayList;
public class Serializar{
    private ArrayList<Jugador> jugadores;//no dejaba si no estaba inicializado
    private ArrayList<Partida> partidas;//no dejaba si no estaba inicializado

    public Serializar(ArrayList<Jugador> jugadores, ArrayList<Partida> partidas){
        this.jugadores = jugadores;
        this.partidas = partidas;
    }
        
    public void serializarJugadores(){
        try{
            FileOutputStream fileOutput = new FileOutputStream("jugadores.txt");
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(this.jugadores);
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void serializarPartidas(){
        try{
            FileOutputStream fileOutput = new FileOutputStream("partidas.txt");
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(this.partidas);
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}