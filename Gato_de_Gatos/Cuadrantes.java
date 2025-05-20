public class Cuadrantes{
    Gato cuadrantes[][];

    public Cuadrantes(){
        this.cuadrantes = new Gato[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                this.cuadrantes[i][j] = new Gato();
            }
        }
    }

    public void bloquearCuadrante(int fila, int columna){ //necesario?
        // cuadrantes[fila][columna].bloquear();
        //logica de bloquear cuadrante -> gato.getEstado
    }

    public boolean hayCuadrantesLibres(){ //chequea si hay cuadrantes libres
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(this.cuadrantes[i][j].getEstado()=="libre"){ //hay a lo menos un cuadrante libre
                    return true;
                }
            }
        }
        return false; //no hay cuadrantes libres
    }

    //metodo para obtener/mostrar coordenadas de los cuadrantes libres
    //si hay cuadrante libre se muestran las coordenadas
    //si el metodo anterior devuelve false se muestra un mensaje de que no hay cuadrantes libres
    //kitian

    public void mostrarCuadrantes(){
        //kitian
    }

    //metodo para terminar la partida?

    public Gato getCuadrante(int fila, int columna){
        return this.cuadrantes[fila][columna];
    }
}