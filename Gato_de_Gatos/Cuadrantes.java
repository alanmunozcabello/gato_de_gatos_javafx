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
        //if(cuadrantes[fila][columna].getGato()[0][0] == "X"){
        //}
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

    //metodo para obtener/mostrar coordenadas de los cuadrantes libres
    //si hay cuadrante libre se muestran las coordenadas
    //si el metodo anterior devuelve false se muestra un mensaje de que no hay cuadrantes libres
    // public void mostrarCuadrantes(){ ----------------------necesario?----------------------
    //     //kitian
    // }
    //I don't think so bro (respecto a si es necesario). Después de todo vamos a estar mostrando la matriz completa en cada turno
    //¿no? (si se te ocurre no imprimir esa aberrocidad de matriz a cada rato en ese caso este metodo sirve)

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