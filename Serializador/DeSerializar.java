import java.io.*;
import java.util.ArrayList;

public class DeSerializar{
    public DeSerializar(){
    }
    public ArrayList<Jugador> deserializar(){
        File archivo = new File("jugadores.txt");
        if(!archivo.exists() || archivo.length() == 0){
            // Si el archivo no existe o está vacío, retorna una lista vacía
            return new ArrayList<Jugador>();
        }
        try{
            FileInputStream fileInput = new FileInputStream(archivo);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            ArrayList<Jugador> jugadores = (ArrayList<Jugador>) objectInput.readObject();
            objectInput.close();
            fileInput.close();
            return jugadores;
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new ArrayList<Jugador>();
        }
    }
}