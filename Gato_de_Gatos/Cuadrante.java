public class Cuadrante{
    Gato cuadrantes[][];

    public Cuadrante(){
        cuadrantes = new Gato[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                cuadrantes[i][j] = new Gato();
            }
        }
    }

    public void bloquearCuadrante(int fila, int columna){
        // cuadrantes[fila][columna].bloquear();
        //logica de bloquear cuadrante -> cambiar estado pero de donde viene?-------
    }

    public boolean hayCuadrantesLibres(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(cuadrantes[i][j].getEstado()=="libre"){ //hay a lo menos un cuadrante libre
                    return true;
                }
            }
        }
        return false; //no hay cuadrantes libres
    }

    //metodo para obtener coordenadas de los cuadrantes libres
    //kitian

    public void mostrarCuadrantes(){
        //kitian
    }

    public Gato getCuadrante(int fila, int columna){
        return cuadrantes[i][j];
    }
}