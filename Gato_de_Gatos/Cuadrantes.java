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

    public void bloquearCuadrante(int fila, int columna){ //necesario? [Cristian: Maybe maybe]
        //NO SE PUEDE COMPARAR STRING CON CHAR MANO, ¿QUE SE HACE?
        Gato gato = cuadrantes[fila][columna];
        if(String.valueOf(gato.getGato()[0][0]).equals("X")) {
        }
        //cuadrantes[fila][columna].setEstado(null);
        //logica de bloquear cuadrante -> gato.getEstado
    }

    public boolean hayCuadrantesLibres(){ //chequea si hay cuadrantes libres
        boolean bandera=false;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(this.cuadrantes[i][j].getEstado().equals("libre")){ //hay a lo menos un cuadrante libre
                    mostrarCoordsCuadrantesLibres(i, j);
                    bandera=true;
                }
            }
        }
        return bandera; //no hay cuadrantes libres
    }

    public void mostrarCoordsCuadrantesLibres(int x, int y){// mostrar al usuario las coordenadas de los cuadrantes libres
        System.out.println("Coordenadas de los cuadrantes libres: "+x+1+","+y+1);
    }

    public void terminarJuego(){ //termina el juego
        //logica de terminar juego
    }

    public Gato getCuadrante(int fila, int columna){
        return this.cuadrantes[fila][columna];
    }
}