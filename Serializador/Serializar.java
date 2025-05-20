import java.io.*;
import java.util.ArrayList;
public class Serializar{
    private ArrayList<Jugador> jugadores;//no dejaba si no estaba inicializado

    public Serializar(ArrayList<Jugador> jugadores){
        this.jugadores = jugadores;
    }
        
    public void guardarInfo(){
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
}